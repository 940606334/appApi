package cn.yearcon.yrcocrmapi.modules.dsa.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-26 10:29
 **/
@Data
public class Salary {
    private int id;
    private String webid;
    private String empNumber;
    private String name;
    private String belongShop;
    private Double rate;
    private Double fixedValue0;
    private Date addTime;
    private Date updateTime;
    private Double target;
    private Double fixedValue1;
    private Double fixedValue2;
    private Double fixedValue3;
    private Double fixedValue4;
    private Double fixedValue5;
    private Double fixedValue6;
    private Double fixedValue7;
    private Double fixedValue8;
    private Double fixedValue9;
    private Double fixedValue10;
    private Double fixedValue11;
    private Double fixedValue12;
    private Double fixedValue13;
    private Double fixedValue14;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return id == salary.id &&
                Objects.equals(webid, salary.webid) &&
                Objects.equals(empNumber, salary.empNumber) &&
                Objects.equals(name, salary.name) &&
                Objects.equals(belongShop, salary.belongShop) &&
                Objects.equals(rate, salary.rate) &&
                Objects.equals(fixedValue0, salary.fixedValue0) &&
                Objects.equals(addTime, salary.addTime) &&
                Objects.equals(updateTime, salary.updateTime) &&
                Objects.equals(target, salary.target) &&
                Objects.equals(fixedValue1, salary.fixedValue1) &&
                Objects.equals(fixedValue2, salary.fixedValue2) &&
                Objects.equals(fixedValue3, salary.fixedValue3) &&
                Objects.equals(fixedValue4, salary.fixedValue4) &&
                Objects.equals(fixedValue5, salary.fixedValue5) &&
                Objects.equals(fixedValue6, salary.fixedValue6) &&
                Objects.equals(fixedValue7, salary.fixedValue7) &&
                Objects.equals(fixedValue8, salary.fixedValue8) &&
                Objects.equals(fixedValue9, salary.fixedValue9) &&
                Objects.equals(fixedValue10, salary.fixedValue10) &&
                Objects.equals(fixedValue11, salary.fixedValue11) &&
                Objects.equals(fixedValue12, salary.fixedValue12) &&
                Objects.equals(fixedValue13, salary.fixedValue13) &&
                Objects.equals(fixedValue14, salary.fixedValue14);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, webid, empNumber, name, belongShop, rate, fixedValue0, addTime, updateTime, target, fixedValue1, fixedValue2, fixedValue3, fixedValue4, fixedValue5, fixedValue6, fixedValue7, fixedValue8, fixedValue9, fixedValue10, fixedValue11, fixedValue12, fixedValue13, fixedValue14);
    }
}
