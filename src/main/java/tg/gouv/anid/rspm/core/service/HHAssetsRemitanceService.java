package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsRemitance;
import tg.gouv.anid.rspm.core.repository.HHAssetsRemitanceRepository;


/**
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 *
 */
@Service
@Transactional
public class HHAssetsRemitanceService extends GenericService<HouseholdAssetsRemitance, Long> {

    protected HHAssetsRemitanceService(HHAssetsRemitanceRepository repository) {
        super(repository);
    }
}
