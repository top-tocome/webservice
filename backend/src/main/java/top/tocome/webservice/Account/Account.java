package top.tocome.webservice.Account;

/**
 * 单个账号
 */
public class Account {

    public Account(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    /**
     * 账号id
     */
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 账号密码
     */
    protected String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 登录该账号
     *
     * @return 登录结果
     */
    public boolean login(String pwd) {
        return this.pwd.equals(pwd);
    }
}
