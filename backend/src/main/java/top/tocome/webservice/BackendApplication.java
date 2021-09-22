package top.tocome.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.tocome.webservice.Account.UserSystem;
import top.tocome.webservice.data.Config;
import top.tocome.webservice.data.ServerConfig;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        save();
    }

    public static void save() {
        UserSystem.Instance.saveUsers();
        for (Config c : Config.autoSaveConfig) {
            c.save();
        }
    }
}
