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
@Table(name = "t_input_warehouse")
@TableName("t_input_warehouse")
public class InputWarehouse extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String inputNo;

    private Long warehouseId;

    private Long operuserId;

    private Long department;

    private Long totalAmount;

    private String remark;

    @Transient
    @TableField(exist=false)
    private String inputWarehouseDetailList;

    @Transient
    @TableField(exist=false)
    private List<InputWarehouseDetail> inputWarehouseDetailListObject;
}