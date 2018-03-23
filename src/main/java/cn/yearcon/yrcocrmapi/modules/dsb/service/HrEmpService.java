package cn.yearcon.yrcocrmapi.modules.dsb.service;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.HrEmployee;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.HrEmpDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayong
 * @create 2018-03-19 15:56
 **/
@Service
public class HrEmpService {

    @Autowired
    private HrEmpDao hrEmpDao;

    public HrEmployee findByUsername(String username){
       return hrEmpDao.findByNo(username);
    }
}
