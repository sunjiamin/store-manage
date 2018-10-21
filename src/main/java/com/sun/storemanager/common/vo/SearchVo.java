package com.sun.storemanager.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sunjiamin
 */
@Data
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;


    private String groupType;

}
