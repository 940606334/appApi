package cn.yearcon.yrcocrmapi.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Performance;
import cn.yearcon.yrcocrmapi.modules.service.SmsService;
import com.google.common.math.IntMath;
import lombok.experimental.var;
import org.junit.Test;

import java.io.Console;
import java.text.ParseException;
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
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String nowDate=sdf.format(date);
        String lastDate=sdf.format(last);
        System.out.println("当前时间:"+nowDate);
        System.out.println("前七天:"+lastDate);
    }
    @Test
    public void test3(){
        int i=3;
         int max=i>0?i:0;
         System.out.println(max);
    }
    @Test
    public void test4(){
        String str="201803";
        System.out.println(str.substring(0,4));
        System.out.println(str.substring(4));
    }
    @Test
    public void test5() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Date lastDate=sdf.parse("20180320");
        Date nowDate=new Date();
        long result=nowDate.getTime()-lastDate.getTime();
        System.out.println(result);
        int days=(int)(result/(1000*60*60*24));
        System.out.println(days);
    }
    @Test
    public void test6(){
        String str="02";
        int day=Integer.parseInt(str);
        System.out.println(day);
    }
    @Test
    public void test7() throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
        String clidate = "19780327";
        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        cBirth.setTime(myFormatter.parse(clidate)); // 设置生日
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        // 输出结果
        if (days == 0) {
            System.out.println("今天生日");
        } else {
            System.out.println("距离生日还有：" + days + "天");
        }

    }

}
