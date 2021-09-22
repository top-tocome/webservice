package top.tocome.webservice.data;

import top.tocome.webservice.Account.PermissionLevel;

public class PermissionConfig {
    public static PermissionConfig Instance = new PermissionConfig();


    public PermissionLevel AboutModify = PermissionLevel.Admin;
}
