<!--定义全局量所有页面都要调用该js文件-->


/**
 * 服务器地址
 * @type {string}
 */
const server = "http://localhost:8000/";

function ajax({type, url, data, success, failed}) {
    $.ajax({
        type: type,
        url: url,
        data: data,
        dataType: "json",
        success: function (data) {
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