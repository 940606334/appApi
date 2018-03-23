package cn.yearcon.yrcocrmapi.modules.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.common.util.GetRandomUtil;
import cn.yearcon.yrcocrmapi.common.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ayong
 * @create 2018-03-20 10:55
 **/
@Service
public class SmsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    public JsonResult sendCheckCode(String mobile){
        String checkCode= GetRandomUtil.getRandomStr(4);
        String content="你的验证码为"+checkCode+",10分钟有效";
        String url="http://192.168.80.151:9899/req/?" +
                "id=3&key=t4dyuwaovfuqkmcg&" +
                "content=" +content+
                "&mobiles="+mobile+"&type=2";
        try {
            new HttpRequestUtils().getHttp(url);
        }catch (Exception e){
            new JsonResult(0,e.getMessage());
        }
        logger.info("设置短信验证码10分钟");
        stringRedisTemplate.opsForValue().set("smscodeMobile="+mobile,checkCode,60*11, TimeUnit.SECONDS);
        return new JsonResult(1,checkCode);
    }

}
