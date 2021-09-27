package top.tocome.webservice.data;

import top.tocome.io.File;
import top.tocome.webservice.Account.Session;

import java.util.Date;

/**
 * 文章
 */
public class Article {

    public static String savePath = Config.dataPath + "articles/";

    private Article() {
    }

    public Article(String title, String desc, String name) {
        id = Session.RandomString(8);
        this.title = title;
        this.desc = desc;
        createDate = new Date();
        this.name = name;
    }

    public String id;
    /**
     * 标题
     */
    public String title;
    /**
     * 简介
     */
    public String desc;
    /**
     * 创建时间
     */
    public Date createDate;
    /**
     * 最后修改时间
     */
    public Date lastModify;
    /**
     * 保存文件名
     */
    public String name;

    public void save(String content) {
        String path = savePath + name + ".md";
        if (new File(path).exists()) {
            name = name + "-r";
            save(content);
            return;
        }
        File.write(path, content.getBytes());
        lastModify = new Date();
    }

    public String read() {
        return File.read(savePath + name + ".md");
    }
}
