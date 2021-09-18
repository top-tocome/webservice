package top.tocome.webservice.data;

/**
 * 错误消息
 */
public class Error {
    public static final Error Success = new Error(0, "ok");
    public static final Error Failed = new Error(-1, "failed");
    public static final Error NoSuchAccount = new Error(-1, "账号不存在");
    public static final Error PwdError = new Error(-1, "密码错误");
    public static final Error AccountExit = new Error(-1, "账号已存在");
    public static final Error NotLogin = new Error(-1, "未登录账号");

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 错误码
     */
    protected int code;

    public int getCode() {
        return code;
    }

    /**
     * 错误消息
     */
    protected String message;

    public String getMessage() {
        return message;
    }
}
