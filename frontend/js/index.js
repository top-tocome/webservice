// https://picsum.photos/

//文章卡片
function card(title, desc, time, url) {
    if (url == null) url = "https://picsum.photos/500/200?random=" + Math.random()
    return `<a class="card" href="./article.html" target="_blank">
                <div class="front" style="background-image: url(${url});">
                    <p>${title}</p>
                </div>
                <div class="back">
                    <div>
                        <p>${time}</p>
                        <p>${desc}</p>
                    </div>
                </div>
            </a>`
}