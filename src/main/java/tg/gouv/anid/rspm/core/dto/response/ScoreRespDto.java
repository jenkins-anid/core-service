package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class ScoreRespDto {
    private Long id;
    private HouseholdRespDto household;
    private int baseScore;
    private int finalScore;
    private LocalDate dateScore;
    private String comments;
    private String status;
}
