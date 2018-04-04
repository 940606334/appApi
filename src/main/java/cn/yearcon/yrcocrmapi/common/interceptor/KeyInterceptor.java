package cn.yearcon.yrcocrmapi.common.interceptor;

import cn.yearcon.yrcocrmapi.common.util.CookieUtil;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppEmployee;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.EmpDao;
import cn.yearcon.yrcocrmapi.modules.dsa.service.AppEmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ayong
 * @create 2018-03-23 15:54
 **/
public class KeyInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        //AppEmpService appEmpService=new AppEmpService();
        /*String username=CookieUtil.getCookie(request,"username");
        logger.info(username);
        String key=CookieUtil.getCookie(request,"app_key");
        logger.info(key);
        String realKey=(String) request.getSession().getAttribute(username+"key");
        if(key==null||key==""){
            return false;
        }
        return key.equals(realKey);*/
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        logger.info(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
