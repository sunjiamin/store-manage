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
@Table(name = "t_input_warehouse_detail")
@TableName("t_input_warehouse_detail")
public class InputWarehouseDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private String inputWarehouseId;

    //private String productId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;



    @Transient
    @TableField(exist=false)
    private String productName;

    private Integer num;

    private String unit;

    private String price;

    private String amount;

    private String warehouseId;

    private String remark;


}