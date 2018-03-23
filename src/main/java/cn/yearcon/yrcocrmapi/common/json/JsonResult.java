package cn.yearcon.yrcocrmapi.common.json;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ayong
 * @create 2018-03-21 15:04
 **/
@Data
public class JsonResult {
    private Integer status;
    private List list=new ArrayList();
    private String msg;
    private Map map=new HashMap<>();
    private String key;

    public JsonResult(Integer status, String msg, String key) {
        this.status = status;
        this.msg = msg;
        this.key = key;
    }

    public JsonResult(Integer status, Map map) {
        this.status = status;
        this.map = map;
    }

    public JsonResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public JsonResult() {

    }

    public JsonResult(Integer status, List list) {
        this.status = status;
        this.list = list;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                ", list=" + list +
                ", msg='" + msg + '\'' +
                '}';
    }
}