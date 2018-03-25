package cn.yearcon.yrcocrmapi.modules.dsb.mapper;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.VIPInfo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.security.PrivateKey;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public interface VIPInfoDao {

    List<VIPInfo> findByUsernameAndDate(
            @Param("username") String username,
            @Param("nowDate") String nowDate,
            @Param("lastDate") String lastDate);

    /*List<VIPInfo> getVIPByStoreid(@Param("v_hr_code") String v_hr_code,
                                  @Param("v_hr_store") Integer v_hr_store);*/
    Iterator<Map> getVIPByStoreid(@Param("v_hr_code") String v_hr_code,
                              @Param("v_hr_store") Integer v_hr_store);
    /*List<VIPInfo> getVIPByStoreid(Map map);*/


}
