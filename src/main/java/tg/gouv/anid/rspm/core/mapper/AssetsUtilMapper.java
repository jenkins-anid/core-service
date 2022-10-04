package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import tg.gouv.anid.rspm.core.dto.request.AssetsUtilReqDto;
import tg.gouv.anid.rspm.core.dto.response.AssetsUtilRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsUtil;

@Mapper(componentModel = "spring")
public interface AssetsUtilMapper {

    HouseholdAssetsUtil toHouseholdAssetsUtil(AssetsUtilReqDto dto);

    AssetsUtilRespDto toAssetsUtilRespDto(HouseholdAssetsUtil assetsUtil);
}
