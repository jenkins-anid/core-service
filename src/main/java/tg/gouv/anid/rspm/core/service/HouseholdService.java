package tg.gouv.anid.rspm.core.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.exception.ResourceNotFoundException;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.request.ConsommationReqDto;
import tg.gouv.anid.rspm.core.dto.request.HouseholdAssetsReqDto;
import tg.gouv.anid.rspm.core.dto.request.HouseholdReqDto;
import tg.gouv.anid.rspm.core.dto.response.HouseholdAssetsRespDto;
import tg.gouv.anid.rspm.core.dto.response.HouseholdRespDto;
import tg.gouv.anid.rspm.core.entity.*;
import tg.gouv.anid.rspm.core.mapper.HouseholdMapper;
import tg.gouv.anid.rspm.core.repository.HouseholdRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

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
    protected HouseholdService(HouseholdRepository repository,
                               HouseholdMapper householdMapper,
                               HHAssetsRemitanceService assetsRemitanceService,
                               HHAssetsDurableService assetsDurableService,
                               HHAssetsUtilService assetsUtilService,
                               HHConsommationService consommationService) {
        super(repository);
        this.householdMapper = householdMapper;
        this.assetsDurableService = assetsDurableService;
        this.assetsRemitanceService = assetsRemitanceService;
        this.assetsUtilService = assetsUtilService;
        this.consommationService = consommationService;
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
        return householdMapper.toHouseholdRespDto(create(household));
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

    public Household getById(Long id) {
        return getOne(id).orElseThrow(() -> new ResourceNotFoundException("household.not.found"));
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
                .ifPresent(assetsRemitance ->
                        output.setRemittance(assetsRemitanceService.mapToRespDto(assetsRemitance)));
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
}
