package cn.yearcon.yrcocrmapi.modules.dsa.entity;

import lombok.Data;

import java.util.Objects;

/**
 * @author ayong
 * @create 2018-03-26 16:00
 **/
@Data
public class SalaryItem {
    private String id;
    private Integer sort;
    private String usable;
    private String name;
    private String webid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryItem that = (SalaryItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(usable, that.usable) &&
                Objects.equals(name, that.name) &&
                Objects.equals(webid, that.webid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sort, usable, name, webid);
    }
}
