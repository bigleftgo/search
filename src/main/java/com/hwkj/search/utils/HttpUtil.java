package com.hwkj.search.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * HttpUtil
 *
 * @author xzr
 * 2020/7/14 15:17
 **/
@Slf4j
public class HttpUtil {

    public static String httpGet(String url,Map<String,Object> mapParam){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("CallCode", "zjdzsj");

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

    //测试中文转码
    public static String httpGet1(String url,Map<String,Object> mapParam){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8");
        headers.setContentType(mediaType);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("CallCode", "zjdzsj");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpEntity<Object> Entity = new HttpEntity<>(null,headers);
        //处理url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        mapParam.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
        String formatUrl = builder.build().encode().toString();

        ResponseEntity<String> exchange = restTemplate.exchange(formatUrl, HttpMethod.GET, Entity, String.class, mapParam);
        String body = exchange.getBody();
        return body;
    }


    //预处理/油气显示模糊查询目前调用该请求方式
    public static String yqxshttpGet(String url,Map<String,Object> mapParam){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("CallCode", "zjdzsj");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> Entity = new HttpEntity<>(null,headers);
        //处理url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        mapParam.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
        String formatUrl = builder.build().encode().toString();

        //油气显示模糊查询关键词 不能 encode 参数
        if(mapParam.get("yqxsgjc") != null){
            String gjc = mapParam.get("yqxsgjc").toString();
            if (StringUtils.isNotBlank(gjc)) {
                formatUrl = url  +"?CallCode=zjdzsj"+"&gjc=" + gjc;
            }
        }

        ResponseEntity<String> exchange = restTemplate.exchange(formatUrl, HttpMethod.GET, Entity, String.class, mapParam);
        String body = exchange.getBody();
        return body;
    }

    //预处理/特殊岩性查询目前调用该请求方式
    public static String tsyxhttpGet(String url,Map<String,Object> mapParam){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("CallCode", "zjdzsj");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> Entity = new HttpEntity<>(null,headers);
        //处理url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        mapParam.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
        String formatUrl = builder.build().encode().toString();
        String djsdmin1 = null;
        String djsdmax1 = null;
        String zxcwxh = null;
        String xdcwdm = null;
        if(mapParam.get("djsdmin1") != null){
            djsdmin1 = mapParam.get("djsdmin1").toString();
        }
        if(mapParam.get("djsdmax1") != null){
            djsdmax1 = mapParam.get("djsdmax1").toString();
        }
        if(mapParam.get("zxcwxh") != null){
            zxcwxh = mapParam.get("zxcwxh").toString();
        }
        if(mapParam.get("xdcwdm") != null){
            xdcwdm = mapParam.get("xdcwdm").toString();
        }
        //特殊岩性"岩石名称"模糊查询关键词 不能 encode 参数
        if(mapParam.get("ysmc") != null){
            String ysmc = mapParam.get("ysmc").toString();
            if (StringUtils.isNotBlank(ysmc)) {
                formatUrl = url  +"?CallCode=zjdzsj"+"&ysmc=" + ysmc+"&djsdmin1="+djsdmin1+"&djsdmax1="+djsdmax1 +"&zxcwxh=" +zxcwxh +"&xdcwdm=" +xdcwdm;
            }
        }

        ResponseEntity<String> exchange = restTemplate.exchange(formatUrl, HttpMethod.GET, Entity, String.class, mapParam);
        String body = exchange.getBody();
        return body;
    }
}

