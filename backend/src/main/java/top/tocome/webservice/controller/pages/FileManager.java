package top.tocome.webservice.controller.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.tocome.io.File;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.FileAttribute;
import top.tocome.webservice.data.ResponseData;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 文档管理页面
 */
public class FileManager {
    public static final Logger logger = LoggerFactory.getLogger("files");

    public static Error invoke(HttpServletRequest request, ResponseData data) {
        String type = request.getParameter("type");
        String path = request.getParameter("path");
        if (path == null) return Error.Null;
        logger.info(type + "   " + path);

        File file = new File(path);
        switch (type) {
            case "list":
                if (!file.isDirectory()) return Error.Failed;
                try {
                    path = file.getCanonicalPath();
                    data.put("realPath", path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                data.put("files", FileAttribute.getAll(path));
                return Error.Success;

            case "mkdir":
                if (file.mkdirs()) return Error.Success;
                return Error.Failed;

            case "delete":
                if (file.exists())
                    if (file.delete())
                        return Error.Success;
                    else
                        return Error.Failed;
                return new Error(-1, "文件不存在");
            default:
                return Error.Failed;
        }
    }

    public static Error upload(MultipartHttpServletRequest request, ResponseData data) {
        String path = request.getParameter("path");
        Map<String, MultipartFile> map = request.getFileMap();
        if (path == null || map.isEmpty()) return Error.Null;
        map.forEach((name, file) -> {
            try {
                File.write(path + "/" + name, file.getBytes());
                logger.info("保存文件 " + path + "/" + name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return Error.Success;
    }
}
