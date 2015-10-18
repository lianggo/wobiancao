<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="currentModule" value="admin"/>
<c:set var="currentFunction" value="customer"/>

<div id="editModal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">用户领取的优惠券</h4>
			</div>
			<div class="modal-body">
				<div>
					<c:choose>
						<c:when test="${customer.couponsGot.size() == 0}">
							<div class="alert alert-danger">
								<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
								<span>没有记录</span>
							</div>
						</c:when>
						<c:otherwise>
							<table class="table table-bordered">
								<tr>
									<td>序号</td>
									<td>优惠券ID</td>
									<td>名称</td>
									<td>商户名称</td>
									<td>logo</td>
								</tr>
								<c:forEach items="${customer.couponsGot}" var="item" varStatus="itemStatus">
									<tr>
										<td>${itemStatus.count}</td>
										<td>${item.id}</td>
										<td>${item.name}</td>
										<td>${item.merchant.name}</td>
										<td><img src="${item.merchant.logo}" height="30px"></td>
									</tr>
								</c:forEach>
							</table>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="editModal_saveButton" type="button" class="btn btn-primary" data-loading-text="正在保存">保存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
