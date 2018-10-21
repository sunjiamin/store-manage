package com.sun.storemanager.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

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
    @JoinColumn(name = "product_id")
    private Product product;


    private Integer num;

    private String unit;

    private String price;

    private String amount;

    private String warehouseId;


    @Transient
    @TableField(exist=false)
    private String  salePersonId;

    @OneToOne
    @JoinColumn(name = "sale_person_id")
    private SalePerson salePerson;

    private String remark;

    @ApiModelProperty(value = "收款状态 1收款完成  2收款支付  3未收款")
    private Integer payStatus;

    @ApiModelProperty(value = "未收款金额")
    private String nopayAmount;



    @Transient
    @TableField(exist=false)
    private String  customerId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ApiModelProperty(value = "附加费")
    private String surcharge;

    @ApiModelProperty(value = "所有总费用")
    private String totalAmount;

}