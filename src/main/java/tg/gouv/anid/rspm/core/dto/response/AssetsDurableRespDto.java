package tg.gouv.anid.rspm.core.dto.response;

import lombok.Data;
import tg.gouv.anid.rspm.core.entity.Household;


/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
public class AssetsDurableRespDto {
    private Long id;
    private HouseholdRespDto household;
    private boolean haveRadio;
    private boolean haveTV;
    private boolean haveVideo;
    private boolean haveHomePhone;
    private boolean haveMobilePhone;
    private boolean haveFridge;
    private boolean haveFan;
    private boolean haveAirConditioner;
    private boolean haveComputer;
    private boolean haveTablet;
    private boolean haveStove;
    private boolean haveBicycle;
    private boolean haveBoat;
    private boolean haveCanoe;
    private boolean haveCar;
    private boolean haveInternet;
}
