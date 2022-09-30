package tg.gouv.anid.rspm.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe entité pour la gestion des scores de vulnérabilité
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Getter
@Setter
@Entity
@Table(name = "RS_SCORES")
@Audited
public class Score extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCR_ID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "HH_ID")
    private Household household;
    @Column(name = "SRC_BASE_SCORE")
    private int baseScore;
    @Column(name = "SRC_FINAL_SCORE")
    private int finalScore;
    @Column(name = "SRC_DATE_SC")
    private LocalDate dateScore;
    @Column(name = "SRC_COMMENTS")
    private String comments;
    @Column(name = "SRC_FIN_STATUS")
    private String status;

}
