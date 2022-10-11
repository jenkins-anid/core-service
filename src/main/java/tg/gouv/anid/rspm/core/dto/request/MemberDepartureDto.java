package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberDepartureDto {
    @NotBlank(message = "household.hin.mandatory")
    private String hin;
    @NotBlank(message = "household.memberUIN.mandatory")
    private String memberUIN;
}
