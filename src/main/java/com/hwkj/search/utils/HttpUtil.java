package com.hwkj.search.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * HttpUtil
 *
 * @author xzr
 * 2020/7/14 15:17
 **/
@Slf4j
public class HttpUtil {

    /**
     * post请求
     * @param objToString jsonListStr
     * @param url url地址
     * @return 返回结果集
     */
    public static String httpPost(String objToString,String url){
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        headers.add("CallCode", "zjdzsj");
//        headers.add("ActionType", "save");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> Entity = new HttpEntity<>(objToString, headers);
        String result = restTemplate.postForObject(url, Entity, String.class);
        return result;
    }

    public static String httpGet(String url,Map<String,Object> mapParam){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        headers.add("CallCode", "zjdzsj");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> Entity = new HttpEntity<>(null,headers);
        //处理url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        mapParam.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
        String formatUrl = builder.build().encode().toString();
        ResponseEntity<String> exchange = restTemplate.exchange(formatUrl, HttpMethod.GET, Entity, String.class, mapParam);
        String body = exchange.getBody();
        return body;
    }
}

