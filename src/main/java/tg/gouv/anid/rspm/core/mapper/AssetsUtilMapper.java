package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tg.gouv.anid.rspm.core.dto.request.AssetsUtilReqDto;
import tg.gouv.anid.rspm.core.dto.response.AssetsUtilRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsUtil;

@Mapper(componentModel = "spring")
public interface AssetsUtilMapper {

    @Mapping(source = "householdId", target = "household.id")
    HouseholdAssetsUtil toHouseholdAssetsUtil(AssetsUtilReqDto dto);

    AssetsUtilRespDto toAssetsUtilRespDto(HouseholdAssetsUtil assetsUtil);
}
