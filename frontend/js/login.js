function login(id, pwd) {
    $.ajax({
        type: "post",
        url: server + "login",
        data: {
            id: id,
            pwd: pwd,
        },
        success: function (data) {
            console.log(data)
            let json = JSON.parse(data)
            if (json.code != 0) {
                alert(json.message)
                return;
            }
        },
        error: function () {
            alert("请求失败")
        }
    })
}