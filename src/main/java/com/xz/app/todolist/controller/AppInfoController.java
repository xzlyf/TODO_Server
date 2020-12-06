package com.xz.app.todolist.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.AppInfo;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.pojo.vo.AppUpdateVo;
import com.xz.app.todolist.service.AppInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: xz
 * @Date: 2020/12/4
 */
@RestController
@RequestMapping("/appinfo")
public class AppInfoController {
    @Autowired
    AppInfoService appInfoService;

    /**
     * 获取当前服务器时间
     */
    @RequestMapping("now")
    public Object getNow() {
        return new ApiResult(StatusEnum.SUCCESS, System.currentTimeMillis());
    }

    @RequestMapping("download")
    public void downloadApk(HttpServletRequest request,
                            HttpServletResponse response,
                            // 获取Header里面的Range内容, 可选项, 可为空
                            @RequestHeader(name = "Range", required = false) String range) {

        // 测试用文件
        File file = new File("F:\\WorkSpace\\AppServer\\budaolepao.apk");

        // 设置Content-Type, 此处可以参考void org.apache.catalina.startup.Tomcat.addDefaultMimeTypeMappings(Context context)
        // 采用的是扩展名判断Content-Type, 内容可以参考org.apache.catalina.startup.MimeTypeMappings.properties
        String mimeType = request.getServletContext().getMimeType(file.getName());
        response.setContentType(null != mimeType ? mimeType : "application/octet-stream; charset=UTF-8");
        // 自定义文件名
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s", file.getName()));
        response.setHeader("Accept-Ranges", "bytes");


        try (
                // 获取Response输出流
                ServletOutputStream out = response.getOutputStream();
                // 测试用文件出入流
                InputStream fis = new FileInputStream(file);) {
            // fis.available()可以获取有限数据流总大小, 但available()返回的是int类型, 适用于小于2 G的文件
            // file.length()可以获取文件大小, 返回的是long类型, 适用于小于8.4215048E7 P的文件
            long length = file.length(); // 获取数据流总大小
            long from = 0, to = length - 1; // 定义有效数据流起始, 截止位置
            if (null == range) {
                response.setHeader("Content-Length", Long.toString(length));
            } else {
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String[] ranges = range.replace("bytes=", "").split("-");
                if (ranges.length > 0) from = Long.parseLong(ranges[0]);
                if (ranges.length > 1) to = Long.parseLong(ranges[1]);
                // 设置本批次数据大小
                response.setHeader("Content-Length", Long.toString(to - from + 1L));
                // 设置本批次数据范围及数据总大小
                response.setHeader("Accept-Ranges", String.format("bytes %d-%d/%d", from, to, length));
            }
            fis.skip(from); // 跳过不需要的数据内容
            long limit = to - from + 1; // 限制数据流大小, 最大等于文件大小
            // 缓冲大小
            int bufferSize = (int) (limit > 2048 ? 2048 : limit);
            // 创建缓冲数组
            byte[] buffer = new byte[bufferSize];
            int num = 0;
            while (0 < limit && (num = fis.read(buffer)) != -1) {
                out.write(buffer, 0, num);
                limit -= num;
                if (limit < bufferSize) {
                    buffer = new byte[(int) limit];
                }
            }
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO :: [远程主机强迫关闭了一个现有的连接。]... 等异常处理
        }
    }


    @GetMapping("checkUpdate")
    public Object checkUpdate(@RequestParam String appid,
                              @RequestParam Integer versionCode) {
        AppInfo info = appInfoService.findByAppid(appid);
        if (info == null) {
            return new ApiResult(StatusEnum.ERROR_APPID_NOTFALL, null);
        }

        if (versionCode >= info.getVersionCode()) {
            return new ApiResult(StatusEnum.WORN_UPDATE_VERSION, null);
        }

        AppUpdateVo updateVo = new AppUpdateVo();
        BeanUtils.copyProperties(info,updateVo);
        return new ApiResult(StatusEnum.SUCCESS, updateVo);
    }
}
