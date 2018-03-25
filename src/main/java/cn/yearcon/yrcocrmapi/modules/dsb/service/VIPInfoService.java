package cn.yearcon.yrcocrmapi.modules.dsb.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.VIPInfo;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.VIPInfoDao;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.VIPInfoMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    @Autowired
    private VIPInfoMapper vipInfoMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 获取前七天消费会员信息
     */
    public JsonResult getVIPInfoByUsername(String username){
        //获取当前时间
        Calendar calendar=Calendar.getInstance();
        Date date=new Date();//获取系统当前时间
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,-7);
        Date last=calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMdd");
        String nowDate=sdf.format(date);
        String lastDate=sdf.format(last);
        logger.info("当前时间:"+nowDate);
        logger.info("前七天:"+lastDate);
        List<VIPInfo> list=vipInfoDao.findByUsernameAndDate(username,nowDate,lastDate);
        logger.info("查询结果:"+list);
        if(list.size()==0){
            return new JsonResult(0,"无消费记录");
        }
        return new JsonResult(1,list);
    }

    /**
     * 获取该员工所在店仓的vip档案
     * @param username
     * @param storeid
     * @return
     */
    public JsonResult getVIPInfoByStoreid(String username,Integer storeid){

        /*try {
            List<VIPInfo> vipByStoreid = vipInfoDao.getVIPByStoreid(username, storeid);
            return new JsonResult(1,vipByStoreid);
        }catch (Exception e){
            return new JsonResult(0,"未找到记录");
        }*/
        List<VIPInfo> list=vipInfoMapper.getVIPByStore(username,storeid);
        if(list.size()==0){
            return new JsonResult(0,"未找到记录");
        }
        return new JsonResult(1,list);
    }

}
