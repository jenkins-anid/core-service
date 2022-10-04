package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import tg.gouv.anid.rspm.core.dto.request.HouseholdReqDto;
import tg.gouv.anid.rspm.core.dto.response.HouseholdRespDto;
import tg.gouv.anid.rspm.core.entity.Household;

@Mapper(componentModel = "spring")
public interface HouseholdMapper {

    Household toHousehold(HouseholdReqDto dto);

    HouseholdRespDto toHouseholdRespDto(Household household);
}
