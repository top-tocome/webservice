const server = "http://localhost:8000/";
const rootPath = "."

function getFileBlock(directory, savetime, size, classname) {
    return `<tr>
                <td>
                <a class="${classname}">${directory}</a>
                </td>
                <td>${savetime}</td>
                <td>${size}</td>
            </tr>`;
}

function showFiles(path) {
    $.ajax({
        type: "post",
        url: server + "files",
        data: {
            path: path
        },
        success: function (data) {
            console.log(data)
            let json = JSON.parse(data)
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

            json.forEach(
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
            alert("error occur")
        }
    })
}