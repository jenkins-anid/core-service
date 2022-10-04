package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

/**
 * @author Francis AHONSU
 *
 * @since 0.0.1
 */
@Data
public class HouseholdHeadReqDto {
    private ResidentReqDto head;
    private HouseholdReqDto household;
}
