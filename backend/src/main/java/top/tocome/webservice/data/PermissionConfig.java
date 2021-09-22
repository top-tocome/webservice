package top.tocome.webservice.data;

import top.tocome.webservice.Account.PermissionLevel;

public class PermissionConfig extends Config{
    public static PermissionConfig Instance = new PermissionConfig();


    public PermissionLevel AboutModify = PermissionLevel.Admin;
    public PermissionLevel Mkdir = PermissionLevel.Admin;
    public PermissionLevel delete = PermissionLevel.Root;
}
