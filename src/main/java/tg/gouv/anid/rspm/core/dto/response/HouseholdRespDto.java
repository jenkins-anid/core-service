package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class HouseholdRespDto {
    private String hin;
    private String name;
    private ResidentRespDto head;
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
    private ScoreRespDto score;
}
