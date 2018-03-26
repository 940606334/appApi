package cn.yearcon.yrcocrmapi.modules.dsa.mapper;

import cn.yearcon.yrcocrmapi.modules.dsa.entity.TargetSalary;
import org.springframework.stereotype.Repository;

/**
 * @author ayong
 * @create 2018-03-26 10:42
 **/
@Repository
public interface TargetSalaryDao {

    int insert(TargetSalary targetSalary);
    int update(TargetSalary targetSalary);
    TargetSalary findByUsername(String username);
    TargetSalary get(int id);
}
