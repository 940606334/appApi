package cn.yearcon.yrcocrmapi.modules.dsa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-29 15:16
 **/

@Data
@ApiModel(value = "DefinedSearch")
public class AppCallog {
    @ApiModelProperty(hidden=true)
    private int id;
    @ApiModelProperty(hidden=true)
    private Date createDate;
    @ApiModelProperty(hidden=true)
    private Date updateDate;

    @ApiModelProperty(value = "手机号码",required = true)
    private String mobile;
    @ApiModelProperty(value = "通话时长",required = true)
    private Integer callTimes;
    @ApiModelProperty(value = "会员编号",required = true)
    private String username;
    @ApiModelProperty(hidden=true)
    private String webid;
    @ApiModelProperty(value = "通话类型",required = true)
    private String callType;

    @Override
    public String toString() {
        return "AppCallog{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", mobile='" + mobile + '\'' +
                ", callTimes=" + callTimes +
                ", username='" + username + '\'' +
                ", webid='" + webid + '\'' +
                ", callType='" + callType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppCallog appCallog = (AppCallog) o;
        return id == appCallog.id &&
                Objects.equals(createDate, appCallog.createDate) &&
                Objects.equals(updateDate, appCallog.updateDate) &&
                Objects.equals(mobile, appCallog.mobile) &&
                Objects.equals(callTimes, appCallog.callTimes) &&
                Objects.equals(username, appCallog.username) &&
                Objects.equals(webid, appCallog.webid) &&
                Objects.equals(callType, appCallog.callType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createDate, updateDate, mobile, callTimes, username, webid, callType);
    }
}
