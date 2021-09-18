const aboutServer = server + "about";

/**
 * 获取关于界面的内容
 */
function getAboutContent() {
    $.ajax({
        type: "post",
        url: aboutServer,
        data: {
            type: "get"
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

/**
 * 修改关于界面的内容
 * @param content 修改后的内容
 */
function modifyAbout(content) {
    $.ajax({
        type: "post",
        url: aboutServer,
        data: {
            type: "modify",
            content: content
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