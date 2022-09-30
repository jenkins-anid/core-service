package tg.gouv.anid.rspm.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.gouv.anid.common.entities.util.Response;
import tg.gouv.anid.rspm.core.dto.request.ResidentReqDto;
import tg.gouv.anid.rspm.core.entity.Resident;
import tg.gouv.anid.rspm.core.service.ResidentService;

@RestController
@RequestMapping("api/residents")
@Tag(name = "API de gestion des résidents")
public class ResidentController {
    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    @Operation(summary = "Permet d'enrégistrer les informations d'un résident dans le système")
    public ResponseEntity<Response> create(@RequestBody ResidentReqDto dto) {
        return ResponseEntity.ok(Response
                .builder()
                .status(HttpStatus.OK)
                .message("household.create.success")
                .data(residentService.addResident(dto))
                .build());
    }

    @GetMapping
    @Operation(summary = "Permet de récupérer la liste paginée des résidents")
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseEntity.ok(Response
                .builder()
                .status(HttpStatus.OK)
                .message("household.getAll.success")
                .data(residentService.getAll(pageable))
                .build());
    }
}
