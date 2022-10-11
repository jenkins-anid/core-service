package tg.gouv.anid.rspm.core.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.enums.HouseholdRelationship;
import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.common.entities.exception.ApplicationException;
import tg.gouv.anid.common.entities.exception.ResourceNotFoundException;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.common.entities.util.SecureString;
import tg.gouv.anid.rspm.core.dto.request.*;
import tg.gouv.anid.rspm.core.dto.response.HouseholdAssetsRespDto;
import tg.gouv.anid.rspm.core.dto.response.HouseholdRespDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentRespDto;
import tg.gouv.anid.rspm.core.entity.*;
import tg.gouv.anid.rspm.core.enums.HistoricStatus;
import tg.gouv.anid.rspm.core.enums.ResidentStatus;
import tg.gouv.anid.rspm.core.mapper.HouseholdMapper;
import tg.gouv.anid.rspm.core.repository.HouseholdRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Service pour la gestion des ménages
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Service
@Transactional(readOnly = true)
public class HouseholdService extends GenericService<Household, Long> {

    private final HouseholdMapper householdMapper;
    private final HHAssetsRemitanceService assetsRemitanceService;
    private final HHAssetsDurableService assetsDurableService;
    private final HHAssetsUtilService assetsUtilService;
    private final HHConsommationService consommationService;
    private final HouseholdHistoricService householdHistoricService;
    private ResidentService residentService;
    private static final String HOUSEHOLD404 = "household.not.found";

    public HouseholdService(HouseholdRepository repository,
                               HouseholdMapper householdMapper,
                               HHAssetsRemitanceService assetsRemitanceService,
                               HHAssetsDurableService assetsDurableService,
                               HHAssetsUtilService assetsUtilService,
                               HHConsommationService consommationService,
                               HouseholdHistoricService householdHistoricService) {
        super(repository);
        this.householdMapper = householdMapper;
        this.assetsDurableService = assetsDurableService;
        this.assetsRemitanceService = assetsRemitanceService;
        this.assetsUtilService = assetsUtilService;
        this.consommationService = consommationService;
        this.householdHistoricService = householdHistoricService;
    }

    @Override
    public Household create(Household household) {
        household.setHin(generateHIN());
        household.setStatus("CREATE");
        return super.create(household);
    }

    @Transactional
    public HouseholdRespDto createHousehold(HouseholdReqDto dto) {
        Household household = householdMapper.toHousehold(dto);
        return createHousehold(household);
    }

    @Transactional
    public HouseholdRespDto createHousehold(Household household) {
        if (Objects.isNull(household.getDesignatedUIN())) {
            household.setDesignatedUIN(household.getHeadUin());
        }
        return householdMapper.toHouseholdRespDto(create(household));
    }

    @Transactional
    public HouseholdRespDto updateHousehold(@NotNull HouseholdReqDto dto) {
        Household household = householdMapper.toHousehold(dto);
        Household old = getById(household.getId());
        if (household.validateUnchanheableField(old)) {
            throw new ApplicationException("household.unchangeable.violated");
        }
        return householdMapper.toHouseholdRespDto(household);
    }

    @Transactional
    public void saveHistoric(HouseholdHistoric householdHistoric) {
        householdHistoricService.create(householdHistoric);
    }


    @Transactional
    public HouseholdRespDto addHouseholdAssets(@NotNull HouseholdAssetsReqDto dto) {
        dto.validateRequestInfo();
        this.assetsUtilService.addAssetsUtil(dto.getAssetsUtil());
        this.assetsRemitanceService.addAssetsRemitance(dto.getAssetsRemitance());
        this.assetsDurableService.addAssetsDurable(dto.getAssetsDurable());
        this.consommationService.addConsommation(dto.getConsommation());
        Household household = repository.findById(dto.getHouseholdId()).orElse(new Household());
        return householdMapper.toHouseholdRespDto(household);
    }

    @Transactional
    public boolean updateDesignatedBeneficiary(String uin, Long householdId) {
        Household household = getById(householdId);
        designatedBeneficiaryControl(household, uin);
        household.setDesignatedUIN(uin);
        repository.saveAndFlush(household);
        return Boolean.TRUE;
    }

    @Transactional
    public boolean changeHouseholdHead(@NotNull HouseholdHeadChangeDto dto) {
        var household = getByHin(dto.getHin());
        var oldHead = residentService.getByUin(dto.getOldHouseholdHeadUIN());
        var newHead = residentService.getByUin(dto.getNewHouseholdHeadUIN());
        householdMemberControl(household, oldHead);
        householdMemberControl(household, newHead);
        householdHeadControl(household.getHeadId(), oldHead.getId());
        household.setHeadId(newHead.getId());
        repository.saveAndFlush(household);
        newHead.setHead(Boolean.TRUE);
        newHead.setRelationHousehold(HouseholdRelationship.HEAD.name());
        oldHead.setHead(Boolean.FALSE);
        oldHead.setRelationHousehold(null);
        residentService.update(newHead);
        residentService.update(oldHead);
        //todo: notificiation
        //send notification to old and newest household head
        //todo: créer le compte utilisateur pour le membre et lui envoyer les credentials à changer à la première connexion
        return Boolean.TRUE;
    }

    //declareDepartureOfMembers

    @Transactional
    public boolean transferHouseholdMember(@NotNull TransferHouseholdMembersDto dto) {
        var oldHousehold = getByHin(dto.getOldHin());
        var newHousehold = getByHin(dto.getNewHin());
        List<Resident> members = residentService.getResidentByUIN(dto.getResidentsUIN());
        members.forEach(resident -> {
            householdHeadTransferControl(resident);
            householdMemberControl(oldHousehold, resident);
            memberDepartureDeclaredControl(resident);
            //todo: si c'est un opérateur qui fait l'action, mettre le status du résident en attente
            //resident.setStatus(ResidentStatus.PENDING);
            resident.setHousehold(newHousehold);
            resident.setStatus(ResidentStatus.IN_HOUSEHOLD);
            residentService.update(resident);
            householdHistoricService.updateOldHistoric(resident.getUin());
            saveHistoric(new HouseholdHistoric(resident, newHousehold, HistoricStatus.CURRENT));
        });
        residentService.updateHouseholdCountSize(oldHousehold.getId());
        return Boolean.TRUE;
    }

    public void householdHeadTransferControl(Resident resident) {
        if (resident.isHead()) {
            throw new ApplicationException("household.head.transfer.unauthorize");
        }
    }


    @Transactional
    public boolean validateTransfer(@NotNull HouseholdTransferValidationDto dto) {
        if (validateOTP(dto.getOtp(), dto.getHouseholdHeadUIN())) {
            List<Resident> residents = residentService.getResidentByUIN(dto.getResidentsUIN());
            residents.forEach(resident -> {
                pendingMemberControl(resident);
                resident.setStatus(ResidentStatus.IN_HOUSEHOLD);
                residentService.getRepository().saveAndFlush(resident);
                //todo: send notification to the household head and the new members
            });
            return Boolean.TRUE;
        }
        throw new ApplicationException("household.transfer.otp.validation.failed");
    }

    public Set<ResidentRespDto> getAllHouseholdMembers(Long householdId) {
        return residentService.getAllByHouseholdFormat(householdId);
    }

    @Transactional
    public void updateHouseholdSize(Long householdId, Integer size, Integer adultCount) {
        Household household = getById(householdId);
        household.setSize(size);
        household.setAdultCount(adultCount);
        household.setResidentCount(size);
        repository.saveAndFlush(household);
    }

    //todo: generateOTP method

    public boolean validateOTP(@NotNull String otp, @NotNull String uin) {
        //todo: validateOTP method impl
        return Boolean.TRUE;
    }

    @Transactional
    public boolean deleteLogicaly(@NotNull Long id) {
        Household household = getById(id);
        Set<Resident> residents = residentService.getAllByHousehold(id);
        residents.forEach(resident -> residentService.deleteLogicaly(resident.getId()));
        HouseholdAssetsRespDto dto = getAllHouseholdAssets(id);
        consommationService.deleteSoft(dto.getConsommation().getId());
        assetsDurableService.deleteSoft(dto.getDurable().getId());
        assetsUtilService.deleteSoft(dto.getUtil().getId());
        assetsRemitanceService.deleteSoft(dto.getRemittance().getId());
        return deleteSoft(household.getId());
    }



    public void householdMemberControl(@NotNull Household household, @NotNull Resident resident) {
        if (!household.getId().equals(resident.getHouseholdId())) {
            throw new ApplicationException("resident.notBelong.toHousehold");
        }
    }

    public void householdHeadControl(@NotNull Long householdHeadId, @NotNull Long controlId) {
        if (!householdHeadId.equals(controlId)) {
            throw new ApplicationException("household.oldHead.not.conform");
        }
    }

    public void designatedBeneficiaryControl(Household household, String designatedBeneficiaryUIN) {
        Resident resident = residentService.getByUin(designatedBeneficiaryUIN);
        if (!resident.getHousehold().getId().equals(household.getId())) {
            throw new ApplicationException("household.designated.beneficiary.notBelongs.toHousehold");
        }
    }

    public void memberDepartureDeclaredControl(@NotNull Resident resident) {
        if (!ResidentStatus.DEPARTURE.equals(resident.getStatus())) {
            throw new ApplicationException("resident.transfer.not.allowed");
        }
    }

    @Transactional
    public boolean declareMemberDeparture(MemberDepartureDto dto) {
        Household household = getByHin(dto.getHin());
        Resident resident = residentService.getByUin(dto.getMemberUIN());
        householdMemberControl(household, resident);
        resident.setStatus(ResidentStatus.DEPARTURE);
        resident.setHousehold(null);
        residentService.update(resident);
        residentService.updateHouseholdCountSize(household.getId());
        householdHistoricService.updateOldHistoric(resident.getUin());
        return Boolean.TRUE;
    }

    public void pendingMemberControl(@NotNull Resident resident) {
        if (!ResidentStatus.PENDING.equals(resident.getStatus())) {
            throw new ApplicationException("resident.incorrect.status");
        }
    }

    public Household getById(Long id) {
        return getOne(id)
                .filter(household -> !household.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException(HOUSEHOLD404));
    }

    public Household getByHin(@NotBlank String hin) {
        return getRepository()
                .findByHin(new SecureString(hin).getValue())
                .filter(household -> !household.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException(HOUSEHOLD404));
    }

    public HouseholdAssetsRespDto getAllHouseholdAssets(@NotNull Long householdId) {
        Household household = getById(householdId);
        HouseholdAssetsRespDto output = new HouseholdAssetsRespDto(household);
        household.getConsommation()
                .ifPresent(householdConsommation ->
                        output.setConsommation(consommationService.mappeToRespDto(householdConsommation)));
        household.getAssetsUtil()
                .ifPresent(householdAssetsUtil ->
                        output.setUtil(assetsUtilService.mapToRespDto(householdAssetsUtil)));
        household.getAssetsRemittance()
                .ifPresent(assetsRemittance ->
                        output.setRemittance(assetsRemitanceService.mapToRespDto(assetsRemittance)));
        household.getAssetsDurable()
                .ifPresent(assetsDurable ->
                        output.setDurable(assetsDurableService.mapToRespDto(assetsDurable)));
        return output;
    }

    public Page<HouseholdRespDto> getAllHousehold(Pageable pageable) {
        Page<Household> households = getAll(pageable);
        return new PageImpl<>(households
                .getContent()
                .stream()
                .map(
                        householdMapper::toHouseholdRespDto)
                .toList(), pageable, households.getTotalElements());
    }

    private String generateHIN() {
        return RandomStringUtils.randomNumeric(10);
    }

    public List<HouseholdRespDto> getAllHousehold() {
        return getAll().stream().map(householdMapper::toHouseholdRespDto).toList();
    }

    public HouseholdRespDto getOneHousehold(Long id) {
        Household household = getOne(id).orElseThrow(() -> new ResourceNotFoundException("household.not.found"));
        return householdMapper.toHouseholdRespDto(household);
    }

    @Override
    public HouseholdRepository getRepository() {
        return (HouseholdRepository) repository;
    }

    @Autowired
    public void setResidentService(ResidentService residentService) {
        this.residentService = residentService;
    }
}
