<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
<div>
    <c:forEach items="${requestScope.list}" var="item">
        <div class="matlist" style="display: flex;gap: 30px;margin-bottom: 20px;"
            data-restnm = "${item.restnm}"
            data-addr = "${item.addr}"
        >
            <div>${item.restnm}</div>
            <div>${item.menunm}</div>
            <div>${item.addr}</div>
        </div>
    </c:forEach>
</div>
<div id="youtubeBox" style="height: 100%">
</div>
</body>
</html>