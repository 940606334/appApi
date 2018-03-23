package cn.yearcon.yrcocrmapi.modules.dsb.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.VIPInfo;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.VIPInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ayong
 * @create 2018-03-23 15:34
 **/
@Service
public class VIPInfoService {
    @Autowired
    private VIPInfoDao vipInfoDao;

    /**
     * 获取前七天消费会员信息
     */
    public void getVIPInfoByUsername(String username){
        //获取当前时间
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
        List<VIPInfo> list=vipInfoDao.findByUsernameAndDate(username,nowDate,lastDate);
    }
}
