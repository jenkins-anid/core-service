package tg.gouv.anid.rspm.core.util;

import java.time.LocalDate;

public class DateUtil {
    private DateUtil() {
        //Default constructor
    }

    public static int calculateAge(LocalDate birthdate) {
        return LocalDate.now().getYear() - birthdate.getYear();
    }
}
