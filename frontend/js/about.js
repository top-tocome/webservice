<!--关于页面js文件-->

const aboutServer = server + "about";

/**
 * 获取关于界面的内容
 */
function getAboutContent(success, failed) {
    ajax({
        type: "post",
        url: aboutServer,
        data: {
            type: "get"
        },
        success: success,
        failed: failed
    })
}

/**
 * 修改关于界面的内容
 */
function modifyAbout(content, success, failed) {
    ajax({
        type: "post",
        url: aboutServer,
        data: {
            type: "modify",
            content: content
        },
        success: success,
        failed: failed
    })
}