package top.tocome.webservice.controller.pages;

import top.tocome.io.File;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.ResponseData;

import javax.servlet.http.HttpServletRequest;

/**
 * 关于页面
 */
public class About {
    public static final String savePath = "about.txt";

    public static Error invoke(HttpServletRequest request, ResponseData data) {
        String type = request.getParameter("type");

        switch (type) {
            case "get":
                data.put("content", File.read(savePath));
                break;
            case "modify":
                String content = request.getParameter("content");
                File.write(savePath, content.getBytes());
                break;
            default:
                return Error.Failed;
        }

        return Error.Success;
    }
}
