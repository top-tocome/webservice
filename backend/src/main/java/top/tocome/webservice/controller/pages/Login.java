package top.tocome.webservice.controller.pages;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        switch (type) {
            case "login":
                return UserSystem.Instance.login(id, pwd, data);

            case "loginOut":
                Session session = JSON.parseObject(request.getParameter("session"), Session.class);
                return UserSystem.Instance.loginOut(session);

            case "register":
                if (id != null && pwd != null)
                    return UserSystem.Instance.register(id, pwd);
                break;
            case "delete":
                if (id != null)
                    return UserSystem.Instance.delete(id);
                break;
            default:
                return Error.Failed;
        }
        return Error.Success;
    }
}
