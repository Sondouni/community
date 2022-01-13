<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>
<!--프로필 이미지 경로 -->
<c:set var="profileImg" value="/res/img/defaultProfile.png"/>
<c:if test="${sessionScope.loginUser.profileimg!='profileImg'}">
    <c:set var="profileImg" value="/images/user/${sessionScope.loginUser.iuser}/${sessionScope.loginUser.profileimg}"/>
</c:if>
<div>
    <h1>Profile</h1>
    <div>
        <div id="profile-view" class="pointer circular--img circular--size300">
            <img id="profile-img" src="${profileImg}">
        </div>
        <my:profileImg
                idVal="profile-view"
                classVal="pointer circular--img circular--size300"
                iuser="${sessionScope.loginUser.iuser}"
                profileImgVal="${sessionScope.loginUser.profileimg}"/>
        <div id="data" data-iuser="${sessionScope.loginUser.iuser}"></div>
        <input type="file" id="profile-file" class="hidden" accept="image/*">
        <pre>
         ID       : ${sessionScope.loginUser.uid}
        </pre>
        <pre>
         Name     : ${sessionScope.loginUser.nm}
        </pre>
        <pre>
         Gender   : ${sessionScope.loginUser.gender==1?'male':'female'}
        </pre>
        <pre>
         JoinDate : ${sessionScope.loginUser.rdt}
        </pre>
    </div>
</div>
