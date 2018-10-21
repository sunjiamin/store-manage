package com.sun.storemanager.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * description:
 *
 * @author sunjiamin
 * @date 2018-10-18 13:29
 */
@Data
public class CensusRatioVo {

    /**
     * 成本总和
     */
    private BigDecimal costTotal;
    /**
     * 出库金额
     */
    private BigDecimal outputTotal;

    private List<String> titileList  ;

    private List<RatioVo> outPutList;
}
