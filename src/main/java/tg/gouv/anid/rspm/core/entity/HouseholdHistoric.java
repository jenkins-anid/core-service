package tg.gouv.anid.rspm.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;
import tg.gouv.anid.rspm.core.dto.response.HouseholdRespDto;
import tg.gouv.anid.rspm.core.enums.HistoricStatus;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe entité pour la gestion de l'historique des ménages habité par les résidents
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RS_H_HOLD_HIST")
@Audited
public class HouseholdHistoric extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HIST_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HH_ID")
    private Household household;
    @ManyToOne
    @JoinColumn(name = "RES_ID")
    private Resident resident;
    @Column(name = "RES_UIN")
    private String uin;
    @Column(name = "HIST_STATUS")
    @Enumerated(EnumType.STRING)
    private HistoricStatus historicStatus;
    @Column(name = "HH_NIM")
    private String hin;
    @Column(name = "HIST_DATE")
    private LocalDate historicDate;

    public HouseholdHistoric(Resident resident, HouseholdRespDto household, HistoricStatus historicStatus) {
        this.resident = resident;
        this.household.setId(household.getId());
        this.historicDate = LocalDate.now();
        this.hin = household.getHin();
        this.historicStatus = historicStatus;
    }

    public HouseholdHistoric(Resident resident, Household household, HistoricStatus historicStatus) {
        this.resident = resident;
        this.household.setId(household.getId());
        this.historicDate = LocalDate.now();
        this.hin = household.getHin();
        this.historicStatus = historicStatus;
    }
}
