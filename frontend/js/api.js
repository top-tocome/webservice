<!--api接口-->

//---------------------api-------------------------

/**
 * 服务器地址
 */
const server = "http://localhost:8000";
/**
 *session
 */
let session = localStorage.getItem("session");

function ajax({type, url, data}, success, failed) {
    $.ajax({
        type: type,
        url: server + url,
        data: data,
        dataType: "json",
        success: function (data) {
            console.log(data)
            if (data.code == 0) {
                if (success == null) alert(data.message)
                else success(data)
            } else {
                if (failed == null) alert(data.message)
                else failed(data)
            }
        },
        error: function (data) {
            console.log(data)
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
 * 删除账号
 */
function deleteUser(id, success, failed) {
    ajax({
            type: "post",
            url: "/login",
            data: {
                type: "delete",
                id: id,
                session: session
            }
        },
        success, failed
    )
}

/**
 * 检测登录状态
 */
function checkLoginState(success, failed) {
    ajax({
            type: "post",
            url: "/login",
            data: {
                type: "check",
                session: session
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
                path: path,
                session: session
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
                path: path,
                session: session
            }
        },
        success,
        failed
    )
}

/**
 * 删除文件
 */
function deleteFiles(path, success, failed) {
    ajax({
            type: "post",
            url: "/files",
            data: {
                type: "delete",
                path: path,
                session: session
            }
        },
        success,
        failed
    )
}

/**
 * 文件上传
 */
function fileUpload(path, filesFormData, success, failed) {
    filesFormData.append("session", session)
    filesFormData.append("path", path)
    $.ajax({
        type: "post",
        url: server + "/upload",
        data: filesFormData,
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data)
            if (data.code == 0) {
                if (success == null) alert(data.message)
                else success(data)
            } else {
                if (failed == null) alert(data.message)
                else failed(data)
            }
        },
        error: function (data) {
            console.log(data)
            alert("请求失败")
        }
    })
}

/**
 *下载文件
 */
function downloadFile(path, name) {
    $.ajax({
        type: "post",
        url: server + "/download",
        data: {
            path: path
        },
        dataTypes: "blob",
        success: function (data) {
            console.log(data)
            let url = window.URL.createObjectURL(new Blob([data]))
            $(`<a></a>`).attr("href", url).attr("download", name)[0].click();
        },
        error: function (data) {
            console.log(data)
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
                type: "get",
                session: session
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
                content: content,
                session: session
            }
        },
        success,
        failed
    )
}

//新文章
function newArticle(title, desc, content, success, failed) {
    ajax({
            type: "post",
            url: "/articles",
            data: {
                type: "new",
                title: title,
                desc: desc,
                content: content,
                session: session
            }
        },
        success,
        failed
    )
}

//获取文章
function getArticle(page, num, success, failed) {
    ajax({
            type: "post",
            url: "/articles",
            data: {
                type: "list",
                page: page,
                num: num,
                session: session
            }
        },
        success,
        failed
    )
}

//---------------------api-------------------------