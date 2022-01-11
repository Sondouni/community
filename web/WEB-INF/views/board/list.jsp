<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
    <div class="title_write">
        <div>
            <h1>${requestScope.listTitle.nm}</h1>
        </div>
        <!--글쓰기 버튼-->
        <c:if test="${sessionScope.loginUser!=null}">
            <div>
                <a href="/board/mod?icategory=${requestScope.icategory}"><input type="button" value="write"></a>
            </div>
        </c:if>

    </div>

    <!--게시판 구현-->
    <c:choose>
        <c:when test="${fn:length(requestScope.boardList)==0}">
            글이 없습니다.
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>num</th>
                    <th>title</th>
                    <th>hits</th>
                    <th>writer</th>
                    <th>rdt</th>
                </tr>
                <c:forEach items="${requestScope.boardList}" var="item">
                    <tr class="record"
                        data-iboard="${item.iboard}"
                    >
                        <td>${item.iboard}</td>
                        <td><c:out value="${item.title}" /></td>
                        <td>${item.hits}</td>
                        <td><c:out value="${item.writernm}" /></td>
                        <td>${item.rdt}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <!---->
</div>