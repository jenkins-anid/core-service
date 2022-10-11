package tg.gouv.anid.rspm.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;

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
@Table(name = "RS_RM_SENDER_INFO")
@NoArgsConstructor
@Audited
public class RemitanceSenderInfo extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "RSI_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ASR_ID")
    @JsonIgnore
    private HouseholdAssetsRemitance assetsRemitance;
    @Column(name = "RSI_SEX")
    private String sex;
    @Column(name = "RSI_RELEATIONSHIP")
    private String relationship;
    @Column(name = "RSI_SEND_AMOUNT")
    private Double amount;
    @Column(name = "RSI_AMOUNT_CURRENCY")
    private String devise;
}
