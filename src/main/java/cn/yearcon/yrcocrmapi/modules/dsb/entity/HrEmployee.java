package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import lombok.Data;


/**
 * @author ayong
 * @create 2018-03-19 9:19
 **/
@Data
public class HrEmployee {

    private int id;
    private String no;
    private String name;
    private String phone;
    private Integer cCustomerId;
    private Integer cCustomerupId;

    @Override
    public String toString() {
        return "HrEmployee{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", cCustomerId=" + cCustomerId +
                ", cCustomerupId=" + cCustomerupId +
                '}';
    }
}
