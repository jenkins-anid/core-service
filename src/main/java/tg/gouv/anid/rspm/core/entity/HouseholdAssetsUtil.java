package tg.gouv.anid.rspm.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;

/**
 * Classe entité pour la gestion des actifs utilitaire d'un ménage
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RS_HH_ASSETS_UTIL")
@Audited
public class HouseholdAssetsUtil extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASU_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HH_ID")
    private Household household;
    @Column(name = "ASU_NB_ROOMS")
    private int roomsCount;
    @Column(name = "ASU_ROOF_TYPE")
    private Long roofTypeId;
    @Column(name = "ASU_WALL_TYPE")
    private Long wallTypeId;
    @Column(name = "ASU_SRC_WATER_GEN")
    private String generalWaterSource;
    @Column(name = "ASU_SRC_DRINK_WATER")
    private String drinkWaterSource;
    @Column(name = "ASU_DIST_CLEANWATER")
    private Double distanceCleanWater;
    @Column(name = "ASU_TTAKEN_WATER")
    private int accessTimeToWaterSource;
    @Column(name = "ASU_TOOILET_TYPE")
    private String toiletType;
    @Column(name = "ASU_TOILET_COUNT")
    private int toiletCount;
    @Column(name = "ASU_TOILET_SHARE_YN")
    private boolean isShareToilet;
    @Column(name = "ASU_ELECTRICITY_YN")
    private boolean hasElectricity;
    @Column(name = "ASU_SRC_ELECTRICITY")
    private String electricitySource;
    @Column(name = "ASU_KITCHEN_SHARE_YN")
    private boolean isShareKitchen;
    @Column(name = "ASU_BATH_SHARE_YN")
    private boolean isShareBath;

}
