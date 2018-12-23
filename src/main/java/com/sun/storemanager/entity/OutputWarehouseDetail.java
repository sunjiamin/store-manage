package com.sun.storemanager.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sunjiamin
 */
@Data
@Entity
@Table(name = "t_output_warehouse_detail")
@TableName("t_output_warehouse_detail")
public class OutputWarehouseDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String outputWarehouseId;

    @Transient
    @TableField(exist=false)
    private String  productId;

    @OneToOne
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Transient
    @TableField(exist=false)
    private String productName;

    @Transient
    @TableField(exist=false)
    private String productCode;

    @Transient
    @TableField(exist=false)
    private String productSpec;



    private Integer num;

    private String unit;

    @ApiModelProperty(value = "出库单价,销售价")
    private String price;

    private String amount;

    private String warehouseId;


    @Transient
    @TableField(exist=false)
    private String  salePersonId;

    @OneToOne
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name = "sale_person_id")
    private SalePerson salePerson;

    @Transient
    @TableField(exist=false)
    private String  salePersonName;


    private String remark;

    @ApiModelProperty(value = "收款状态 1收款完成  2收款支付  3未收款")
    private Integer payStatus;

    @Transient
    @TableField(exist=false)
    private String payStatusStr;

    @ApiModelProperty(value = "未收款金额")
    private String nopayAmount;



    @Transient
    @TableField(exist=false)
    private String  customerId;

    @OneToOne
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Transient
    @TableField(exist=false)
    private String  customerName;

    @ApiModelProperty(value = "附加费")
    private String surcharge;

    @ApiModelProperty(value = "所有总费用")
    private String totalAmount;

    @ApiModelProperty(value = "出库时的进价，成本价")
    private String costPrice;

    @ApiModelProperty(value = "当前利润，（销售价-成本价）x 出库数量 + 附加费")
    private String profit;

    public OutputWarehouseDetail(){

    }

    public OutputWarehouseDetail(String id, String outputWarehouseId,
                                 String productId, Integer num, String unit, String price,
                                 String costPrice, String amount, Integer payStatus,
                                 String surcharge, String totalAmount, String nopayAmount,
                                 String profit, String salePersonId, String customerId,
                                 String warehouseId, String remark, Integer delFlag, String createBy,
                                 Date createTime, String updateBy, Date updateTime, String customerName,
                                 String productCode, String productSpec, String salePersonName) {
        this.id = id;
        this.outputWarehouseId = outputWarehouseId;
        this.productId = productId;
        this.num = num;
        this.unit = unit;
        this.price = price;
        this.costPrice = costPrice;
        this.amount = amount;
        this.payStatus = payStatus;
        this.surcharge = surcharge;
        this.totalAmount = totalAmount;
        this.nopayAmount = nopayAmount;
        this.profit = profit;
        this.salePersonId = salePersonId;
        this.customerId = customerId;
        this.warehouseId = warehouseId;
        this.remark = remark;
        this.delFlag = delFlag;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.customerName = customerName;
        this.productCode = productCode;
        this.productSpec = productSpec;
        this.salePersonName = salePersonName;
    }

}