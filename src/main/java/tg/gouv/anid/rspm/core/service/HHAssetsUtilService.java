package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsUtil;
import tg.gouv.anid.rspm.core.repository.HHAssetsUtilRepository;

/**
 * @author Francis AHONSU
 */
@Service
@Transactional(readOnly = true)
public class HHAssetsUtilService extends GenericService<HouseholdAssetsUtil, Long> {

    protected HHAssetsUtilService(HHAssetsUtilRepository repository) {
        super(repository);
    }
}
