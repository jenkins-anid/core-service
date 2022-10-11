package tg.gouv.anid.rspm.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;
import tg.gouv.anid.common.entities.enums.MaritalStatus;
import tg.gouv.anid.rspm.core.enums.ResidentStatus;
import tg.gouv.anid.rspm.core.model.Profession;
import tg.gouv.anid.rspm.core.model.SchoolLevel;
import tg.gouv.anid.rspm.core.util.DateUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Classe entité pour la gestion des résidents
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Entity
@Table(name = "RS_RESIDENTS")
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Resident extends Auditable<String> {
    @Id
    @GeneratedValue
    @Column(name = "RES_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "HH_ID")
    private Household household;
    @NotBlank(message = "resident.uin.mandatory")
    @Column(name = "RES_UIN", unique = true, length = 12, updatable = false)
    private String uin;
    @NotBlank(message = "resident.name.mandatory")
    @Column(name = "RES_NAME", updatable = false)
    private String name;
    @NotBlank(message = "resident.surname.mandatory")
    @Column(name = "RES_SURNAME", updatable = false)
    private String surname;
    @NotNull(message = "resident.isHead.mandatory")
    @Column(name = "RES_IS_HEAD_YN")
    private boolean isHead;
    @NotBlank(message = "resident.civility.mandatory")
    @Column(name = "RES_TITLE")
    private String civility;
    @NotBlank(message = "resident.sex.mandatory")
    @Column(name = "RES_SEX", updatable = false)
    private String sex;
    @NotBlank(message = "resident.nationality.mandatory")
    @Column(name = "RES_NATIONALITY")
    private String nationality;
    @NotNull(message = "resident.docType.mandatory")
    @Column(name = "RES_NAT_DOC_TYPE")
    private Long nationalityDocType;
    @Column(name = "RES_NAT_DOC")
    private byte[] nationalityDoc;
    @NotBlank(message = "resident.countryCode.mandatory")
    @Column(name = "RES_COUNTRY_DOC")
    private String countryCode;
    @NotNull(message = "resident.birthdate.mandatory")
    @Column(name = "RES_BIRTH_DATE", updatable = false)
    private LocalDate birthDate;
    @Column(name = "RES_AGE")
    private int age;
    @NotNull(message = "resident.maritalStatus.mandatory")
    @Column(name = "RES_MARITAL_STATUS")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @Column(name = "RES_PROFESSION")
    private Long professionId;
    @Transient
    private Profession profession;
    @Column(name = "RES_SALARY")
    private Double salary;
    @Column(name = "RES_LITERACY_YN")
    private boolean literacy;
    @Column(name = "RES_SCHOOL_LEVEL")
    private Long schoolLevelId;
    @Transient
    private SchoolLevel schoolLevel;
    @Column(name = "RES_FINISH_SCHOOL_YR")
    private Integer finishSchoolYear;
    @Column(name = "RES_ATSCHOOL_NOW_YR")
    private boolean atSchoolNow;
    @Column(name = "RES_ACTUAL_SCHOOL")
    private String actualSchool;
    @NotBlank(message = "resident.birthCountry.mandatory")
    @Column(name = "RES_COUNTRY_OFBIRTH")
    private String birthCountry;
    @Column(name = "RES_MIGRATED_YN")
    private boolean migrated;
    @Column(name = "RES_MIGRATION_COUNTRY")
    private String migrationCountry;
    @Column(name = "RES_RETURN_YR")
    private Integer returnYear;
    @Column(name = "RES_REGION")
    private String region;
    @Column(name = "RES_ADD_PREFECTURE")
    private String prefecture;
    @Column(name = "RES_ADD_CANTON")
    private String canton;
    @Column(name = "RES_ADD_CITY")
    private String city;
    @Column(name = "RES_ADD_AREA")
    private String locality;
    @Column(name = "RES_ADDRESS")
    private String address;
    @Column(name = "RES_TEL", unique = true)
    private String phone;
    @Column(name = "RES_EMAIL", unique = true)
    private String email;
    @NotBlank(message = "resident.relationHousehold.mandatory")
    @Column(name = "RES_RELATION_HH")
    private String relationHousehold;
    @Column(name = "RES_SCORE")
    private Integer score;
    @Column(name = "RES_DIST_SCHOOL")
    private Double distanceSchool;
    @Column(name = "RES_TTAKEN_SCHOOL")
    private Integer timeTakenSchool;
    @Column(name = "RES_STATUS")
    @Enumerated(EnumType.STRING)
    private ResidentStatus status;
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<HouseholdHistoric> historics;
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ResidentDoc> residentDocs;

    @PrePersist
    @PreUpdate
    @Override
    public void setUp() {
        super.setUp();
        this.age = DateUtil.calculateAge(this.birthDate);
        statusCheck();
    }

    public void statusCheck() {
        if (Objects.isNull(status)) {
            this.status = ResidentStatus.CREATE;
        } else if (!ResidentStatus.BANNED.equals(status)
                && !ResidentStatus.DEPARTURE.equals(status)
                &&  isHouseholdNonNull()) {
            this.status = ResidentStatus.IN_HOUSEHOLD;
        }
    }

    public boolean isHouseholdNonNull() {
        return Objects.nonNull(household) && Objects.nonNull(household.getId());
    }

    public boolean validateUnchangeableInfo(Resident old) {
        if (id.equals(old.getId())
                && uin.equals(old.getUin())
                && name.equals(old.getName())
                && surname.equals(old.getSurname())
                && household.getId().equals(old.getHousehold().getId())
                && sex.equals(old.getSex())
                && birthDate.equals(old.getBirthDate())
        ) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Long getHouseholdId() {
        return Objects.nonNull(household) ? household.getId() : null;
    }
}
