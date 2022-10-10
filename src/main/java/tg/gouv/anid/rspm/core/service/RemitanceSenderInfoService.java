package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.entity.RemitanceSenderInfo;
import tg.gouv.anid.rspm.core.repository.RemitanceSenderInfoRepository;

/**
 * @author Francis AHONSU
 */
@Service
@Transactional(readOnly = true)
public class RemitanceSenderInfoService extends GenericService<RemitanceSenderInfo, Long> {

    public RemitanceSenderInfoService(RemitanceSenderInfoRepository repository) {
        super(repository);
    }
}
