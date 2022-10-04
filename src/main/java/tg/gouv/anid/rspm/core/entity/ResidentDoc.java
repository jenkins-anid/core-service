package tg.gouv.anid.rspm.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "RS_RESIDENTS_DOCS")
@Audited
public class ResidentDoc extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_DOC_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "RES_ID")
    private Resident resident;
    @NotNull(message = "residentDoc.type.mandatory")
    @Column(name = "R_DOC_TYPE")
    private String type;
    @NotBlank(message = "residentDoc.title.mandatory")
    @Column(name = "R_DOC_TITLE")
    private String title;
    @NotBlank(message = "residentDoc.description.mandatory")
    @Column(name = "R_DOC_DESCRIPTION")
    private String description;
    @NotNull(message = "residentDoc.object.mandatory")
    @Column(name = "R_DOC_OBJECT")
    private byte[] object;

}
