package com.codeartist.component.core.util;

import com.codeartist.component.core.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Web工具类
 *
 * @author 艾江南
 * @date 2020/9/8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebUtil {

    /**
     * 获取请求IP
     */
    public static String getClientIp() {
        HttpServletRequest request = getRequest();
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        int length = 15;
        if (ip != null && ip.length() > length) {
            String str = ",";
            if (ip.indexOf(str) > 0) {
                ip = ip.substring(0, ip.indexOf(str));
            }
        }
        return ip;
    }

    /**
     * 获取当前请求信息
     */
    public static HttpServletRequest getRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(att -> (ServletRequestAttributes) att)
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new BusinessException("Request info error."));
    }

    /**
     * 获取当前响应信息
     */
    public static HttpServletResponse getResponse() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(att -> (ServletRequestAttributes) att)
                .map(ServletRequestAttributes::getResponse)
                .orElseThrow(() -> new BusinessException("Response info error."));
    }
}
