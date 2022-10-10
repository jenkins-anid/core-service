package tg.gouv.anid.rspm.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
        return ResponseEntity.ok(ResponseUtil
                        .successResponse(householdService.createHousehold(dto)));
    }

    @PutMapping(value = "{id}")
    @Operation(summary = "Editer un ménage",
            description = "Permet de Modifier les informations d'un ménage dans le système")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody @Valid HouseholdReqDto dto) {
        dto.setId(id);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.updateHousehold(dto)));
    }

    @PutMapping(value = "{id}/designated-beneficiary")
    @Operation(summary = "Changer le bénéficiaire désigné d'un ménage",
            description = "Permet de désigner un bénéficiaire pour un ménage dans le système")
    public ResponseEntity<Response> updateDesignatedBeneficiary(@PathVariable Long id, @RequestBody @NotNull String uin) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.updateDesignatedBeneficiary(uin, id)));
    }

    @PutMapping(value = "change-head")
    @Operation(summary = "Changer le chef d'un ménage",
            description = "Permet de changer le chef d'un ménage par un autre membre du ménage")
    public ResponseEntity<Response> changeHouseholdHead(@RequestBody @Valid HouseholdHeadChangeDto dto) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.changeHouseholdHead(dto)));
    }

    @PutMapping(value = "transfer")
    @Operation(summary = "Transférer les membres d'un ménage",
            description = "Permet de transférer un ou plusieurs membre d'un ménage")
    public ResponseEntity<Response> transferHouseholdMembers(@RequestBody @Valid TransferHouseholdMembersDto dto) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.transferHouseholdMember(dto)));
    }

    @PutMapping(value = "transfer/validate")
    @Operation(summary = "Valider le transfert des membres d'un ménage",
            description = "Permet de valider le transfert d'un ou de plusieurs membre d'un ménage")
    public ResponseEntity<Response> validateHouseholdMembersTransfer(@RequestBody @Valid HouseholdTransferValidationDto dto) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.validateTransfer(dto)));
    }

    @PostMapping(value = "{householdId}/assets")
    @Operation(summary = "Ajouter les actifs ou composants d'un ménage",
            description = "Permet d'ajouter les actifs à un ménage dans le système")
    public ResponseEntity<Response> addAssets(@PathVariable Long householdId, @RequestBody @Valid HouseholdAssetsReqDto dto) {
        dto.setHouseholdId(householdId);
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.addHouseholdAssets(dto)));
    }

    @GetMapping(value = "{id}")
    @Operation(summary = "Récupérer un ménage",
            description = "Permet de consulter un ménage à partir de son id dans le système")
    public ResponseEntity<Response> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.getOneHousehold(id)));
    }

    @GetMapping(value = "{householdId}/assets")
    @Operation(summary = "Récupérer la liste des tous les actifs d'un ménage",
            description = "Permet de consulter la liste des actifs d'un  ménages dans le système")
    public ResponseEntity<Response> getAllAssetsByHousehold(@PathVariable Long householdId) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.getAllHouseholdAssets(householdId)));
    }

    @GetMapping
    @Operation(summary = "Récupérer la liste des ménages",
            description = "Permet de consulter la liste paginée des  ménages dans le système")
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return ResponseEntity.ok(ResponseUtil
                .successResponse(householdService.getAll(pageable)));
    }

    @GetMapping(value = "{householdId}/consommation")
    @Operation(summary = "Permet de consulter la liste des consommations d'un ménage dans le système",
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
    @Operation(summary = "Récupérer les actifs durable d'un ménage",
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

}
