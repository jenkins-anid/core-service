package tg.gouv.anid.rspm.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
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
    @Column(name = "HH_HIN", nullable = false, unique = true, updatable = false)
    private String hin;
    @NotBlank(message = "household.name.mandatory")
    @Column(name = "HH_NAME")
    private String name;
    @NotNull(message = "household.headId.mandatory")
    @Column(name = "HH_HEAD_ID")
    private Long headId;
    @Transient
    private String headUin;
    @Column(name = "HH_INT_MONTH")
    private Integer interviewMonth;
    @Column(name = "HH_INT_YR")
    private Integer interviewYear;
    @NotNull(message = "household.size.mandatory")
    @Column(name = "HH_SIZE")
    private Integer size;
    @Column(name = "HH_WEIGHT")
    private Double weight;
    @Column(name = "HH_NB_ADULT")
    private Integer adultCount;
    @Column(name = "HH_NB_RES")
    private Integer residentCount;
    @NotBlank(message = "household.status.mandatory")
    @Column(name = "HH_STATUS")
    private String status;
    @Column(name = "HH_HOME_OWNER_YN")
    private Boolean isHomeOwner;
    @Column(name = "HH_H_OCCUP_STATUS")
    private String homeOccupationStatus;
    @Column(name = "HH_DIST_HEALTH")
    private Double healthCenterDistance;
    @Column(name = "HH_TTAKEN_HEALTH")
    private Integer timeTakenToHealthCenter;
    @Column(name = "HH_DESIGNATED_ID")
    private String designatedUIN;
    @OneToOne(mappedBy = "household")
    private Score score;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HouseholdAssetsUtil> assetsUtils;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HouseholdAssetsDurable> assetsDurables;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HouseholdAssetsRemitance> assetsRemitances;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HouseholdConsommation> consommations;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Resident> members;


    public Household(Long id) {
        this.id = id;
    }

    public Optional<HouseholdAssetsUtil> getAssetsUtil() {
        return this.assetsUtils.stream()
                .filter(householdAssetsUtil -> !householdAssetsUtil.isDeleted())
                .findFirst();
    }

    public Optional<HouseholdAssetsDurable> getAssetsDurable() {
        return this.assetsDurables
                .stream()
                .filter(assetsDurable -> !assetsDurable.isDeleted())
                .findFirst();
    }

    public Optional<HouseholdAssetsRemitance> getAssetsRemittance() {
        return this.assetsRemitances
                .stream()
                .filter(assetsRemitance -> !assetsRemitance.isDeleted())
                .findFirst();
    }

    public Optional<HouseholdConsommation> getConsommation() {
        return this.consommations
                .stream()
                .filter(consommation -> !consommation.isDeleted())
                .findFirst();
    }


    public boolean validateUnchanheableField(Household old) {
        if (hin.equals(old.hin)
                && headId.equals(old.headId)
                && status.equals(old.status)
                && designatedUIN.equals(old.designatedUIN)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

}
