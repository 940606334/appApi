package cn.yearcon.yrcocrmapi.modules.dsb.mapper;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.VIPInfo;
import oracle.jdbc.internal.OracleTypes;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * 业绩
 *
 * @author ayong
 * @create 2018-03-26 16:58
 **/
@Repository
public class PerformanceDao {
    @Autowired
    @Qualifier(value = "sqlSessionFactory2")
    SqlSessionFactory sqlSessionFactory2;

    public void getPerforMance(String begindate,String endDate,String username){
        SqlSession sqlSession=sqlSessionFactory2.openSession();
        Connection conn=sqlSession.getConnection();
        ResultSet rs = null;
        try {
            String sql = "{call yek_app_retail(?,?,?,?,?)}";
            CallableStatement cstmt  =conn.prepareCall(sql);
            cstmt.setString(1,begindate);
            cstmt.setString(2,endDate);
            cstmt.setString(3,username);
            cstmt.registerOutParameter(4, OracleTypes.CURSOR);
            cstmt.registerOutParameter(5,OracleTypes.NUMBER);
            cstmt.execute();
            rs = (ResultSet) cstmt.getObject(4);
            while (rs.next()){

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
