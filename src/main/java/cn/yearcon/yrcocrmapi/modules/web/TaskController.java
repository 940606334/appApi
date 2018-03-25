package cn.yearcon.yrcocrmapi.modules.web;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsb.service.VIPInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation(value = "查看消费信息",notes = "查看过去七天消费信息")
    @RequestMapping(value = "last.info" ,method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult getVIPInfo(String username){
        logger.info("username="+username);
        if (username==null||"".equals(username.trim())){
            return  new JsonResult(0,"参数不能为空");
        }
        return vipInfoService.getVIPInfoByUsername(username);
    }
    @ApiOperation(value = "查看员工所在店仓的vip档案",notes = "获取该员工所在店仓的vip档案")
    @RequestMapping(value = "vipinfo.store" ,method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult getVIPInfoByStoreid(String username,Integer storeid){
        logger.info("username="+username+",storeid="+storeid);
        if (username==null||"".equals(username.trim())){
            return  new JsonResult(0,"参数不能为空");
        }
        if (storeid==null){
            return  new JsonResult(0,"参数不能为空");
        }
        JsonResult jsonResult=vipInfoService.getVIPInfoByStoreid(username,storeid);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
}
