package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tg.gouv.anid.rspm.core.dto.request.AssetsDurableReqDto;
import tg.gouv.anid.rspm.core.dto.response.AssetsDurableRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsDurable;

@Mapper(componentModel = "spring")
public interface AssetsDurableMapper {

    @Mapping(source = "householdId", target = "household.id")
    HouseholdAssetsDurable toHouseholdAssetsDurable(AssetsDurableReqDto dto);

    AssetsDurableRespDto toAssetsDurableRespDto(HouseholdAssetsDurable assetsDurable);

}
