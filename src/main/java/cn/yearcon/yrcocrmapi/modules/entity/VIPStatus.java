package cn.yearcon.yrcocrmapi.modules.entity;

import lombok.Data;

/**
 * @author ayong
 * @create 2018-03-27 10:53
 **/
@Data
public class VIPStatus {
    private int[] sevenDayNum=new int[2];
    private int[] opencardNotExpense=new int[2];
    private int[] sleep=new int[2];
    private int[] deepSleep=new int[2];
    private int[] runAway=new int[2];
    private int[] activate=new int[2];
    private int[] vipBirthday=new int[2];

    @Override
    public String toString() {
        return "VIPStatus{" +
                "sevenDayNum=" + sevenDayNum +
                ", opencardNotExpense=" + opencardNotExpense +
                ", sleep=" + sleep +
                ", deepSleep=" + deepSleep +
                ", runAway=" + runAway +
                ", activate=" + activate +
                ", vipBirthday=" + vipBirthday +
                '}';
    }
}
