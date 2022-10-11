package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.exception.ResourceNotFoundException;
import tg.gouv.anid.common.entities.repository.GenericRepository;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.common.entities.util.SecureString;
import tg.gouv.anid.rspm.core.entity.HouseholdHistoric;
import tg.gouv.anid.rspm.core.enums.HistoricStatus;
import tg.gouv.anid.rspm.core.repository.HouseholdHistoricRepository;


/**
 * @author Francis AHONSU
 */
@Service
@Transactional(readOnly = true)
public class HouseholdHistoricService extends GenericService<HouseholdHistoric, Long> {

    public HouseholdHistoricService(HouseholdHistoricRepository repository) {
        super(repository);
    }

    @Transactional
    public void updateOldHistoric(String uin) {
        getRepository().updateOldHistoric(uin);
    }

    @Override
    public HouseholdHistoricRepository getRepository() {
        return (HouseholdHistoricRepository) repository;
    }
}
