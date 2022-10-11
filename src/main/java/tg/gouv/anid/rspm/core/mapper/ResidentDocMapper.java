package tg.gouv.anid.rspm.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tg.gouv.anid.common.entities.util.Converter;
import tg.gouv.anid.rspm.core.dto.request.ResidentDocReqDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentDocRespDto;
import tg.gouv.anid.rspm.core.entity.ResidentDoc;


/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Mapper(componentModel = "spring")
public interface ResidentDocMapper {

    ResidentDocRespDto toResidentDocRespDto(ResidentDoc residentDoc);

    @Mapping(target = "object", expression = "java(convertToByteArray(dto.getObject()))")
    @Mapping(source = "residentId", target = "resident.id")
    ResidentDoc toResidentDoc(ResidentDocReqDto dto);

    default byte[] convertToByteArray(String base64) {
        return Converter.convertToByteImage(base64);
    }
}
