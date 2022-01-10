{
    let detailElem = document.querySelector('.detailBox');
    const iboardElem = detailElem.dataset.iboard;
    const icategory = detailElem.dataset.icategory;
    console.log(iboardElem);
    //삭제
    if(iboardElem){
        let delBtnElem = document.querySelector('#btnDel');
        delBtnElem.addEventListener('click',function (e){
           if(confirm(msg.isdel)){
               location.href=`/board/delete?icategory=${icategory}&iboard=${iboardElem}`;
           }
        });
    }
}