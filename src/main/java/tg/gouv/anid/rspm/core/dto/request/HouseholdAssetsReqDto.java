package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;
import tg.gouv.anid.common.entities.exception.ApplicationException;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class HouseholdAssetsReqDto {
    private AssetsUtilReqDto assetsUtil;
    private AssetsDurableReqDto assetsDurable;
    private AssetsRemitanceReqDto assetsRemitance;
    private ConsommationReqDto consommation;
    @NotNull(message = "household.id.mandatory")
    private Long householdId;

    public void validateRequestInfo() {
        if ((Objects.nonNull(assetsUtil) && !householdId.equals(assetsUtil.getHouseholdId())) ||
                (Objects.nonNull(assetsDurable) && !householdId.equals(assetsDurable.getHouseholdId())) ||
                (Objects.nonNull(consommation) && !householdId.equals(assetsRemitance.getHouseholdId())) ||
                (Objects.nonNull(consommation) && !householdId.equals(consommation.getHouseholdId()))
        ) {
            throw new ApplicationException("household.assets.data.validation.failed");
        }
    }
}
