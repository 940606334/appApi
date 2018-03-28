package cn.yearcon.yrcocrmapi.modules.dsb.mapper;

import cn.yearcon.yrcocrmapi.modules.dsb.entity.Performance;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Store;
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
 * 根据员工id查询啊所属门店
 * @author ayong
 * @create 2018-03-28 13:46
 **/
@Repository
public class StoreDao {
    @Autowired
    @Qualifier(value = "sqlSessionFactory2")
    SqlSessionFactory sqlSessionFactory2;



    public Store findByUsername(String username){
        SqlSession sqlSession=sqlSessionFactory2.openSession();
        Connection conn=sqlSession.getConnection();
        ResultSet rs = null;
        Store store=new Store();
        try {
            String sql = "{call yek_hr_getvipstore(?,?)}";
            CallableStatement cstmt  =conn.prepareCall(sql);
            cstmt.setString(1,username);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet) cstmt.getObject(2);
            if(rs.next()){
                store.setId(rs.getInt("id"));
                store.setName(rs.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return store;
    }
}
