package cn.yearcon.yrcocrmapi.modules.dsb.mapper;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.VIPInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VIPInfoDao {

    List<VIPInfo> findByUsernameAndDate(
            @Param("username") String username,
            @Param("nowDate") String nowDate,
            @Param("lastDate") String lastDate);


}
