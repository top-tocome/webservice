package top.tocome.webservice.Account;

/**
 * 用户权限级别
 */
public enum PermissionLevel {
    /**
     * Root
     */
    Root,
    /**
     * 管理员
     */
    Admin,
    /**
     * 用户
     */
    User,
    /**
     * 访客
     */
    Visitor,
    /**
     * 黑名单
     */
    Black
}
