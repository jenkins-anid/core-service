package tg.gouv.anid.rspm.core.repository;

import tg.gouv.anid.rspm.core.entity.HouseholdAssetsUtil;

import java.util.List;

/**
 * Classe repository pour la gestion des actifs utilitaire d'un ménage
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
public interface HHAssetsUtilRepository extends BaseRepository<HouseholdAssetsUtil, Long, Long> {

    List<HouseholdAssetsUtil> findAllByHousehold_id(Long id);
}
