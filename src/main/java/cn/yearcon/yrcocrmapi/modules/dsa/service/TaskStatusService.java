package cn.yearcon.yrcocrmapi.modules.dsa.service;

import cn.yearcon.yrcocrmapi.common.util.DateUtil;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.Usertaskstatus;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.TaskStatusDao;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.VIPInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ayong
 * @create 2018-03-27 14:21
 **/
@Service
public class TaskStatusService {
    @Autowired
    private TaskStatusDao taskStatusDao;


    public int save(String username,VIPInfo vipInfo){
        Usertaskstatus entity=taskStatusDao.findByVipid(vipInfo.getId());
        int row=0;
        if(entity==null){
            entity=new Usertaskstatus();
            entity.setUsername(username);
            entity.setVipid(vipInfo.getId());
            entity.setTotalTimes(0);
            entity.setUpdateDate(new Date());
            entity.setStatus(0);
            taskStatusDao.insert(entity);
        }else{
            Date lastupdate=entity.getUpdateDate();//上次修改时间
            Date nowDate=new Date();//现在修改时间
            int days=DateUtil.compareDays(nowDate,lastupdate);
            if(days>30){
                entity.setStatus(0);
                taskStatusDao.update(entity);
            }
            if (entity.getStatus()==1){
                row=1;
            }
            vipInfo.setDealDate(entity.getUpdateDate());
            vipInfo.setTotalTimes(entity.getTotalTimes());
        }
        return row;

    }

    /**
     * 唤醒会员
     */
    public void wakenVip(int vipid){
        Usertaskstatus entity = taskStatusDao.findByVipid(vipid);
        entity.setStatus(1);
        entity.setUpdateDate(new Date());
        entity.setTotalTimes((entity.getTotalTimes()+1));
        taskStatusDao.update(entity);
    }
}
