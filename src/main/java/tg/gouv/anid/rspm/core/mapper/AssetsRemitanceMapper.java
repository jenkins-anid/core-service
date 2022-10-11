package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tg.gouv.anid.rspm.core.dto.request.AssetsRemitanceReqDto;
import tg.gouv.anid.rspm.core.dto.response.AssetsRemitanceRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsRemitance;

@Mapper(componentModel = "spring")
public interface AssetsRemitanceMapper {

    @Mapping(source = "householdId", target = "household.id")
    HouseholdAssetsRemitance toHouseholdAssetsRemitance(AssetsRemitanceReqDto dto);

    AssetsRemitanceRespDto toAssetRemitanceRespDto(HouseholdAssetsRemitance assetsRemitance);
}
