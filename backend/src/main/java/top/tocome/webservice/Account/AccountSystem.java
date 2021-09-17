package top.tocome.webservice.Account;

import com.alibaba.fastjson.JSON;
import top.tocome.io.File;

import java.util.ArrayList;
import java.util.Random;

/**
 * 账号系统
 */
public class AccountSystem {
    /**
     * 系统单例
     */
    public static final AccountSystem Instance = new AccountSystem();

    private AccountSystem() {
        //默认账号
        register("Admin", "123456");
    }

    /**
     * 所有已注册的账号
     */
    protected ArrayList<Account> accounts = new ArrayList<>();

    /**
     * 登录一个账号
     *
     * @return 登录结果
     */
    public boolean login(String id, String pwd) {
        Account a = getAccount(id);
        return a != null && a.login(pwd);
    }

    /**
     * 注册一个账户，如果账户已存在则注册失败
     *
     * @param id  新账户{@link Account#id id}
     * @param pwd 新账户{@link Account#pwd 密码}
     * @return 注册结果
     */
    public boolean register(String id, String pwd) {
        Account a = getAccount(id);
        if (a == null) {
            accounts.add(new Account(id, pwd));
            return true;
        }
        return false;
    }

    /**
     * 获取一个已注册的账户
     *
     * @param id {@link Account#id}
     * @return account or null
     */
    public Account getAccount(String id) {
        for (Account a : accounts) {
            if (a.getId().equals(id)) return a;
        }
        return null;
    }

    /**
     * 保存账号信息的文件
     */
    String savePath = "accounts.json";

    /**
     * 保存账号信息到{@link #savePath}
     */
    public void saveAccounts() {
        File.write(savePath, JSON.toJSONBytes(accounts));
    }

    /**
     * 从{@link #savePath}中读取账号信息
     */
    public void loadAccounts() {
        accounts = (ArrayList<Account>) JSON.parseArray(File.read(savePath), Account.class);
    }

    /**
     * 当前有效的Session
     */
    protected ArrayList<Session> sessions = new ArrayList<>();

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
