package cn.yearcon.yrcocrmapi.modules.dsa.entity;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-27 13:13
 **/
@Data
public class Usertaskstatus {

   // private int id;
    private String username;//用户名
    private int vipid;//主键
    private int status; //状态
    private Integer totalTimes; //累计次数
    private Integer webid;
    private Date updateDate; //修改日期

    @Override
    public String toString() {
        return "Usertaskstatus{" +
                //"id=" + id +
                ", username='" + username + '\'' +
                ", vipid=" + vipid +
                ", status=" + status +
                ", totalTimes=" + totalTimes +
                ", webid=" + webid +
                ", updateDate=" + updateDate +
                '}';
    }

    public Usertaskstatus() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usertaskstatus that = (Usertaskstatus) o;
        return //id == that.id &&
                vipid == that.vipid &&
                status == that.status &&
                Objects.equals(username, that.username) &&
                Objects.equals(totalTimes, that.totalTimes);
    }

    @Override
    public int hashCode() {

        return Objects.hash( username, vipid, status, totalTimes);
    }
}
