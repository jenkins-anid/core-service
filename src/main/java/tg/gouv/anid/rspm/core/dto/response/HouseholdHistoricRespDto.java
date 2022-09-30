package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class HouseholdHistoricRespDto {
    private Long id;
    private HouseholdRespDto household;
    private ResidentRespDto resident;
    private String uin;
    private String historicStatus;
    private String hin;
    private LocalDate historicDate;
}
