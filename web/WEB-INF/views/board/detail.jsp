<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>
<div class="preNextBtn">
    <c:if test="${requestScope.prevNext.previboard>0}">
        <div>
            <a href="/board/detail?iboard=${requestScope.prevNext.previboard}"><img id="preBtn" class="circular--size40 img-return" src="/res/img/arrowimg.png"></a>
        </div>
    </c:if>
</div>
<div class="detailBox"
    data-icategory = "${requestScope.detail.icategory}"
     data-iboard = "${requestScope.detail.iboard}"
     data-iuser = "${sessionScope.loginUser.iuser}"
     data-boardiuser="${requestScope.detail.iuser}"
>
    <div class="titleBox">
        ${requestScope.preNext.previboard}
        ${requestScope.preNext.nextiboard}
        <nav>${requestScope.detail.catenm}</nav>
        <h1>${requestScope.detail.title}</h1>
    </div>
    <div><textarea readonly rows="10">${requestScope.detail.ctnt}</textarea></div>
    <div><input type="text" value="${requestScope.detail.writernm}"readonly></div>
    <div><input type="text" value="${requestScope.detail.hits}"readonly></div>
    <div><input type="text" value="${requestScope.detail.rdt}"readonly></div>
    <div>
        <a href="/board/list/${requestScope.detail.icategory}"><button type="button">Back</button></a>
        <c:if test="${sessionScope.loginUser.iuser==requestScope.detail.iuser}">
            <a href="/board/mod?iboard=${requestScope.detail.iboard}"><button type="button" id="btnChg">Change</button></a>
            <button type="button" id="btnDel">Delete</button>
        </c:if>
        <hr/>
    </div>
    <!--댓글기능-->
    <div class="cmtBox">
        <c:if test="${sessionScope.loginUser!=null}">
                <form id="cmtFrm">
                    <input type="text" name="cmt">
                    <input class="submit-btn" type="submit" value="comment">
                </form>
        </c:if>
        <div id="cmtList">

        </div>
    </div>

    <!--댓글기능 끝-->
</div>
<div class="preNextBtn">
    <c:if test="${requestScope.prevNext.nextiboard>0}">
        <div>
            <a href="/board/detail?iboard=${requestScope.prevNext.nextiboard}"><img id="nextBtn" class="circular--size40 " src="/res/img/arrowimg.png"></a>
        </div>
    </c:if>
</div>

