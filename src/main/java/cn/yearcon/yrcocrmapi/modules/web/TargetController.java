package cn.yearcon.yrcocrmapi.modules.web;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsa.service.TaskStatusService;
import cn.yearcon.yrcocrmapi.modules.dsb.service.StoreService;
import cn.yearcon.yrcocrmapi.modules.service.PerformanceService;
import cn.yearcon.yrcocrmapi.modules.service.SetTargetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Perf;

/**
 * 我的指标
 *
 * @author ayong
 * @create 2018-03-23 14:25
 **/
@RestController
@Api(description = "我的指标(设置日工资,获取业绩,获取开卡指标)")
@RequestMapping(value="target")
public class TargetController {
    @Autowired
    SetTargetService setTargetService;
    @Autowired
    PerformanceService performanceService;
    @Autowired
    StoreService storeService;

    @RequestMapping(value = "salary.set",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value ="设置日工资",notes = "输入今日目标工资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "员工账号",paramType = "query",dataType = "string",required = true),
            @ApiImplicitParam(name = "targetSalary",value = "目标工资",paramType = "query",dataType = "double",required = true)
    })
    public JsonResult getTarget(String username,Double targetSalary){
        return setTargetService.setTargetAndCalculateInfo(username,targetSalary);
    }
    @ApiOperation(value ="获取员工业绩",notes = "输入员工账号和日期")
    @RequestMapping(value = "performance.get",method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "员工账号",paramType = "query",dataType = "string",required = true),
            @ApiImplicitParam(name = "beginDate",value = "开始时间",paramType = "query",dataType = "string",required = true),
            @ApiImplicitParam(name = "endDate",value = "结束时间",paramType = "query",dataType = "string",required = true)
    })
    public JsonResult getPerformance(String username,String beginDate,String endDate){
        return performanceService.getPerformance(username,beginDate,endDate);
    }

    @ApiOperation(value ="获取开卡指标",notes = "输入员工账号和日日期")
    @RequestMapping(value = "opencard.target",method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "员工账号",paramType = "query",dataType = "string",required = true),
            @ApiImplicitParam(name = "date",value = "0所有数据,201610指定月份数据）",paramType = "query",dataType = "int",required = true)
    })
    public JsonResult getCardtarget(String username,Integer date){
        return performanceService.getCardTarget(username,date);
    }
    @ApiOperation(value ="获取员工所属店铺",notes = "获取员工所属店铺")
    @RequestMapping(value = "store.get",method = {RequestMethod.GET})
    @ApiImplicitParam(value = "员工账号", name="username" ,paramType = "query",dataType = "string",required = true)
    public JsonResult getStore(String username){
        return storeService.findStoreByUsername(username);
    }

}
