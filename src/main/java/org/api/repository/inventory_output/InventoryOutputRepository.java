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
            "INNER JOIN CustomerDeliveryDestEntity b ON a.plan_customer_delivery_destination_id = b.deliveryDestination_id " +
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
            "LEFT JOIN CustomerDeliveryDestEntity b ON a.plan_customer_delivery_destination_id = b.deliveryDestination_id " +
            "LEFT JOIN  SupplierDeliveryDestEntity c ON a.actual_customer_delivery_destination_id = c.deliveryDestination_id " +
            "LEFT JOIN CustomerEntity d ON a.plan_customer_id = d.customerId " +
            "LEFT JOIN CustomerEntity e ON a.actual_customer_id = e.customerId " +
            "LEFT JOIN RepositoryEntity f ON a.plan_repository_id = f.repository_id " +
            "LEFT JOIN RepositoryEntity g ON a.actual_repository_id = g.repository_id"
            + " Where a.delFlg = '0' "+
            "AND (:f_orderDate IS NULL OR a.order_date >= :f_orderDate) " +
                                            "AND (:t_orderDate IS NULL OR a.order_date <= :t_orderDate) "+
            "AND (:f_planOutputDate IS NULL OR a.plan_output_date >= :f_planOutputDate) " +
                                            "AND (:t_planOutputDate IS NULL OR a.plan_output_date <= :t_planOutputDate ) "+
            "AND (:f_planWorkingDate IS NULL OR a.plan_working_date >= :f_planWorkingDate) " +
                                            "AND (:t_planWorkingDate IS NULL OR a.plan_working_date <= :t_planWorkingDate)"+
            "AND (:f_planDeliverDate IS NULL OR a.plan_deliver_date >= :f_planDeliverDate) " +
                                            "AND (:t_planDeliverDate IS NULL OR a.plan_deliver_date <= :t_planDeliverDate)"+
            "AND (:f_slipNo IS NULL OR a.slip_no >= :f_slipNo) AND (:t_slipNo IS NULL OR a.slip_no <= :t_slipNo) "+
            "AND (:f_customerId IS NULL OR (a.plan_customer_id >= :f_customerId OR a.actual_customer_id >= :f_customerId)) " +
                                            "AND (:t_customerId IS NULL OR (a.plan_customer_id <= :t_customerId OR a.actual_customer_id <= :t_customerId))"+
            "AND (:customerName IS NULL OR (a.plan_customer_id IN " +
                                                                "(SELECT sd1.customerId FROM CustomerEntity sd1 " +
                                                                "WHERE sd1.customerName LIKE CONCAT('%', :customerName, '%')) "+
                                            "OR a.actual_customer_id IN " +
                                                                "(SELECT sd3.customerId FROM CustomerEntity sd3 " +
                                                                "WHERE sd3.customerName LIKE CONCAT('%', :customerName, '%'))))"+
            "AND (:f_deliverDestId IS NULL OR (a.plan_customer_delivery_destination_id >= :f_deliverDestId " +
                                                "OR a.actual_customer_delivery_destination_id >= :f_deliverDestId)) " +
                                            "AND (:t_deliverDestId IS NUll OR (a.plan_customer_delivery_destination_id <= :t_deliverDestId " +
                                                "OR a.actual_customer_delivery_destination_id <= :t_deliverDestId)) "+
            "AND (:deliveryDestName IS NULL OR (a.plan_customer_delivery_destination_id IN " +
                                                                    "(SELECT cd1.deliveryDestination_id " +
                                                                    "FROM CustomerDeliveryDestEntity cd1 " +
                                                                    "WHERE cd1.departmentName " +
                                                                    "LIKE CONCAT('%', :deliveryDestName, '%')) " +
                                            "OR a.actual_customer_delivery_destination_id IN " +
                                                                    "(SELECT cd3.deliveryDestination_id " +
                                                                    "FROM CustomerDeliveryDestEntity cd3 " +
                                                                    "WHERE cd3.departmentName " +
                                                                    "LIKE CONCAT('%', :deliveryDestName, '%')))) "+
            "AND (:f_supplierId IS NULL OR (a.inventory_output_id IN " +
                                                                "(SELECT actual_output_detail.inventory_output_id " +
                                                                    "FROM InventoryActualOutputDetailEntity actual_output_detail " +
                                                                    "WHERE actual_output_detail.supplier_id >= :f_supplierId " +
                                                                    "GROUP BY actual_output_detail.inventory_output_id) " +
                                            "OR a.inventory_output_id IN " +
                                                                    "(SELECT plan_output_detail.inventory_output_id " +
                                                                        "FROM InventoryPlanOutputDetailEntity plan_output_detail " +
                                                                        "WHERE plan_output_detail.supplier_id >= :f_supplierId " +
                                                                        "GROUP BY plan_output_detail.inventory_output_id))) "+
            "AND (:t_supplierId IS NULL OR (a.inventory_output_id IN " +
                                                                "(SELECT actual_output_detail1.inventory_output_id " +
                                                                    "FROM InventoryActualOutputDetailEntity actual_output_detail1 " +
                                                                    "WHERE actual_output_detail1.supplier_id <= :t_supplierId " +
                                                                    "GROUP BY actual_output_detail1.inventory_output_id) " +
                                            "OR a.inventory_output_id IN " +
                                                                    "(SELECT plan_output_detail1.inventory_output_id " +
                                                                        "FROM InventoryPlanOutputDetailEntity plan_output_detail1 " +
                                                                        "WHERE plan_output_detail1.supplier_id <= :t_supplierId " +
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
            "AND ((:f_productId IS NULL OR (a.inventory_output_id IN " +
                                                                "(SELECT actual_output_detail.inventory_output_id " +
                                                                    "FROM InventoryActualOutputDetailEntity actual_output_detail " +
                                                                    "WHERE actual_output_detail.product_id >= :f_productId " +
                                                                    "GROUP BY actual_output_detail.inventory_output_id) " +
                                                            "OR a.inventory_output_id IN " +
                                                                                "(SELECT plan_output_detail.inventory_output_id " +
                                                                                    "FROM InventoryPlanOutputDetailEntity plan_output_detail " +
                                                                                    "WHERE plan_output_detail.product_id >= :f_productId " +
                                                                                    "GROUP BY plan_output_detail.inventory_output_id))) " +
                                            "AND :t_productId IS NULL OR (a.inventory_output_id IN "+
                                                                        "(SELECT actual_output_detail.inventory_output_id " +
                                                                            "FROM InventoryActualOutputDetailEntity actual_output_detail " +
                                                                            "WHERE actual_output_detail.product_id <= :t_productId " +
                                                                            "GROUP BY actual_output_detail.inventory_output_id) " +
                                                                        "OR a.inventory_output_id IN " +
                                                                                "(SELECT plan_output_detail.inventory_output_id " +
                                                                                "FROM InventoryPlanOutputDetailEntity plan_output_detail " +
                                                                                "WHERE plan_output_detail.product_id <= :t_productId " +
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
            "AND ((:f_repositoryId IS NULL OR (a.plan_repository_id >= :f_repositoryId OR a.actual_repository_id >= :f_repositoryId)) " +
                                             "AND (:t_repositoryId IS NULL OR (a.plan_repository_id <= :t_repositoryId OR a.actual_repository_id <= :t_repositoryId))) "+
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
            "AND (:is_closed = '' OR a.is_closed = :is_closed) " +
            "ORDER BY a.slip_no ASC"
    )
    Page<InventoryOutputListDTO> findInventoryOutputInfoByCondition(
                                                                    @Param("f_orderDate") String f_orderDate,
                                                                    @Param("t_orderDate") String t_orderDate,
                                                                    @Param("f_planOutputDate") String f_planOutputDate,
                                                                    @Param("t_planOutputDate") String t_planOutputDate,
                                                                    @Param("f_planWorkingDate") String f_planWorkingDate,
                                                                    @Param("t_planWorkingDate") String t_planWorkingDate,
                                                                    @Param("f_planDeliverDate") String f_planDeliverDate,
                                                                    @Param("t_planDeliverDate") String t_planDeliverDate,
                                                                    @Param("f_slipNo") String f_slipNo,
                                                                    @Param("t_slipNo") String t_slipNo,
                                                                    @Param("f_customerId") Integer f_customerId,
                                                                    @Param("t_customerId") Integer t_customerId,
                                                                    @Param("customerName") String customerName,
                                                                    @Param("f_deliverDestId") Integer f_deliverDestId,
                                                                    @Param("t_deliverDestId") Integer t_deliverDestId,
                                                                    @Param("deliveryDestName") String deliveryDestName,
                                                                    @Param("f_supplierId") Integer f_supplierId,
                                                                    @Param("t_supplierId") Integer t_supplierId,
                                                                    @Param("supplierName") String supplierName,
                                                                    @Param("f_productId") Integer f_productId,
                                                                    @Param("t_productId") Integer t_productId,
                                                                    @Param("productName") String productName,
                                                                    @Param("f_repositoryId") String f_repositoryId,
                                                                    @Param("t_repositoryId") String t_repositoryId,
                                                                    @Param("batchNo") String batchNo,
                                                                    @Param("deliveryType") Integer deliveryType,
                                                                    @Param("deliveryStatus") String deliveryStatus,
                                                                    @Param("is_closed") String is_closed,
                                                                    Pageable pageable);
}
