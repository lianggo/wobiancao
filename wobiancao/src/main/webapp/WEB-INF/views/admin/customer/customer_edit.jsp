<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="currentModule" value="admin"/>
<c:set var="currentFunction" value="customer"/>
<script type="text/javascript">
$('#editModal_saveButton').on('click', function () {
	var name = $('#editModal input[name=name]').val();
	
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/${currentModule}/${currentFunction}/save',
			timeout : 60000,
			data : {
				id : '${item.id}',
				name : name,
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
				<h4 class="modal-title">编辑用户信息</h4>
			</div>
			<div class="modal-body">
				用户信息从微信同步，不可编辑
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
