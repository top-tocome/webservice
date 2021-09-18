package top.tocome.webservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.Account.Session;
import top.tocome.webservice.data.ResponseData;
import top.tocome.webservice.filemanager.FileAttribute;

@RestController
public class Controller {

    @PostMapping("/files")
    @CrossOrigin
    public String files(String path) {
        ResponseData data = new ResponseData();

        data.put("files", FileAttribute.getAll(path));

        return data.toJSONString();
    }

    @PostMapping("/login")
    @CrossOrigin
    public String login(String id, String pwd) {
        ResponseData data = new ResponseData();

        if (UserSystem.Instance.login(id, pwd)) {
            Session session = UserSystem.Instance.newSession();
            data.put("session", session);
        } else data.put("code", -1).put("message", "登录失败");

        return data.toJSONString();
    }
}
