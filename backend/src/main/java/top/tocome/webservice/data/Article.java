package top.tocome.webservice.data;

import top.tocome.io.File;

import java.util.Date;

/**
 * 文章
 */
public class Article {

    public static String savePath = Config.dataPath + "articles/";

    private Article() {
    }

    public Article(String title, String desc, String name) {
        this.title = title;
        this.desc = desc;
        createDate = new Date();
        this.name = name;
    }

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
        File.write(savePath + name, content.getBytes());
        lastModify = new Date();
    }

    public String read() {
        return File.read(savePath + name);
    }
}
