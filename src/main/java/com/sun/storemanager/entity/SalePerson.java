package com.sun.storemanager.entity;

import com.sun.storemanager.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author sunjiamin
 */
@Data
@Entity
@Table(name = "t_sale_person")
@TableName("t_sale_person")
public class SalePerson extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String userName;

    private Integer type;

    private Integer sex;

    private String mobile;

    private String email;

    private String address;

    private String avatar;

    private String remark;


}