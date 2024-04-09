package org.api.bean.reponse.inventory_output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryOutputListResponse {

    @JsonProperty("")
    private Integer inventoryOutputId;
    private String is_closed;
    private String output_status;
    private String slip_no;
    private String plan_output_date;
    private String batch_status;
    private String order_date;
    private String actual_output_date;
    private String plan_working_date;
    private String plan_deliver_date;
    private String actual_deliver_date;
    private String destinationCode;
    private String departmentName;
//    private String destinationCode;
//    private String departmentName;
    private String customerCode;
    private String customerName;
//    private String customerCode;
//    private String customerName;
    private String repository_code;
    private String repository_name;
//    private String repository_code;
//    private String repository_name;
    private String plan_supplier_slip_no;
    private String actual_supplier_slip_no;
    private Integer sum_plan_quantity;
    private Integer sum_actual_quantity;
}
