package top.tocome.webservice.controller.pages;

import top.tocome.webservice.data.Article;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.ResponseData;
import top.tocome.webservice.manager.ArticleManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Articles {

    public static Error invoke(HttpServletRequest request, ResponseData data) {
        String type = request.getParameter("type");


        switch (type) {
            case "list":

                try {
                    List<Article> articles = ArticleManager.Instance.getArticles(
                            Integer.parseInt(request.getParameter("page")),
                            Integer.parseInt(request.getParameter("num")));
                    if (articles != null) {
                        data.put("articles", articles);
                        return Error.Success;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Error.Failed;

            case "new":
                String title = request.getParameter("title");
                String desc = request.getParameter("desc");
                String content = request.getParameter("content");
                if (title == null || desc == null || content == null) return Error.Null;
                ArticleManager.Instance.newArticle(title, desc, content);
                return Error.Success;
            case "modify":

            case "delete":
            default:
                return Error.Failed;
        }
    }
}
