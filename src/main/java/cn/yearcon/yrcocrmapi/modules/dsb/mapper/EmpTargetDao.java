package cn.yearcon.yrcocrmapi.modules.dsb.mapper;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.EmpTarget;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpTargetDao {

    @Select("SELECT SUM(b.tot_amt_actual) as complete,hr.no AS username \n" +
            "FROM m_retail a,m_retailitem b,hr_employee hr,c_store rs\n" +
            "WHERE a.id=b.m_retail_id\n" +
            "      AND a.status=2\n" +
            "      AND a.billdate= #{date}\n" +
            "      AND a.salesrep_id=hr.id(+)\n" +
            "      AND hr.no in #{username}\n" +
            "      AND a.c_store_id=rs.id\n" +
            "      AND (rs.c_customer_id=#{webid} OR rs.c_customerup_id=#{parentWebid} )\n" +
            "group by  hr.no")
    EmpTarget getTargetToday(@Param("username") String username,
                             @Param("date") String date,
                             @Param("webid") Integer webid,
                             @Param("parentWebid") Integer parentWebid);
}
