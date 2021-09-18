package top.tocome.webservice.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocome.webservice.Account.Session;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.ResponseData;
import top.tocome.webservice.frontdata.FileAttribute;

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
    public String login(boolean type, String id, String pwd, String session) {
        Error error = type ? UserSystem.Instance.login(id, pwd) : UserSystem.Instance.loginOut(JSON.parseObject(session, Session.class));
        ResponseData data = new ResponseData(error);
        if (type && error == Error.Success) {
            data.put("session", UserSystem.Instance.getUser(id).session);
        }
        return data.toJSONString();
    }
}
