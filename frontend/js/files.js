<!--文件管理页面的js文件-->


/**
 * 文件请求地址
 * @type {string}
 */
const fileServer = server + "files"
/**
 * 服务器文件的根路径
 * @type {string}
 */
const rootPath = "."

/**
 * 单个文件的展示块
 * @param directory 文件名/目录名
 * @param savetime 上次保存时间
 * @param size 文件大小
 * @param classname css类名
 * @returns {string}
 */
function getFileBlock(directory, savetime, size, classname) {
    return `<tr>
                <td>
                <a class="${classname}">${directory}</a>
                </td>
                <td>${savetime}</td>
                <td>${size}</td>
            </tr>`;
}

/**
 * 请求数据更新文件并展示
 * @param path 服务器对应文件路径
 */
function showFiles(path) {
    $.ajax({
        type: "post",
        url: fileServer,
        data: {
            type: "list",
            path: path
        },
        dataType: "json",
        success: function (data) {
            console.log(data)
            if (data.code != 0) {
                alert(data.message)
                return;
            }
            $("#files").html(getFileBlock("directory", "savetime", "size"))
                .append(
                    $(getFileBlock("..", "", "", "dir icon"))
                        .click(function () {
                            if (path != rootPath) {
                                path = path.substring(0, path.lastIndexOf("\\"));
                                console.log(path)
                                showFiles(path)
                            }

                        })
                )

            data.files.forEach(
                file => {
                    let classname;
                    if (file.isDirectory == true) classname = "dir icon";
                    else classname = "file icon";
                    $("#files").append(
                        $(getFileBlock(file.name, file.time, file.size, classname))
                            .click(function () {
                                console.log(file.path)
                                if (file.isDirectory == true) {
                                    showFiles(file.path)
                                }
                            })
                    )
                })
        },
        error: function () {
            alert("请求失败")
        }
    })
}

/**
 * 新建目录
 */
function mkdir(path, success, failed) {
    ajax({
        type: "post",
        url: fileServer,
        data: {
            type: "mkdir",
            path: path
        },
        success: success,
        failed: failed
    })
}