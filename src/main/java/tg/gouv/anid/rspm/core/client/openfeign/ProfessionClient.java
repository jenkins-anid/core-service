package tg.gouv.anid.rspm.core.client.openfeign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tg.gouv.anid.rspm.core.model.Profession;

import java.util.List;

@FeignClient(name = "RSPM-ADMINISTRATION-SERVICE")
public interface ProfessionClient {

    @GetMapping("api/v1/professions")
    List<Profession> getAll();
}
