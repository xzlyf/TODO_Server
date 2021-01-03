package com.xz.app.todolist.interceptor;

import com.alibaba.fastjson.JSON;
import com.xz.app.todolist.constant.Local;
import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.utils.MD5Util;
import com.xz.app.todolist.utils.ServletUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interceptor for api sign
 * 验签拦截器
 */
@Component
public class SignInterceptor implements HandlerInterceptor {
    private static long REQUEST_TIMEOUT = 10 * 60 * 1000;//服务器时间不可与服务器相差超过10分钟

    /**
     * @param request：请求对象
     * @param response：响应对象
     * @param handler：处理对象：controller中的信息
     * @return:true表示正常,false表示被拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截请求时间不在范围的请求
        String timestampStr = request.getHeader("timestamp");
        long timestamp;
        try {
            if (StringUtils.isBlank(timestampStr)) {
                ServletUtil.renderString(response, JSON.toJSONString(new ApiResult(StatusEnum.ERROR_TIMESTAMP_NULL, null)));
                return false;
            }
            timestamp = Long.parseLong(timestampStr);
            long now = System.currentTimeMillis();
            if (now - timestamp >= REQUEST_TIMEOUT || now - timestamp <= -REQUEST_TIMEOUT) {
                ServletUtil.renderString(response, JSON.toJSONString(new ApiResult(StatusEnum.ERROR_TIMESTAMP, null)));
                return false;
            }
        } catch (Exception e) {
            ServletUtil.renderString(response, JSON.toJSONString(new ApiResult(StatusEnum.ERROR_TIMESTAMP_RECEIVE, e.getMessage())));
            return false;
        }

        //拦截appid异常的的请求
        String appId = request.getHeader("appid");
        if (StringUtils.isBlank(appId)) {
            ServletUtil.renderString(response, JSON.toJSONString(new ApiResult(StatusEnum.ERROR_APPID_NULL, null)));
            return false;
        }
        //logger.info(System.currentTimeMillis()+"=====来自APPID的请求:"+appId);

        //拦截签名校验失败的请求
        String sign = request.getHeader("sign");
        if (StringUtils.isBlank(sign)) {
            ServletUtil.renderString(response, JSON.toJSONString(new ApiResult(StatusEnum.ERROR_SIGN_NULL, null)));
            return false;
        }


        //开始校验签名  参考验参规则
        String origin;
        origin = MD5Util.getMD5(appId + Local.client_secret + timestampStr + Local.version);
        System.out.println("timestamp------->"+timestamp);
        System.out.println("appId    ------->"+appId);
        System.out.println("sign     ------->"+sign);
        System.out.println("originSign------>"+origin);
        assert origin != null;
        if (!origin.equalsIgnoreCase(sign)) {
            ServletUtil.renderString(response, JSON.toJSONString(new ApiResult(StatusEnum.ERROR_SIGN, null)));
            return false;
        }

        //sign校验无问题,放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}