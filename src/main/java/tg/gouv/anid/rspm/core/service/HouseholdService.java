package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.entity.Household;
import tg.gouv.anid.rspm.core.repository.HouseholdRepository;

/**
 * Service pour la gestion des ménages
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Service
@Transactional(readOnly = true)
public class HouseholdService extends GenericService<Household, Long> {

    protected HouseholdService(HouseholdRepository repository) {
        super(repository);
    }
}
