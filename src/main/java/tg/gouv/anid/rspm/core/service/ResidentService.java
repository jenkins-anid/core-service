package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.exception.ApplicationException;
import tg.gouv.anid.common.entities.exception.ResourceNotFoundException;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.request.HouseholdHeadReqDto;
import tg.gouv.anid.rspm.core.dto.request.ResidentDocReqDto;
import tg.gouv.anid.rspm.core.dto.request.ResidentReqDto;
import tg.gouv.anid.rspm.core.dto.response.HouseholdRespDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentDocRespDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentRespDto;
import tg.gouv.anid.rspm.core.entity.Household;
import tg.gouv.anid.rspm.core.entity.HouseholdHistoric;
import tg.gouv.anid.rspm.core.entity.Resident;
import tg.gouv.anid.rspm.core.entity.ResidentDoc;
import tg.gouv.anid.rspm.core.enums.HistoricStatus;
import tg.gouv.anid.rspm.core.enums.ResidentStatus;
import tg.gouv.anid.rspm.core.mapper.HouseholdMapper;
import tg.gouv.anid.rspm.core.mapper.ResidentDocMapper;
import tg.gouv.anid.rspm.core.mapper.ResidentMapper;
import tg.gouv.anid.rspm.core.repository.ResidentDocRepository;
import tg.gouv.anid.rspm.core.repository.ResidentRepository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * service pour la gestion des résidents
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Service
@Transactional(readOnly = true)
public class ResidentService extends GenericService<Resident, Long> {

    private final ResidentMapper residentMapper;
    private final ResidentDocMapper residentDocMapper;
    private final ResidentDocRepository residentDocRepository;
    private final HouseholdMapper householdMapper;
    private final HouseholdService householdService;
    private final HouseholdHistoricService householdHistoricService;

    public ResidentService(ResidentRepository repository,
                              ResidentMapper residentMapper,
                              ResidentDocRepository residentDocRepository,
                              ResidentDocMapper residentDocMapper,
                              HouseholdMapper householdMapper,
                              HouseholdService householdService,
                           HouseholdHistoricService householdHistoricService) {
        super(repository);
        this.residentMapper = residentMapper;
        this.residentDocRepository = residentDocRepository;
        this.residentDocMapper = residentDocMapper;
        this.householdMapper = householdMapper;
        this.householdService = householdService;
        this.householdHistoricService = householdHistoricService;
    }

    @Transactional
    public HouseholdRespDto createHouseholdHeadResident(@NotNull HouseholdHeadReqDto dto) {
        //save the resident
        Resident head = residentMapper.toResident(dto.getHead());
        checkExistingResidentAndUpdateHistoric(head.getUin());
        //todo: check UIN existing in resident control table
        create(head);
        //save the household
        Household household = householdMapper.toHousehold(dto.getHousehold());
        household.setHeadId(head.getId());
        household.setHeadUin(head.getUin());
        HouseholdRespDto householdRespDto = householdService.createHousehold(household);
        //update the resident with household
        head.setHousehold(new Household(householdRespDto.getId()));
        repository.saveAndFlush(head);
        //return response
        ResidentRespDto headDto = residentMapper.toResidentRespDto(head);
        householdRespDto.setHead(headDto);
        addNewHistoric(head, householdRespDto);
        return householdRespDto;
    }

    public void addNewHistoric(Resident resident, HouseholdRespDto householdRespDto) {
        householdService.saveHistoric(new HouseholdHistoric(resident, householdRespDto, HistoricStatus.CURRENT));
    }

    @Transactional
    public ResidentRespDto updateHousehold(@NotNull ResidentReqDto dto) {
        Resident resident = residentMapper.toResident(dto);
        Resident old = getById(resident.getId());
        if (!resident.validateUnchangeableInfo(old)) {
            throw new ApplicationException("unchangeable.data.validation.failed");
        }
        return residentMapper.toResidentRespDto(update(resident));
    }

    public Resident getById(Long id) {
        return getOne(id)
                .filter(Resident::isDeleted)
                .orElseThrow(() -> new ResourceNotFoundException("resident.not.found"));
    }


    public Resident getByUin(String uin) {
        return getRepository()
                .findByUin(uin)
                .filter(resident -> !resident.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("resident.notFound.byUIN"));
    }

    public List<Resident> getResidentByUIN(@NotNull Set<String> residentsUIN) {
        List<Resident> members = new ArrayList<>();
        residentsUIN.forEach(uin -> members.add(getByUin(uin)));
        return members;
    }

    public void checkExistingResidentAndUpdateHistoric(String uin) {
        if (getRepository().existsByUinAndStatusNot(uin, ResidentStatus.DEPARTURE)) {
            throw new ApplicationException("resident.already.exist");
        }
        householdHistoricService.updateOldHistoric(uin);
    }

    @Transactional
    public ResidentRespDto addResident(ResidentReqDto dto) {
        Resident resident = residentMapper.toResident(dto);
        checkExistingResidentAndUpdateHistoric(resident.getUin());
        //Vérifier les contraintes réferentiel avant l'enrégistrement
        //Récupérer les objets des autres services après enrégistrement
        ResidentRespDto residentRespDto = residentMapper.toResidentRespDto(this.create(resident));
        addNewHistoric(resident, householdService.getOneHousehold(resident.getHousehold().getId()));
        return residentRespDto;
    }

    @Transactional
    public ResidentDocRespDto addResidentDoc(ResidentDocReqDto dto) {
        ResidentDoc residentDoc = residentDocMapper.toResidentDoc(dto);
        return residentDocMapper
                .toResidentDocRespDto(
                        residentDocRepository.save(residentDoc));
    }

    @Override
    public ResidentRepository getRepository() {
        return (ResidentRepository) repository;
    }
}
