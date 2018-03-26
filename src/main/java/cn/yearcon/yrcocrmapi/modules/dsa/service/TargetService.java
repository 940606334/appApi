package cn.yearcon.yrcocrmapi.modules.dsa.service;

import cn.yearcon.yrcocrmapi.common.execption.ServiceExecption;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppEmployee;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.Salary;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.TargetInfo;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.TargetSalary;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.SalaryDao;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.TargetSalaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 设置目标日工资
 *
 * @author ayong
 * @create 2018-03-26 11:01
 **/
@Service
public class TargetService {
    @Autowired
    TargetSalaryDao targetSalaryDao;

    @Autowired
    AppEmpService appEmpService;
    @Autowired
    SalaryDao salaryDao;
    /**
     * 设置日积分
     * @param username
     * @param targetSalary
     */
    public void setTargetSalary(String username,Double targetSalary){
        //设置日工资
        AppEmployee byUsername = appEmpService.findByUsername(username);
        TargetSalary entity =new TargetSalary();
        TargetSalary targetSalaryByUsername = targetSalaryDao.findByUsername(username);
        if (targetSalaryByUsername==null){
            entity.setTargetSalary(targetSalary);
            entity.setUsername(username);
            entity.setWebid(byUsername.getCCustomerId().toString());
            entity.setUpdateDate(new Date());
            targetSalaryDao.insert(entity);
        }else{
            targetSalaryByUsername.setUpdateDate(new Date());
            targetSalaryByUsername.setTargetSalary(targetSalary);
            targetSalaryDao.update(targetSalaryByUsername);
        }


    }


}
