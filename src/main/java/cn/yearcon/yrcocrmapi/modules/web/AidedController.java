package cn.yearcon.yrcocrmapi.modules.web;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppEmployee;
import cn.yearcon.yrcocrmapi.modules.dsa.service.AppEmpService;
import cn.yearcon.yrcocrmapi.modules.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

/**
 * 辅助功能接口
 *
 * @author ayong
 * @create 2018-03-19 15:10
 **/
@RestController
@Api(description = "辅助功能接口(注册,登录,修改密码,验证码)")
@RequestMapping(value = "aided")
public class AidedController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AppEmpService appEmpService;
    @Autowired
    private SmsService smsService;


    @ApiOperation(value = "注册账号", notes = "注册账号")
    @RequestMapping(value = "/reg",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult reg(String username,String password,String checkcode,String mobile){
        if(!checkUser(username,password,checkcode,mobile)){
            return new JsonResult(0,"输入参数不完整");
        }
        AppEmployee appEmployee=new AppEmployee();
        appEmployee.setUsername(username);
        appEmployee.setPassword(password);
        appEmployee.setMobile(mobile);
        appEmployee.setCheckcode(checkcode);
        return appEmpService.reg(appEmployee);
    }

    @ApiOperation(value = "获取短信验证码", notes = "输入手机号获取短信验证码")
    @RequestMapping(value = "/sms.code",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult getCheckCode(String mobile){
        JsonResult jsonResult=smsService.sendCheckCode(mobile);
        logger.info(jsonResult.toString());
        return jsonResult;
    }
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult login(String username,String password){
        logger.info("username="+username+",password="+password);
        if(username==null||"".equals(username.trim())){
            return new JsonResult(0,"用户名不能为空");
        }
        if(password==null||"".equals(password.trim())){
            return new JsonResult(0,"密码不能为空");
        }

        return appEmpService.login(username,password);
    }

    /**
     * 修改密码
     * @param username
     * @param mobile
     * @param checkcode
     * @param password
     * @return
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(value = "/update.pwd",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonResult updatePwd(String username,String mobile,String checkcode,String password){
        if(!checkUser(username,password,checkcode,mobile)){
            return new JsonResult(0,"输入参数不完整");
        }
        return appEmpService.updatePwd(username,mobile,checkcode,password);
    }

    /**
     * 验证表单信息
     * @param username
     * @param mobile
     * @param checkcode
     * @param password
     * @return
     */
    public boolean checkUser(String username,String mobile,String checkcode,String password){
        logger.info("username="+username+",password="+password+",checkcode="+checkcode+",mobile="+mobile);
        if(username==null||"".equals(username.trim())){
            //return new JsonResult(0,"用户名不能为空");
            return false;
        }
        if(password==null||"".equals(password.trim())){
            //return new JsonResult(0,"密码不能为空");
            return false;
        }
        if(checkcode==null||"".equals(checkcode.trim())){
            //return new JsonResult(0,"验证码不能为空");
            return false;
        }
        if(mobile==null||"".equals(mobile.trim())){
            //return new JsonResult(0,"手机不能为空");
            return false;
        }
        return true;

    }

}
