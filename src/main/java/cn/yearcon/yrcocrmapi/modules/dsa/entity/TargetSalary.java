package cn.yearcon.yrcocrmapi.modules.dsa.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-26 10:29
 **/
@Data
public class TargetSalary {
    private int id;
    private String username;
    private Double targetSalary;
    private Date updateDate;
    private String webid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetSalary that = (TargetSalary) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(targetSalary, that.targetSalary) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(webid, that.webid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, targetSalary, updateDate, webid);
    }
}
