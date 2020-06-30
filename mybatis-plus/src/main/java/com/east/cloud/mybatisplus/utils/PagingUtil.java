package com.east.cloud.mybatisplus.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 分页共通
 */
public class PagingUtil {
    public static void pagingHandle(Map map){
        if(map.containsKey("pageNumber") && map.containsKey("pagequantity")){
            Integer pageNumber = Integer.parseInt( String.valueOf(map.get("pageNumber")) );
            Integer pagequantity = Integer.parseInt( String.valueOf(map.get("pagequantity")) );
            map.remove("pageNumber");
            map.remove("pagequantity");
            if(pageNumber<=0){
                map.put("pageBegin",0);
                map.put("pageEnd",pagequantity);
            }else{
                map.put("pageBegin",(pageNumber-1)*pagequantity);
                map.put("pageEnd",pagequantity);
            }
        }
    }
    public static String getIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
