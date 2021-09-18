<!--登录页面的js文件-->


/**
 * 登录请求地址
 * @type {string}
 */
const loginServer = server + "login"

/**
 * 向服务器发送登录请求
 */
function login(id, pwd, success, failed) {
    ajax({
        type: "post",
        url: loginServer,
        data: {
            type: "true",
            id: id,
            pwd: pwd
        },
        success: success,
        failed: failed
    })
}

/**
 * 退出当前账号
 */
function loginOut(success, failed) {
    ajax({
        type: "post",
        url: loginServer,
        data: {
            type: "false",
            session: localStorage.getItem("session")
        },
        success: success,
        failed: failed
    })
}