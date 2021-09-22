package top.tocome.webservice.Account;

import com.alibaba.fastjson.JSON;
import top.tocome.io.File;
import top.tocome.webservice.data.*;
import top.tocome.webservice.data.Error;

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

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    /**
     * 通过id获取一个已注册的用户
     *
     * @param id {@link User#id}
     * @return account or null
     */
    public User getUser(String id) {
        if (id == null) return null;
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
        if (session == null) return null;
        for (User u : allUsers) {
            if (u.isLogin() && u.session.sameAs(session)) return u;
        }
        return null;
    }

    /**
     * 登录一个用户
     *
     * @return {@link Error 登录结果}
     */
    public Error login(String id, String pwd, Action action) {
        User u = getUser(id);
        if (u == null) return Error.NoSuchAccount;
        if (!u.login(pwd)) return Error.PwdError;
        return action.run(u);
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
     * 检查请求权限
     *
     * @param session 识别登录的账号
     * @param level   需要的权限级别
     * @param action  权限识别通过时执行
     */
    public Error checkPermission(Session session, PermissionLevel level, Action action) {
        User u = UserSystem.Instance.getUserBySession(session);
        if (u == null) return Error.HasNotLogin;
        if (!u.hasPermission(level))
            return Error.NoPermission;
        return action.run(u);
    }

    /**
     * 保存用户信息的文件
     */
    protected String savePath = Config.dataPath + "users.json";

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

    public interface Action {
        Error run(User u);
    }
}
