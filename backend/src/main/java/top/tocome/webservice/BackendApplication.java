package top.tocome.webservice;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.tocome.io.File;
import top.tocome.io.Stream;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Article;
import top.tocome.webservice.data.Config;
import top.tocome.webservice.manager.ArticleManager;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BackendApplication.class, args);
        load();
        boolean exit = false;
        while (!exit) {
            System.out.print(">");
            String s = Stream.readLine();
            switch (s) {
                case "exit":
                    exit = true;
                    SpringApplication.exit(context);
                    break;
                case "users":
                    System.out.println(JSON.toJSONString(UserSystem.Instance.getAllUsers()));
                    break;
                case "save":
                    save();
                default:
                    break;
            }
        }
        save();
    }

    public static void load() {
        new File(Config.dataPath).mkdirs();
        new File(Article.savePath).mkdirs();
        UserSystem.Instance.loadUsers();
    }

    public static void save() {
        UserSystem.Instance.saveUsers();
        ArticleManager.Instance.saveArticles();
    }
}
