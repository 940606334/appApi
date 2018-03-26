package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import lombok.Data;

/**
 * @author ayong
 * @create 2018-03-26 14:07
 **/
@Data
public class EmpTarget {
    private String username; //员工编号
    private Double complete;//当日完成指标


    @Override
    public String toString() {
        return "EmpTarget{" +
                "username='" + username + '\'' +
                ", complete='" + complete + '\'' +
                '}';
    }
}
