package com.sun.storemanager.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * @author sunjiamin
 */
@Data
@Entity
@Table(name = "t_product_stock")
@TableName("t_product_stock")
public class ProductStock extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String productId;

    private String productName;

    private Integer productStock;

    private Integer stockThreshold;

    private String remark;


//    @OneToOne
//    @JoinColumn(name = "product_id")


    @Transient
    @TableField(exist=false)
    private Product product;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "配件编码")
    private String productCode;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "配件规格")
    private String productSpec;


    /**
     * 库存数量开始 供搜索使用
     */
    @Transient
    @TableField(exist=false)
    private String stockStart;

    /**
     * 库存数量结束 供搜索使用
     */
    @Transient
    @TableField(exist=false)
    private String stockEnd;

    @Transient
    @TableField(exist=false)
    private String stockStatus;


}