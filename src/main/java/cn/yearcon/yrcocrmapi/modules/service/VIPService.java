package cn.yearcon.yrcocrmapi.modules.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.common.util.DateUtil;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.Usertaskstatus;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.TaskStatusDao;
import cn.yearcon.yrcocrmapi.modules.dsa.service.TaskStatusService;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.VIPInfo;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.VIPInfoDao;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.VIPInfoMapper;
import cn.yearcon.yrcocrmapi.modules.dsb.service.VIPInfoService;
import cn.yearcon.yrcocrmapi.modules.entity.VIPStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ayong
 * @create 2018-03-27 10:23
 **/
@Service
public class VIPService {
    @Autowired
    private VIPInfoDao vipInfoDao;
    @Autowired
    private VIPInfoMapper vipInfoMapper;
    @Autowired
    private TaskStatusDao taskStatusDao;
    @Autowired
    private TaskStatusService taskStatusService;

    public VIPStatus vipList(String username,Integer storeid){
        //获取七天内会员消费记录
        //获取当前时间
        Calendar calendar=Calendar.getInstance();
        Date date=new Date();//获取系统当前时间
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,-7);
        Date last=calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMdd");
        String nowDate=sdf.format(date);
        String lastDate=sdf.format(last);
        List<VIPInfo> sevenDayList = vipInfoDao.findByUsernameAndDate(username, nowDate, lastDate);
        //创建会员列表状态
        VIPStatus vipStatus=new VIPStatus();

        //获取会员信息列表中的唤醒状态数量
        int waken=getStatusCount(sevenDayList,username);//7天回访已唤醒数
        int[] sevenDayNum={sevenDayList.size()-waken,waken};//7天回访
        vipStatus.setSevenDayNum(sevenDayNum);
        //获取沉睡会员列表
        List<VIPInfo> vipStatusList=vipInfoMapper.getVIPByStore(username,storeid);

        List<VIPInfo> opencardNotExpenseList=findListByStatus("5",vipStatusList);//5开卡未消费
        waken=getStatusCount(opencardNotExpenseList,username);//开卡未消费已唤醒数
        int[] opencardNotExpense={opencardNotExpenseList.size()-waken,waken};
        vipStatus.setOpencardNotExpense(opencardNotExpense);

        List<VIPInfo> sleepList=findListByStatus("4",vipStatusList);//4睡眠
        waken=getStatusCount(sleepList,username);//睡眠已唤醒数
        int[] sleep={sleepList.size()-waken,waken};
        vipStatus.setSleep(sleep);

        List<VIPInfo> deepSleepList=findListByStatus("3",vipStatusList);//修眠
        waken=getStatusCount(deepSleepList,username);//修眠已唤醒数
        int[] deepSleep={deepSleepList.size()-waken,waken};
        vipStatus.setDeepSleep(deepSleep);

        List<VIPInfo> runAwayList=findListByStatus("2",vipStatusList);//流失
        waken=getStatusCount(runAwayList,username);//流失已唤醒数
        int[] runAway={runAwayList.size()-waken,waken};
        vipStatus.setRunAway(runAway);

        List<VIPInfo> activateList=findListByStatus("1",vipStatusList);//激活
        waken=getStatusCount(activateList,username);//激活已唤醒数
        int[] activate={activateList.size()-waken,waken};
        vipStatus.setActivate(activate);


        List<VIPInfo> vipBirthdayList=vipStatusList.stream() //生日关怀
                .filter(vipInfo -> DateUtil.isbirthday(vipInfo.getBirthday()))
                .collect(Collectors.toList());
        waken=getStatusCount(vipBirthdayList,username);
        int[] vipBirthday={vipBirthdayList.size()-waken,waken};
        vipStatus.setVipBirthday(vipBirthday);

        return vipStatus;

    }


    /**
     * 根据不同的状态获取会员列表
     * @return
     */
    public List<VIPInfo> findListByStatus(String status,List<VIPInfo> list){
        return  list.stream()
                .filter(vipInfo -> status.equals(vipInfo.getVip_status()))
                .collect(Collectors.toList());
    }

    /**
     * 获取列表中的处理数
     * @return
     */
    public int getStatusCount(List<VIPInfo> list,String username){
        int waken=0;
        for(VIPInfo vipInfo:list){
            int row=taskStatusService.save(username,vipInfo.getId());
            waken+=row;
        }
        return waken;
    }
}
