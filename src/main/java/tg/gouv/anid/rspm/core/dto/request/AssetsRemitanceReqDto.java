package tg.gouv.anid.rspm.core.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tg.gouv.anid.rspm.core.entity.Household;
import tg.gouv.anid.rspm.core.entity.RemitanceSenderInfo;

import java.util.Set;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class AssetsRemitanceReqDto {
    @JsonIgnore
    private Long id;
    private Long householdId;
    private boolean activeRemitance;
    private Set<RemitanceSenderInfoReqDto> senderInfos;
    private Float annualFoodRemitanceTotalAmount;
    private Float annualOtherThingsRemitanceTotalAmount;
}
