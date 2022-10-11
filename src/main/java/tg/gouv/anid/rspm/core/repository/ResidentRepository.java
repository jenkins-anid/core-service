package tg.gouv.anid.rspm.core.repository;

import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.rspm.core.entity.Resident;
import tg.gouv.anid.rspm.core.enums.ResidentStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    Set<Resident> findAllByHousehold_idAndStateNotAndStatusIn(Long id, State state, List<ResidentStatus> status);
    Integer countByHousehold_id(Long id);
    Integer countByHousehold_idAndAgeGreaterThanEqual(Long id, Integer majority);
}
