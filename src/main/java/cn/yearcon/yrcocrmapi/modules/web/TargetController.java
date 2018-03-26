package cn.yearcon.yrcocrmapi.modules.web;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.service.SetTargetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "salary.set",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value ="设置日工资",notes = "输入今日目标工资")
    public JsonResult getTarget(String username,Double targetSalary){
        return setTargetService.setTargetAndCalculateInfo(username,targetSalary);
    }
}
