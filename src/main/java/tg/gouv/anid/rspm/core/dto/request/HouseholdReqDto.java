package tg.gouv.anid.rspm.core.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseholdReqDto {
    private String hin;
    private String name;
    private Long headId;
    private int interviewMonth;
    private int interviewYear;
    private int size;
    private double weight;
    private int adultCount;
    private int residentCount;
    private String status;
    private boolean isHomeOwner;
    private String homeOccupationStatus;
    private Double healthCenterDistance;
    private int timeTakenToHealthCenter;
    private ScoreReqDto score;
}
