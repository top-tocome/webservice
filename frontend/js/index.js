// https://picsum.photos/

//文章卡片
function card(title, desc, url) {
    if (url == null) url = "https://picsum.photos/500/200?random=" + Math.random()
    return `<a class="card" href="#">
                <div class="front" style="background-image: url(${url});">
                    <p>${title}</p>
                </div>
                <div class="back">
                    <div>
                        <p>${desc}</p>
                    </div>
                </div>
            </a>`
}