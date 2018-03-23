package cn.yearcon.yrcocrmapi.service;

import cn.yearcon.yrcocrmapi.modules.service.SmsService;
import com.google.common.math.IntMath;
import lombok.experimental.var;
import org.junit.Test;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ayong
 * @create 2018-03-21 14:19
 **/
public class TestService {

    @Test
    public  void test1(){
        //String json=new SmsService().sendSms("18657821125","验证码是12313");
        //Systemout.println(json);
        String str="";
        int random;
        for(int i=0;i<4;i++){
            random= (int)(Math.random()*10);
            str+=random;
        }
        System.out.println(str);
    }

    /**
     * 获取当前时间和前七天时间
     */
    @Test
    public void test2(){
        Calendar calendar=Calendar.getInstance();
        Date date=new Date();//获取系统当前时间
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,-7);
        Date last=calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMdd");
        String nowDate=sdf.format(date);
        String lastDate=sdf.format(last);
        System.out.println("当前时间:"+nowDate);
        System.out.println("前七天:"+lastDate);
    }
}
