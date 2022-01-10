<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="detailBox"
    data-icategory = "${requestScope.detail.icategory}"
>
    <div>
        <h1>Change</h1>
    </div>
    <form action="/board/change" method="post">
        <div class="titleBox">
            <input type="text" name="title" value="${requestScope.detail.title}">
        </div>
        <input type="hidden" name="iboard" value="${requestScope.detail.iboard}">
        <div><textarea name="ctnt" rows="10">${requestScope.detail.ctnt}</textarea></div>
        <div>
            <button type="submit">change</button>
            <a href="/board/detail?iboard=${requestScope.detail.iboard}"><button type="button">cancel</button></a>
        </div>
    </form>

</div>