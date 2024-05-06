package org.api.services;

import org.api.dto.inventory_output.reponse.search.InventoryOutputListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryOutputService {
    Page<InventoryOutputListDTO> getAllInventoryInfor(Pageable pageable);
    Page<InventoryOutputListDTO> searchInventoryInformation(String f_orderDate, String t_orderDate, String f_planOutputDate,
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
                                                            String is_closed, Pageable pageable);
}
