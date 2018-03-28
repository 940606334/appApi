package cn.yearcon.yrcocrmapi.modules.dsa.mapper;

import cn.yearcon.yrcocrmapi.modules.dsa.entity.Usertaskstatus;
import org.springframework.stereotype.Repository;

/**
 * @author ayong
 * @create 2018-03-27 13:15
 **/
@Repository
public interface TaskStatusDao {
    int insert(Usertaskstatus entity);
    int update(Usertaskstatus entity);
    Usertaskstatus findByVipid(int vipid);

    Usertaskstatus get(int vipid);

}
