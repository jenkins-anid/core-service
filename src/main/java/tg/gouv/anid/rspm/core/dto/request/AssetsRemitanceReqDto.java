package tg.gouv.anid.rspm.core.dto.request;

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
    private Long id;
    private Household household;
    private boolean activeRemitance;
    private Set<RemitanceSenderInfoReqDto> senderInfos;
    private Float annualFoodRemitanceTotalAmount;
    private Float annualOtherThingsRemitanceTotalAmount;
}
