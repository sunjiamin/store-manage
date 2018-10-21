package com.sun.storemanager.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author sunjiamin
 */
@Data
@Entity
@Table(name = "t_output_warehouse")
@TableName("t_output_warehouse")
public class OutputWarehouse extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String outputNo;

    private String warehouseId;

    private String operuserId;

    private String department;

    private String totalAmount;

    private String remark;

    @Transient
    @TableField(exist=false)
    private String outputWarehouseDetailList;

    @Transient
    @TableField(exist=false)
    private List<OutputWarehouseDetail> outputWarehouseDetailListObject;
}