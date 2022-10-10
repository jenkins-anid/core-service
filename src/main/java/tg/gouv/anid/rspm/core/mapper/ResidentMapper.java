package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tg.gouv.anid.common.entities.util.Converter;
import tg.gouv.anid.rspm.core.dto.request.ResidentReqDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentRespDto;
import tg.gouv.anid.rspm.core.entity.Resident;

@Mapper(componentModel = "spring")
public interface ResidentMapper {

    @Mapping( target = "nationalityDoc", expression = "java(convertToByteArray(dto.getNationalityDoc()))")
    @Mapping(source = "householdId", target = "household")
    Resident toResident(ResidentReqDto dto);

    ResidentRespDto toResidentRespDto(Resident resident);

    default byte[] convertToByteArray(String base64) {
        return Converter.convertToByteImage(base64);
    }
}
