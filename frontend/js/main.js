//init


$(document).ready(function () {
        //导航栏
        $("div.navbar").append(`
    <a href="./index.html">主页</a>
    <a href="./files.html">文档管理</a>
    <a href="./tools.html">脚本类工具</a>`)

        $("#showLogin").click(function () {
            //检测登录和隐藏登录
            if (session != null) {
                checkLoginState(function () {
                    $("div.navbar").append($(`<a href="#" class="right">退出登录</a>`).click(
                        function () {
                            loginOut(function () {
                                alert("退出登录成功")
                                localStorage.removeItem("session")
                            })
                        })).append(`<a href="./editer.html" class="right">新文章</a>`)
                }, function (data) {
                    alert(data.message)
                    localStorage.removeItem("session")
                })
            } else {
                $("div.navbar").append(`<a href="./login.html" class="right">登录</a>`)
                $("#showLogin").remove()
            }
        })

    }
)