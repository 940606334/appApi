package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 会员信息
 *
 * @author ayong
 * @create 2018-03-23 15:08
 **/
@Data
//@ApiModel(value = "会员信息",reference = "我是参考")
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

    private String vip_status; //会员状态
    private int waken_status;// 是否唤醒

    private String tot_amt_actual; //最后一笔消费金额
    private String mptno;//最后一笔购买产品
    private int cvis;//回访次数
    private String name;//服务店仓
    private String no;//业务员编号
    private String docno;//最后一笔消费订单号

    private String[] userlabel;//会员标签

    private Date dealDate;//处理时间
    private int totalTimes;//次数

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
