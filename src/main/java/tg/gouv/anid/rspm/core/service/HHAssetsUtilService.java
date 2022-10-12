package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.request.AssetsUtilReqDto;
import tg.gouv.anid.rspm.core.dto.response.AssetsUtilRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsUtil;
import tg.gouv.anid.rspm.core.mapper.AssetsUtilMapper;
import tg.gouv.anid.rspm.core.repository.HHAssetsUtilRepository;

import java.util.List;
import java.util.Objects;

/**
 * @author Francis AHONSU
 */
@Service
@Transactional(readOnly = true)
public class HHAssetsUtilService extends GenericService<HouseholdAssetsUtil, Long> {

    private final AssetsUtilMapper assetsUtilMapper;

    public HHAssetsUtilService(HHAssetsUtilRepository repository,
                                  AssetsUtilMapper assetsUtilMapper) {
        super(repository);
        this.assetsUtilMapper = assetsUtilMapper;
    }

    @Transactional
    public AssetsUtilRespDto addAssetsUtil(AssetsUtilReqDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        HouseholdAssetsUtil assetsUtil = assetsUtilMapper.toHouseholdAssetsUtil(dto);
        return assetsUtilMapper.toAssetsUtilRespDto(create(assetsUtil));
    }

    public List<HouseholdAssetsUtil> getByHousehold(Long householdId) {
        return getRepository().findAllByHousehold_idAndStateNot(householdId, State.DELETED);
    }

    public List<AssetsUtilRespDto> getByHouseholdId(Long householdId) {
        return getByHousehold(householdId)
                .stream()
                .map(assetsUtilMapper::toAssetsUtilRespDto)
                .toList();
    }

    public AssetsUtilRespDto mapToRespDto(HouseholdAssetsUtil householdAssetsUtil) {
        return assetsUtilMapper.toAssetsUtilRespDto(householdAssetsUtil);
    }

    @Override
    public HHAssetsUtilRepository getRepository() {
        return (HHAssetsUtilRepository) repository;
    }
}
