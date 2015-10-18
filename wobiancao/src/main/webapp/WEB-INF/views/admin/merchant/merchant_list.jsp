<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="currentModule" value="admin"/>
<c:set var="currentFunction" value="merchant"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/admin/common/admin_import.jsp"%>
<title></title>
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/common/admin_header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<%@ include file="/WEB-INF/views/admin/common/admin_sidebar.jsp"%>
			</div>
			<div class="col-md-10">
				<h1 class="page-header">商户管理</h1>
				<div style="margin-bottom:10px">
					<button type="button" class="btn btn-primary" onclick="remoteModal('${pageContext.request.contextPath}/${currentModule}/${currentFunction}/edit')">创建商户</button>
				</div>
				<div>
					<c:choose>
						<c:when test="${page.totalElements == 0}">
							<div class="alert alert-danger">
								<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
								<span>没有找到满足条件的记录</span>
							</div>
						</c:when>
						<c:otherwise>
							<table class="table table-bordered">
								<tr>
									<td>序号</td>
									<td>名称</td>
									<td>描述</td>
									<td>logo</td>
									<td>状态</td>
									<td>操作</td>
								</tr>
								<c:forEach items="${page.content}" var="item" varStatus="itemStatus">
									<tr>
										<td>${itemStatus.count}</td>
										<td>${item.name}</td>
										<td>${item.description}</td>
										<td>${item.logo}</td>
										<td>${item.status}</td>
										<td>
											<button type="button" class="btn btn-primary btn-sm" onclick="remoteModal('${pageContext.request.contextPath}/${currentModule}/${currentFunction}/edit?id=${item.id}')">编辑</button>
											<a href="${pageContext.request.contextPath}/${currentModule}/coupon/list?merchantId=${item.id}" class="btn btn-primary btn-sm">管理优惠券</a>
											<button type="button" class="btn btn-primary btn-sm" onclick="remoteModal('${pageContext.request.contextPath}/${currentModule}/${currentFunction}/confirm?id=${item.id}')">删除</button>
											
										</td>
									</tr>
								</c:forEach>
							</table>
							<nav>
								<tags:pagination page="${page}" showPageCount="20" />
							</nav>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/admin/common/admin_footer.jsp"%>
</body>
</html>
