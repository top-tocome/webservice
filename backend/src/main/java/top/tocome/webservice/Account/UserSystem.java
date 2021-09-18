package top.tocome.webservice.Account;

import com.alibaba.fastjson.JSON;
import top.tocome.io.File;

import java.util.ArrayList;
import java.util.Random;

/**
 * 用户系统
 */
public class UserSystem {
    /**
     * 系统单例
     */
    public static final UserSystem Instance = new UserSystem();

    private UserSystem() {
        //默认用户
        register("admin", "123456");
    }

    /**
     * 所有已注册的用户
     */
    public ArrayList<User> users = new ArrayList<>();

    /**
     * 登录一个用户
     *
     * @return 登录结果
     */
    public boolean login(String id, String pwd) {
        User u = getUser(id);
        return u != null && u.login(pwd);
    }

    /**
     * 注册一个用户，如果用户已存在则注册失败
     *
     * @param id  新用户{@link User#id id}
     * @param pwd 新用户{@link User#pwd 密码}
     * @return 注册结果
     */
    public boolean register(String id, String pwd) {
        User u = getUser(id);
        if (u == null) {
            users.add(new User(id, pwd));
            return true;
        }
        return false;
    }

    /**
     * 获取一个已注册的用户
     *
     * @param id {@link User#id}
     * @return account or null
     */
    public User getUser(String id) {
        for (User u : users) {
            if (u.id.equals(id)) return u;
        }
        return null;
    }

    /**
     * 保存用户信息的文件
     */
    String savePath = "accounts.json";

    /**
     * 保存用户信息到{@link #savePath}
     */
    public void saveUsers() {
        File.write(savePath, JSON.toJSONBytes(users));
    }

    /**
     * 从{@link #savePath}中读取用户信息
     */
    public void loadUsers() {
        users = (ArrayList<User>) JSON.parseArray(File.read(savePath), User.class);
    }

    /**
     * 当前有效的Session
     */
    public ArrayList<Session> sessions = new ArrayList<>();

    /**
     * 判断session是否有效
     *
     * @param session 前端传递过来的session
     */
    public boolean isSessionValid(Session session) {
        for (Session s : sessions) {
            if (s.sameAs(session)) return true;
        }
        return false;
    }

    /**
     * 创建一个新的session
     */
    public Session newSession() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            char c = (char) (random.nextInt(74) + 48);
            if ((c >= 91 && c <= 96) || (c >= 58 && c <= 64)) i--;
            else sb.append(c);
        }
        Session session = new Session(sb.toString());
        sessions.add(session);
        return session;
    }
}
