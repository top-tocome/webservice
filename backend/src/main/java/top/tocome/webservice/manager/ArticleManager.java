package top.tocome.webservice.manager;

import com.alibaba.fastjson.JSON;
import top.tocome.io.File;
import top.tocome.webservice.data.Article;
import top.tocome.webservice.data.Config;

import java.util.ArrayList;
import java.util.List;

public class ArticleManager {

    protected static String savePath = Config.dataPath + "articles.json";

    public static ArticleManager Instance = new ArticleManager();

    private ArticleManager() {
        loadArticles();
    }

    protected ArrayList<Article> articles = new ArrayList<>();

    public void newArticle(String title, String desc, String content) {
        Article article = new Article(title, desc, articles.size() + ".md");
        article.save(content);
        articles.add(article);
    }

    public List<Article> getArticles(int page, int num) {
        if (page > 0 && num > 0) {
            int size = articles.size();
            int start = (page - 1) * num;
            int end = start + num;
            if (end <= size) {
                return articles.subList(start, end);
            } else if (start < size)
                return articles.subList(start, size);
            else if (start > size)
                return null;
        }
        return null;
    }

    public void saveArticles() {
        File.write(savePath, JSON.toJSONBytes(articles));
    }

    public void loadArticles() {
        if (new File(savePath).exists())
            articles = (ArrayList<Article>) JSON.parseArray(File.read(savePath), Article.class);
    }
}
