//메세지 객체
const msg = {
    'isdel':'are you sure to delete??',

    'fnIsDel' : function (target){
        return `${target}을(를)`+this.isdel;
    }

}

function cateColor(icategory){
    let curMenuPage = document.querySelector('#menu'+icategory);
    curMenuPage.style.color = 'rgba(213,93,93,0.5)';
    curMenuPage.style.fontWeight = 'bold';
}
{
    //현재 주소창을 가져와서 선택된 메뉴창 분기
    //강사님이랑 좀 다름, 나중에 찾아보기(fn:split)
    let curUrl = window.location.href;
    if(curUrl.includes('list')){
        let icategory = curUrl.replace('http://localhost:8090/board/list/','');
        console.log(icategory);
        cateColor(icategory);
    }
}


{
    //디테일에서도 메뉴 컬러 유지
    let detailBoxElem = document.querySelector('.detailBox');
    if(detailBoxElem){
        cateColor(detailBoxElem.dataset.icategory);
    }
}