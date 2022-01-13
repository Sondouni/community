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

const regex = {
    id: /^([a-zA-Z0-9]{4,15})$/,        //대소문자_숫자조합으로 4~15글자
    pw: /^([a-zA-Z0-9!@_]{4,20})$/,     //대소문자+숫자+!@_ 조합으로 4~20글자
    nm: /^([가-힣]{2,5})$/,             //한글조합으로 2~5글자
    msg: {
        id: '대소문자_숫자조합으로 4~15글자',
        pw: '대소문자+숫자+!@_ 조합으로 4~20글자',
        nm: '한글조합으로 2~5글자',
    },
    isWrongWith: function(target, val) {
        return !this[target].test(val);
    }
};