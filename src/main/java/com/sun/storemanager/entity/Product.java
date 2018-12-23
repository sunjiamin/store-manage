package com.sun.storemanager.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "t_product")
@TableName("t_product")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "配件名称")
    private String productName;

    @ApiModelProperty(value = "配件编码")
    private String productCode;

    @ApiModelProperty(value = "配件规格")
    private String productSpec;


    @ApiModelProperty(value = "配件进价")
    private String productPrice;

    @ApiModelProperty(value = "配件卖价")
    private String productSalePrice;


    @OneToOne
    @JoinColumn(name = "product_class")
    @NotFound(action= NotFoundAction.IGNORE)
    private ProductClass productClass;

    @Transient
    @TableField(exist=false)
    private String productClassName;

    @Transient
    @TableField(exist=false)
    private String productClassId;

    @ApiModelProperty(value = "供应商")
    @OneToOne
    @JoinColumn(name = "supplier_id")
    @NotFound(action=NotFoundAction.IGNORE)
    private Supplier supplier;

    @Transient
    @TableField(exist=false)
    private String supplierId;

    @Transient
    @TableField(exist=false)
    private String supplierName;

    @ApiModelProperty(value = "品牌")
    private String brandId;


    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "全拼")
    private String quanping;

    @ApiModelProperty(value = "配件图片")
    private String images;

    @ApiModelProperty(value = "备注")
    private String remark;

    public Product(){

    }

//
    public Product(String id, String productName, String productCode, String productSalePrice, String productPrice,
                   String productSpec,   String supplierId, String brandId,   String quanping, String unit,
                   String images, String remark, Integer delFlag, String createBy, Date createTime, String updateBy,
                   Date updateTime) {
        this.id = id;
        this.productName = productName;
        this.productCode = productCode;
        this.productSalePrice = productSalePrice;
        this.productPrice = productPrice;
        this.productSpec = productSpec;
        this.supplierId = supplierId;
        this.brandId = brandId;
        this.quanping = quanping;
        this.unit = unit;
        this.images = images;
        this.remark = remark;
        this.delFlag = delFlag;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }





}