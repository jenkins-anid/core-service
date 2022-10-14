package tg.gouv.anid.rspm.core.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.util.StringUtils;
import tg.gouv.anid.common.entities.enums.MaritalStatus;
import tg.gouv.anid.common.entities.exception.ApplicationException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResidentReqDto {
    @JsonIgnore
    private Long id;
    @NotBlank(message = "resident.name.mandatory")
    private String name;
    @NotBlank(message = "resident.surname.mandatory")
    private String surname;
    private boolean isHead;

    private Long householdId;
    @NotBlank(message = "resident.uin.mandatory")
    private String uin;
    @NotBlank(message = "resident.civility.mandatory")
    private String civility;
    @NotBlank(message = "resident.sex.mandatory")
    private String sex;
    @NotBlank(message = "resident.nationality.mandatory")
    private String nationality;
    private Long nationalityDocType;
    private String nationalityDoc;
    @NotBlank(message = "resident.countryCode.mandatory")
    private String countryCode;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "resident.birthDate.mandatory")
    private LocalDate birthDate;
    @NotNull(message = "resident.maritalStatus.mandatory")
    private MaritalStatus maritalStatus;
    @NotNull(message = "resident.profession.mandatory")
    private Long professionId;
    private Double salary;
    private boolean literacy;
    private Long schoolLevelId;
    private int finishSchoolYear;
    private boolean atSchoolNow;
    private String actualSchool;
    @NotBlank(message = "resident.birthCountry.mandatory")
    private String birthCountry;
    private boolean migrated;
    private String migrationCountry;
    private int returnYear;
    @NotBlank(message = "resident.region.mandatory")
    private String region;
    @NotBlank(message = "resident.prefecture.mandatory")
    private String prefecture;
    @NotBlank(message = "resident.canton.mandatory")
    private String canton;
    @NotBlank(message = "resident.city.mandatory")
    private String city;
    @NotBlank(message = "resident.locality.mandatory")
    private String locality;
    @NotBlank(message = "resident.address.mandatory")
    private String address;
    private String phone;
    private String email;
    @NotBlank(message = "resident.relationHousehold.mandatory")
    private String relationHousehold;
    private Double distanceSchool;
    private int timeTakenSchool;
    private boolean haveBankAccount;

    public void householdIdMandatoryControl() {
        if (Objects.isNull(householdId))
            throw new ApplicationException("household.id.mandatory");
        if (Boolean.TRUE.equals(isHead)) {
            throw new ApplicationException("household.head.duplication");
        }
    }
}
