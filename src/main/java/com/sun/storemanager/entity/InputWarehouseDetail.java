package com.sun.storemanager.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ApiModelProperty(value = "入库时配件进价配件进价")
    private String price;

    private String amount;

    private String warehouseId;

    private String remark;


}