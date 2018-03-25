package cn.yearcon.yrcocrmapi.modules.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
