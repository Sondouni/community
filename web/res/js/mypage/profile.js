{
    //DataElem
    const dataElem = document.querySelector('#data');
    //input type = 'file'
    const profileFileElem = document.querySelector('#profile-file');
    if(profileFileElem){
        profileFileElem.addEventListener('change',function (e){
            const img = profileFileElem.files[0];
            if(img){
                uploadProfileImg(img);
            }
        });
    }
    //프로필 이미지 클릭 이벤트
    const profileViewElem = document.querySelector('#profile-view');
    if(profileViewElem){
        profileViewElem.addEventListener('click',function (e){
            if(profileFileElem){
                profileFileElem.click();
            }
        });
    }

    const uploadProfileImg = (img) =>{
        const fData = new FormData();
        fData.append('profileimg',img);

        var url = '/user/mypage/profile';

        fetch(url,{
            'method':'POST',
            'body':fData
        })
            .then(res=>
                res.json())
            .then(data=>{
                console.log(data);
                /*
                img에 아이디넣고, substring으로 하는방법
                let profileImageElem = document.querySelector('#profile-img');
                let pathElem = profileImageElem.src;
                console.log(pathElem.substring(0,pathElem.lastIndexOf('/'))+'/'+data.result);
                profileImageElem.src = pathElem.substring(0,pathElem.lastIndexOf('/'))+'/'+data.result;
                 */
                setProfileImg(data);
            }).catch((e)=>{
                console.log(e);
        });
    }
    //이미지 세팅
    const setProfileImg = (data) =>{
        if(!data.result){ return; }
        const iuser = dataElem.dataset.iuser;
        const img = profileViewElem.querySelector('img');
        const imgHeader = document.querySelector('#headerProfileImage');
        img.src = `/images/user/${iuser}/${data.result}`;
        imgHeader.src = `/images/user/${iuser}/${data.result}`;
    }

}