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
            @RequestParam(name = "f_orderDate", required = false) String f_orderDate,
            @RequestParam(name = "t_orderDate", required = false) String t_orderDate,
            @RequestParam(name = "f_planOutputDate", required = false) String f_planOutputDate,
            @RequestParam(name = "t_planOutputDate", required = false) String t_planOutputDate,
            @RequestParam(name = "f_planWorkingDate", required = false) String f_planWorkingDate,
            @RequestParam(name = "t_planWorkingDate", required = false) String t_planWorkingDate,
            @RequestParam(name = "f_planDeliverDate", required = false) String f_planDeliverDate,
            @RequestParam(name = "t_planDeliverDate", required = false) String t_planDeliverDate,
            @RequestParam(name = "f_slipNo", required = false) String f_slipNo,
            @RequestParam(name = "t_slipNo", required = false) String t_slipNo,
            @RequestParam(name = "f_customerId", required = false) Integer f_customerId,
            @RequestParam(name = "t_customerId", required = false) Integer t_customerId,
            @RequestParam(name = "customerName", required = false) String customerName,
            @RequestParam(name = "f_deliverDestId", required = false) Integer f_deliverDestId,
            @RequestParam(name = "t_deliverDestId", required = false) Integer t_deliverDestId,
            @RequestParam(name = "deliveryDestName", required = false) String deliveryDestName,
            @RequestParam(name = "f_supplierId", required = false) Integer f_supplierId,
            @RequestParam(name = "t_supplierId", required = false) Integer t_supplierId,
            @RequestParam(name = "supplierName", required = false) String supplierName,
            @RequestParam(name = "f_productId", required = false) Integer f_productId,
            @RequestParam(name = "t_productId", required = false) Integer t_productId,
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "f_repositoryId", required = false) String f_repositoryId,
            @RequestParam(name = "t_repositoryId", required = false) String t_repositoryId,
            @RequestParam(name = "batchNo", required = false) String batchNo,
            @RequestParam(name = "deliveryType", required = false, defaultValue = "1") Integer deliveryType,
            @RequestParam(name = "deliveryStatus", required = false, defaultValue = "1") String deliveryStatus,
            @RequestParam(name = "is_closed", required = false, defaultValue = "") String is_closed,
            @PageableDefault(size = PageableConstrants.DEFAULT_SIZE,
                    page = PageableConstrants.DEFAULT_PAGE)
            Pageable pageable, Integer page, Integer size){

        Page<InventoryOutputListDTO> inventoryOutputListPage =
                inventoryOutputService.searchInventoryInformation(f_orderDate,t_orderDate, f_planOutputDate,
                        t_planOutputDate, f_planWorkingDate, t_planWorkingDate, f_planDeliverDate,
                        t_planDeliverDate, f_slipNo, t_slipNo, f_customerId, t_customerId, customerName,
                        f_deliverDestId, t_deliverDestId, deliveryDestName, f_supplierId, t_supplierId,
                        supplierName,f_productId, t_productId, productName, f_repositoryId, t_repositoryId,
                        batchNo, deliveryType, deliveryStatus, is_closed, pageable);

        HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), inventoryOutputListPage);
        return new ResponseEntity<>(PaginationUtils.generatePage(inventoryOutputListPage), headers, HttpStatus.OK);
    }

//    @GetMapping(value = "search", produces = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<Paging<InventoryOutputListDTO>> searchInformationInventoryOutput(
//            String f_orderDate,
//            String t_orderDate,
//            String f_planOutputDate,
//            String t_planOutputDate,
//            String f_planWorkingDate,
//            String t_planWorkingDate,
//            String f_planDeliverDate,
//            String t_planDeliverDate,
//            String f_slipNo,
//            String t_slipNo,
//            Integer f_customerId,
//            Integer t_customerId,
//            String customerName,
//            Integer f_deliverDestId,
//            Integer t_deliverDestId,
//            String deliveryDestName,
//            Integer f_supplierId,
//            Integer t_supplierId,
//            String supplierName,
//            Integer f_productId,
//            Integer t_productId,
//            String productName,
//            String f_repositoryId,
//            String t_repositoryId,
//            String batchNo,
//            @RequestParam(name = "deliveryType", required = false, defaultValue = "1") Integer deliveryType,
//            @RequestParam(name = "deliveryStatus", required = false, defaultValue = "1") String deliveryStatus,
//            @RequestParam(name = "is_closed", required = false, defaultValue = "1") String is_closed,
//            @PageableDefault(size = PageableConstrants.DEFAULT_SIZE,
//                    page = PageableConstrants.DEFAULT_PAGE)
//            Pageable pageable, Integer page, Integer size){
//
//        Page<InventoryOutputListDTO> inventoryOutputListPage =
//                inventoryOutputService.searchInventoryInformation(f_orderDate,t_orderDate, f_planOutputDate,
//                        t_planOutputDate, f_planWorkingDate, t_planWorkingDate, f_planDeliverDate,
//                        t_planDeliverDate, f_slipNo, t_slipNo, f_customerId, t_customerId, customerName,
//                        f_deliverDestId, t_deliverDestId, deliveryDestName, f_supplierId, t_supplierId,
//                        supplierName,f_productId, t_productId, productName, f_repositoryId, t_repositoryId,
//                        batchNo, deliveryType, deliveryStatus, is_closed, pageable);
//
//        HttpHeaders headers = PaginationUtils
//                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), inventoryOutputListPage);
//        return new ResponseEntity<>(PaginationUtils.generatePage(inventoryOutputListPage), headers, HttpStatus.OK);
//    }
}
