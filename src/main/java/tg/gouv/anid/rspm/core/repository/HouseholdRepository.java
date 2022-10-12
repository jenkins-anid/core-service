package tg.gouv.anid.rspm.core.repository;

import org.springframework.data.jpa.repository.Query;
import tg.gouv.anid.rspm.core.entity.Household;

import java.util.Optional;

/**
 * Classe repository pour la gestion des ménages
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
public interface HouseholdRepository extends BaseRepository<Household, Long, Long> {
    Optional<Household> findByHin(String hin);
}
