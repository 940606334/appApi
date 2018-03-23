package cn.yearcon.yrcocrmapi.modules.dsb.mapper;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.HrEmployee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author ayong
 * @create 2018-03-19 9:27
 **/
@Repository
public interface HrEmpDao {

    /*@Select("SELECT t.NAME,t.id,t.C_CUSTOMER_ID,t.PHONE,t.C_CUSTOMERUP_ID\n" +
            "FROM hr_employee t WHERE t.NO =#{no} AND  t.isactive ='Y'")
    @Results({
            @Result(property = "name",  column = "NAME"),
            @Result(property = "phone",  column = "PHONE"),
            @Result(property = "cCustomerId", column = "C_CUSTOMER_ID"),
            @Result(property = "cCustomerupId", column = "C_CUSTOMERUP_ID")
    })*/
    HrEmployee findByNo(@Param("no") String no);
}
