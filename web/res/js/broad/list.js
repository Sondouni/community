const matlistElem = document.querySelectorAll('.matlist');
const youtubeBoxElem = document.querySelector('#youtubeBox');

let searchKeyWord = findkeyword(matlistElem[0].dataset.restnm,matlistElem[0].dataset.addr);
showYoutube(searchKeyWord);

if(matlistElem){
    matlistElem.forEach((item)=>{
        item.addEventListener('click',(e)=>{
            showYoutube(findkeyword(item.dataset.restnm,item.dataset.addr))
        }) ;
    });
}
function showYoutube(searchKeyWord){
    let myApiKey = "AIzaSyBZRrYQ1XdveD-B4yT0gqbmNdp1HkZr5nY";
    let url = `https://www.googleapis.com/youtube/v3/search?q=${searchKeyWord}&key=${myApiKey}`;
    fetch(url)
        .then(function (res){
            return res.json();
        }).then((data)=>{
            let list = data.items;
            makeYoutube(list);
    });
}

function makeYoutube(list){
    youtubeBoxElem.innerHTML = null;
    for(var i =0;i<list.length;i++) {
        if (list[i].id.videoId) {
            let vidDivElem = document.createElement('div');
            vidDivElem.innerHTML = `
                        <iframe class="iframeVid"
                            src="https://www.youtube.com/embed/${list[i].id.videoId}"
                            width="300" height="180"
                            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen>
                        </iframe>
                    `;
            youtubeBoxElem.append(vidDivElem);
            break;
        }
    }

}
function findkeyword(restnm,addr){
    addr= addr.split(' ')[0];
    let keyWord = addr+' '+restnm;
    return keyWord;
}