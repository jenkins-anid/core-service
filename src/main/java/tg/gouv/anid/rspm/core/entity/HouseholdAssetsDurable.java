package tg.gouv.anid.rspm.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;

/**
 * Classe entité pour la gestion des actifs durable des ménages
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RS_HH_ASSETS_DURABLE")
@Audited
public class HouseholdAssetsDurable extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ASD_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HH_ID")
    @JsonIgnore
    private Household household;
    @Column(name = "ASD_RADIO_YN")
    private boolean haveRadio;
    @Column(name = "ASD_TELEVISION_YN")
    private boolean haveTV;
    @Column(name = "ASD_VIDEO_YN")
    private boolean haveVideo;
    @Column(name = "ASD_LANDPHONE_YN")
    private boolean haveHomePhone;
    @Column(name = "ASD_CELLPHONE_YN")
    private boolean haveMobilePhone;
    @Column(name = "ASD_FRIDGE_YN")
    private boolean haveFridge;
    @Column(name = "ASD_FAN_YN")
    private boolean haveFan;
    @Column(name = "ASD_AIRCONDITIONER_YN")
    private boolean haveAirConditioner;
    @Column(name = "ASD_COMPUTER_YN")
    private boolean haveComputer;
    @Column(name = "ASD_TABLET_YN")
    private boolean haveTablet;
    @Column(name = "ASD_STOVE_YN")
    private boolean haveStove;
    @Column(name = "ASD_BCYCLE_YN")
    private boolean haveBicycle;
    @Column(name = "ASD_BOAT_YN")
    private boolean haveBoat;
    @Column(name = "ASD_CANOE_YN")
    private boolean haveCanoe;
    @Column(name = "ASD_CAR_YN")
    private boolean haveCar;
    @Column(name = "ASD_INTERNET_YN")
    private boolean haveInternet;
}
