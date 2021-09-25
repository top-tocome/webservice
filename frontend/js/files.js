<!--文件管理页面的js文件-->

/**
 * 单个文件的展示块
 */
function getFileBlock(name, id) {
    return `<div>
                <input id="${id}" type="checkbox">
                <label for="${id}">${name}</label>
                <div class="sub"></div>
            </div>`;
}

/**
 * 请求数据更新文件并展示
 */
function showFiles(path, dirId) {
    if ($("#" + dirId).siblings("div.sub").html() == "")
        listFiles(path, function (data) {
            path = data.realPath;
            $("h1.treePath").text(path)
            let i = 0
            data.files.forEach(
                file => {
                    if (file.isDirectory == true) {
                        let id = dirId + "-" + i
                        $("#" + dirId).siblings("div.sub").append($(getFileBlock(file.name, id)))
                        $("#" + id).click(
                            function () {
                                showFiles(file.path, id)
                            })
                        i++;
                    } else
                        $("#" + dirId).siblings("div.sub").append(
                            $(`<a href="#">${file.name}</a>`).click(
                                function () {
                                    downloadFile(file.path, file.name)
                                }))
                })
        })
}