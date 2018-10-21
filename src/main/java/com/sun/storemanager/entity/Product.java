package com.sun.storemanager.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author sunjiamin
 */
@Data
@Entity
@Table(name = "t_product")
@TableName("t_product")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "配件名称")
    private String productName;

    @ApiModelProperty(value = "配件编码")
    private String productCode;

    @ApiModelProperty(value = "配件规格")
    private String productSpec;




    @OneToOne
    @JoinColumn(name = "product_class")
    private ProductClass productClass;



    @ApiModelProperty(value = "供应商")
    private String supplierId;

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








}