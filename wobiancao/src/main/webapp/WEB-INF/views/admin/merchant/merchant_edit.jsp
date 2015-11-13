<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="currentModule" value="admin"/>
<c:set var="currentFunction" value="merchant"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript">
$('#editModal_saveButton').on('click', function () {
	var name = $('#editModal input[name=name]').val();
	var description = $('#editModal input[name=description]').val();
	var logo = $('#editModal input[name=logo]').val();
// 	var status = $('#editModal input[name=status]').val();
	
	
	$.ajax({
		type: 'POST',
		url: '${pageContext.request.contextPath}/${currentModule}/${currentFunction}/save',
			timeout : 60000,
			data : {
				id : '${item.id}',
				name : name,
				description : description,
				logo : logo,
// 				status : status
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
	
function ajaxFileUpload() {
	$.ajaxFileUpload({
		url : '${pageContext.request.contextPath}/${currentModule}/${currentFunction}/uploadImage',
		secureuri : false,
		fileElementId : 'imageFile',
		dataType : 'json',
		data : {name : $("#name").val()},
		success : function(data, status) {
			$('#viewImg').attr('src', data.picUrl);
			$('#logo').val(data.picUrl);
		},
		error : function(data, status, e) {
			alert('上传出错');
		}
	})
	return false;
}
</script>

<div id="editModal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">编辑商户信息</h4>
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
						<label for="description">描述</label>
						<input id="description" name="description" value="${item.description}" type="text" class="form-control">
					</div>
					<div class="form-group">
						<label for="logo">logo</label>
						<input id="logo" name="logo" value="${item.logo}" type="hidden" class="form-control">
						<img id="viewImg" src="${item.logo}" style="width:350px">
						<input id="imageFile" type="file" size="45" name="imageFile" class="input">
						<button class="button" onclick="ajaxFileUpload()">上传</button>
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="status">状态</label> -->
<%-- 						<input id="status" name="status" value="${item.status}" type="text" class="form-control"> --%>
<!-- 					</div> -->
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
