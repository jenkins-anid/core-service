package tg.gouv.anid.rspm.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;

/**
 * Classe entité pour la gestion des consommations des ménages
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RS_HH_CONSOMMATIONS")
@Audited
public class HouseholdConsommation extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONS_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HH_ID")
    @JsonIgnore
    private Household household;
    @Column(name = "CONS_FOOD_ANN")
    private Double annualFoodConso;
    @Column(name = "CONS_ALL_ANN")
    private Double annualAllConso;
    @Column(name = "CONS_ADULT_FOOD_DEP_ANN")
    private Double adultAnnualFoodExpense;
    @Column(name = "CONS_ADULT_ALL_DEP_ANN")
    private Double adultAnnualAllExpense;

}
