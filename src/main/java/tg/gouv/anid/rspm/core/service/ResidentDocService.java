package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.enums.State;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.response.ResidentDocRespDto;
import tg.gouv.anid.rspm.core.entity.ResidentDoc;
import tg.gouv.anid.rspm.core.mapper.ResidentDocMapper;
import tg.gouv.anid.rspm.core.repository.ResidentDocRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ResidentDocService extends GenericService<ResidentDoc, Long> {

    private final ResidentDocMapper residentDocMapper;

    public ResidentDocService(ResidentDocRepository repository, ResidentDocMapper residentDocMapper) {
        super(repository);
        this.residentDocMapper = residentDocMapper;
    }

    public List<ResidentDoc> getByResident(Long residentId) {
        return getRepository().findAllByResident_idAndStateNot(residentId, State.DELETED);
    }

    public List<ResidentDocRespDto> getByResidentId(Long residentId) {
        return getByResident(residentId)
                .stream()
                .map(residentDocMapper::toResidentDocRespDto)
                .toList();
    }

    @Override
    public ResidentDocRepository getRepository() {
        return (ResidentDocRepository) repository;
    }
}
