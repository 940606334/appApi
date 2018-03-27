package cn.yearcon.yrcocrmapi.modules.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.CardTarget;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Performance;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.PerformanceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ayong
 * @create 2018-03-27 9:34
 **/
@Service
public class PerformanceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PerformanceDao performanceDao;

    public JsonResult getPerformance(String username,String beginDate,String endDate){
        logger.info("username="+username+",begindate="+beginDate+",endDate="+endDate);
        if(username==null ||"".equals(username)){
            return new JsonResult(0,"参数不能为空");
        }
        if(beginDate==null||"".equals(beginDate)){
            return new JsonResult(0,"参数不能为空");
        }
        if(endDate==null||"".equals(endDate)){
            return new JsonResult(0,"参数不能为空");
        }
        List<Performance> list = performanceDao.getPerformance(beginDate, endDate, username);
        if(list.size()==0){
            return new JsonResult(0,"无记录");
        }
        return new JsonResult(1,list);
    }


    public JsonResult getCardTarget(String username ,Integer date){
        logger.info("username="+username+",date="+date);
        if(username==null ||"".equals(username)){
            return new JsonResult(0,"参数不能为空");
        }
        if(date==null||"".equals(date)){
            return new JsonResult(0,"参数不能为空");
        }
        List<CardTarget> list = performanceDao.getCardTarget(username, date);
        if(list.size()==0){
            return new JsonResult(0,"无记录");
        }
        return new JsonResult(1,list);
    }
}
