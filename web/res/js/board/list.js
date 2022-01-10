let recordElem = document.querySelectorAll('.record');
if(recordElem){//디테일로 가기
    recordElem.forEach(function (item){
        item.addEventListener('mouseover',function (e){
            item.style.cursor = 'pointer';
            // item.style.backgroundColor = 'black';
            item.style.opacity = '0.5';
        });
        item.addEventListener('mouseout',function (e){
            item.style.cursor = 'default';
            // item.style.backgroundColor = 'white';
            item.style.opacity = '1';
        });
        let iboard = item.dataset.iboard;
        console.log(`${item.dataset.iboard}`);
       item.addEventListener('click',function (e){

           location.href=`/board/detail?iboard=${iboard}`;
       });
    });
}