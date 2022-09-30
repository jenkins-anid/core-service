package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tg.gouv.anid.rspm.core.dto.request.ResidentReqDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentRespDto;
import tg.gouv.anid.rspm.core.entity.Resident;

@Mapper(componentModel = "spring")
public interface ResidentMapper {

    @Mapping( target = "nationalityDoc", expression = "java(Constant.convertToByteImage(dto.getNationalityDoc()))")
    Resident toResident(ResidentReqDto dto);

    ResidentRespDto toResidentRespDto(Resident resident);
}
