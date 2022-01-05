{
    //id 중복체크 함수
    const setIdChkMsg = (data) =>{
        const idChkMsgElem = document.querySelector('#id-chk-msg')
        switch (data.result){
            case 1:
                idChkMsgElem.innerHTML = `not possible to make new ID`;
                break;
            case 0:
                idChkMsgElem.innerHTML = `possible to make new ID`;
                break;
        }
    }

    let dataResult;
    //id 정규식
    const idRegex = /^([A-Za-z0-9]{4,15})$/g;
    const pwRegex = /^([A-Za-z0-9!@_]{4,20})$/g;
    const nmRegex = /^([가-힣]{2,5})$/g;

    const idFailMsg = 'id should be writen 4~12char, with english and number';
    //id 중복체크
    const joinFrmElem = document.querySelector('#join-frm');
    if(joinFrmElem){
        //id에 값이 있나없나 체크하고 -> id 중복체크
        const idBtnChkElem = joinFrmElem.querySelector('#btn-id-chk');
        const idChkSpanElem = joinFrmElem.querySelector('#id-chk-msg');
        const submitBtnElem = joinFrmElem.querySelector('#submit-btn-frm');
        idBtnChkElem.addEventListener('click',()=>{
            const idVal = joinFrmElem.uid.value;
            const pwVal = joinFrmElem.upw.value;
            const rePwVal = joinFrmElem.querySelector('#upw-chk');
            if(idVal.length<4){
                alert('please type more than 4 char');
                return;
            }else if(!idRegex.test(idVal)){
                alert(idFailMsg);
                return;
            }
            if(idVal){
                var url = `/user/idchk/${idVal}`;

                fetch(url)
                    .then(res=> res.json())
                    .then((data)=>{
                        console.log(data);
                        console.log(data.result);
                        dataResult = data.result;
                        /*
                        if(data.result===1){
                            alert('you can use your ID');
                            idChkSpanElem.innerHTML=`사용할수 있는 아이디 입니다`;
                        }else {
                            alert('it`s duplicated ID');
                            idChkSpanElem.innerHTML=`이미 사용중인 아이디 입니다`;
                        }

                         */
                        switch (data.result){
                            case 1:
                                idChkSpanElem.innerHTML = `가능`;
                                break;
                            case 0 :
                                idChkSpanElem.innerHTML = `불가능`;
                                break;
                        }
                    }).catch((e)=>{
                        console.log(e);
                });
    /*
                fetch(url)
                    .then(function (res){
                        return res.json();
                    }).then(function (data){
                });

     */
            }
        });
        //아이디 수정시
        resetBtnElem = joinFrmElem.reset;
        resetBtnElem.addEventListener('click',function (e){
            idChkSpanElem.innerHTML = "";
        });
        const uidElem = joinFrmElem.querySelector('#uidForm');
        uidElem.addEventListener('keyup',function (e){
            idChkSpanElem.innerHTML = "";
            // console.log('수정전'+dataResult);
            dataResult = undefined;
            // console.log(dataResult);
        });
        //join클릭시
        submitBtnElem.addEventListener('click',function (e){
            console.log(dataResult);
            const uid = joinFrmElem.uid.value;
            const upw = joinFrmElem.upw.value;
            const nm = joinFrmElem.nm.value;
            const rePwVal = joinFrmElem.querySelector('#upw-chk');

            console.log(idRegex.test(uid));
            console.log(uid);
            console.log(pwRegex.test(upw));
            console.log(upw);
            console.log(nmRegex.test(nm));
            console.log(nm);
            if(!idRegex.test(uid)){
                alert(idFailMsg);
                e.preventDefault();
            }else if(!pwRegex.test(upw)){
                alert('password should be writen 4~20char, with english, number, !@_');
                e.preventDefault();
            }else if(!nmRegex.test(nm)){
                alert('name should be writen 2~4char, in korean');
                e.preventDefault();
            }else if(!(upw===rePwVal)){
                alert('please write same password');
                e.preventDefault();
            }else if(idChkSpanElem.innerHTML===`불가능`){
                alert('다른 아이디를 이용해주세요.')
                e.preventDefault();
            }else if (idChkSpanElem.innerHTML===''){
                alert('아이디 중복 체크를 해주세요.')
                e.preventDefault();
            } else{
                joinFrmElem.submit();
            }
        });
    }
    console.log(dataResult);

}