package cn.yearcon.yrcocrmapi.modules.dsb.mapper;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.CardTarget;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Performance;
import oracle.jdbc.internal.OracleTypes;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Performance> getPerformance(String begindate,String endDate,String username){
        SqlSession sqlSession=sqlSessionFactory2.openSession();
        Connection conn=sqlSession.getConnection();
        ResultSet rs = null;
        List<Performance> list=new ArrayList<>();
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
                Performance performance=new Performance();
                performance.setAmt(rs.getDouble("amt"));
                performance.setAttname(rs.getString("attname"));
                performance.setAttribname(rs.getString("attribname"));
               // performance.setName(rs.getString("name"));
                performance.setQty(rs.getInt("qty"));
                list.add(performance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<CardTarget> getCardTarget(String username,Integer date){
        SqlSession sqlSession=sqlSessionFactory2.openSession();
        Connection conn=sqlSession.getConnection();
        ResultSet rs = null;
        List<CardTarget> list=new ArrayList<>();
        try {
            String sql = "{call yek_app_target(?,?,?)}";
            CallableStatement cstmt  =conn.prepareCall(sql);
            cstmt.setString(1,username);
            cstmt.setInt(2,date);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet) cstmt.getObject(3);
            while (rs.next()){
                CardTarget entity=new CardTarget();
                String yearMonth=rs.getString("yearmoth");
                entity.setYear(yearMonth.substring(0,4));
                entity.setMonth(yearMonth.substring(4));
                entity.setVip_amt_mark(rs.getInt("vip_amt_mark"));
                entity.setVipamt(rs.getInt("vipamt"));
                list.add(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
