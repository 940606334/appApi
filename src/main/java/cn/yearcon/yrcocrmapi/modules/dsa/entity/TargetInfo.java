package cn.yearcon.yrcocrmapi.modules.dsa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * 指标信息
 *
 * @author ayong
 * @create 2018-03-26 13:50
 **/
@Data
public class TargetInfo {
    @JsonFormat(pattern ="YYYYMMdd")
    private Date targetDate;//指标日期
    private Double targetToday;//当日指标
    private Double complete;//完成指标
    private Double remain;//剩余指标
    private String completestatus;
    private Double targetSalary;

    public TargetInfo() {
    }

    @Override
    public String toString() {
        return "TargetInfo{" +
                "targetDate=" + targetDate +
                ", targetToday=" + targetToday +
                ", complete=" + complete +
                ", remain=" + remain +
                ", completestatus='" + completestatus + '\'' +
                ", targetSalary=" + targetSalary +
                '}';
    }
}
