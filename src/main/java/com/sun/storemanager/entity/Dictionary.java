package com.sun.storemanager.entity;

import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author SunJiamin
 */
@Data
@Entity
@Table(name = "t_dictionary")
@TableName("t_dictionary")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典类型")
    private String type;

    @ApiModelProperty(value = "字典类型名称")
    private String typeName;

    @ApiModelProperty(value = "字典代码")
    private String code;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "备注")
    private String remark;

}