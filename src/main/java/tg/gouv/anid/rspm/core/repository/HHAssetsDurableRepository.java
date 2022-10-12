package tg.gouv.anid.rspm.core.repository;

import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsDurable;

import java.util.List;

/**
 * Classe repository pour la gestion des actifs durable des ménages
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
public interface HHAssetsDurableRepository extends BaseRepository<HouseholdAssetsDurable, Long, Long> {
    List<HouseholdAssetsDurable> findAllByHousehold_idAndStateNot(Long id, State state);
}
