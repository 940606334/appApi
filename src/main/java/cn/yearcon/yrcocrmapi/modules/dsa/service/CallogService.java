package cn.yearcon.yrcocrmapi.modules.dsa.service;

import cn.yearcon.yrcocrmapi.common.execption.ServiceExecption;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppCallog;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.CallogDao;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ayong
 * @create 2018-03-29 15:57
 **/
@Service
public class CallogService {
    @Autowired
    private CallogDao callogDao;
    @Autowired
    private EmpDao empDao;

    public void insert(AppCallog appCallog){
        Integer webid=empDao.findWebidByUsername(appCallog.getUsername());
        if(webid==null){
            throw new ServiceExecption("账号错误");
        }
        appCallog.setWebid(webid.toString());
        appCallog.setCreateDate(new Date());
        appCallog.setUpdateDate(new Date());
        callogDao.insert(appCallog);
    }
}
