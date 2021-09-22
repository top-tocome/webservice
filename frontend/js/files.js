<!--文件管理页面的js文件-->

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
    listFiles(path,
        function (data) {
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
        function (data) {
            alert(data.message)
        })
}