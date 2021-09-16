package top.tocome.webservice.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tocome.webservice.filemanager.FileAttribute;

@RestController
public class Controller {

    @PostMapping("/files")
    @CrossOrigin
    public String files(String path) {
        return JSON.toJSONString(FileAttribute.getAll(path));
    }

}
