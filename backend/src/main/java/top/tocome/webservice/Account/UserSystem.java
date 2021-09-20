package top.tocome.webservice.Account;

import com.alibaba.fastjson.JSON;
import top.tocome.io.File;
import top.tocome.webservice.data.Error;
import top.tocome.webservice.data.ResponseData;

import java.util.ArrayList;

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
     * 通过session获取一个已登录的用户
     *
     * @param session 前端传递过来的session
     */
    public User getUserBySession(Session session) {
        for (User u : allUsers) {
            if (u.isLogin() && u.session.sameAs(session)) return u;
        }
        return null;
    }

    /**
     * 登录一个用户
     *
     * @param data 用于添加新生成的session
     * @return {@link Error 登录结果}
     */
    public Error login(String id, String pwd, ResponseData data) {
        User u = getUser(id);
        if (u == null) return Error.NoSuchAccount;
        if (!u.login(pwd)) return Error.PwdError;
        data.put("session", u.session);
        return Error.Success;
    }

    /**
     * 退出登录
     */
    public Error loginOut(Session session) {
        User u = getUserBySession(session);
        if (u == null) return Error.HasNotLogin;
        u.loginOut();
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
     * 删除一个用户，如果用户不存在则删除失败
     */
    public Error delete(String id) {
        User u = getUser(id);
        if (u == null) return Error.NoSuchAccount;
        allUsers.remove(u);
        return Error.Success;
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
}
