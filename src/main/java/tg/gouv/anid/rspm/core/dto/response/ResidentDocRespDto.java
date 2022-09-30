package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;


/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class ResidentDocRespDto {
    private Long id;
    private ResidentRespDto resident;
    private String type;
    private String title;
    private String description;
    private byte[] object;
}
