package top.tocome.webservice.Account;

/**
 * 用户
 */
public class User {

    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public User(String id, String pwd, PermissionLevel level) {
        this.id = id;
        this.pwd = pwd;
        this.level = level;
    }

    /**
     * 用户id
     */
    public String id;
    /**
     * 用户密码
     */
    public String pwd;
    /**
     * 用户权限级别
     */
    public PermissionLevel level = PermissionLevel.User;
    /**
     * 用户的登录状态
     */
    protected Session session = null;

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 登录该账号
     *
     * @return 登录结果
     */
    public boolean login(String pwd) {
        return this.pwd.equals(pwd);
    }

    /**
     * 退出该账号
     */
    public void loginOut() {
        session = null;
    }

    /**
     * 该账号是否已登录
     */
    public boolean isLogin() {
        return session != null;
    }

    /**
     * 判断用户是否有level及以上权限级别
     */
    public boolean hasPermission(PermissionLevel level) {
        return this.level.ordinal() <= level.ordinal();
    }
}
