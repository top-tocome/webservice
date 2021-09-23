package top.tocome.webservice.controller.pages;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.tocome.webservice.Account.PermissionLevel;
import top.tocome.webservice.Account.Session;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.ResponseData;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录页面
 */
public class Login {
    public static final Logger logger = LoggerFactory.getLogger("login");

    public static Error invoke(HttpServletRequest request, ResponseData data) {
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        Session session = JSON.parseObject(request.getParameter("session"), Session.class);

        switch (type) {
            case "login":
                return UserSystem.Instance.login(id, pwd,
                        u -> {
                            Session newSession = Session.newSession();
                            u.setSession(newSession);
                            data.put("session", newSession);
                            logger.info("用户" + u.id + "登录成功\n" + "session" + JSON.toJSONString(newSession));
                            return Error.Success;
                        });

            case "loginOut":
                return UserSystem.Instance.loginOut(session);

            case "register":
                if (id == null || pwd == null) return Error.Null;
                return UserSystem.Instance.register(id, pwd);

            case "delete":
                if (id == null) return Error.Null;
                return UserSystem.Instance.checkPermission(session, PermissionLevel.Root,
                        u -> UserSystem.Instance.delete(id));
            case "check":
                if (UserSystem.Instance.getUserBySession(session) == null) return Error.LoginInvalid;
                return Error.Success;
            default:
                return Error.Failed;
        }
    }
}
