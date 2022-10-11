package tg.gouv.anid.rspm.core.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseholdReqDto {
    @JsonIgnore
    private Long id;
    @NotBlank(message = "household.name.mandatory")
    private String name;
    private Long headId;
    private Integer interviewMonth;
    private Integer interviewYear;
    private Integer size;
    private Double weight;
    private Integer adultCount;
    private Integer residentCount;
    private boolean isHomeOwner;
    private String homeOccupationStatus;
    private Double healthCenterDistance;
    private Integer timeTakenToHealthCenter;
}
