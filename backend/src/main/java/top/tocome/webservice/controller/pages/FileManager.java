package top.tocome.webservice.controller.pages;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.tocome.io.File;
import top.tocome.webservice.Account.Session;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.FileAttribute;
import top.tocome.webservice.data.PermissionConfig;
import top.tocome.webservice.data.ResponseData;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 文档管理页面
 */
public class FileManager {

    public static Error invoke(HttpServletRequest request, ResponseData data) {
        String type = request.getParameter("type");
        String path = request.getParameter("path");

        switch (type) {
            case "list":
                data.put("files", FileAttribute.getAll(path));
                return Error.Success;

            case "mkdir":
                Session session = JSON.parseObject(request.getParameter("session"), Session.class);
                return UserSystem.Instance.checkPermission(session, PermissionConfig.Instance.Mkdir,
                        u -> {
                            new File(path).mkdirs();
                            return Error.Success;
                        });
            default:
                return Error.Failed;
        }
    }

    public static Error upload(MultipartHttpServletRequest request, ResponseData data) {
        Map<String, MultipartFile> map = request.getFileMap();
        System.out.println(map);
        map.forEach((s, file) -> {
            try {
                File.write(s, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return Error.Success;
    }
}
