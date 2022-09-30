package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;


/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class ConsommationReqDto {
    private Long id;
    private Long householdId;
    private Double annualFoodConso;
    private Double annualAllConso;
    private Double adultAnnualFoodExpense;
    private Double adultAnnualAllExpense;
}
