package tg.gouv.anid.rspm.core.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResidentReqDto {
    private String name;
    private String surname;
    private boolean isHead;
    private String civility;
    private String sex;
    private String nationality;
    private Long nationalityDocType;
    private String nationalityDoc;
    private String countryCode;
    private LocalDate birthDate;
    private int age;
    private String maritalStatus;
    private Long professionId;
    private Double salary;
    private boolean literacy;
    private Long schoolLevelId;
    private int finishSchoolYear;
    private boolean atSchoolNow;
    private String actualSchool;
    private String birthCountry;
    private boolean migrated;
    private String migrationCountry;
    private int returnYear;
    private String region;
    private String prefecture;
    private String canton;
    private String city;
    private String locality;
    private String address;
    private String phone;
    private String email;
    private String relationHousehold;
    private String score;
    private Double distanceSchool;
    private int timeTakenSchool;
}
