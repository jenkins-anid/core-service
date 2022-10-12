package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.request.AssetsRemitanceReqDto;
import tg.gouv.anid.rspm.core.dto.response.AssetsRemitanceRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsRemitance;
import tg.gouv.anid.rspm.core.mapper.AssetsRemitanceMapper;
import tg.gouv.anid.rspm.core.repository.HHAssetsRemitanceRepository;

import java.util.List;
import java.util.Objects;


/**
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 *
 */
@Service
@Transactional
public class HHAssetsRemitanceService extends GenericService<HouseholdAssetsRemitance, Long> {
    private final AssetsRemitanceMapper assetsRemitanceMapper;

    public HHAssetsRemitanceService(HHAssetsRemitanceRepository repository,
                                       AssetsRemitanceMapper assetsRemitanceMapper) {
        super(repository);
        this.assetsRemitanceMapper = assetsRemitanceMapper;
    }

    @Transactional
    public AssetsRemitanceRespDto addAssetsRemitance(AssetsRemitanceReqDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        HouseholdAssetsRemitance assetsRemitance = assetsRemitanceMapper.toHouseholdAssetsRemitance(dto);
        return assetsRemitanceMapper.toAssetRemitanceRespDto(create(assetsRemitance));
    }

    public List<HouseholdAssetsRemitance> getByHousehold(Long householdId) {
        return getRepository().findAllByHousehold_idAndStateNot(householdId, State.DELETED);
    }

    public List<AssetsRemitanceRespDto> getByHouseholdId(Long householdId) {
        return getByHousehold(householdId)
                .stream()
                .map(assetsRemitanceMapper::toAssetRemitanceRespDto)
                .toList();
    }

    public AssetsRemitanceRespDto mapToRespDto(HouseholdAssetsRemitance assetsRemitance) {
        return assetsRemitanceMapper.toAssetRemitanceRespDto(assetsRemitance);
    }

    @Override
    public HHAssetsRemitanceRepository getRepository() {
        return (HHAssetsRemitanceRepository) repository;
    }
}
