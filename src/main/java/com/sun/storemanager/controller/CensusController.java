package com.sun.storemanager.controller;

import cn.hutool.core.date.DateUtil;
import com.sun.storemanager.common.utils.ResultUtil;
import com.sun.storemanager.common.vo.*;
import com.sun.storemanager.common.utils.DateUtils;
import com.sun.storemanager.entity.InputWarehouseDetail;
import com.sun.storemanager.entity.OutputWarehouseDetail;
import com.sun.storemanager.service.InputWarehouseDetailService;
import com.sun.storemanager.service.OutputWarehouseDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * description:
 *
 * @author sunjiamin
 * @date 2018-10-17 14:04
 */
@Slf4j
@RestController
@Api(description = "客户管理接口")
@RequestMapping("/store/census")
public class CensusController {

    @Autowired
    private InputWarehouseDetailService inputWarehouseDetailService;

    @Autowired
    private OutputWarehouseDetailService outputWarehouseDetailService;


    @RequestMapping(value = "/getRatio",method = RequestMethod.GET)
    @ApiOperation(value = "统计利润分配比例")
    public Result<CensusRatioVo> getRatio( @ModelAttribute SearchVo searchVo){
        CensusRatioVo  censusRatioVo = new CensusRatioVo();
        try {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            end = DateUtils.AddDay(end,1);
            List<InputWarehouseDetail> inputList = inputWarehouseDetailService.findByCreateTimeBetween(start, end);
            List<OutputWarehouseDetail> outputList = outputWarehouseDetailService.findByCreateTimeBetween(start, end);
            //成本总和
            BigDecimal costTotal = new BigDecimal(0);
            //出库金额
            BigDecimal outputTotal = new BigDecimal(0);

            //1.统计成本
            for (InputWarehouseDetail input : inputList) {
                String amount = input.getAmount();
                costTotal = costTotal.add(new BigDecimal(amount));
            }

            List<String> titileList  = new ArrayList<>();
            Map<String,BigDecimal> outPutMap = new HashMap<>();
            List<RatioVo> outPutList = new ArrayList<>();
            //2.统计收益
            for (OutputWarehouseDetail output : outputList) {
                String totalAmount = output.getTotalAmount();
                BigDecimal bigDecimal = new BigDecimal(totalAmount);
                outputTotal =  outputTotal.add(bigDecimal);
                String key = output.getSalePerson().getUserName();
                if(outPutMap.containsKey(key)){
                    BigDecimal s = outPutMap.get(key);
                    s = s.add(bigDecimal);
                    outPutMap.put(key,s);
                }else{
                    titileList.add(key);
                    outPutMap.put(key,bigDecimal);
                }
            }

            for (Map.Entry<String, BigDecimal> stringBigDecimalEntry : outPutMap.entrySet()) {
                RatioVo ratioVo = new RatioVo();
                ratioVo.setKey(stringBigDecimalEntry.getKey());
                ratioVo.setValue(stringBigDecimalEntry.getValue());
                outPutList.add(ratioVo);
            }

            censusRatioVo.setCostTotal(costTotal);
            censusRatioVo.setOutputTotal(outputTotal);
            censusRatioVo.setOutPutList(outPutList);
            censusRatioVo.setTitileList(titileList);

            return new ResultUtil<CensusRatioVo>().setData(censusRatioVo);

        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResultUtil<CensusRatioVo>().setErrorMsg("系统错误");
        }


    }
    @RequestMapping(value = "/getProfit",method = RequestMethod.GET)
    @ApiOperation(value = "统计利润")
    public Result<CensusVo> getByCondition( @ModelAttribute SearchVo searchVo){
        CensusVo censusVo = new CensusVo();
        try {

            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            end = DateUtils.AddDay(end,1);
            //分组类型 1按天分组  2按月分组  3 按年分组
            String groupType = searchVo.getGroupType();

            List<InputWarehouseDetail> inputList = inputWarehouseDetailService.findByCreateTimeBetween(start, end);
            List<OutputWarehouseDetail> outputList = outputWarehouseDetailService.findByCreateTimeBetween(start, end);

            //成本总和
            BigDecimal costTotal = new BigDecimal(0);
            //出库金额
            BigDecimal outputTotal = new BigDecimal(0);
            //利润总和
            BigDecimal profitTotal = new BigDecimal(0);
            //未收款总和
            BigDecimal noPayTotal = new BigDecimal(0);

            //1.统计成本
            for (InputWarehouseDetail input : inputList) {
                String amount = input.getAmount();
                costTotal = costTotal.add(new BigDecimal(amount));
            }
            //2.统计收益
            for (OutputWarehouseDetail output : outputList) {
                String totalAmount = output.getTotalAmount();
                String nopayAmount = output.getNopayAmount();
                noPayTotal =  noPayTotal.add(new BigDecimal(nopayAmount));
                outputTotal =  outputTotal.add(new BigDecimal(totalAmount));
            }

            for (OutputWarehouseDetail output : outputList) {
                String profit = output.getProfit();
                profitTotal =  profitTotal.add(new BigDecimal(profit));
            }


            List<String> titileList = new ArrayList<>();
            List<String> profitList = new ArrayList<>();
            List<String> noPayList = new ArrayList<>();
            List<String> outPutList = new ArrayList<>();
            List<String> costList = new ArrayList<>();

            if(Constants.GROUP_TYPE_DAY.equals(groupType)){
                //1按天分组
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    Date tempStart = DateUtils.AddDay(start,i);
                    if(DateUtils.compareDate(tempStart,end)<=0){
                        titileList.add(DateUtils.formatDate(tempStart));
                    }else{
                        break;
                    }
                }

            }else if(Constants.GROUP_TYPE_MONTH.equals(groupType)){
                //2按月分组
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    Date tempStart = DateUtils.AddMonth(start,i);
                    if(DateUtils.compareDate(tempStart,end)<=0){
                        titileList.add(DateUtils.formatDate(tempStart,DateUtils.DATE_FORMAT_YYYYMM));
                    }else{
                        break;
                    }
                }

            }else{
                //3 按年分组
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    Date tempStart = DateUtils.AddYear(start,i);
                    if(DateUtils.compareDate(tempStart,end)<=0){
                        titileList.add(DateUtils.formatDate(tempStart,DateUtils.DATE_FORMAT_YYYY));
                    }else{
                        break;
                    }
                }
            }

            Map<String,List<InputWarehouseDetail>> inputGroupMap = new HashMap<>();
            Map<String,List<OutputWarehouseDetail>> outputGroupMap = new HashMap<>();

            for (InputWarehouseDetail inputWarehouseDetail : inputList) {
                Date createTime = inputWarehouseDetail.getCreateTime();
                String createStr = getGroupKey(groupType,createTime);

                if(inputGroupMap.containsKey(createStr)){
                    inputGroupMap.get(createStr).add(inputWarehouseDetail);
                }else{
                    List<InputWarehouseDetail> t = new ArrayList();
                    t.add(inputWarehouseDetail);
                    inputGroupMap.put(createStr,t);
                }
            }

            for (OutputWarehouseDetail outputWarehouseDetail : outputList) {
                Date createTime = outputWarehouseDetail.getCreateTime();
                String createStr = getGroupKey(groupType,createTime);
                if(outputGroupMap.containsKey(createStr)){
                    outputGroupMap.get(createStr).add(outputWarehouseDetail);
                }else{
                    List<OutputWarehouseDetail> t = new ArrayList();
                    t.add(outputWarehouseDetail);
                    outputGroupMap.put(createStr,t);
                }
            }


            for (String title : titileList) {
                List<InputWarehouseDetail> inputSubList = inputGroupMap.get(title);
                List<OutputWarehouseDetail> outputWSubList = outputGroupMap.get(title);

                BigDecimal subCost = getSubCost(inputSubList);
                BigDecimal subOutPutAmount = getSubOutPutAmount(outputWSubList);
                BigDecimal subProfit = getSubProfitAmount(outputWSubList);
                BigDecimal subNoPay = getSubNoPay(outputWSubList);

                costList.add(subCost.toString());
                profitList.add(subProfit.toString());
                noPayList.add(subNoPay.toString());
                outPutList.add(subOutPutAmount.toString());

            }

            censusVo.setCostTotal(costTotal);
            censusVo.setNoPayTotal(noPayTotal);
            censusVo.setProfitTotal(profitTotal);
            censusVo.setOutputTotal(outputTotal);

            censusVo.setNoPayList(noPayList);
            censusVo.setProfitList(profitList);
            censusVo.setTitileList(titileList);
            censusVo.setCostList(costList);
            censusVo.setOutPutList(outPutList);


            return new ResultUtil<CensusVo>().setData(censusVo);


        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return new ResultUtil<CensusVo>().setErrorMsg("系统错误");
        }

    }

    /**
     *
     * @param inputWarehouseDetails
     * @return
     */
    private BigDecimal getSubCost(List<InputWarehouseDetail> inputWarehouseDetails){
        BigDecimal costTotal = new BigDecimal(0);

        if(null == inputWarehouseDetails || inputWarehouseDetails.size()<=0){
            return costTotal;
        }
        for (InputWarehouseDetail inputWarehouseDetail : inputWarehouseDetails) {
            String amount = inputWarehouseDetail.getAmount();
            costTotal = costTotal.add(new BigDecimal(amount));
        }

        return costTotal;
    }

    /**
     *
     * @param outputWarehouseDetails
     * @return
     */
    private BigDecimal getSubOutPutAmount(List<OutputWarehouseDetail> outputWarehouseDetails){
        BigDecimal outputTotal = new BigDecimal(0);

        if(null == outputWarehouseDetails || outputWarehouseDetails.size()<=0){
            return outputTotal;
        }
        for (OutputWarehouseDetail output : outputWarehouseDetails) {
            String totalAmount = output.getTotalAmount();
            outputTotal =  outputTotal.add(new BigDecimal(totalAmount));
        }

        return outputTotal;
    }

    private BigDecimal getSubProfitAmount(List<OutputWarehouseDetail> outputWarehouseDetails){
        BigDecimal profitTotal = new BigDecimal(0);

        if(null == outputWarehouseDetails || outputWarehouseDetails.size()<=0){
            return profitTotal;
        }
        for (OutputWarehouseDetail output : outputWarehouseDetails) {
            String profit = output.getProfit();
            profitTotal =  profitTotal.add(new BigDecimal(profit));
        }

        return profitTotal;
    }

    /**
     *
     * @param outputWarehouseDetails
     * @return
     */
    private BigDecimal getSubNoPay(List<OutputWarehouseDetail> outputWarehouseDetails){
        BigDecimal noPayTotal = new BigDecimal(0);
        if(null == outputWarehouseDetails || outputWarehouseDetails.size()<=0){
            return noPayTotal;
        }
        for (OutputWarehouseDetail output : outputWarehouseDetails) {
            String nopayAmount = output.getNopayAmount();
            noPayTotal =  noPayTotal.add(new BigDecimal(nopayAmount));
        }

        return noPayTotal;
    }

    private String getGroupKey(String groupType,Date createTime) throws ParseException {
        String createStr = null;
        if(Constants.GROUP_TYPE_DAY.equals(groupType)){
            createStr = DateUtils.formatDate(createTime);
        }else if(Constants.GROUP_TYPE_MONTH.equals(groupType)){
            createStr = DateUtils.formatDate(createTime,DateUtils.DATE_FORMAT_YYYYMM);
        }else{
            createStr = DateUtils.formatDate(createTime,DateUtils.DATE_FORMAT_YYYY);
        }
        return createStr;
    }


}
