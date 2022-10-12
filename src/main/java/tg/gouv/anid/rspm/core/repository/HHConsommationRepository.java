package tg.gouv.anid.rspm.core.repository;

import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.rspm.core.entity.HouseholdConsommation;

import java.util.List;

/**
 * Classe repository pour la gestion des consommations des ménages
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
public interface HHConsommationRepository extends BaseRepository<HouseholdConsommation, Long, Long> {

List<HouseholdConsommation> findAllByHousehold_idAndStateNot(Long id, State state);
}
