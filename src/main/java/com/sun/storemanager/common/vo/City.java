package com.sun.storemanager.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sunjiamin
 */
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
