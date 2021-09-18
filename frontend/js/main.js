<!--定义全局量所有页面都要调用该js文件-->


/**
 * 服务器地址
 * @type {string}
 */
const server = "http://localhost:8000/";

function ajax(type, url, data, funSuccess, funFailed) {
    $.ajax({
        type: type,
        url: url,
        data: data,
        success: function (data) {
            console.log(data)
            let json = JSON.parse(data)
            if (json.code != 0) {
                alert(json.message)
                //Todo:失败
                funFailed(json)
                return;
            }
            alert(json.message)
            //Todo:成功
            funSuccess(json)
        },
        error: function () {
            alert("请求失败")
        }
    })
}