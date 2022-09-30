package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;


/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class ConsommationRespDto {
    private Long id;
    private HouseholdRespDto household;
    private Double annualFoodConso;
    private Double annualAllConso;
    private Double adultAnnualFoodExpense;
    private Double adultAnnualAllExpense;
}
