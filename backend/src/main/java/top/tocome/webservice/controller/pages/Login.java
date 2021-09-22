package top.tocome.webservice.controller.pages;

import com.alibaba.fastjson.JSON;
import top.tocome.webservice.Account.Session;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.ResponseData;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录页面
 */
public class Login {

    public static Error invoke(HttpServletRequest request, ResponseData data) {
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        switch (type) {
            case "login":
                return UserSystem.Instance.login(id, pwd, data);

            case "loginOut":
                String session = request.getParameter("session");
                return UserSystem.Instance.loginOut(JSON.parseObject(session, Session.class));

            case "register":
                if (id != null && pwd != null)
                    return UserSystem.Instance.register(id, pwd);
            default:
                return Error.Failed;
        }
    }
}
