<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="firstUrl" value="/${resourcePath}/pages/1" />
<c:url var="lastUrl" value="/${resourcePath}/pages/${deploymentLog.totalPages}" />
<c:url var="prevUrl" value="/${resourcePath}/pages/${currentIndex - 1}" />
<c:url var="nextUrl" value="/${resourcePath}/pages/${currentIndex + 1}" />

<div class="row">
  <div class="col-sm-6">
	 <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
    <ul class="pagination">
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <!-- <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li> -->
                <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="#">Previous</a></li>
            </c:when>
            <c:otherwise>
               <%--  <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li> --%>
                <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="${prevUrl}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/${resourcePath}/pages/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <%-- <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li> --%>
                    <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <%-- <li><a href="${pageUrl}"><c:out value="${i}" /></a></li> --%>
                    <li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == deploymentLog.totalPages}">
               <!--  <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li> -->
                <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="#">Next</a></li>
            </c:when>
            <c:otherwise>
                <%-- <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li> --%>
                <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="${nextUrl}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    </div>
  </div>
</div>