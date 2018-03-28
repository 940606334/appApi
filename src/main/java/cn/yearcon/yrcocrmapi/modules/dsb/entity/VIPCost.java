package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import lombok.Data;

import java.util.Date;

/**
 * 会员消费记录
 *
 * @author ayong
 * @create 2018-03-28 10:48
 **/
@Data
public class VIPCost {
    private int vipid;
    private String storeName;//消费店仓
    private  String salesname; //交易编号
    private String mptno;//购买商品
    private Double totamtactual;//交易金额
    private Date statustime;//交易时间
    private String docno;//单据编号

    @Override
    public String toString() {
        return "VIPCost{" +
                "vipid=" + vipid +
                ", storeName='" + storeName + '\'' +
                ", salesname='" + salesname + '\'' +
                ", mptno='" + mptno + '\'' +
                ", totamtactual=" + totamtactual +
                ", statustime=" + statustime +
                ", docno='" + docno + '\'' +
                '}';
    }
}
