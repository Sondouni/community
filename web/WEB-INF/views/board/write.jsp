<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h1>write</h1>
    <form action="/board/write" method="post">
        <input type="hidden" class="wicate" name="icategory" value="${param.icategory}">
        <div><label>title : <input type="text" name="title"></label></div>
        <div><label>content : <textarea name="ctnt"></textarea></label></div>
        <div><input type="submit" value="WRITE"></div>
    </form>
</div>