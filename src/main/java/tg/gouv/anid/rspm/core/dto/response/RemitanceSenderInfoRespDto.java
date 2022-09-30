package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;


/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class RemitanceSenderInfoRespDto {
    private Long id;
    private HouseholdRespDto household;
    private String sex;
    private String relationship;
    private Double amount;
    private String devise;
}
