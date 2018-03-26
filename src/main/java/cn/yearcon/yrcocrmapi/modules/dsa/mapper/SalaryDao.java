package cn.yearcon.yrcocrmapi.modules.dsa.mapper;

/**
 * @author ayong
 * @create 2018-03-26 13:07
 **/

import cn.yearcon.yrcocrmapi.modules.dsa.entity.Salary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface SalaryDao {
    Salary get(int id);

    Salary findByUsername(@Param("username") String username);

}
