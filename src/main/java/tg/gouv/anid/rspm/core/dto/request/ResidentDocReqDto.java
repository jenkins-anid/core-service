package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class ResidentDocReqDto {
    private Long residentId;
    private String type;
    private String title;
    private String description;
    private String object;
}
