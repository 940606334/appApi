package cn.yearcon.yrcocrmapi.modules.dsa.mapper;

import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppCallog;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface CallogDao {

    @Insert("insert into app_callog(" +
            "id,mobile,username,call_times,create_date,update_date," +
            "webid,call_type)value(" +
            "#{id},#{mobile},#{username},#{callTimes}," +
            "#{createDate},#{updateDate},#{webid},#{callType})")
    int insert(AppCallog appCallog);
}
