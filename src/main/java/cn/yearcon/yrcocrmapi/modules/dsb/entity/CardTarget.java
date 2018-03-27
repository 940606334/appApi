package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import lombok.Data;

/**
 * 开卡指标
 *
 * @author ayong
 * @create 2018-03-27 9:09
 **/
@Data
public class CardTarget {
    private Integer vip_amt_mark;  //业务量
    private Integer  vipamt; // 已完成
    //private String  yearmoth;//  年月
    private String  year;//年
    private String  month; //月

    public CardTarget() {
    }

    @Override
    public String toString() {
        return "CardTarget{" +
                "vip_amt_mark='" + vip_amt_mark + '\'' +
                ", vipamt='" + vipamt + '\'' +
               // ", yearmoth='" + yearmoth + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
