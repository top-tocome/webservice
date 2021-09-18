package top.tocome.webservice.Account;

import java.util.Date;

/**
 * 自定义Session
 */
public class Session {

    public Session(String sessionId) {
        this.sessionId = sessionId;
        creatDate = new Date();
    }

    /**
     * session创建的时间
     */
    public Date creatDate;
    /**
     * 区别其他session的唯一id
     */
    public String sessionId;

    /**
     * 判断session是否相同
     */
    public boolean sameAs(Session session) {
        return sessionId.equals(session.sessionId);
    }
}
