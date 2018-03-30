package cn.yearcon.yrcocrmapi.modules.dsa.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.common.util.CookieUtil;
import cn.yearcon.yrcocrmapi.common.util.GetRandomUtil;
import cn.yearcon.yrcocrmapi.common.util.MD5Util;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppEmployee;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.EmpDao;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.HrEmployee;
import cn.yearcon.yrcocrmapi.modules.dsb.service.HrEmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author ayong
 * @create 2018-03-19 16:00
 **/
@Service
public class AppEmpService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmpDao empDao;
    @Autowired
    private HrEmpService hrEmpService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册
     * @param appEmployee
     * @return
     */
    public JsonResult reg(AppEmployee appEmployee){

        String checkcode=appEmployee.getCheckcode();
        String mobile=appEmployee.getMobile();
        String code=stringRedisTemplate.opsForValue().get("smscodeMobile="+mobile);
        if(!checkcode.equals(code)){
            return new JsonResult(0,"验证码不对");
        }
        if (empDao.findByUsername(appEmployee.getUsername())!=null){
            return new JsonResult(0,"该工号已注册过了");
        }
        HrEmployee byUsername = hrEmpService.findByUsername(appEmployee.getUsername());
        if(byUsername==null){
            return new JsonResult(0,"未找到该工号所在的区域");
        }
        appEmployee.setCCustomerId(byUsername.getCCustomerId());
        appEmployee.setCCustomerupId(byUsername.getCCustomerupId());
        String plaintext=appEmployee.getPassword();
        logger.info("加密前:"+plaintext);
        String message=MD5Util.getMD5(plaintext);
        logger.info("加密后:"+message);
        appEmployee.setPassword(message);
        String key= GetRandomUtil.getRandomStr(6);
        logger.info("随机生成6位数字:"+key);
        key=MD5Util.getMD5(key);
        logger.info("随机数加密后:"+key);
        appEmployee.setKey(key);
        empDao.insert(appEmployee);
        return new JsonResult(1,"注册成功",key);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public JsonResult login(String username, String password,
                            HttpServletRequest request, HttpServletResponse response){
        AppEmployee byUsername = empDao.findByUsername(username);
        logger.info(byUsername.toString());
        if (byUsername==null){
            return new JsonResult(0,"用户名或密码错误");
        }
        String loginpass=MD5Util.getMD5(password);
        if(!loginpass.equals(byUsername.getPassword())){
            return new JsonResult(0,"用户名或密码错误");
        }
        byUsername.setLoginDate(new Date());
        byUsername.setLoginTimes((byUsername.getLoginTimes()+1));
        byUsername.setKey(MD5Util.getMD5(GetRandomUtil.getRandomStr(6)));
        empDao.update(byUsername);
        request.getSession().setAttribute(username+"key",byUsername.getKey());
        CookieUtil.setCookie(response,"app_username",username);
        CookieUtil.setCookie(response,"app_key",byUsername.getKey());
        return new JsonResult(1,"登陆成功",byUsername.getKey());
    }

    /**
     * 忘记密码
     * @param username
     * @param mobile
     * @param checkcode
     * @param password
     * @return
     */
    public JsonResult forgetPwd(String username,String mobile,String checkcode,String password){
        AppEmployee byUsername = empDao.findByUsername(username);
        String code=stringRedisTemplate.opsForValue().get("smscodeMobile="+mobile);
        if(!checkcode.equals(code)){
            return new JsonResult(0,"验证码不对");
        }
        if (!mobile.equals(byUsername.getMobile())){
            return new JsonResult(0,"绑定手机号错误");
        }
        String message=MD5Util.getMD5(password);
        byUsername.setPassword(message);
        byUsername.setUpdateDate(new Date());
        empDao.update(byUsername);
        return new JsonResult(1,"修改成功");
    }
    /**
     * 修改密码
     * @param username
     * @return
     */
    public JsonResult updatePwd(String username,String oldpassword,String newpassword){
        AppEmployee byUsername = empDao.findByUsername(username);
        if(byUsername==null){
            return new JsonResult(0,"用户名或密码错误");
        }
        String message=MD5Util.getMD5(oldpassword);
        if(!message.equals(byUsername.getPassword())){
            return new JsonResult(0,"用户名或密码错误");
        }
        message=MD5Util.getMD5(newpassword);
        byUsername.setPassword(message);
        byUsername.setUpdateDate(new Date());
        empDao.update(byUsername);
        return new JsonResult(1,"修改成功");
    }
    /**
     * 根据用户名获取员工信息
     * @param username
     * @return
     */
    public  AppEmployee findByUsername(String username){
        return  empDao.findByUsername(username);
    }
}
