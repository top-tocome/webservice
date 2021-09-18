package top.tocome.webservice.controller.pages;

import top.tocome.io.File;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.ResponseData;
import top.tocome.webservice.frontdata.FileAttribute;

import javax.servlet.http.HttpServletRequest;

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

    public static Error upload(HttpServletRequest request, ResponseData data) {
        String type = request.getParameter("type");

        switch (type) {
            case "1":
                break;
            default:
                return Error.Failed;
        }

        return Error.Success;
    }
}
