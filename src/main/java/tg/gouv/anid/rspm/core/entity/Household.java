package tg.gouv.anid.rspm.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Classe entité pour la gestion des ménages
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Getter
@Setter
@Entity
@Table(name = "RS_H_HOLD")
@NoArgsConstructor
@Audited
public class Household extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HH_ID")
    private Long id;
    @NotBlank(message = "household.hin.mandatory")
    @Column(name = "HH_HIN", nullable = false)
    private String hin;
    @NotBlank(message = "household.name.mandatory")
    @Column(name = "HH_NAME")
    private String name;
    @NotNull(message = "household.headId.mandatory")
    @Column(name = "HH_HEAD_ID")
    private Long headId;
    @NotNull(message = "household.interviewMonth.mandatory")
    @Column(name = "HH_INT_MONTH")
    private int interviewMonth;
    @NotNull(message = "household.interviewYear.mandatory")
    @Column(name = "HH_INT_YR")
    private int interviewYear;
    @NotNull(message = "household.size.mandatory")
    @Column(name = "HH_SIZE")
    private int size;
    @NotNull(message = "household.weight.mandatory")
    @Column(name = "HH_WEIGHT")
    private double weight;
    @NotNull(message = "household.adultCount.mandatory")
    @Column(name = "HH_NB_ADULT")
    private int adultCount;
    @NotNull(message = "household.residentCount.mandatory")
    @Column(name = "HH_NB_RES")
    private int residentCount;
    @NotBlank(message = "household.status.mandatory")
    @Column(name = "HH_STATUS")
    private String status;
    @NotNull(message = "household.isHomeOwner.mandatory")
    @Column(name = "HH_HOME_OWNER_YN")
    private boolean isHomeOwner;
    @Column(name = "HH_H_OCCUP_STATUS")
    private String homeOccupationStatus;
    @Column(name = "HH_DIST_HEALTH")
    private Double healthCenterDistance;
    @Column(name = "HH_TTAKEN_HEALTH")
    private int timeTakenToHealthCenter;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private Set<HouseholdAssetsUtil> assetsUtils;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private Set<HouseholdAssetsDurable> assetsDurables;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private Set<HouseholdAssetsRemitance> assetsRemitances;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private Set<HouseholdConsommation> consommations;
    @OneToOne(mappedBy = "household")
    private Score score;

}
