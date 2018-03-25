package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import lombok.Data;

/**
 * 会员信息
 *
 * @author ayong
 * @create 2018-03-23 15:08
 **/
@Data
public class VIPInfo {

    private int id; // ---vipid
    private String cardno; //---卡号
    private String vipname;  //--姓名
    private String sex; //---性别
    private String birthday;// ---生日
    private Integer integral; // ---当前积分
    private String is_verify;//---是否认证
    private String lastdate; //--最后一次购买时间
    private String mobile;  //---会员手机
    private String cardType;   // ---卡类型
    private String vip_status;

    @Override
    public String toString() {
        return "VIPInfo{" +
                "id=" + id +
                ", cardno='" + cardno + '\'' +
                ", vipname='" + vipname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", integral='" + integral + '\'' +
                ", is_verify='" + is_verify + '\'' +
                ", lastdate='" + lastdate + '\'' +
                ", mobile='" + mobile + '\'' +
                ", cardType='" + cardType + '\'' +
                ", vip_status='" + vip_status + '\'' +
                '}';
    }
}
