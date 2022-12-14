package tg.gouv.anid.rspm.core.dto.request;

import lombok.Data;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class AssetsUtilReqDto {
    private Long householdId;
    private int roomsCount;
    private Long roofTypeId;
    private Long wallTypeId;
    private String generalWaterSource;
    private String drinkWaterSource;
    private Double distanceCleanWater;
    private int accessTimeToWaterSource;
    private String toiletType;
    private int toiletCount;
    private boolean isShareToilet;
    private boolean hasElectricity;
    private String electricitySource;
    private boolean isShareKitchen;
    private boolean isShareBath;
}
