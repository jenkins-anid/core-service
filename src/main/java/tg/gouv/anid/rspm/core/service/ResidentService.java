package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.request.HouseholdHeadReqDto;
import tg.gouv.anid.rspm.core.dto.request.ResidentDocReqDto;
import tg.gouv.anid.rspm.core.dto.request.ResidentReqDto;
import tg.gouv.anid.rspm.core.dto.response.HouseholdRespDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentDocRespDto;
import tg.gouv.anid.rspm.core.dto.response.ResidentRespDto;
import tg.gouv.anid.rspm.core.entity.Household;
import tg.gouv.anid.rspm.core.entity.Resident;
import tg.gouv.anid.rspm.core.entity.ResidentDoc;
import tg.gouv.anid.rspm.core.mapper.HouseholdMapper;
import tg.gouv.anid.rspm.core.mapper.ResidentDocMapper;
import tg.gouv.anid.rspm.core.mapper.ResidentMapper;
import tg.gouv.anid.rspm.core.repository.ResidentDocRepository;
import tg.gouv.anid.rspm.core.repository.ResidentRepository;

import javax.validation.constraints.NotNull;

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
    private final ResidentDocMapper residentDocMapper;
    private final ResidentDocRepository residentDocRepository;
    private final HouseholdMapper householdMapper;
    private final HouseholdService householdService;

    protected ResidentService(ResidentRepository repository,
                              ResidentMapper residentMapper,
                              ResidentDocRepository residentDocRepository,
                              ResidentDocMapper residentDocMapper,
                              HouseholdMapper householdMapper,
                              HouseholdService householdService) {
        super(repository);
        this.residentMapper = residentMapper;
        this.residentDocRepository = residentDocRepository;
        this.residentDocMapper = residentDocMapper;
        this.householdMapper = householdMapper;
        this.householdService = householdService;
    }

    @Transactional
    public HouseholdRespDto createHouseholdHeadResident(@NotNull HouseholdHeadReqDto dto) {
        Resident head = residentMapper.toResident(dto.getHead());
        Household household = householdMapper.toHousehold(dto.getHousehold());
        create(head);
        household.setHeadId(head.getId());
        ResidentRespDto headDto = residentMapper.toResidentRespDto(head);
        HouseholdRespDto householdRespDto = householdService.createHousehold(household);
        householdRespDto.setHead(headDto);
        return householdRespDto;
    }

    @Transactional
    public ResidentRespDto addResident(ResidentReqDto dto) {
        Resident resident = residentMapper.toResident(dto);
        //Vérifier les contraintes réferentiel avant l'enrégistrement
        //Récupérer les objets des autres services après enrégistrement
        return residentMapper.toResidentRespDto(this.create(resident));
    }

    @Transactional
    public ResidentDocRespDto addResidentDoc(ResidentDocReqDto dto) {
        ResidentDoc residentDoc = residentDocMapper.toResidentDoc(dto);
        return residentDocMapper
                .toResidentDocRespDto(
                        residentDocRepository.save(residentDoc));
    }
}
