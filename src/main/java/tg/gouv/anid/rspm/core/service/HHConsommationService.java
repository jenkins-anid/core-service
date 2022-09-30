package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.entity.HouseholdConsommation;
import tg.gouv.anid.rspm.core.repository.HHConsommationRepository;

/**
 * @author Francis AHONSU
 */
@Service
@Transactional(readOnly = true)
public class HHConsommationService extends GenericService<HouseholdConsommation, Long> {

    protected HHConsommationService(HHConsommationRepository repository) {
        super(repository);
    }
}
