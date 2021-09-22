package top.tocome.webservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.tocome.webservice.controller.pages.About;
import top.tocome.webservice.controller.pages.FileManager;
import top.tocome.webservice.controller.pages.Login;
import top.tocome.webservice.data.ResponseData;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {

    @PostMapping("/files")
    @CrossOrigin
    public String files(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        data.setError(FileManager.invoke(request, data));
        return data.toJSONString();
    }

    @PostMapping("/upload")
    @CrossOrigin
    public String upload(MultipartHttpServletRequest request) {
        ResponseData data = new ResponseData();
        data.setError(FileManager.upload(request, data));
        return data.toJSONString();
    }

    @PostMapping("/login")
    @CrossOrigin
    public String login(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        data.setError(Login.invoke(request, data));
        return data.toJSONString();
    }

    @PostMapping("/about")
    @CrossOrigin
    public String about(HttpServletRequest request) {
        ResponseData data = new ResponseData();
        data.setError(About.invoke(request, data));
        return data.toJSONString();
    }
}
