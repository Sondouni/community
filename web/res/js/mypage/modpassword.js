{
    //비밀번호 변경
    const pwChgFormElem = document.querySelector('#pwChgForm');

    if(pwChgFormElem){
        const pwSubmitElem = pwChgFormElem.querySelector('#pwSubmit');
        pwSubmitElem.addEventListener('click',function (e){
            e.preventDefault();
            const curPwElem = pwChgFormElem.querySelector('#current-pw');
            let curPw = curPwElem.value;
            const newPwElem = pwChgFormElem.querySelector('#new-pw');
            let newPW = newPwElem.value;
            const rePwElem = pwChgFormElem.querySelector('#renew-pw');
            let rePw = rePwElem.value;
            const iuser = pwChgFormElem.dataset.iuser;
            console.log(curPw);
            console.log(newPW);
            console.log(rePw);
            console.log(iuser);

            if(newPW!==rePw){
                alert('변경하는 비밀번호를 같게 적어주세요');
                e.preventDefault();
            }else {
                const url = '/user/mypage/modpassword';
                let param = {
                    'upw':curPw,
                    'newUpw':newPW,
                    'iuser':iuser
                };
                fetch(url,{
                    'method':'POST',
                    'headers':{'Content-Type':'application/json'},
                    'body':JSON.stringify(param)
                }).then(function (res){
                    return res.json();
                }).then(function (data){
                    switch (data){
                        case 0:
                            alert('비밀번호 변경실패 : 비밀번호 틀림');
                            break;
                        case 1:
                            alert('비밀번호 변경! 새로 로그인 해주세요');
                            location.href='/user/logout'
                            break;
                        case 2:
                            alert('비밀번호 변경실패 : 알수없는 에러');
                            break;
                    }
                }).catch(function (err){
                    console.log(err);
                });
            }


        });
    }
}
