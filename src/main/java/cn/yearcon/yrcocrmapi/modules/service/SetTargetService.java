package cn.yearcon.yrcocrmapi.modules.service;

import cn.yearcon.yrcocrmapi.common.execption.ServiceExecption;
import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppEmployee;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.Salary;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.SalaryItem;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.TargetInfo;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.SalaryDao;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.SalaryitemDao;
import cn.yearcon.yrcocrmapi.modules.dsa.service.AppEmpService;
import cn.yearcon.yrcocrmapi.modules.dsa.service.SalaryItemService;
import cn.yearcon.yrcocrmapi.modules.dsa.service.TargetService;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.EmpTarget;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.EmpTargetDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.target.EmptyTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ayong
 * @create 2018-03-26 14:28
 **/
@Service
public class SetTargetService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TargetService targetService;
    @Autowired
    private EmpTargetDao empTargetDao;

    @Autowired
    AppEmpService appEmpService;

    @Autowired
    SalaryDao salaryDao;
    /**
     * 设置日工资并计算指标详情
     */
    public JsonResult setTargetAndCalculateInfo(String username, Double targetSalary){

        //设置日工资
        targetService.setTargetSalary(username,targetSalary);
        //计算详细信息
        Salary empSalary = salaryDao.findByUsername(username);
        //说明： 日工资=各工资项（…）+当天完成业绩指标* 换算率
        if(empSalary==null){
            //throw new ServiceExecption("请设置工资项");
            return new JsonResult(0,"请设置工资项");
        }
        Double fixedValue=getFixValue(empSalary);

        TargetInfo info=new TargetInfo();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMdd");
        String today=sdf.format(new Date());//获取当日日期
        //获取机构id
        AppEmployee byUsername = appEmpService.findByUsername(username);
        EmpTarget emptargetToday = empTargetDao.getTargetToday(username, today, byUsername.getCCustomerId(), byUsername.getCCustomerupId());
        //当日已完成指标
        Double complete=emptargetToday!=null?emptargetToday.getComplete():0;

        info.setComplete(complete);//
        //
        double result=(targetSalary - fixedValue)>0?(targetSalary - fixedValue):0;
        //当日要完成的指标
        double targetToday = result/ empSalary.getRate();
        double remian=(targetToday-complete)>0?(targetToday-complete):0;//剩余指标
        info.setTargetToday(targetToday);
        info.setRemain(remian);
        info.setComplete(complete);
        String completestatus=remian>0?"未完成":"已完成";
        info.setCompletestatus(completestatus);
        info.setTargetSalary(targetSalary);
        info.setTargetDate(new Date());
        logger.info(info.toString());
        return new JsonResult(0,info);
    }

    @Autowired
    private SalaryitemDao salaryItemDao;
    /**
     * 获取固定值
     * @param appSalary
     * @return
     */
    public Double getFixValue(Salary appSalary){
        String webid=appSalary.getWebid();
        List<SalaryItem> byWebid = salaryItemDao.findByWebid(webid);
        int num=byWebid.size();
        Double fixValue=0.0;
        switch (num) {
            case 15:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                appSalary.getFixedValue6()+appSalary.getFixedValue7()+appSalary.getFixedValue8()+
                appSalary.getFixedValue9()+appSalary.getFixedValue10()+appSalary.getFixedValue11()+
                appSalary.getFixedValue12()+appSalary.getFixedValue13()+appSalary.getFixedValue14();
                break;
            case 14:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                    appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                    appSalary.getFixedValue6()+appSalary.getFixedValue7()+appSalary.getFixedValue8()+
                    appSalary.getFixedValue9()+appSalary.getFixedValue10()+appSalary.getFixedValue11()+
                    appSalary.getFixedValue12()+appSalary.getFixedValue13();
                break;
            case 13:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                        appSalary.getFixedValue6()+appSalary.getFixedValue7()+appSalary.getFixedValue8()+
                        appSalary.getFixedValue9()+appSalary.getFixedValue10()+appSalary.getFixedValue11()+
                        appSalary.getFixedValue12();
                break;
            case 12:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                        appSalary.getFixedValue6()+appSalary.getFixedValue7()+appSalary.getFixedValue8()+
                        appSalary.getFixedValue9()+appSalary.getFixedValue10()+appSalary.getFixedValue11();
                break;
            case 11:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                        appSalary.getFixedValue6()+appSalary.getFixedValue7()+appSalary.getFixedValue8()+
                        appSalary.getFixedValue9()+appSalary.getFixedValue10();
                break;
            case 10:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                        appSalary.getFixedValue6()+appSalary.getFixedValue7()+appSalary.getFixedValue8()+
                        appSalary.getFixedValue9();
                break;
            case 9:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                        appSalary.getFixedValue6()+appSalary.getFixedValue7()+appSalary.getFixedValue8();
                break;
            case 8:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                        appSalary.getFixedValue6()+appSalary.getFixedValue7();
                break;
            case 7:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5()+
                        appSalary.getFixedValue6();
                break;
            case 6:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4()+appSalary.getFixedValue5();
                break;
            case 5:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3()+appSalary.getFixedValue4();
                break;
            case 4:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2()+
                        appSalary.getFixedValue3();
                break;
            case 3:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1()+appSalary.getFixedValue2();
                break;
            case 2:
                fixValue=appSalary.getFixedValue0()+appSalary.getFixedValue1();
                break;
            case 1:
                fixValue=appSalary.getFixedValue0();
            default:
                break;
        }
        return fixValue;
    }
}
