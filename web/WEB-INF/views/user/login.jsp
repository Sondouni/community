<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<h1>LOGIN</h1>
<form action="/user/login" method="post">
    <div>
        <label>ID : <input type="text" name="uid" required></label>
    </div>
    <div>
        <label>PW : <input type="password" name="upw" required></label>
    </div>
    <div>
        <input type="submit" value="LOGIN">
       <a href="/user/join"><input type="button" value="JOIN"></a>
    </div>
</form>