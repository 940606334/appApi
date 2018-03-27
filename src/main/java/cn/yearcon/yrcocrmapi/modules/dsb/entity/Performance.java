package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import lombok.Data;

/**
 * 业绩
 *
 * @author ayong
 * @create 2018-03-27 8:11
 **/
@Data
public class Performance {
    //private String name;//店仓
    private String attribname;//品类
    private String attname;//年份

    private Integer qty;//成交数量
    private Double amt;//成交金额

    public Performance() {
    }

    @Override
    public String toString() {
        return "PerforMance{" +
               // "name='" + name + '\'' +
                ", attribname='" + attribname + '\'' +
                ", attname='" + attname + '\'' +
                ", qty=" + qty +
                ", amt=" + amt +
                '}';
    }
}
