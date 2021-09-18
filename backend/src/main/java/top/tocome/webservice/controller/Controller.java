package top.tocome.webservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Error;
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
        Error error = UserSystem.Instance.login(id, pwd);
        ResponseData data = new ResponseData(error);
        if (error == Error.Success) {
            data.put("session", UserSystem.Instance.getUser(id).session);
        }
        return data.toJSONString();
    }
}
