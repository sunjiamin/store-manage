package com.sun.storemanager.dao.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.sun.storemanager.entity.OutputWarehouseDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface OutputWarehouseDetailMapper {


//    int insertSelective(OutputWarehouseDetail record);
//
//    @Select({
//        "select",
//        "id, output_warehouse_id, product_id, num, unit, price, cost_price, amount, pay_status, ",
//        "surcharge, total_amount, nopay_amount, profit, sale_person_id, customer_id, ",
//        "warehouse_id, remark, del_flag, create_by, create_time, update_by, update_time  ",
//        "from t_output_warehouse_detail",
//        "where id = #{id,jdbcType=VARCHAR}"
//    })
//    @ResultMap("BaseResultMap")
//    OutputWarehouseDetail selectByPrimaryKey(String id);


    List<OutputWarehouseDetail> selectByCond(Page<OutputWarehouseDetail> p, Map<String, Object> paramMap);

   // int updateByPrimaryKeySelective(OutputWarehouseDetail record);


}