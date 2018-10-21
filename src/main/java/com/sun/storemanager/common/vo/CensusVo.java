package com.sun.storemanager.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author sunjiamin
 * @date 2018-10-17 16:18
 */
@Data
public class CensusVo  implements Serializable {

    //成本总和
    private BigDecimal costTotal  ;
    //出库金额
    private BigDecimal outputTotal ;
    //利润总和
    private BigDecimal profitTotal  ;
    //未收款总和
    private BigDecimal noPayTotal  ;

    private List<String> titileList  ;
    private List<String> profitList  ;
    private List<String> costList  ;
    private List<String> noPayList  ;
    private List<String> outPutList  ;

}
