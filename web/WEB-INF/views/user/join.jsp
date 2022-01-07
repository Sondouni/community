<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="login-join-container">
    <h1>JOIN</h1>
    <form action="/user/join" method="post" id="join-frm">
        <div><label>id : <input type="text" name="uid" id="uidForm" required></label></div>
        <div><input type="button" value="ID duplication Check" id="btn-id-chk"><span id="id-chk-msg"></span></div>
        <div><label>name : <input type="text" name="nm" required></label></div>
        <div><label>pw : <input type="password" name="upw" required></label></div>
        <div><label>re-pw : <input type="password" id="upw-chk" required></label></div>
        <div>
            <label>female<input type="radio" name="gender" value="2" checked></label>
            <label>male<input type="radio" name="gender" value="1"></label>
        </div>
        <div>
            <input type="submit" value="JOIN" id="submit-btn-frm">
            <input type="reset" name="reset" value="RESET">
        </div>
    </form>
</div>