package top.tocome.webservice.Account;

import com.alibaba.fastjson.JSON;
import top.tocome.io.File;
import top.tocome.webservice.data.Error;

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
        loadUsers();
    }

    /**
     * 所有已注册的用户
     */
    protected ArrayList<User> allUsers = new ArrayList<>();

    /**
     * 登录一个用户
     *
     * @return 登录结果
     * @see Error
     */
    public Error login(String id, String pwd) {
        User u = getUser(id);
        if (u == null) return Error.NoSuchAccount;
        if (!u.login(pwd)) return Error.PwdError;
        if (u.session == null) usersHasLogin.add(u);
        u.session = newSession();
        return Error.Success;
    }

    /**
     * 注册一个用户，如果用户已存在则注册失败
     *
     * @param id  新用户{@link User#id id}
     * @param pwd 新用户{@link User#pwd 密码}
     * @return 注册结果
     */
    public Error register(String id, String pwd) {
        User u = getUser(id);
        if (u != null) return Error.AccountExit;
        allUsers.add(new User(id, pwd));
        return Error.Success;
    }

    /**
     * 通过id获取一个已注册的用户
     *
     * @param id {@link User#id}
     * @return account or null
     */
    public User getUser(String id) {
        for (User u : allUsers) {
            if (u.id.equals(id)) return u;
        }
        return null;
    }

    /**
     * 保存用户信息的文件
     */
    protected String savePath = "users.json";

    /**
     * 保存用户信息到{@link #savePath}
     */
    public void saveUsers() {
        File.write(savePath, JSON.toJSONBytes(allUsers));
    }

    /**
     * 从{@link #savePath}中读取用户信息
     */
    public void loadUsers() {
        if (new File(savePath).exists())
            allUsers = (ArrayList<User>) JSON.parseArray(File.read(savePath), User.class);
    }

    /**
     * 当已登录的用户
     */
    protected ArrayList<User> usersHasLogin = new ArrayList<>();

    /**
     * 通过session获取一个已登录的用户
     *
     * @param session 前端传递过来的session
     */
    public User getUser(Session session) {
        for (User u : usersHasLogin) {
            if (u.session.sameAs(session)) return u;
        }
        return null;
    }

    /**
     * 退出登录
     */
    public Error loginOut(Session session) {
        User u = getUser(session);
        if (u == null) return Error.NotLogin;
        u.session = null;
        usersHasLogin.remove(u);
        return Error.Success;
    }

    /**
     * 创建一个新的session
     */
    public Session newSession() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            char c = (char) (random.nextInt(74) + 48);
            if ((c >= 91 && c <= 96) || (c >= 58 && c <= 64)) i--;
            else sb.append(c);
        }
        return new Session(sb.toString());
    }
}
