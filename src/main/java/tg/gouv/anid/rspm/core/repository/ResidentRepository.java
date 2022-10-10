package tg.gouv.anid.rspm.core.repository;

import tg.gouv.anid.rspm.core.entity.Resident;
import tg.gouv.anid.rspm.core.enums.ResidentStatus;

import java.util.Optional;

/**
 * Classe repository pour la gestion des résidents
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
public interface ResidentRepository extends BaseRepository<Resident, Long, Long> {

    boolean existsByUinAndStatusNot(String uin, ResidentStatus status);

    Optional<Resident> findByUin(String uin);
}
