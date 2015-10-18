<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="showPageCount" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	int current = page.getNumber() + 1; //当前页
	int begin = Math.max(1, current - showPageCount / 2); //显示的起始页
	int end = Math.min(begin + (showPageCount - 1), page.getTotalPages()); //显示的终止页

	request.setAttribute("current", current);
	request.setAttribute("begin", begin);
	request.setAttribute("end", end);
%>

<ul class="pagination pull-right">

	<c:choose>
		<c:when test="${page.number == 0}">
			<li class="disabled"><span>&laquo;</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="?page=0${searchParamsUrl}">&laquo;</a></li>
		</c:otherwise>
	</c:choose>

	<c:forEach var="i" begin="${begin}" end="${end}">
		<c:choose>
			<c:when test="${i == current}">
				<li class="active"><span>${i}</span></li>
			</c:when>
			<c:otherwise>
				<li><a href="?page=${i-1}${searchParamsUrl}">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${page.number == page.totalPages - 1}">
			<li class="disabled"><span>&raquo;</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="?page=${page.totalPages-1}${searchParamsUrl}">&raquo;</a></li>
		</c:otherwise>
	</c:choose>

</ul>

<ul class="pagination">
	<li><span style="border: none; background-color: #fff;">共${page.totalPages}页，${page.totalElements}条记录</span></li>
</ul>
