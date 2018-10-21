package com.sun.storemanager.entity;

import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author sunjiamin
 */
@Data
@Entity
@Table(name = "t_product_class")
@TableName("t_product_class")
public class ProductClass extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String className;

    private String classCode;

    private String remark;



}