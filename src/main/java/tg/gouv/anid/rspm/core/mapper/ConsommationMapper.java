package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tg.gouv.anid.rspm.core.dto.request.ConsommationReqDto;
import tg.gouv.anid.rspm.core.dto.response.ConsommationRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdConsommation;

@Mapper(componentModel = "spring")
public interface ConsommationMapper {

    @Mapping(source = "householdId", target = "household.id")
    HouseholdConsommation toHouseholdConsommation(ConsommationReqDto dto) ;

    ConsommationRespDto toConsommationRespDto(HouseholdConsommation consommation);
}
