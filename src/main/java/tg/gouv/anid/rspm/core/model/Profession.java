package tg.gouv.anid.rspm.core.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Profession implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    private Long id;
    private String name;
}
