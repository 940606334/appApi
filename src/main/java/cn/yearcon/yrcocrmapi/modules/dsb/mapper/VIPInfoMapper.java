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
import java.util.ArrayList;
import java.util.List;

/**
 * @author ayong
 * @create 2018-03-25 13:46
 **/
@Repository
public class VIPInfoMapper {
    @Autowired
    @Qualifier(value = "sqlSessionFactory2")
    SqlSessionFactory sqlSessionFactory2;


    public List<VIPInfo> getVIPByStore(String username,Integer storeid){
        List<VIPInfo> list=new ArrayList<>();
        SqlSession sqlSession=sqlSessionFactory2.openSession();
        Connection conn=sqlSession.getConnection();
        ResultSet rs = null;
        try {
            String sql = "{call yek_store_getvip(?,?,?)}";
            CallableStatement cstmt  =conn.prepareCall(sql);
            cstmt.setInt(1,storeid);
            cstmt.setString(2,username);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet) cstmt.getObject(3);
            while (rs.next()){
                VIPInfo vipInfo=new VIPInfo();
                vipInfo.setId(rs.getInt("id"));
                vipInfo.setBirthday(rs.getString("birthday"));
                vipInfo.setCardno(rs.getString("cardno"));
                vipInfo.setCardType(rs.getString("typename"));
                vipInfo.setIntegral(rs.getInt("integral"));
                vipInfo.setIs_verify(rs.getString("is_verify"));
                vipInfo.setLastdate(rs.getString("lastdate"));
                vipInfo.setMobile(rs.getString("mobil"));
                vipInfo.setVipname(rs.getString("vipname"));
                vipInfo.setVip_status(rs.getString("VIP_STATUS"));
                vipInfo.setSex(rs.getString("sex"));
                list.add(vipInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
