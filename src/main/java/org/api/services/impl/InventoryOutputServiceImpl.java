package org.api.services.impl;

import org.api.dto.inventory_output.reponse.search.InventoryOutputListDTO;
import org.api.repository.inventory_output.InventoryOutputRepository;
import org.api.services.InventoryOutputService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryOutputServiceImpl implements InventoryOutputService {

    private final InventoryOutputRepository inventoryOutputRepository;

    public InventoryOutputServiceImpl(InventoryOutputRepository inventoryOutputRepository) {
        this.inventoryOutputRepository = inventoryOutputRepository;
    }

    @Override
    public Page<InventoryOutputListDTO> getAllInventoryInfor(Pageable pageRequest) {

        Page<InventoryOutputListDTO> listResult = inventoryOutputRepository.findAllInventoryOutputInfor(pageRequest);
        return new PageImpl<>(listResult.getContent(), listResult.getPageable(), listResult.getTotalElements());
    }

    @Override
    public Page<InventoryOutputListDTO> searchInventoryInformation(String f_orderDate, String t_orderDate, String f_planOutputDate,
                                                                   String t_planOutputDate, String f_planWorkingDate,
                                                                   String t_planWorkingDate, String f_planDeliverDate,
                                                                   String t_planDeliverDate, String f_slipNo, String t_slipNo,
                                                                   Integer f_customerId, Integer t_customerId, String customerName,
                                                                   Integer f_deliverDestId, Integer t_deliverDestId,
                                                                   String deliveryDestName, Integer f_supplierId,
                                                                   Integer t_supplierId, String supplierName,
                                                                   Integer f_productId, Integer t_productId, String productName,
                                                                   Integer f_repositoryId, Integer t_repositoryId,
                                                                   String batchNo, Integer deliveryType, String deliveryStatus,
                                                                   String is_closed, Pageable pageable) {

        Page<InventoryOutputListDTO> searchResult = inventoryOutputRepository
                .findInventoryOutputInfoByCondition(f_orderDate,t_orderDate, f_planOutputDate, t_planOutputDate, f_planWorkingDate,
                                                    t_planWorkingDate, f_planDeliverDate, t_planDeliverDate, f_slipNo, t_slipNo,
                                                    f_customerId, t_customerId, customerName, f_deliverDestId,
                                                    t_deliverDestId, deliveryDestName,f_supplierId, t_supplierId,
                                                    supplierName, f_productId, t_productId, productName, f_repositoryId,
                                                    t_repositoryId, batchNo, deliveryType, deliveryStatus, is_closed, pageable);

        return new PageImpl<>(searchResult.getContent(), searchResult.getPageable(), searchResult.getTotalElements());
    }
}
