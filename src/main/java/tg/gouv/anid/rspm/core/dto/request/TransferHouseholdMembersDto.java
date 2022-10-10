package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

import java.util.Set;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class TransferHouseholdMembersDto {
    private String oldHin;
    private String newHin;
    private Set<String> residentsUIN;
}
