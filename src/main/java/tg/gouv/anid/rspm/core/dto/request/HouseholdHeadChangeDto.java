package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class HouseholdHeadChangeDto {
    @NotBlank(message = "household.hin.mandatory")
    private String hin;
    @NotBlank(message = "household.oldHead.mandatory")
    private String oldHouseholdHead;
    @NotBlank(message = "household.newHead.mandatory")
    private String newHouseholdHead;
}
