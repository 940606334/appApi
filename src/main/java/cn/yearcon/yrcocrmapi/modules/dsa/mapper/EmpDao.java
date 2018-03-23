package cn.yearcon.yrcocrmapi.modules.dsa.mapper;

import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDao {

    AppEmployee findOne(Integer id);

    int insert(AppEmployee AppEmployee);

    AppEmployee findByUsername(String username);

    int update(AppEmployee AppEmployee);
}
