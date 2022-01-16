let detailElem = document.querySelector('.detailBox');
const iboard = detailElem.dataset.iboard;
console.log('iboard : '+iboard);
const icategory = detailElem.dataset.icategory;
console.log('icategory : '+icategory);
const iuser = detailElem.dataset.iuser;
console.log('iuser : '+iuser);
{

    //삭제
    if(iboard){
        let delBtnElem = document.querySelector('#btnDel');
        if(delBtnElem){
            delBtnElem.addEventListener('click',function (e){
                if(confirm(msg.isdel)){
                    location.href=`/board/delete?icategory=${icategory}&iboard=${iboard}`;
                }
            });
        }
    }
}
{
    //쿼리스트링 숨기기 안됨,,
    // history.replaceState({}, null, location.pathname);
}
{
    //쿼리스트링을 숨기면 새로고침시 쿼리스트링이 없어서 에러가 뜬다.
    // 그래서 새로고침 이벤트를 가로채서 새로운 곳으로 가보자
    /*
    window.onbeforeunload = function (e){
        e = e || window.event;
        e.preventDefault();
        location.href=`/board/detail?iboard=${iboard}`;
    };
    안됨
     */


}
{
    //이전 다음글 ajax 처리

}
{
    //댓글 ajax 기능
    //1.댓글 쓰기
    const cmtFrmElem = document.querySelector('#cmtFrm');
    const cmtListElem = document.querySelector('#cmtList');
    if(cmtFrmElem){
        cmtFrmElem.addEventListener('submit',function (e){
            e.preventDefault();
            let ctnt = cmtFrmElem.cmt.value;
            if(ctnt.length===0){
                alert('댓글 내용을 작성해 주세요');
            }else if(regex.isWrongWith('ctnt',ctnt)){
                alert('댓글내용중에 부적절한것이 잇습니다.')
            }else {
                insBoardCmtAjax(ctnt);
                cmtFrmElem.cmt.value = '';
            }
        });
    }
    //댓글 리스트
    if(cmtListElem){
        getCmtList(iboard);
    }


}
//댓글 쓰기 함수
function insBoardCmtAjax(ctnt){
    const entity = {
        iuser : iuser,
        iboard : iboard,
        ctnt : ctnt
    }
    const header = {
        'method':'POST',
        'headers':{'Content-Type':'application/json'},
        'body':JSON.stringify(entity)
    }
    let insUrl = '/board/cmt/ins';
    fetch(insUrl,header)
        .then(function (res){
            return res.json();
        }).then(function (data){
            const cmtListDivIDElem = document.querySelectorAll('.cmtListDiv');
            if(cmtListDivIDElem){
                console.log('삭제')
                cmtListDivIDElem.forEach(function (item){
                    item.remove();
                });
            }
            getCmtList(iboard);
    }).catch(function (err){
        console.log(err);
    });
}

//리스트 호출 함수
function getCmtList (iboard){
    let listUrl = `/board/cmt/list?iboard=${iboard}`;
    fetch(listUrl)
        .then(function (res){
            return res.json();
        }).then(function (data){
            console.log(typeof data.result);
            let cmtList = data.result;
            makeList(cmtList);
    }).catch(function (err){
        console.log(err);
    });
}
//리스트를 받아서 div 태그 만들어주기
function makeList (cmtList){
    const cmtListElem = document.querySelector('#cmtList');
    console.log(cmtList.length);
    console.log(typeof cmtList);
    cmtList.forEach(function (item){
        let divElem = document.createElement('div');
        divElem.className = 'cmtListDiv';
        divElem.innerHTML = `
        <input type="text" class="listNm" name="nm" value="${item.nm}" readonly/>
        <input type="text" class="listCtnt" name="ctnt" value="${item.ctnt}" readonly/>
        <input type="text" class="listRdt" name="rdt" value="${item.rdt}" readonly/>
          `;
        cmtListElem.appendChild(divElem);
        console.log('cmt iuser : '+item.iuser)
        console.log('login iuser : '+iuser);
        if(item.iuser==iuser){
            let btnDivElem = document.createElement('div');
            btnDivElem.className = 'cmtBtnsDiv';
            btnDivElem.innerHTML=`
            <input type="button" value="change" id="cmtModBtn"/>
            <input type="button" value="delete" id="cmtDelBtn"/>
             `;
            divElem.appendChild(btnDivElem);
        }
    });
}