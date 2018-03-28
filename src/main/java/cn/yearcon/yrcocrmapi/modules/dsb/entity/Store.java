package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import lombok.Data;

/**
 * @author ayong
 * @create 2018-03-28 13:49
 **/
@Data
public class Store {

    private int id; //门店id
    private String name; //门店名称

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
