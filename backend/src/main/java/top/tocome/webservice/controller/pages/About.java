package top.tocome.webservice.controller.pages;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.tocome.io.File;
import top.tocome.webservice.Account.Session;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.*;
import top.tocome.webservice.data.Error;

import javax.servlet.http.HttpServletRequest;

/**
 * 关于页面
 */
public class About {
    public static final Logger logger = LoggerFactory.getLogger("about");
    public static final String savePath = Config.dataPath + "about.txt";

    public static Error invoke(HttpServletRequest request, ResponseData data) {
        String type = request.getParameter("type");
        logger.info(type);

        switch (type) {
            case "get":
                if (!new File(savePath).exists()) return Error.NoData;
                data.put("content", File.read(savePath));
                return Error.Success;

            case "modify":
                Session session = JSON.parseObject(request.getParameter("session"), Session.class);
                return UserSystem.Instance.checkPermission(session, PermissionConfig.Instance.AboutModify,
                        u -> {
                            String content = request.getParameter("content");
                            if (content == null) return Error.Failed;
                            File.write(savePath, content.getBytes());
                            return Error.Success;
                        }
                );

            default:
                return Error.Failed;
        }
    }
}
