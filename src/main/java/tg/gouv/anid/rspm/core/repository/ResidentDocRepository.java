package tg.gouv.anid.rspm.core.repository;

import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.rspm.core.entity.ResidentDoc;

import java.util.List;

public interface ResidentDocRepository extends BaseRepository<ResidentDoc, Long, Long>{

    List<ResidentDoc> findAllByResident_idAndStateNot(Long residentId, State state);
}
