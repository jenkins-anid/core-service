package tg.gouv.anid.rspm.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.gouv.anid.common.entities.util.Response;
import tg.gouv.anid.rspm.core.util.ResponseUtil;
import tg.gouv.anid.rspm.core.dto.request.*;
import tg.gouv.anid.rspm.core.service.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Francis AHONSU
 *
 * @since 0.0.1
 */
@RestController
@RequestMapping("api/households")
@Tag(name = "API de gestion des ménages")
@Slf4j(topic = "EventLog")
public class HouseholdController {
    private final HouseholdService householdService;
    private final HHConsommationService consommationService;
    private final HHAssetsUtilService assetsUtilService;
    private final HHAssetsDurableService assetsDurableService;
    private final HHAssetsRemitanceService assetsRemitanceService;

    public HouseholdController(HouseholdService householdService,
                               HHConsommationService consommationService,
                               HHAssetsUtilService assetsUtilService,
                               HHAssetsDurableService assetsDurableService,
                               HHAssetsRemitanceService assetsRemitanceService) {
        this.householdService = householdService;
        this.consommationService = consommationService;
        this.assetsUtilService = assetsUtilService;
        this.assetsDurableService = assetsDurableService;
        this.assetsRemitanceService = assetsRemitanceService;
    }

    @PostMapping
    @Operation(summary = "Créer un ménage",
            description = "Permet de créer un ménage dans le système")
    public ResponseEntity<Response> create(@RequestBody @Valid HouseholdReqDto dto) {
        log.info("Création de ménage | POST api/households | dto : {}", dto);
        return ResponseEntity.ok(ResponseUtil
                        .successResponse(householdService.createHousehold(dto)));
    }

    @PutMapping(value = "{id}")
    @Operation(summary = "Editer un ménage",
            description = "Permet de Modifier les informations d'un ménage dans le système")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody @Valid HouseholdReqDto dto) {
        dto.setId(id);
        log.info("Edition de ménage | PUT api/households/{} | dto : {}", id, dto);
        dto.hinNonNullControl();
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.updateHousehold(dto)));
    }

    @PutMapping(value = "{id}/designated-beneficiary")
    @Operation(summary = "Changer le bénéficiaire désigné d'un ménage",
            description = "Permet de désigner un bénéficiaire pour un ménage dans le système")
    public ResponseEntity<Response> updateDesignatedBeneficiary(@PathVariable Long id, @RequestBody @NotNull DesignatedUinWrapper uinWrapper) {
        log.info("Update bénéficiaire désigné de ménage | PUT api/households/{}/designated-beneficiary | dto : {}", id, uinWrapper);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.updateDesignatedBeneficiary(uinWrapper.getUin(), id)));
    }

    @PutMapping(value = "change-head")
    @Operation(summary = "Changer le chef d'un ménage",
            description = "Permet de changer le chef d'un ménage par un autre membre du ménage")
    public ResponseEntity<Response> changeHouseholdHead(@RequestBody @Valid HouseholdHeadChangeDto dto) {
        log.info("Changement de chef de ménage | PUT api/households/change-head | dto : {}", dto);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.changeHouseholdHead(dto)));
    }

    @PutMapping(value = "transfer")
    @Operation(summary = "Transférer les membres d'un ménage",
            description = "Permet de transférer un ou plusieurs membre d'un ménage")
    public ResponseEntity<Response> transferHouseholdMembers(@RequestBody @Valid TransferHouseholdMembersDto dto) {
        log.info("Transfert de membres de ménage | PUT api/households/transfer | dto : {}", dto);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.transferHouseholdMember(dto)));
    }

    @PutMapping(value = "departure")
    @Operation(summary = "Déclarer le départ d'un membre de ménage",
            description = "Permet de déclarer le départ d'un membre d'un ménage afin qu'il puisse intégrer un nouveau ménage")
    public ResponseEntity<Response> declareDeparture(@RequestBody @Valid MemberDepartureDto dto) {
        log.info("Déclaration de départ d'un membre | PUT api/households/departure | dto : {}", dto);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.declareMemberDeparture(dto)));
    }

    @PutMapping(value = "transfer/validate")
    @Operation(summary = "Valider le transfert des membres d'un ménage",
            description = "Permet de valider le transfert d'un ou de plusieurs membre d'un ménage")
    public ResponseEntity<Response> validateHouseholdMembersTransfer(@RequestBody @Valid HouseholdTransferValidationDto dto) {
        log.info("Validation de l'intégration d'un membre de ménage | PUT api/households/transfer/validate | dto : {}", dto);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.validateTransfer(dto)));
    }

    @PostMapping(value = "{householdId}/assets")
    @Operation(summary = "Ajouter les actifs ou composants d'un ménage",
            description = "Permet d'ajouter les actifs à un ménage dans le système")
    public ResponseEntity<Response> addAssets(@PathVariable Long householdId, @RequestBody @Valid HouseholdAssetsReqDto dto) {
        dto.setHouseholdId(householdId);
        log.info("Ajout des actifs d'un ménage | POST api/households/{}/assets | dto : {}", householdId, dto);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.addHouseholdAssets(dto)));
    }

    @GetMapping(value = "{id}")
    @Operation(summary = "Récupérer un ménage",
            description = "Permet de consulter un ménage à partir de son id dans le système")
    public ResponseEntity<Response> getOne(@PathVariable Long id) {
        log.info("Récupération d'un ménage | GET api/households/{} | dto : no-input", id);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.getOneHousehold(id)));
    }

    @GetMapping(value = "{id}/members")
    @Operation(summary = "Récupérer les membres d'un ménage",
            description = "Permet de consulter la liste des membres d'un ménage dans le système")
    public ResponseEntity<Response> getHouseholdMembers(@PathVariable Long id) {
        log.info("Récupération des membres d'un ménage | GET api/households/{}/members | dto : no-input", id);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.getAllHouseholdMembers(id)));
    }



    @GetMapping(value = "{householdId}/assets")
    @Operation(summary = "Récupérer la liste des tous les actifs d'un ménage",
            description = "Permet de consulter la liste des actifs d'un  ménages dans le système")
    public ResponseEntity<Response> getAllAssetsByHousehold(@PathVariable Long householdId) {
        log.info("Récupération des actifs d'un ménage | GET api/households/{}/assets | dto : no-input", householdId);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.getAllHouseholdAssets(householdId)));
    }

    @GetMapping
    @Operation(summary = "Récupérer la liste des ménages",
            description = "Permet de consulter la liste paginée des  ménages dans le système")
    public ResponseEntity<Response> getAll(Pageable pageable) {
        log.info("Récupération de la liste paginée des ménage | GET api/households | pageable : {}", pageable);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.getAllHousehold(pageable)));
    }

    @GetMapping(value = "all")
    @Operation(summary = "Récupérer la liste des ménages",
            description = "Permet de consulter la liste total des  ménages dans le système")
    public ResponseEntity<Response> getAll() {
        log.info("Récupération de la liste totale des ménages | GET api/households/ | dto : no-input");
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.getAllHousehold()));
    }

    @GetMapping(value = "{householdId}/consommation")
    @Operation(summary = "Consulter la liste des consommations",
            description = "Permet de consulter la liste des consommations d'un ménage dans le système")
    public ResponseEntity<Response> getConsommationByHousehold(@PathVariable Long householdId) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(consommationService.getByHouseholdId(householdId)));
    }

    @PostMapping(value = "consommation")
    @Operation(summary = "Ajouter la consommation du ménage",
            description = "Permet d'ajouter la consommation pour un ménage dans le système")
    public ResponseEntity<Response> addConsommation(@RequestBody @Valid ConsommationReqDto dto) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(consommationService.addConsommation(dto)));
    }

    @GetMapping(value = "{householdId}/assets-util")
    @Operation(summary = "Récupérer les actifs utilitaire d'un ménage",
            description = "Permet de consulter la liste des actifs utilitaire d'un ménage dans le système")
    public ResponseEntity<Response> getAssetsUtilByHousehold(@PathVariable Long householdId) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(assetsUtilService.getByHouseholdId(householdId)));
    }

    @PostMapping(value = "assets-util")
    @Operation(summary = "Ajouter un actif utilitaire à un ménage",
            description = "Permet d'ajouter un actif utilitaire pour un ménage dans le système")
    public ResponseEntity<Response> addAssetsUtil(@RequestBody @Valid AssetsUtilReqDto dto) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(assetsUtilService.addAssetsUtil(dto)));
    }

    @GetMapping(value = "{householdId}/assets-durable")
    @Operation(summary = "Récupérer les actifs durable d'un ménage",
            description = "Permet de consulter la liste des actifs durable d'un ménage dans le système")
    public ResponseEntity<Response> getAssetsDurableByHousehold(@PathVariable Long householdId) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(assetsDurableService.getByHouseholdId(householdId)));
    }

    @PostMapping(value = "assets-durable")
    @Operation(summary = "Ajouter un actif durable à un ménage",
            description = "Permet d'ajouter un actif durable pour un ménage dans le système")
    public ResponseEntity<Response> addAssetsDurable(@RequestBody @Valid AssetsDurableReqDto dto) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(assetsDurableService.addAssetsDurable(dto)));
    }

    @GetMapping(value = "{householdId}/assets-remittance")
    @Operation(summary = "Récupérer les transferts de fond d'un ménage",
            description = "Permet de consulter la liste des versements d'un ménage dans le système")
    public ResponseEntity<Response> getAssetsRemitanceByHousehold(@PathVariable Long householdId) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(assetsRemitanceService.getByHouseholdId(householdId)));
    }

    @PostMapping(value = "assets-remittance")
    @Operation(summary = "Enrégistrer les transferts de fond d'un ménage",
            description = "Permet d'ajouter les transferts de fond pour un ménage dans le système")
    public ResponseEntity<Response> addRemittance(@RequestBody @Valid AssetsRemitanceReqDto dto) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(assetsRemitanceService.addAssetsRemitance(dto)));
    }

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Supprimer un ménage",
            description = "Permet de supprimer logiquement un ménage dans le système")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        log.info("Suppression d'un ménage | GET api/households/{} | dto : no-input", id);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.deleteLogicaly(id)));
    }

    @Data
    private static class DesignatedUinWrapper {
        private String uin;
    }

}
