<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="currentModule" value="admin"/>
<c:set var="currentFunction" value="customer"/>
<script type="text/javascript">
$('#deleteConfirmModal_deleteButton').on('click', function () {
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/${currentModule}/${currentFunction}/delete',
		timeout: 60000,
		data: 'id=${item.id}',
		dataType: 'json',
		beforeSend: function() {
			$('#deleteConfirmModal_deleteButton').button('loading');
		},
		complete: function() {
			$('#deleteConfirmModal_deleteButton').button('reset');
			$('#deleteConfirmModal').on('hidden.bs.modal', function () {
				document.location.reload();
			})
		},
		success: function(data, textStatus) {
			if (data.status == 0) {
				$('#deleteConfirmModal_questionText').hide();
				$('#deleteConfirmModal_successText').show();
				$('#deleteConfirmModal_deleteButton').hide();
			} else {
				$('#deleteConfirmModal_errorMessage').html(data.message);
				$('#deleteConfirmModal_questionText').hide();
				$('#deleteConfirmModal_failText').show();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(errorThrown);
			$('#deleteConfirmModal_errorMessage').html(textStatus);
			$('#deleteConfirmModal_questionText').hide();
			$('#deleteConfirmModal_failText').show();
		}
	});
});
</script>

<div id="deleteConfirmModal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">删除用户</h4>
			</div>
			<div class="modal-body">
				<div id="deleteConfirmModal_questionText">
					<h2 style="text-align: center">
						<span class="glyphicon glyphicon-question-sign" style="color: #31b0d5"></span>
						<span>&nbsp;真的要删除用户${item.nickname}(OPENID:${item.openId})吗？</span>
					</h2>
				</div>
				<div id="deleteConfirmModal_successText" style="display: none">
					<h2 style="text-align: center">
						<span class="glyphicon glyphicon-ok-sign" style="color: #449d44"></span>
						<span>&nbsp;删除成功</span>
					</h2>
				</div>
				<div id="deleteConfirmModal_failText" style="display: none">
					<h2 style="text-align: center">
						<span class="glyphicon glyphicon-remove-sign" style="color: #c9302c"></span>
						<span>&nbsp;删除失败</span>
					</h2>
					<div id="deleteConfirmModal_errorMessage" class="alert alert-danger"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="deleteConfirmModal_deleteButton" type="button" class="btn btn-primary" data-loading-text="正在删除">删除</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
