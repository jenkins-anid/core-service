package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.entity.HouseholdAssetsDurable;
import tg.gouv.anid.rspm.core.repository.HHAssetsDurableRepository;


/**
 *
 * @author Francis AHONSU
 *
 * @since 0.0.1
 *
 */
@Service
@Transactional(readOnly = true)
public class HHAssetsDurableService extends GenericService<HouseholdAssetsDurable, Long> {

    protected HHAssetsDurableService(HHAssetsDurableRepository repository) {
        super(repository);
    }
}
