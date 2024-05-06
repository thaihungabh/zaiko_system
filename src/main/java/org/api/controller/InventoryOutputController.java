package org.api.controller;

import org.api.dto.inventory_output.reponse.search.InventoryOutputListDTO;
import org.api.services.InventoryOutputService;
import org.api.utils.PageableConstrants;
import org.api.utils.PaginationUtils;
import org.api.utils.Paging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/inventory-output")
public class InventoryOutputController {

    private final InventoryOutputService inventoryOutputService;


    public InventoryOutputController(InventoryOutputService inventoryOutputService) {
        this.inventoryOutputService = inventoryOutputService;
    }

//    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<Paging<InventoryOutputListDTO>> getAllInventoryOutputInformation(
//                                                                                    @PageableDefault(size = PageableConstrants.DEFAULT_SIZE,
//                                                                                            page = PageableConstrants.DEFAULT_PAGE)
//                                                                                           Pageable pageable, Integer page, Integer size) throws Exception {
//
//        Page<InventoryOutputListDTO> inventoryOutputListPage = inventoryOutputService.getAllInventoryInfor(pageable);
//        HttpHeaders headers = PaginationUtils
//                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), inventoryOutputListPage);
//        return new ResponseEntity<>(PaginationUtils.generatePage(inventoryOutputListPage), headers, HttpStatus.OK);
//    }

    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Paging<InventoryOutputListDTO>> searchInformationInventoryOutput(
            @RequestParam(name = "fromOrderDate", required = false) String fromOrderDate,
            @RequestParam(name = "toOrderDate", required = false) String toOrderDate,
            @RequestParam(name = "fromPlanOutputDate", required = false) String fromPlanOutputDate,
            @RequestParam(name = "toPlanOutputDate", required = false) String toPlanOutputDate,
            @RequestParam(name = "fromPlanWorkingDate", required = false) String fromPlanWorkingDate,
            @RequestParam(name = "toPlanWorkingDate", required = false) String toPlanWorkingDate,
            @RequestParam(name = "fromPlanDeliverDate", required = false) String fromPlanDeliverDate,
            @RequestParam(name = "toPlanDeliverDate", required = false) String toPlanDeliverDate,
            @RequestParam(name = "fromSlipNo", required = false) String fromSlipNo,
            @RequestParam(name = "toSlipNo", required = false) String toSlipNo,
            @RequestParam(name = "fromCustomerId", required = false) Integer fromCustomerId,
            @RequestParam(name = "toCustomerId", required = false) Integer toCustomerId,
            @RequestParam(name = "customerName", required = false) String customerName,
            @RequestParam(name = "fromDeliverDestId", required = false) Integer fromDeliverDestId,
            @RequestParam(name = "toDeliverDestId", required = false) Integer toDeliverDestId,
            @RequestParam(name = "deliveryDestName", required = false) String deliveryDestName,
            @RequestParam(name = "fromSupplierId", required = false) Integer fromSupplierId,
            @RequestParam(name = "toSupplierId", required = false) Integer toSupplierId,
            @RequestParam(name = "supplierName", required = false) String supplierName,
            @RequestParam(name = "fromProductId", required = false) Integer fromProductId,
            @RequestParam(name = "toProductId", required = false) Integer toProductId,
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "fromRepositoryId", required = false) Integer fromRepositoryId,
            @RequestParam(name = "toRepositoryId", required = false) Integer toRepositoryId,
            @RequestParam(name = "batchNo", required = false) String batchNo,
            @RequestParam(name = "deliveryType", required = false, defaultValue = "1") Integer deliveryType,
            @RequestParam(name = "deliveryStatus", required = false, defaultValue = "1") String deliveryStatus,
            @RequestParam(name = "isClosed", required = false, defaultValue = "") String isClosed,
            @PageableDefault(size = PageableConstrants.DEFAULT_SIZE,
                    page = PageableConstrants.DEFAULT_PAGE)
            Pageable pageable, Integer page, Integer size){

        Page<InventoryOutputListDTO> inventoryOutputListPage =
                inventoryOutputService.searchInventoryInformation(fromOrderDate,toOrderDate, fromPlanOutputDate,
                        toPlanOutputDate, fromPlanWorkingDate, toPlanWorkingDate, fromPlanDeliverDate,
                        toPlanDeliverDate, fromSlipNo, toSlipNo, fromCustomerId, toCustomerId, customerName,
                        fromDeliverDestId, toDeliverDestId, deliveryDestName, fromSupplierId, toSupplierId,
                        supplierName,fromProductId, toProductId, productName, fromRepositoryId, toRepositoryId,
                        batchNo, deliveryType, deliveryStatus, isClosed, pageable);

        HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), inventoryOutputListPage);
        return new ResponseEntity<>(PaginationUtils.generatePage(inventoryOutputListPage), headers, HttpStatus.OK);
    }
}
