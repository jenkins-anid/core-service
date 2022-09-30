package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.request.ResidentReqDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentRespDto;
import tg.gouv.anid.rspm.core.entity.Resident;
import tg.gouv.anid.rspm.core.mapper.ResidentMapper;
import tg.gouv.anid.rspm.core.repository.ResidentRepository;

/**
 * service pour la gestion des résidents
 *
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@Service
@Transactional(readOnly = true)
public class ResidentService extends GenericService<Resident, Long> {

    private final ResidentMapper residentMapper;

    protected ResidentService(ResidentRepository repository, ResidentMapper residentMapper) {
        super(repository);
        this.residentMapper = residentMapper;
    }

    @Transactional
    public ResidentRespDto addResident(ResidentReqDto dto) {
        Resident resident = residentMapper.toResident(dto);
        //Vérifier les contraintes réferentiel avant l'enrégistrement
        //Récupérer les objets des autres services après enrégistrement
        return residentMapper.toResidentRespDto(this.create(resident));
    }
}
