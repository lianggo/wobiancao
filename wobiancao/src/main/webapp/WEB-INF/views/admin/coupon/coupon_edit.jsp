<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="currentModule" value="admin"/>
<c:set var="currentFunction" value="coupon"/>
<script type="text/javascript">
$('#editModal_saveButton').on('click', function () {
	var merchantId = $('#editModal select[name=merchantId]').val();
	var name = $('#editModal input[name=name]').val();
	var slogan = $('#editModal input[name=slogan]').val();
	var description = $('#editModal input[name=description]').val();
	var categoryId = $('#editModal select[name=categoryId]').val();
	var likeCount = $('#editModal input[name=likeCount]').val();
	var thirdUrl = $('#editModal input[name=thirdUrl]').val();
	var timeBegin = $('#editModal input[name=timeBegin]').val();
	var timeEnd = $('#editModal input[name=timeEnd]').val();
	var status = $('#editModal select[name=status]').val();
	
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/${currentModule}/${currentFunction}/save',
			timeout : 60000,
			data : {
				id : '${item.id}',
				merchantId : merchantId,
				name : name,
				slogan : slogan,
				description : description,
				categoryId : categoryId,
				likeCount : likeCount,
				thirdUrl : thirdUrl,
				timeBegin : timeBegin,
				timeEnd : timeEnd,
				status : status
			},
			dataType : 'json',
			beforeSend : function() {
				$('#editModal_saveButton').button('loading');
			},
			complete : function() {
				$('#editModal_saveButton').button('reset');
				$('#editModal').on('hidden.bs.modal', function() {
					document.location.reload();
				})
			},
			success : function(data, textStatus) {
				if (data.status == 0) {
					$('#editModal_successText').show();
					$('#editModal_failText').hide();
				} else {
					$('#editModal_errorMessage').html(data.message);
					$('#editModal_failText').show();
					$('#editModal_successText').hide();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(errorThrown);
				$('#editModal_errorMessage').html(textStatus);
				$('#editModal_failText').show();
				$('#editModal_successText').hide();
			}
		});
	});
</script>

<div id="editModal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">编辑优惠券信息</h4>
			</div>
			<div class="modal-body">
				<div id="editModal_successText" class="alert alert-success" style="display: none">
					<strong>保存成功</strong>
				</div>
				<div id="editModal_failText" class="alert alert-danger" style="display: none">
					<strong id="editModal_errorMessage"></strong>
				</div>
				<div id="editModal_form">
					<div class="form-group">
						<label for="name">名称</label>
						<input id="name" name="name" value="${item.name}" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="slogan">广告语</label>
						<input id="slogan" name="slogan" value="${item.slogan}" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="description">描述</label>
						<input id="description" name="description" value="${item.description}" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="merchantId">商户</label>
						<select id="merchantId" name="merchantId" class="form-control">
							<c:forEach items="${merchants}" var="merchant">
								<option value="${merchant.id}" ${currentMerchantId == merchant.id ? "selected" : ""}>${merchant.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="categoryId">类别</label>
						<select id="categoryId" name="categoryId" class="form-control">
							<c:forEach items="${couponCategories}" var="couponCategory">
								<option value="${couponCategory.id}" ${item.categoryId == couponCategory.id ? "selected" : ""}>${couponCategory.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="likeCount">喜欢数</label>
						<input id="likeCount" name="likeCount" value="${item.likeCount}" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="thirdUrl">外部链接</label>
						<input id="thirdUrl" name="thirdUrl" value="${item.thirdUrl}" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="timeBegin">开始时间</label>
						<input id="timeBegin" name="timeBegin" value="<fmt:formatDate value="${item.timeBegin}" pattern="yyyy-MM-dd"/>" type="date" class="form-control">
					</div>
					<div class="form-group">
						<label for="timeEnd">结束时间</label>
						<input id="timeEnd" name="timeEnd" value="<fmt:formatDate value="${item.timeEnd}" pattern="yyyy-MM-dd"/>" type="date" class="form-control">
					</div>
					<div class="form-group">
						<label for="status">状态</label>
						<select id="status" name="status" class="form-control">
							<option value="ENABLED" ${item.status == "ENABLED" ? "selected" : ""}>可用</option>
							<option value="DISABLED" ${item.status == "DISABLED" ? "selected" : ""}>不可用</option>
						</select>
					</div>
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
