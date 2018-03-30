package cn.yearcon.yrcocrmapi.common.config;

import cn.yearcon.yrcocrmapi.common.interceptor.KeyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置
 * @author ayong
 * @create 2018-03-23 15:55
 **/
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new KeyInterceptor())
                .addPathPatterns("/repertory/*")
                .addPathPatterns("/target/*")
                .addPathPatterns("/task/*")
                .excludePathPatterns("/aided/*");
        super.addInterceptors(registry);
    }
}
