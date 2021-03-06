<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:importAttribute name="menuList"/>

<header class="h50">
    <div class="flex-container p-lr-20 flex-align-center">
        <c:choose>
            <c:when test="${sessionScope.loginUser==null}">
                <div class="m-r-20"><a href="/user/login"><input type="button" value="LOGIN"></a></div>
            </c:when>
            <c:otherwise>
                <div class="m-r-20"><a href="/user/logout" class="font-color-white"><input type="button" value="LOG_OUT"></a></div>
                <div class="m-r-20 circular--size40 pointer circular--img"><a href="/user/mypage/profile" ><img id="headerProfileImage" src="${sessionScope.loginUser.profileimg=='profileImg'?'/res/img/defaultProfile.png':'/images/user/'+=sessionScope.loginUser.iuser+='/'+=sessionScope.loginUser.profileimg}"></a></div>
            </c:otherwise>
        </c:choose>

        <c:forEach items="${menuList}" var="item">
                <div class="m-r-20"><a href="/board/list/${item.icategory}" id="menu${item.icategory}" class="font-color-my">${item.nm}</a></div>
        </c:forEach>
    </div>
</header>