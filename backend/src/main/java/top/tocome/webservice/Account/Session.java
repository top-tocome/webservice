package top.tocome.webservice.Account;

import java.util.Date;
import java.util.Random;

/**
 * 自定义Session
 */
public class Session {
    /**
     * {@link #sessionId}的长度
     */
    public static final int SessionIdLength = 16;

    private Session() {
    }

    public Session(String sessionId) {
        this.sessionId = sessionId;
        createDate = new Date();
    }

    /**
     * session创建的时间
     */
    public Date createDate;
    /**
     * 随机生成的区别其他session的唯一id
     */
    public String sessionId;

    /**
     * 判断session是否相同
     */
    public boolean sameAs(Session session) {
        return sessionId.equals(session.sessionId);
    }

    /**
     * 随机创建一个新的session
     */
    public static Session newSession() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SessionIdLength; i++) {
            char c = (char) (random.nextInt(74) + 48);
            if ((c >= 91 && c <= 96) || (c >= 58 && c <= 64)) i--;
            else sb.append(c);
        }
        return new Session(sb.toString());
    }
}
