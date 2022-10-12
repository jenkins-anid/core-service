package tg.gouv.anid.rspm.core.util;

import java.time.LocalDate;

/**
 * @author Francis AHONSU
 *
 * @since 0.0.1
 */
public class DateUtil {
    private DateUtil() {
        //Default constructor
    }

    public static int calculateAge(LocalDate birthdate) {
        return LocalDate.now().getYear() - birthdate.getYear();
    }
}
