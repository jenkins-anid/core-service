package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.request.AssetsDurableReqDto;
import tg.gouv.anid.rspm.core.dto.response.AssetsDurableRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsDurable;
import tg.gouv.anid.rspm.core.mapper.AssetsDurableMapper;
import tg.gouv.anid.rspm.core.repository.HHAssetsDurableRepository;

import java.util.List;
import java.util.Objects;


/**
 *
 * @author Francis AHONSU
 *
 * @since 0.0.1
 *
 */
@Service
@Transactional(readOnly = true)
public class HHAssetsDurableService extends GenericService<HouseholdAssetsDurable, Long> {

    private final AssetsDurableMapper assetsDurableMapper;
    public HHAssetsDurableService(HHAssetsDurableRepository repository,
                                     AssetsDurableMapper assetsDurableMapper) {
        super(repository);
        this.assetsDurableMapper = assetsDurableMapper;
    }

    @Transactional
    public AssetsDurableRespDto addAssetsDurable(AssetsDurableReqDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        HouseholdAssetsDurable assetsDurable = assetsDurableMapper.toHouseholdAssetsDurable(dto);
        return assetsDurableMapper.toAssetsDurableRespDto(create(assetsDurable));
    }

    public List<HouseholdAssetsDurable> getByHousehold(Long householdId) {
        return getRepository().findAllByHousehold_idAndStateNot(householdId, State.DELETED);
    }

    public List<AssetsDurableRespDto> getByHouseholdId(Long householdId) {
        return getByHousehold(householdId).stream().map(assetsDurableMapper::toAssetsDurableRespDto).toList();
    }

    public AssetsDurableRespDto mapToRespDto(HouseholdAssetsDurable assetsDurable) {
        return assetsDurableMapper.toAssetsDurableRespDto(assetsDurable);
    }

    @Override
    public HHAssetsDurableRepository getRepository() {
        return (HHAssetsDurableRepository) repository;
    }
}
