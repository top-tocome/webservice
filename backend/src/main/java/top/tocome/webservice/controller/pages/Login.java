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
        String path = request.getParameter("path");

        switch (type) {
            case "true":
                String id = request.getParameter("id");
                String pwd = request.getParameter("pwd");
                Error error = UserSystem.Instance.login(id, pwd);
                if (error == Error.Success) {
                    data.put("session", UserSystem.Instance.getUser(id).session);
                }
                return error;

            case "false":
                String session = request.getParameter("session");
                return UserSystem.Instance.loginOut(JSON.parseObject(session, Session.class));

            default:
                return Error.Failed;
        }
    }
}
