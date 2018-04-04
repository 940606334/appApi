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
    private Integer code;
    private String msg;
    private Object data;
    private String key;

    public JsonResult(Integer code, String msg, String key) {
        this.code = code;
        this.msg = msg;
        this.key = key;
    }

    public JsonResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult() {

    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", key='" + key + '\'' +
                '}';
    }
}
