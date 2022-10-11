package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;

import java.util.Set;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class AssetsRemitanceRespDto {
    private Long id;
    private boolean activeRemitance;
    private Set<RemitanceSenderInfoRespDto> senderInfos;
    private Float annualFoodRemitanceTotalAmount;
    private Float annualOtherThingsRemitanceTotalAmount;
}
