package top.tocome.webservice;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.tocome.io.Stream;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Config;
import top.tocome.webservice.data.PermissionConfig;
import top.tocome.webservice.data.ServerConfig;

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
        UserSystem.Instance.loadUsers();
        ServerConfig.Instance.load();
        PermissionConfig.Instance.load();
    }

    public static void save() {
        UserSystem.Instance.saveUsers();
        ServerConfig.Instance.save();
        PermissionConfig.Instance.save();
    }
}
