package tg.gouv.anid.rspm.core.repository;

import tg.gouv.anid.rspm.core.entity.ResidentDoc;

import java.util.List;

public interface ResidentDocRepository extends BaseRepository<ResidentDoc, Long, Long>{

    List<ResidentDoc> findAllByResident_id(Long residentId);
}
