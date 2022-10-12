package tg.gouv.anid.rspm.core.repository;

import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsRemitance;

import java.util.List;

/**
 * Classe repository pour la gestion des transferts d'argent reçu par le ménage
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
public interface HHAssetsRemitanceRepository extends BaseRepository<HouseholdAssetsRemitance, Long, Long> {
    List<HouseholdAssetsRemitance> findAllByHousehold_idAndStateNot(Long id, State state);
}
