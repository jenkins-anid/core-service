package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.entity.Score;
import tg.gouv.anid.rspm.core.repository.ScoreRepository;

/**
 * @author Francis AHONSU
 */
@Service
@Transactional(readOnly = true)
public class ScoreService extends GenericService<Score, Long> {

    protected ScoreService(ScoreRepository repository) {
        super(repository);
    }
}
