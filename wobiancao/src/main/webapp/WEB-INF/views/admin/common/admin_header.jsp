<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header class="navbar navbar-static-top navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">窝边草管理后台</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${sessionScope.admin != null}">
				<li><a href="#">当前用户：${sessionScope.admin}</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/logout">退出登录</a></li>
			</c:if>
		</ul>
	</div>
</header>
