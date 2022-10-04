package tg.gouv.anid.rspm.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.gouv.anid.common.entities.util.Response;
import tg.gouv.anid.rspm.core.dto.request.HouseholdHeadReqDto;
import tg.gouv.anid.rspm.core.dto.request.ResidentDocReqDto;
import tg.gouv.anid.rspm.core.dto.request.ResidentReqDto;
import tg.gouv.anid.rspm.core.service.ResidentDocService;
import tg.gouv.anid.rspm.core.service.ResidentService;
import tg.gouv.anid.rspm.core.util.ResponseUtil;

import javax.validation.Valid;


/**
 * @author Francis AHONSU
 *
 * @version 0.0.1
 */
@RestController
@RequestMapping("api/residents")
@Tag(name = "API de gestion des résidents")
public class ResidentController {
    private final ResidentService residentService;
    private final ResidentDocService residentDocService;

    public ResidentController(ResidentService residentService,
                              ResidentDocService residentDocService) {
        this.residentService = residentService;
        this.residentDocService = residentDocService;
    }

    @PostMapping
    @Operation(summary = "Créer un résident",
            description = "Permet d'enrégistrer les informations d'un résident dans le système")
    public ResponseEntity<Response> create(@RequestBody ResidentReqDto dto) {
        return ResponseEntity.ok(Response
                .builder()
                .status(HttpStatus.OK)
                .message("household.create.success")
                .data(residentService.addResident(dto))
                .build());
    }

    @PostMapping(value = "household-head")
    @Operation(summary = "Créer un chef de Ménage",
            description = "Permet d'enrégistrer un chef de ménage et par ricochet le ménage")
    public ResponseEntity<Response> createHouseholdHead(@RequestBody @Valid HouseholdHeadReqDto dto) {
        return ResponseEntity.ok(
                ResponseUtil.successResponse(
                        residentService.createHouseholdHeadResident(dto)));
    }


    @GetMapping
    @Operation(summary = "Récupérer la liste des résidents",
            description = "Permet de récupérer la liste paginée des résidents")
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseEntity.ok(Response
                .builder()
                .status(HttpStatus.OK)
                .message("resident.getAll.success")
                .data(residentService.getAll(pageable))
                .build());
    }

    @PostMapping(value = "add-resident-doc")
    @Operation(summary = "Enrégistrer un document d'un résident",
            description = "Permet d'ajouter un document d'un résident")
    public ResponseEntity<Response> addResidentDoc(@RequestBody ResidentDocReqDto dto) {
        return ResponseEntity.ok(Response
                .builder()
                        .status(HttpStatus.OK)
                        .message("residentDoc.add.success")
                        .data(residentService.addResidentDoc(dto))
                .build());
    }

    @PostMapping(value = "{id}/docs")
    @Operation(summary = "Récupérer la liste des document d'un résident",
            description = "Permet consulter les documents d'un résident")
    public ResponseEntity<Response> getAllResidentDocByResident(@PathVariable Long id) {
        return ResponseEntity.ok(Response
                .builder()
                .status(HttpStatus.OK)
                .message("residentDoc.get.success")
                .data(residentDocService.getByResidentId(id))
                .build());
    }


}
