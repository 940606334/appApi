package cn.yearcon.yrcocrmapi.modules.web;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsa.service.TaskStatusService;
import cn.yearcon.yrcocrmapi.modules.dsb.service.VIPInfoService;
import cn.yearcon.yrcocrmapi.modules.entity.DefinedSearch;
import cn.yearcon.yrcocrmapi.modules.entity.VIPStatus;
import cn.yearcon.yrcocrmapi.modules.service.VIPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的工作
 *
 * @author ayong
 * @create 2018-03-23 14:50
 **/
@RestController
@Api(description = "我的工作")
@RequestMapping(value = "task")
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private VIPInfoService vipInfoService;
    @Autowired
    private VIPService vipService;
    @Autowired
    TaskStatusService taskStatusService;
    @ApiOperation(value = "查看会员状态列表",notes = "查看会员状态列表")
    @RequestMapping(value = "vipstatus.list" ,method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "员工账号",paramType = "query",dataType = "string",required = true)

    })
    public  JsonResult getVIPStatusList(String username){
        logger.info("username="+username);
        if (username==null||"".equals(username.trim())){
            return  new JsonResult(0,"参数不能为空");
        }
        /*if (storeid==null){
            return  new JsonResult(0,"参数不能为空");
        }*/
        VIPStatus vipStatus = vipService.vipList(username);
        logger.info(vipStatus.toString());
        return new JsonResult(1,vipStatus);
    }
    /*@ApiOperation(value = "获取7天前消费会员",notes = "查看过去七天消费信息")
    @RequestMapping(value = "vipinfo.sevenday" ,method = {RequestMethod.GET})
    @ApiImplicitParam(value = "员工账号", name="username" ,paramType = "query",dataType = "string",required = true)
    public JsonResult getVIPInfo(String username){
        logger.info("username="+username);
        if (username==null||"".equals(username.trim())){
            return  new JsonResult(0,"参数不能为空");
        }
        return vipService.wakenSevenDaybackList(username);
    }*/
    @ApiOperation(value = "获取不同会员状态列表",notes = "根据会员状态获取不同列表 1.激活 2.流失 3.修眠 4.睡眠 5.开卡未消费,6.生日关怀,7.7天回返")
    @RequestMapping(value = "vipinfo.store" ,method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "员工账号",paramType = "query",dataType = "string",required = true),
            @ApiImplicitParam(name="vipStatus",value="会员状态1.激活 2.流失 3.修眠 4.睡眠 5.开卡未消费,6.生日关怀,7.7天回返",paramType = "query",dataType = "string",required = true)
    })
    public JsonResult getVIPInfoByStoreid(String username,String vipStatus){
        logger.info("username="+username);
        if (username==null||"".equals(username.trim())){
            return  new JsonResult(0,"参数不能为空");
        }
        JsonResult jsonResult=vipService.wakenVipStatusList(username,vipStatus);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    /*@ApiOperation(value = "获取7天内生日的会员信息",notes = "获取7天内生日的会员信息")
    @RequestMapping(value = "vipinfo.birthday" ,method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "员工账号",paramType = "query",dataType = "string",required = true),
    })
    public JsonResult getVIPInfoByBirthday(String username){
        logger.info("username="+username);
        if (username==null||"".equals(username.trim())){
            return  new JsonResult(0,"参数不能为空");
        }
        JsonResult jsonResult=vipService.wakenBirthdayList(username);
        logger.info(jsonResult.toString());
        return jsonResult;
    }*/
    @ApiOperation(value ="唤醒会员",notes = "根据会员id,唤醒会员")
    @RequestMapping(value = "waken.vip",method = {RequestMethod.GET})
    @ApiImplicitParam(value = "会员id", name="vipid" ,paramType = "query",dataType = "int",required = true)
    public JsonResult wakenVip(Integer vipid){
        taskStatusService.wakenVip(vipid);
        return new JsonResult(1,"已唤醒");
    }
    @ApiOperation(value ="会员详情",notes = "根据会员id查询会员详情")
    @RequestMapping(value = "vipinfo.get",method = {RequestMethod.GET})
    @ApiImplicitParam(value = "会员id", name="vipid" ,paramType = "query",dataType = "int",required = true)
    public JsonResult findByid(Integer vipid){
        return vipInfoService.getVIPById(vipid);
    }
    @ApiOperation(value ="会员三个月内消费记录",notes = "根据会员id查询三个月内消费记录")
    @RequestMapping(value = "vipcost.get",method = {RequestMethod.GET})
    @ApiImplicitParam(value = "会员id", name="vipid" ,paramType = "query",dataType = "int",required = true)
    public JsonResult findCostByid(Integer vipid){
        return vipInfoService.findCostById(vipid);
    }
    @ApiOperation(value ="高级自定义查询会员列表",notes = "高级自定义查询会员列表")
    @RequestMapping(value = "viplist.search",method = {RequestMethod.GET})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "员工账号",paramType = "query",dataType = "string",required =true),
            @ApiImplicitParam(name = "storeid", value = "店铺id",paramType = "query",dataType = "int",required =true)
    })
    public JsonResult findVipListDefind(String username,Integer storeid, DefinedSearch definedSearch){
        logger.info("username="+username+",storeid="+storeid+",definedSearch="+definedSearch);
        if (username==null||"".equals(username.trim())){
            return  new JsonResult(0,"参数不能为空");
        }
        if (storeid==null){
            return  new JsonResult(0,"参数不能为空");
        }
        return vipService.findVipListBySearch(username,storeid,definedSearch);
    }
}
