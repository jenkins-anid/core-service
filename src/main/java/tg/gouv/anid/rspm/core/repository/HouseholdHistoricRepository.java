package tg.gouv.anid.rspm.core.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tg.gouv.anid.rspm.core.entity.HouseholdHistoric;
import tg.gouv.anid.rspm.core.enums.HistoricStatus;

import java.util.Optional;

/**
 * Classe repository pour la gestion de l'historique des ménages habité par les résidents
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
public interface HouseholdHistoricRepository extends BaseRepository<HouseholdHistoric, Long, Long> {

    @Modifying
    @Query(value = "UPDATE RS_H_HOLD_HIST SET HIST_STATUS = :status WHERE RES_UIN = :uin and HIST_STATUS = :current")
    void updateOldHistoric(@Param("uin") String uin, @Param("current") HistoricStatus current, @Param("status") HistoricStatus status);

}
