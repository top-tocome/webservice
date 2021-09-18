package top.tocome.webservice.Account;

/**
 * 用户
 */
public class User {

    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
        name = id;
    }

    /**
     * 用户昵称
     */
    public String name;

    /**
     * 用户id
     */
    public String id;

    /**
     * 用户密码
     */
    public String pwd;
    /**
     * 用户的登录状态
     */
    public Session session = null;
    /**
     * 用户权限级别
     */
    public PermissionLevel level = PermissionLevel.User;


    /**
     * 登录该账号
     *
     * @return 登录结果
     */
    public boolean login(String pwd) {
        return this.pwd.equals(pwd);
    }
}
