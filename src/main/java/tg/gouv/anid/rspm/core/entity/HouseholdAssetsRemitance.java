package tg.gouv.anid.rspm.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;
import tg.gouv.anid.common.entities.exception.ApplicationException;

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
    @Column(name = "ASR_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HH_ID")
    @JsonIgnore
    private Household household;
    @Column(name = "ASR_HH_REMIT_YN")
    private boolean activeRemitance;
    @OneToMany(mappedBy = "assetsRemitance", cascade = CascadeType.ALL)
    private Set<RemitanceSenderInfo> senderInfos;
    @Column(name = "ASR_AMT_RMT_FD")
    private Float annualFoodRemitanceTotalAmount;
    @Column(name = "ASR_AMT_RMT_OTH")
    private Float annualOtherThingsRemitanceTotalAmount;

    @PrePersist
    @PreUpdate
    @Override
    public void setUp() {
        super.setUp();
        this.validateAmount();
        this.validateSenderInfos();
    }

    public void validateSenderInfos() {
        if (Boolean.TRUE.equals(activeRemitance) && senderInfos.isEmpty()) {
            throw new ApplicationException("remittance.sender.info.required");
        }

        if(Boolean.FALSE.equals(activeRemitance) && !senderInfos.isEmpty()) {
            throw new ApplicationException("remittance.is.disabled");
        }
    }

    public void validateAmount() {
        if (activeRemitance && annualFoodRemitanceTotalAmount < 1) {
            throw new ApplicationException("remittance.FoodRemitanceTotalAmount.not.set");
        }

        if (activeRemitance && annualOtherThingsRemitanceTotalAmount < 1) {
            throw new ApplicationException("remittance.OtherThingsRemitanceTotalAmount.not.set");
        }

    }
}
