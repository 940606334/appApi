package cn.yearcon.yrcocrmapi.modules.dsb.entity;

import lombok.Data;

/**
 * @author ayong
 * @create 2018-03-29 10:49
 **/
@Data
public class Product {

    private String id;

    private String productNo; //货号

    private String color;//颜色

    private String storeName;//商店名称
    private String storeid;//商店id

    private String coordinate;//坐标

    private String sizeid; //尺码id
    private String colorid; //颜色id
    private String colorCode; //颜色代码
    private String sizeCode; //尺码

    public Product() {
    }

    @Override
    public String toString() {
        return "product{" +
                "id='" + id + '\'' +
                ", productNo='" + productNo + '\'' +
                ", color='" + color + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeid='" + storeid + '\'' +
                ", coordinate='" + coordinate + '\'' +
                '}';
    }
}
