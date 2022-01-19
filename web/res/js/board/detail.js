let detailElem = document.querySelector('.detailBox');
const iboard = detailElem.dataset.iboard;
console.log('iboard : '+iboard);
const icategory = detailElem.dataset.icategory;
console.log('icategory : '+icategory);
const iuser = detailElem.dataset.iuser;
console.log('iuser : '+iuser);
//댓글 리스트 id
const cmtListElem = document.querySelector('#cmtList');
console.log('리스트 엘레먼트');
console.log(cmtListElem);

{
    //현재 시간 구하기
    let today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth();
    let data = today.getDate();
    let hours = today.getHours();
    let minutes = today.getMinutes();
    function timepassed(rdt){
        let timearr = rdt.split(' ');
        let timeymd = timearr[0].split('-');
        let timehms = timearr[1].split(':');
        var rdtDate = new Date(timeymd[0],parseInt(timeymd[1])-1,timeymd[2],timehms[0],timehms[1]);
        var curDate = new Date(year,month,data,hours,minutes);
        var elapsedSec = (curDate.getTime()-rdtDate.getTime())/60000;
        if(elapsedSec<60){
            return elapsedSec+'분 전';
        }else if(elapsedSec>=60 && elapsedSec<=1440){
            return Math.round(elapsedSec/60) + '시간 전';
        }else if(elapsedSec>1440 && elapsedSec<= 7200){
            return Math.round(elapsedSec/1440) + '일 전';
        }else {
            return timeymd.join('-');
        }
    }
}

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
            /*
            const cmtListDivIDElem = document.querySelectorAll('.cmtListDiv');

            if(cmtListDivIDElem){
                console.log('삭제')
                cmtListDivIDElem.forEach(function (item){
                    item.remove();
                });
            }
            getCmtList(iboard);
             */
        console.log(data);
        if(data!=0){
            if(cmtListElem.lastElementChild==null){
                cmtListElem.innerHTML=null;
            }
            const insertObj = {
                icmt : data,
                nm : detailElem.dataset.nm,
                profileimg : detailElem.dataset.profileimg,
                ctnt : ctnt,
                iuser : iuser,
                rdt : 'new'
            }
            let divElem = makeDiv(insertObj);
            window.scrollTo({top:divElem.offsetTop,behavior:'smooth'});
        }
    }).catch(function (err){
        console.log(err);
    });
}

//리스트 호출 함수
function getCmtList (iboard){
    let listUrl = `/board/cmt/${iboard}`;
    myFetch.get(listUrl,setCmtList);
    /*
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

     */
}
//리스트를 받아서 div 태그 만들어주기
function setCmtList (cmtList){
    if(cmtList.length==0){
        cmtListElem.innerHTML = 'please write a comment';
    }
    console.log(cmtList.length);
    console.log(typeof cmtList);
    cmtList.forEach(function (item){
        makeDiv(item);
    });
}
function makeDiv(item){
    if(item.rdt.length>5){
        item.rdt = timepassed(item.rdt);
    }
    let divElem = document.createElement('div');
    divElem.className = 'cmtListDiv';
    divElem.innerHTML = `
        <input type="text" class="listIcmt" value="${item.icmt}"/>
        <div class="listNmProfile">
            <div class="listNm">${item.nm}</div>
            <img class="circular--size40 circular--img" src="${item.profileimg=='profileImg'?'/res/img/defaultProfile.png':'/images/user/'+item.iuser+'/'+item.profileimg}"/>
        </div>
        <input type="text" class="listCtnt" name="ctnt" value="${item.ctnt}" readonly/>
        <input type="text" class="listRdt" name="rdt" value="${item.rdt}" readonly/>
          `;
    cmtListElem.appendChild(divElem);
    console.log('cmt iuser : '+item.iuser)
    console.log('login iuser : '+iuser);
    if(item.iuser==iuser){
        let btnDivElem = document.createElement('div');
        let btnDel = addDelBtn(item.icmt,divElem);
        let btnMod = addModBtn(divElem,btnDivElem,item.icmt);
        btnDivElem.appendChild(btnDel);
        btnDivElem.appendChild(btnMod);

        divElem.appendChild(btnDivElem);
        /*
        btnDivElem.className = 'cmtBtnsDiv';
        btnDivElem.innerHTML=`
            <input type="button" value="change" id="cmtModBtn"/>
            <input type="button" value="delete" id="cmtDelBtn"/>
             `;
        divElem.appendChild(btnDivElem);

         */
    }
    return divElem;
}
//댓글 삭제 버튼
function addDelBtn(icmt,divElem){
    let btnDel = document.createElement('button');
    btnDel.innerText = 'delete';
    btnDel.addEventListener('click',function (e){
       if(confirm('are you sure to delete this comment?')){
           let url = `/board/cmt/${icmt}`;
           myFetch.del(url,function (data){
               console.log(data);
               if(data!=1){
                   alert('fail to delete comment');
               }else {
                   divElem.remove();
                   console.log('삭제후');
                   console.log(cmtListElem.lastElementChild);
                    if(cmtListElem.lastElementChild==null){
                        cmtListElem.innerHTML = 'please write a comment';
                    }
               }
           });
       }
    });
    return btnDel;
}
function addModBtn(divElem,btnDivElem,icmt){
    let btnMod = document.createElement('button');
    btnMod.innerText = 'change';
    let divCtnt = divElem.querySelector('.listCtnt');
    btnMod.addEventListener('click',function (e){
        //현재 값 가져오기
        let oldCtnt = divCtnt.value;

        let subBtnDivElem = document.createElement('div');

        //다른곳에 change가 눌러졌다면 cancel눌러주기기
       let divCtntArr = document.querySelectorAll('.listCtnt');
        divCtntArr.forEach(function (divCtnt){
            if (!divCtnt.readOnly){
                let ModCancelBtnElem = document.querySelector('#ModCancelBtn');
                ModCancelBtnElem.click();
            }
        });


        divCtnt.readOnly = false;
        //change버튼 클릭시 divCtnt 스타일 변경
        divCtnt.style.border = '1px solid #000000';
        //먼저 있던 버튼들을 삭제
        btnDivElem.remove();
        let modSubmit = document.createElement('button');
        modSubmit.innerText = 'submit';
        let cancelBtn = document.createElement('button');
        cancelBtn.id = 'ModCancelBtn';
        cancelBtn.innerText = 'cancel';
        //새로운 div를 추가해서 버튼을 삽입
        subBtnDivElem.appendChild(modSubmit);
        subBtnDivElem.appendChild(cancelBtn);

        divElem.appendChild(subBtnDivElem);
        console.log(btnDivElem);
        //cancel,submit 이벤트 추가
        cancelBtn.addEventListener('click',function (e){
            subBtnDivElem.remove();
            divElem.appendChild(btnDivElem);
            divCtnt.value = oldCtnt;
            divCtnt.readOnly = true;
            divCtnt.style.border = 'none';
        });
        modSubmit.addEventListener('click',function (e){
            let modUrl = `/board/cmt`;
            let param = {
                ctnt : divCtnt.value,
                icmt : icmt
            }
            myFetch.put(modUrl,(data)=>{
                if(data==1){
                    let ModCancelBtnElem = document.querySelector('#ModCancelBtn');
                    ModCancelBtnElem.click();
                    divCtnt.value = param.ctnt;
                }else {
                    alert('fail to change your comment');
                }
            },param);
        });
    });
    return btnMod;
}

{
    //좋아요 ajax 처리

    const favBtnElem = document.querySelector('#favBtn');
    const favListElem = document.querySelector('#favList');
    let isLike;
    /*
    const selFav = () =>{
        return  fetch(`/board/fav/${iboard}`).then(res=>res.json());
    }
    async function isLikeConfirm(){
        let isLike;
        try{
            isLike = await selFav();
        }catch (err){
            console.log(err)
        }
        return isLike;

        let isLike = isLikeConfirm();
        console.log(isLike);
    }
     */
    //하트를 띄어주기
    const selFav = () =>{
        myFetch.get(`/board/fav/${iboard}`,(data)=>{
            if(data.result==1){
                favBtnElem.className = 'fas fa-heart color-pink fs-30';
                isLike = 1;
            }else {
                favBtnElem.className = 'far fa-heart color-pink fs-30';
                isLike = 0;
            }
            selFavList();
        });
    }
    //좋아요 갯수, 리스트 뿌려주기
    const selFavList = ()=>{
        myFetch.get(`/board/fav?iboard=${iboard}`,(data)=>{
            favListElem.innerHTML = `Like&nbsp${data.result.length}`;
            favListElem.addEventListener('click',function (e){

            });
        });
    }


    selFav();


    favBtnElem.addEventListener('click',(e)=>{
        console.log('isLike : '+isLike);
        if(isLike==0){
            if(!iuser){
                alert('you can add heart only after login');
                return;
            }
            myFetch.post('/board/fav',(data)=>{
                console.log(data);
                if(data.result!=1){
                    alert('fail to like');
                }
                selFav();
            },{
                iboard : iboard
            });
        }else {
            myFetch.del(`/board/fav/${iboard}`,(data)=>{
                if(data.result!=1){
                    alert('fail to cancel');
                }
                selFav();
            });
        }
    });


}