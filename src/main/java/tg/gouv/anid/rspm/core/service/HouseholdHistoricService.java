package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.entity.HouseholdHistoric;
import tg.gouv.anid.rspm.core.repository.HouseholdHistoricRepository;


/**
 * @author Francis AHONSU
 */
@Service
@Transactional(readOnly = true)
public class HouseholdHistoricService extends GenericService<HouseholdHistoric, Long> {

    protected HouseholdHistoricService(HouseholdHistoricRepository repository) {
        super(repository);
    }
}
