package tg.gouv.anid.rspm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tg.gouv.anid.common.entities.repository.GenericRepository;
import tg.gouv.anid.common.entities.service.GenericService;
import tg.gouv.anid.rspm.core.dto.request.ConsommationReqDto;
import tg.gouv.anid.rspm.core.dto.response.ConsommationRespDto;
import tg.gouv.anid.rspm.core.entity.HouseholdConsommation;
import tg.gouv.anid.rspm.core.mapper.ConsommationMapper;
import tg.gouv.anid.rspm.core.repository.HHConsommationRepository;

import java.util.List;
import java.util.Objects;

/**
 * @author Francis AHONSU
 */
@Service
@Transactional(readOnly = true)
public class HHConsommationService extends GenericService<HouseholdConsommation, Long> {

    private final ConsommationMapper consommationMapper;

    public HHConsommationService(HHConsommationRepository repository,
                                    ConsommationMapper consommationMapper) {
        super(repository);
        this.consommationMapper = consommationMapper;
    }

    @Transactional
    public ConsommationRespDto addConsommation(ConsommationReqDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        HouseholdConsommation consommation = consommationMapper.toHouseholdConsommation(dto);
        return consommationMapper.toConsommationRespDto(create(consommation));
    }

    public List<HouseholdConsommation> getByHousehold(Long householdId) {
        return getRepository().findAllByHousehold_id(householdId);
    }

    public List<ConsommationRespDto> getByHouseholdId(Long householdId) {
        return getByHousehold(householdId)
                .stream().map(consommationMapper::toConsommationRespDto).toList();
    }

    public ConsommationRespDto mappeToRespDto(HouseholdConsommation consommation) {
        return consommationMapper.toConsommationRespDto(consommation);
    }

    @Override
    public HHConsommationRepository getRepository() {
        return (HHConsommationRepository) repository;
    }
}
