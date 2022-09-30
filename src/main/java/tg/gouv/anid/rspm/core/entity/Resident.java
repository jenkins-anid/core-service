package tg.gouv.anid.rspm.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import tg.gouv.anid.common.entities.entity.Auditable;
import tg.gouv.anid.rspm.core.model.Profession;
import tg.gouv.anid.rspm.core.model.SchoolLevel;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(name = "RES_UIN")
    private String uin;
    @Column(name = "RES_NAME")
    private String name;
    @Column(name = "RES_SURNAME")
    private String surname;
    @Column(name = "RES_IS_HEAD_YN")
    private boolean isHead;
    @Column(name = "RES_TITLE")
    private String civility;
    @Column(name = "RES_SEX")
    private String sex;
    @Column(name = "RES_NATIONALITY")
    private String nationality;
    @Column(name = "RES_NAT_DOC_TYPE")
    private Long nationalityDocType;
    @Column(name = "RES_NAT_DOC")
    private byte[] nationalityDoc;
    @Column(name = "RES_COUNTRY_DOC")
    private String countryCode;
    @Column(name = "RES_BIRTH_DATE")
    private LocalDate birthDate;
    @Column(name = "RES_AGE")
    private int age;
    @Column(name = "RES_MARITAL_STATUS")
    private String maritalStatus;
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
    private int finishSchoolYear;
    @Column(name = "RES_ATSCHOOL_NOW_YR")
    private boolean atSchoolNow;
    @Column(name = "RES_ACTUAL_SCHOOL")
    private String actualSchool;
    @Column(name = "RES_COUNTRY_OFBIRTH")
    private String birthCountry;
    @Column(name = "RES_MIGRATED_YN")
    private boolean migrated;
    @Column(name = "RES_MIGRATION_COUNTRY")
    private String migrationCountry;
    @Column(name = "RES_RETURN_YR")
    private int returnYear;
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
    @Column(name = "RES_TEL")
    private String phone;
    @Column(name = "RES_EMAIL")
    private String email;
    @Column(name = "RES_RELATION_HH")
    private String relationHousehold;
    @Column(name = "RES_SCORE")
    private int score;
    @Column(name = "RES_DIST_SCHOOL")
    private Double distanceSchool;
    @Column(name = "RES_TTAKEN_SCHOOL")
    private int timeTakenSchool;
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private Set<HouseholdHistoric> historics;
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL)
    private Set<ResidentDoc> residentDocs;

}
