package top.tocome.webservice.Account;

/**
 * 用户
 */
public class User {

    public User(Account account) {
        this.account = account;
        name = account.getId();
    }

    /**
     * 用户昵称
     */
    public String name;
    /**
     * 该用户的账号
     */
    public Account account;
    /**
     * 用户的登录状态
     */
    public Session session = null;
    /**
     * 用户权限级别
     */
    public PermissionLevel level = PermissionLevel.User;
}
