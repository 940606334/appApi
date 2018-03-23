package cn.yearcon.yrcocrmapi.common.util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author ayong
 * @create 2018-03-19 16:11
 **/
public class HttpRequestUtils {
    /**
     * getHttp
     * @param url
     * @return
     */
    public String getHttp(String url){
        String str=null;
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        /* 这个对象有add()方法，可往请求头存入信息 */
        headers.setContentType(MediaType.APPLICATION_JSON);
        /* 解决中文乱码的关键 , 还有更深层次的问题 关系到 StringHttpMessageConverter，先占位，以后补全*/
        HttpEntity<String> entity = new HttpEntity<String>("body", headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        str=exchange.getBody();
        return str;
    }
    /**
     * postHttp
     * @param url
     * @return
     */
    public String postHttp(String url){
       /* GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, Object> map = g.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());*/
        String str=null;
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //* 解决中文乱码的关键 , 还有更深层次的问题 关系到 StringHttpMessageConverter，先占位，以后补全*//*
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        str=exchange.getBody();
        return str;
    }
}
