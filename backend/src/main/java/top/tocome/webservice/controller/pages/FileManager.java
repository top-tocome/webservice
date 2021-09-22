package top.tocome.webservice.controller.pages;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.tocome.io.File;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.ResponseData;
import top.tocome.webservice.data.FileAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

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
                break;
            case "mkdir":
                new File(path).mkdirs();
                break;
            default:
                return Error.Failed;
        }

        return Error.Success;
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
