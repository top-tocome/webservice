package top.tocome.webservice;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.tocome.io.Stream;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Config;
import top.tocome.webservice.data.ServerConfig;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(BackendApplication.class, args);

        while (true) {
            System.out.print(">");
            String s = Stream.readLine();
            if (s.equals("exit")) break;
            else if (s.equals("users")) {
                System.out.println(JSON.toJSONString(UserSystem.Instance.getAllUsers()));
            }
        }
        save();
    }

    public static void save() {
        UserSystem.Instance.saveUsers();
        for (Config c : Config.autoSaveConfig) {
            c.save();
        }
    }
}
