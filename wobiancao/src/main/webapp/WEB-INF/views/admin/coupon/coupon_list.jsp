<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="currentModule" value="admin"/>
<c:set var="currentFunction" value="coupon"/>
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
				<h1 class="page-header">优惠券管理 <small>${merchant.name}</small></h1>
				<div style="height: 50px">
					<form id="searchForm" method="get" action="${pageContext.request.contextPath}/${currentModule}/${currentFunction}/list" class="form-inline">
						<label for="categoryId">选择一个类别：</label>
						<select id="categoryId" name="categoryId" class="form-control">
							<c:forEach items="${couponCategories}" var="couponCategory">
								<option value="${couponCategory.id}" ${currentCategoryId == couponCategory.id ? "selected" : ""}>${couponCategory.name}</option>
							</c:forEach>
						</select>
						<button type="submit" class="form-control btn btn-primary">搜索</button>
					</form>
				</div>
				<div style="margin-bottom:10px">
					<button type="button" class="btn btn-primary" onclick="remoteModal('${pageContext.request.contextPath}/${currentModule}/${currentFunction}/edit?merchantId=${merchant.id}')">添加优惠券</button>
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
									<td>ID</td>
									<td>名称</td>
									<td>商户</td>
									<td>类别</td>
									<td>领取数</td>
									<td>开始时间</td>
									<td>结束时间</td>
									<td>操作</td>
								</tr>
								<c:forEach items="${page.content}" var="item" varStatus="itemStatus">
									<tr>
										<td>${itemStatus.count}</td>
										<td>${item.id}</td>
										<td>${item.name}</td>
										<td>${item.merchant.name}</td>
										<td>${couponCategoryMap[item.categoryId].name}</td>
										<td>${item.getCount}</td>
										<td><fmt:formatDate value="${item.timeBegin}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${item.timeEnd}" pattern="yyyy-MM-dd"/></td>
										<td>
											<button type="button" class="btn btn-primary btn-sm" onclick="remoteModal('${pageContext.request.contextPath}/${currentModule}/${currentFunction}/edit?id=${item.id}')">编辑</button>
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
