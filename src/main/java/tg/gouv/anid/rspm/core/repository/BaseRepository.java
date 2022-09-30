package tg.gouv.anid.rspm.core.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;
import tg.gouv.anid.common.entities.entity.BaseEntity;
import tg.gouv.anid.common.entities.repository.GenericRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<?>, I extends Serializable, J extends Number & Comparable<J>> extends RevisionRepository<E, I, J>, GenericRepository<E, I> {
}
