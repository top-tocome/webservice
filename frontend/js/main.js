<!--定义全局量所有页面都要调用该js文件-->


//---------------------api-------------------------

/**
 * 服务器地址
 */
const server = "http://localhost:8000";
/**
 *session
 */
const session = localStorage.getItem("session");

function ajax({type, url, data}, success, failed) {
    $.ajax({
        type: type,
        url: server + url,
        data: data,
        dataType: "json",
        success: function (data) {
            console.log(data)
            if (data.code == 0)
                success(data)
            else
                failed(data)
        },
        error: function () {
            alert("请求失败")
        }
    })
}

/**
 * 向服务器发送登录请求
 */
function login(id, pwd, success, failed) {
    ajax({
            type: "post",
            url: "/login",
            data: {
                type: "login",
                id: id,
                pwd: pwd
            }
        },
        success,
        failed
    )
}

/**
 * 退出当前账号
 */
function loginOut(success, failed) {
    ajax({
            type: "post",
            url: "/login",
            data: {
                type: "loginOut",
                session: session
            }
        },
        success,
        failed
    )
}

/**
 * 注册账号
 */
function register(id, pwd, success, failed) {
    ajax({
            type: "post",
            url: "/login",
            data: {
                type: "register",
                id: id,
                pwd: pwd
            }
        },
        success, failed
    )
}

/**
 * 请求目录下所有文件
 */
function listFiles(path, success, failed) {
    ajax({
            type: "post",
            url: "/files",
            data: {
                type: "list",
                path: path
            }
        },
        success, failed
    )
}

/**
 * 新建目录
 */
function mkdir(path, success, failed) {
    ajax({
            type: "post",
            url: "/files",
            data: {
                type: "mkdir",
                path: path
            }
        },
        success,
        failed
    )
}

/**
 * 文件上传
 */
function fileUpload(filesFormData, success, failed) {
    $.ajax({
        type: "post",
        url: "/upload",
        data: filesFormData,
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data)
            if (json.code == 0)
                success(data)
            else
                failed(data)
        },
        error: function () {
            alert("请求失败")
        }
    })
}


/**
 * 获取关于界面的内容
 */
function getAboutContent(success, failed) {
    ajax({
            type: "post",
            url: "/about",
            data: {
                type: "get"
            }
        },
        success,
        failed
    )
}

/**
 * 修改关于界面的内容
 */
function modifyAbout(content, success, failed) {
    ajax({
            type: "post",
            url: "/about",
            data: {
                type: "modify",
                content: content
            }
        },
        success,
        failed
    )
}

//---------------------api-------------------------