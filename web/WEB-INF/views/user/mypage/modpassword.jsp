<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h1>비밀번호변경</h1>
    <form id="pwChgForm" action="/user/mypage/modpassword" method="post"
        data-iuser = "${sessionScope.loginUser.iuser}"
    >
        <div><label>현재 비밀번호 : <input type="password" id="current-pw"></label></div>
        <div><label>변경 비밀번호 : <input type="password" id="new-pw"></label></div>
        <div><label>확인 비밀번호 : <input type="password" id="renew-pw"></label></div>
        <div><input type="submit" value="Change" id="pwSubmit"></div>
    </form>
</div>
