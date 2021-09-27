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
        Article article = new Article(title, desc, title);
        article.save(content);
        articles.add(article);
        saveArticles();
    }

    public List<Article> getArticles(int page, int num) {
        if (page > 0 && num > 0) {
            int size = articles.size();
            int start = size - page * num;
            int end = start + num;
            if (start >= 0) {
                return articles.subList(start, end);
            } else if (end < 0)
                return null;
            else return articles.subList(0, end);
        }
        return null;
    }

    public Article getArticleById(String id) {
        for (Article a : articles) {
            if (a.id.equals(id)) return a;
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
