package org.api.repository.inventory_output;

import org.api.bean.jpa.InventoryOutputEntity;
import org.api.dto.inventory_output.reponse.search.InventoryOutputListDTO;
import org.api.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryOutputRepository extends BaseRepository<InventoryOutputEntity, Integer> {

    @Query(value = "SELECT new org.api.dto.inventory_output.reponse.search.InventoryOutputListDTO( a.inventory_output_id, " +
            "a.is_closed, a.output_status, a.slip_no, a.plan_output_date, a.batch_status, a.order_date, a.plan_output_date, " +
            "a.actual_output_date, a.plan_working_date, a.actual_output_date, a.plan_deliver_date, a.actual_deliver_date, " +
            "b.destinationCode, b.departmentName, c.destinationCode, c.departmentName, d.customerCode, d.customerName, " +
            "e.customerCode, e.customerName, f.repository_code, f.repository_name, g.repository_code, g.repository_name, " +
            "a.plan_supplier_slip_no, a.actual_supplier_slip_no, a.sum_plan_quantity, a.sum_actual_quantity) " +
            "FROM InventoryOutputEntity a " +
            "INNER JOIN CustomerDeliveryDestEntity b ON a.plan_customer_delivery_destination_id = b.deliveryDestinationId " +
            "INNER JOIN  SupplierDeliveryDestEntity c ON a.actual_customer_delivery_destination_id = c.deliveryDestination_id " +
            "INNER JOIN CustomerEntity d ON a.plan_customer_id = d.customerId " +
            "INNER JOIN CustomerEntity e ON a.actual_customer_id = e.customerId " +
            "INNER JOIN RepositoryEntity f ON a.plan_repository_id = f.repository_id " +
            "INNER JOIN RepositoryEntity g ON a.actual_repository_id = g.repository_id ")
    Page<InventoryOutputListDTO> findAllInventoryOutputInfor(Pageable pageable);

    @Query("SELECT new org.api.dto.inventory_output.reponse.search.InventoryOutputListDTO(a.inventory_output_id, " +
            "a.is_closed, a.output_status, a.slip_no, a.plan_output_date, a.batch_status, a.order_date, a.plan_output_date, " +
            "a.actual_output_date, a.plan_working_date, a.actual_output_date, a.plan_deliver_date, a.actual_deliver_date, " +
            "b.destinationCode, b.departmentName, c.destinationCode, c.departmentName, d.customerCode, d.customerName, " +
            "e.customerCode, e.customerName, f.repository_code, f.repository_name, g.repository_code, g.repository_name, " +
            "a.plan_supplier_slip_no, a.actual_supplier_slip_no, a.sum_plan_quantity, a.sum_actual_quantity) " +
            "FROM InventoryOutputEntity a " +
            "LEFT JOIN CustomerDeliveryDestEntity b ON a.plan_customer_delivery_destination_id = b.deliveryDestinationId " +
            "LEFT JOIN  SupplierDeliveryDestEntity c ON a.actual_customer_delivery_destination_id = c.deliveryDestination_id " +
            "LEFT JOIN CustomerEntity d ON a.plan_customer_id = d.customerId " +
            "LEFT JOIN CustomerEntity e ON a.actual_customer_id = e.customerId " +
            "LEFT JOIN RepositoryEntity f ON a.plan_repository_id = f.repository_id " +
            "LEFT JOIN RepositoryEntity g ON a.actual_repository_id = g.repository_id"
            + " Where a.delFlg = '0' "+
            "AND (:fromOrderDate IS NULL OR a.order_date >= :fromOrderDate) " +
                                            "AND (:toOrderDate IS NULL OR a.order_date <= :toOrderDate) "+
            "AND (:fromPlanOutputDate IS NULL OR a.plan_output_date >= :fromPlanOutputDate) " +
                                            "AND (:toPlanOutputDate IS NULL OR a.plan_output_date <= :toPlanOutputDate ) "+
            "AND (:fromPlanWorkingDate IS NULL OR a.plan_working_date >= :fromPlanWorkingDate) " +
                                            "AND (:toPlanWorkingDate IS NULL OR a.plan_working_date <= :toPlanWorkingDate)"+
            "AND (:fromPlanDeliverDate IS NULL OR a.plan_deliver_date >= :fromPlanDeliverDate) " +
                                            "AND (:toPlanDeliverDate IS NULL OR a.plan_deliver_date <= :toPlanDeliverDate)"+
            "AND (:fromSlipNo IS NULL OR a.slip_no >= :fromSlipNo) AND (:toSlipNo IS NULL OR a.slip_no <= :toSlipNo) "+
            "AND (:fromCustomerId IS NULL OR (a.plan_customer_id >= :fromCustomerId OR a.actual_customer_id >= :fromCustomerId)) " +
                                            "AND (:toCustomerId IS NULL OR (a.plan_customer_id <= :toCustomerId OR a.actual_customer_id <= :toCustomerId))"+
            "AND (:customerName IS NULL OR (a.plan_customer_id IN " +
                                                                "(SELECT sd1.customerId FROM CustomerEntity sd1 " +
                                                                "WHERE sd1.customerName LIKE CONCAT('%', :customerName, '%')) "+
                                            "OR a.actual_customer_id IN " +
                                                                "(SELECT sd3.customerId FROM CustomerEntity sd3 " +
                                                                "WHERE sd3.customerName LIKE CONCAT('%', :customerName, '%'))))"+
            "AND (:fromDeliverDestId IS NULL OR (a.plan_customer_delivery_destination_id >= :fromDeliverDestId " +
                                                "OR a.actual_customer_delivery_destination_id >= :fromDeliverDestId)) " +
                                            "AND (:toDeliverDestId IS NUll OR (a.plan_customer_delivery_destination_id <= :toDeliverDestId " +
                                                "OR a.actual_customer_delivery_destination_id <= :toDeliverDestId)) "+
            "AND (:deliveryDestName IS NULL OR (a.plan_customer_delivery_destination_id IN " +
                                                                    "(SELECT cd1.deliveryDestinationId " +
                                                                    "FROM CustomerDeliveryDestEntity cd1 " +
                                                                    "WHERE cd1.departmentName " +
                                                                    "LIKE CONCAT('%', :deliveryDestName, '%')) " +
                                            "OR a.actual_customer_delivery_destination_id IN " +
                                                                    "(SELECT cd3.deliveryDestinationId " +
                                                                    "FROM CustomerDeliveryDestEntity cd3 " +
                                                                    "WHERE cd3.departmentName " +
                                                                    "LIKE CONCAT('%', :deliveryDestName, '%')))) "+
            "AND (:fromSupplierId IS NULL OR (a.inventory_output_id IN " +
                                                                "(SELECT actual_output_detail.inventory_output_id " +
                                                                    "FROM InventoryActualOutputDetailEntity actual_output_detail " +
                                                                    "WHERE actual_output_detail.supplier_id >= :fromSupplierId " +
                                                                    "GROUP BY actual_output_detail.inventory_output_id) " +
                                            "OR a.inventory_output_id IN " +
                                                                    "(SELECT plan_output_detail.inventory_output_id " +
                                                                        "FROM InventoryPlanOutputDetailEntity plan_output_detail " +
                                                                        "WHERE plan_output_detail.supplier_id >= :fromSupplierId " +
                                                                        "GROUP BY plan_output_detail.inventory_output_id))) "+
            "AND (:toSupplierId IS NULL OR (a.inventory_output_id IN " +
                                                                "(SELECT actual_output_detail1.inventory_output_id " +
                                                                    "FROM InventoryActualOutputDetailEntity actual_output_detail1 " +
                                                                    "WHERE actual_output_detail1.supplier_id <= :toSupplierId " +
                                                                    "GROUP BY actual_output_detail1.inventory_output_id) " +
                                            "OR a.inventory_output_id IN " +
                                                                    "(SELECT plan_output_detail1.inventory_output_id " +
                                                                        "FROM InventoryPlanOutputDetailEntity plan_output_detail1 " +
                                                                        "WHERE plan_output_detail1.supplier_id <= :toSupplierId " +
                                                                        "GROUP BY plan_output_detail1.inventory_output_id))) "+
            "AND (:supplierName IS NULL OR (a.inventory_output_id IN " +
                                                                "(SELECT actual_output_detail2.inventory_output_id " +
                                                                    "FROM SupplierEntity supllier_a " +
                                                                    "JOIN InventoryActualOutputDetailEntity actual_output_detail2 " +
                                                                        "ON supllier_a.supplierId = actual_output_detail2.supplier_id " +
                                                                    "WHERE supllier_a.supplierId IN " +
                                                                                "(SELECT sb1.supplierId FROM SupplierEntity sb1 " +
                                                                                    "WHERE sb1.supplierName " +
                                                                                    "LIKE CONCAT('%', :supplierName, '%')) " +
                                                                "GROUP BY actual_output_detail2.inventory_output_id) " +
                                            "OR a.inventory_output_id IN " +
                                                                "(SELECT plan_output_detail2.inventory_output_id " +
                                                                    "FROM SupplierEntity supllier_b " +
                                                                    "JOIN InventoryPlanOutputDetailEntity plan_output_detail2 " +
                                                                        "ON supllier_b.supplierId = plan_output_detail2.supplier_id " +
                                                                    "WHERE supllier_b.supplierId IN " +
                                                                                "(SELECT sb3.supplierId FROM SupplierEntity sb3 " +
                                                                                    "WHERE sb3.supplierName " +
                                                                                    "LIKE CONCAT('%', :supplierName, '%')) " +
                                                                "GROUP BY plan_output_detail2.inventory_output_id))) "+
            "AND ((:fromProductId IS NULL OR (a.inventory_output_id IN " +
                                                                "(SELECT actual_output_detail.inventory_output_id " +
                                                                    "FROM InventoryActualOutputDetailEntity actual_output_detail " +
                                                                    "WHERE actual_output_detail.product_id >= :fromProductId " +
                                                                    "GROUP BY actual_output_detail.inventory_output_id) " +
                                                            "OR a.inventory_output_id IN " +
                                                                                "(SELECT plan_output_detail.inventory_output_id " +
                                                                                    "FROM InventoryPlanOutputDetailEntity plan_output_detail " +
                                                                                    "WHERE plan_output_detail.product_id >= :fromProductId " +
                                                                                    "GROUP BY plan_output_detail.inventory_output_id))) " +
                                            "AND :toProductId IS NULL OR (a.inventory_output_id IN "+
                                                                        "(SELECT actual_output_detail.inventory_output_id " +
                                                                            "FROM InventoryActualOutputDetailEntity actual_output_detail " +
                                                                            "WHERE actual_output_detail.product_id <= :toProductId " +
                                                                            "GROUP BY actual_output_detail.inventory_output_id) " +
                                                                        "OR a.inventory_output_id IN " +
                                                                                "(SELECT plan_output_detail.inventory_output_id " +
                                                                                "FROM InventoryPlanOutputDetailEntity plan_output_detail " +
                                                                                "WHERE plan_output_detail.product_id <= :toProductId " +
                                                                                "GROUP BY plan_output_detail.inventory_output_id))) "+
            "AND ((:productName IS NULL OR (a.inventory_output_id IN " +
                                                            "(SELECT actual_output_detail.inventory_output_id " +
                                                            "FROM ProductEntity product " +
                                                            "JOIN InventoryActualOutputDetailEntity actual_output_detail " +
                                                                "ON product.productId = actual_output_detail.product_id " +
                                                            "WHERE product.productId IN " +
                                                                "(SELECT p.productId FROM ProductEntity p " +
                                                                    "WHERE p.name1 LIKE CONCAT('%', :productName, '%') " +
                                                                    "OR p.name2 LIKE CONCAT('%', :productName, '%') " +
                                                                    "OR p.name3 LIKE CONCAT('%', :productName, '%') " +
                                                                    "OR p.name4 LIKE CONCAT('%', :productName, '%') " +
                                                                    "OR p.name5 LIKE CONCAT('%', :productName, '%'))" +
                                                            "GROUP BY actual_output_detail.inventory_output_id)) " +
                                            "OR a.inventory_output_id IN " +
                                                            "(SELECT plan_output_detail.inventory_output_id " +
                                                            "FROM ProductEntity product " +
                                                            "JOIN InventoryPlanOutputDetailEntity plan_output_detail " +
                                                                "ON product.productId = plan_output_detail.product_id " +
                                                            "WHERE product.productId IN " +
                                                                "(SELECT p.productId FROM ProductEntity p " +
                                                                    "WHERE p.name1 LIKE CONCAT('%', :productName, '%') " +
                                                                    "OR p.name2 LIKE CONCAT('%', :productName, '%') " +
                                                                    "OR p.name3 LIKE CONCAT('%', :productName, '%') " +
                                                                    "OR p.name4 LIKE CONCAT('%', :productName, '%') " +
                                                                    "OR p.name5 LIKE CONCAT('%', :productName, '%')) " +
                                                            "GROUP BY plan_output_detail.inventory_output_id))) "+
            "AND ((:fromRepositoryId IS NULL OR (a.plan_repository_id >= :fromRepositoryId OR a.actual_repository_id >= :fromRepositoryId)) " +
                                             "AND (:toRepositoryId IS NULL OR (a.plan_repository_id <= :toRepositoryId OR a.actual_repository_id <= :toRepositoryId))) "+
            "AND ((:batchNo IS NULL OR (a.inventory_output_id IN " +
                                                            "(SELECT actual_output_detail.inventory_output_id " +
                                                                "FROM InventoryActualOutputDetailEntity actual_output_detail " +
                                                                "WHERE actual_output_detail.batch_no " +
                                                                "LIKE CONCAT('%', :batchNo, '%') " +
                                                            "GROUP BY actual_output_detail.inventory_output_id)) " +
                                            "OR a.inventory_output_id IN " +
                                                            "(SELECT plan_output_detail.inventory_output_id " +
                                                                "FROM InventoryPlanOutputDetailEntity plan_output_detail " +
                                                                "WHERE plan_output_detail.batch_no " +
                                                                "LIKE CONCAT('%', :batchNo, '%') " +
                                                                "GROUP BY plan_output_detail.inventory_output_id))) "+
            "AND ((:deliveryType = 2 AND a.plan_output_date IS NOT NULL) OR (:deliveryType = 3 AND a.plan_output_date IS NULL) OR :deliveryType = 1 ) "+
            "AND ((:deliveryStatus = '2' AND a.sum_actual_quantity = 0) " +
                                                "OR (:deliveryStatus = '3' AND a.sum_actual_quantity <  a.sum_plan_quantity AND a.sum_actual_quantity > 0) " +
                                                "OR (:deliveryStatus = '4' AND a.sum_actual_quantity >= a.sum_plan_quantity) OR :deliveryStatus = 1) "+
            "AND (:isClosed = '' OR a.is_closed = :isClosed) " +
            "ORDER BY a.slip_no ASC"
    )
    Page<InventoryOutputListDTO> findInventoryOutputInfoByCondition(
                                                                    @Param("fromOrderDate") String fromOrderDate,
                                                                    @Param("toOrderDate") String toOrderDate,
                                                                    @Param("fromPlanOutputDate") String fromPlanOutputDate,
                                                                    @Param("toPlanOutputDate") String toPlanOutputDate,
                                                                    @Param("fromPlanWorkingDate") String fromPlanWorkingDate,
                                                                    @Param("toPlanWorkingDate") String toPlanWorkingDate,
                                                                    @Param("fromPlanDeliverDate") String fromPlanDeliverDate,
                                                                    @Param("toPlanDeliverDate") String toPlanDeliverDate,
                                                                    @Param("fromSlipNo") String fromSlipNo,
                                                                    @Param("toSlipNo") String toSlipNo,
                                                                    @Param("fromCustomerId") Integer fromCustomerId,
                                                                    @Param("toCustomerId") Integer toCustomerId,
                                                                    @Param("customerName") String customerName,
                                                                    @Param("fromDeliverDestId") Integer fromDeliverDestId,
                                                                    @Param("toDeliverDestId") Integer toDeliverDestId,
                                                                    @Param("deliveryDestName") String deliveryDestName,
                                                                    @Param("fromSupplierId") Integer fromSupplierId,
                                                                    @Param("toSupplierId") Integer toSupplierId,
                                                                    @Param("supplierName") String supplierName,
                                                                    @Param("fromProductId") Integer fromProductId,
                                                                    @Param("toProductId") Integer toProductId,
                                                                    @Param("productName") String productName,
                                                                    @Param("fromRepositoryId") Integer fromRepositoryId,
                                                                    @Param("toRepositoryId") Integer toRepositoryId,
                                                                    @Param("batchNo") String batchNo,
                                                                    @Param("deliveryType") Integer deliveryType,
                                                                    @Param("deliveryStatus") String deliveryStatus,
                                                                    @Param("isClosed") String isClosed,
                                                                    Pageable pageable);
}
