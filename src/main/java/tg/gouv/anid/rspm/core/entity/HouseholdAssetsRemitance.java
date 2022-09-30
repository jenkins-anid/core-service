package tg.gouv.anid.rspm.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;
import java.util.Set;

/**
 * Classe entité pour la gestion des transferts d'argent reçu par le ménage
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Getter
@Setter
@Entity
@Table(name = "RS_HH_ASSETS_REMITANCE")
@Audited
public class HouseholdAssetsRemitance extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ASR_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HH_ID")
    private Household household;
    @Column(name = "ASR_HH_REMIT_YN")
    private boolean activeRemitance;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private Set<RemitanceSenderInfo> senderInfos;
    @Column(name = "ASR_AMT_RMT_FD")
    private Float annualFoodRemitanceTotalAmount;
    @Column(name = "ASR_AMT_RMT_OTH")
    private Float annualOtherThingsRemitanceTotalAmount;
}
