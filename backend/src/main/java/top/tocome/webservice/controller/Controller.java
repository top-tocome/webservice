package top.tocome.webservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.tocome.io.File;
import top.tocome.webservice.controller.pages.About;
import top.tocome.webservice.controller.pages.Articles;
import top.tocome.webservice.controller.pages.FileManager;
import top.tocome.webservice.controller.pages.Login;
import top.tocome.webservice.data.ResponseData;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {
    public static final Logger logger = LoggerFactory.getLogger("server entry");

    @PostMapping("/files")
    @CrossOrigin
    public String files(HttpServletRequest request) {
        logger.info("new request:" + request.getRequestURI());
        ResponseData data = new ResponseData();
        data.setError(FileManager.invoke(request, data));
        return data.toJSONString();
    }

    @PostMapping("/upload")
    @CrossOrigin
    public String upload(MultipartHttpServletRequest request) {
        logger.info("new request:" + request.getRequestURI());
        ResponseData data = new ResponseData();
        data.setError(FileManager.upload(request, data));
        return data.toJSONString();
    }

    @PostMapping("/login")
    @CrossOrigin
    public String login(HttpServletRequest request) {
        logger.info("new request:" + request.getRequestURI());
        ResponseData data = new ResponseData();
        data.setError(Login.invoke(request, data));
        return data.toJSONString();
    }

    @PostMapping("/about")
    @CrossOrigin
    public String about(HttpServletRequest request) {
        logger.info("new request:" + request.getRequestURI());
        ResponseData data = new ResponseData();
        data.setError(About.invoke(request, data));
        return data.toJSONString();
    }

    @PostMapping(value = "/download", produces = {"application/text"})
    @CrossOrigin
    public FileSystemResource download(HttpServletRequest request) {
        logger.info("new request:" + request.getRequestURI());
        String path = request.getParameter("path");
        logger.info(path);
        return new FileSystemResource(new File(path));
    }

    @PostMapping("/articles")
    @CrossOrigin
    public String articles(HttpServletRequest request) {
        logger.info("new request:" + request.getRequestURI());
        ResponseData data = new ResponseData();
        data.setError(Articles.invoke(request, data));
        return data.toJSONString();
    }
}
