package cn.yearcon.yrcocrmapi.modules.dsa.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-19 9:22
 **/
@Data
public class AppEmployee {
    private int id;
    private String mobile;
    private Date loginDate;
    private Date createDate;
    private Date updateDate;
    private String username;
    private String password;
    private String key;
    private Integer loginTimes;
    private Integer cCustomerId;
    private Integer cCustomerupId;
    private String available;
    private String remark;
    @Transient
    private String checkcode;//验证码

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppEmployee that = (AppEmployee) o;
        return id == that.id &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(loginDate, that.loginDate) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(key, that.key) &&
                Objects.equals(loginTimes, that.loginTimes) &&
                Objects.equals(cCustomerId, that.cCustomerId) &&
                Objects.equals(cCustomerupId, that.cCustomerupId) &&
                Objects.equals(available, that.available) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mobile, loginDate, createDate, updateDate, username, password, key, loginTimes, cCustomerId, cCustomerupId, available, remark);
    }

    @Override
    public String toString() {
        return "AppEmployee{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", loginDate=" + loginDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
                ", loginTimes=" + loginTimes +
                ", cCustomerId=" + cCustomerId +
                ", cCustomerupId=" + cCustomerupId +
                '}';
    }
}
