<!--登录页面的js文件-->


/**
 * 登录请求地址
 * @type {string}
 */
const loginServer = server + "login"

/**
 * 向服务器发送登录请求
 * @param id 账号id
 * @param pwd 账号密码
 */
function login(id, pwd) {
    $.ajax({
        type: "post",
        url: loginServer,
        data: {
            type: true,
            id: id,
            pwd: pwd
        },
        success: function (data) {
            console.log(data)
            let json = JSON.parse(data)
            if (json.code != 0) {
                alert(json.message)
                //Todo:登录失败
                return;
            }
            //Todo:登录成功
            alert(json.message)
            localStorage.setItem("session", JSON.stringify(json.session))
        },
        error: function () {
            alert("请求失败")
        }
    })
}

/**
 * 退出当前账号
 */
function loginOut() {
    $.ajax({
        type: "post",
        url: loginServer,
        data: {
            type: false,
            session: localStorage.getItem("session")
        },
        success: function (data) {
            console.log(data)
            let json = JSON.parse(data)
            if (json.code != 0) {
                alert(json.message)
                //Todo:失败
                return;
            }
            alert(json.message)
            //Todo:成功
        },
        error: function () {
            alert("请求失败")
        }
    })
}