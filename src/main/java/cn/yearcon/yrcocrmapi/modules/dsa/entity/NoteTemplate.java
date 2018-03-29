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
public class NoteTemplate {
    private String id;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String remarks;
    private String delFlag;
    private String title;
    private String content;
    private String webid;


    @Override
    public String toString() {
        return "NoteTemplate{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", webid='" + webid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteTemplate that = (NoteTemplate) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(webid, that.webid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createBy, createDate, updateBy, updateDate, remarks, delFlag, title, content, webid);
    }
}
