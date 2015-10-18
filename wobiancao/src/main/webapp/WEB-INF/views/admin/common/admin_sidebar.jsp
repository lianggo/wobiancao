<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="panel panel-default" style="margin-top: 40px">
	<div class="panel-heading">功能列表</div>
	<div class="list-group">
		<a href="${pageContext.request.contextPath}/admin/merchant/list" class="list-group-item ${currentFunction == 'merchant' ? 'active' : ''}">商户管理</a>
		<a href="${pageContext.request.contextPath}/admin/coupon/list" class="list-group-item ${currentFunction == 'coupon' ? 'active' : ''}">优惠券管理</a>
		<a href="${pageContext.request.contextPath}/admin/couponCategory/list" class="list-group-item ${currentFunction == 'couponCategory' ? 'active' : ''}">优惠券类别管理</a>
		<a href="${pageContext.request.contextPath}/admin/customer/list" class="list-group-item ${currentFunction == 'customer' ? 'active' : ''}">用户管理</a>
	</div>
</div>
