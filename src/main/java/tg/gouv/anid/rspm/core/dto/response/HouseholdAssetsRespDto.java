package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;
import tg.gouv.anid.rspm.core.entity.Household;


/**
 * @author Francis AHONSU
 *
 * @since 0.0.1
 */
@Data
public class HouseholdAssetsRespDto {
    private Long id;
    private String hin;
    private String name;
    private AssetsUtilRespDto util;
    private AssetsDurableRespDto durable;
    private AssetsRemitanceRespDto remittance;
    private ConsommationRespDto consommation;

    public HouseholdAssetsRespDto(Household household) {
        this.id = household.getId();
        this.hin = household.getHin();
        this.name = household.getName();
    }

}
