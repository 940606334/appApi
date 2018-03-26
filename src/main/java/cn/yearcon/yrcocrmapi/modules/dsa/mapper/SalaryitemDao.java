package cn.yearcon.yrcocrmapi.modules.dsa.mapper;

import cn.yearcon.yrcocrmapi.modules.dsa.entity.SalaryItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ayong
 * @create 2018-03-26 16:01
 **/
@Repository
public interface SalaryitemDao {
    @Select("select * from app_salary_item where " +
            "webid=#{webid}")
    List<SalaryItem> findByWebid(@Param("webid") String webid);
}
