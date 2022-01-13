<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:importAttribute name="subMenuList"/>
<div class="p10 leftmenu">
    <ul>
        <c:forEach items="${subMenuList}" var="item">
            <c:set var="className" value="${item.href == lastPath ? 'submenu-selected' : ''}" />
            <li class="p-ud5-lr10 ${className}"><a href="/user/mypage/${item.href}">${item.nm}</a></li>
        </c:forEach>
    </ul>
</div>
