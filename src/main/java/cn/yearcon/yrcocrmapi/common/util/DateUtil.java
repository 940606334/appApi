package cn.yearcon.yrcocrmapi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ayong
 * @create 2018-03-27 14:49
 **/
public class DateUtil {

    /**
     * 比较两个日期相差的天数
     * @param nowDate
     * @param lastDate
     * @return
     */
    public static int compareDays(Date nowDate,Date lastDate){
        long result=nowDate.getTime()-lastDate.getTime();
        //System.out.println(result);
        int days=(int)(result/(1000*60*60*24));
        return days;
    }
    /**
     * 判断生日是否在7天内
     */
    public static boolean isbirthday(String birthday)  {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        try {
            cBirth.setTime(myFormatter.parse(birthday)); // 设置生日
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        if (days<7){
            return true;
        }else{
            return false;
        }
    }
}
