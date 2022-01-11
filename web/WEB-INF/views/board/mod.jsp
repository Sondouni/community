<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="detailBox"
    data-icategory = "${requestScope.detail.icategory}"
>
    <div>
        <h1>${requestScope.title}</h1>
    </div>
    <form action="/board/mod" method="post" id="modForm">
        <div class="titleBox">
            <input type="text" name="title" value="${requestScope.detail.title}">
        </div>
        <input type="hidden" name="iboard" value="${requestScope.detail!=null?requestScope.detail.iboard:0}">
        <input type="hidden" name="icategory" value="${param.icategory!=null?param.icategory:requestScope.detail.icategory}">
        <div><textarea name="ctnt" rows="10">${requestScope.detail.ctnt}</textarea></div>
        <div>
            <button type="submit">${requestScope.detail!=null?"change":"write"}</button>
            <a href="${requestScope.detail!=null?"/board/detail?iboard="+=requestScope.detail.iboard:"/board/list/"+=param.icategory}"><button type="button">cancel</button></a>
        </div>
    </form>

</div>