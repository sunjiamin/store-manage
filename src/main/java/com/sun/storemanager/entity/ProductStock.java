package com.sun.storemanager.entity;

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

    //private String productId;

    private String productName;

    private Integer productStock;

    private Integer stockThreshold;

    private String remark;


    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


}