package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import tg.gouv.anid.rspm.core.dto.request.AssetsDurableReqDto;
import tg.gouv.anid.rspm.core.dto.response.AssetsDurableRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsDurable;

@Mapper(componentModel = "spring")
public interface AssetsDurableMapper {

    HouseholdAssetsDurable toHouseholdAssetsDurable(AssetsDurableReqDto dto);

    AssetsDurableRespDto toAssetsDurableRespDto(HouseholdAssetsDurable assetsDurable);

}
