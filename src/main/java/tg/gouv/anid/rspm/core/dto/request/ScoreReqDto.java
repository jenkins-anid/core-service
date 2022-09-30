package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class ScoreReqDto {
    private Long householdId;
    private int baseScore;
    private int finalScore;
    private LocalDate dateScore;
    private String comments;
}
