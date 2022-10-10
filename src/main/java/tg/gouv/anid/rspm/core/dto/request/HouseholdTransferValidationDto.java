package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class HouseholdTransferValidationDto {
    @NotNull
    private Set<String> residentsUIN;
    /**
     * OneTimePassword
     */
    @NotBlank
    private String otp;
    @NotBlank
    private String householdHeadUIN;
}
