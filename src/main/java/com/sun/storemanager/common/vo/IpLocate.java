package com.sun.storemanager.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author sunjiamin
 */
@Data
public class IpLocate implements Serializable {

    private String retCode;

    private City result;
}

