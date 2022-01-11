<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="detailBox"
    data-icategory = "${requestScope.detail.icategory}"
     data-iboard = "${requestScope.detail.iboard}"
>
    <div class="titleBox">
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
    </div>
</div>