<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<!-- saved from url=(0039)http://wbc.izhuomi.com/Users/viewMyPage -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>窝边草</title>
<link href="/favicon.ico" type="image/x-icon" rel="icon">
<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon">
<link rel="stylesheet" type="text/css" href="/resources/css/components-rounded.css">
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/fonts/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/default.css">
<link rel="stylesheet" type="text/css" href="/resources/css/custom.css">
<link rel="stylesheet" type="text/css" href="/resources/css/layout.css">
<link rel="stylesheet" type="text/css" href="/resources/css/profile_wobiancao.css">
<link rel="stylesheet" type="text/css" href="/resources/css/wbc.css">
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/wbc.js"></script>
<style type="text/css">
.jqstooltip {
	position: absolute;
	left: 0px;
	top: 0px;
	visibility: hidden;
	background: rgb(0, 0, 0) transparent;
	background-color: rgba(0, 0, 0, 0.6);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000);
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
	color: white;
	font: 10px arial, san serif;
	text-align: left;
	white-space: nowrap;
	padding: 5px;
	border: 1px solid white;
	z-index: 10000;
}

.jqsfield {
	color: white;
	font: 10px arial, san serif;
	text-align: left;
}
</style>
</head>
<body>
	<div class="page-container">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content" style="min-height: 1067px;">
			<div class="container">
				<div class="row margin-top-10">
					<div class="">
						<!-- BEGIN PROFILE SIDEBAR -->
						<div class="" style="width: 100%; height: 100%">
							<!-- PORTLET MAIN -->
							<div class="portlet wbc-profile-sidebar-portlet">
								<!-- SIDEBAR USERPIC -->
								<div class="wbc-profile-userpic">
									<img src="${customer.avatar}" class="img-responsive">
								</div>
								<!-- END SIDEBAR USERPIC -->
								<!-- SIDEBAR BUTTONS -->
								<div class="wbc-user-follow-shop-username">${customer.nickname}</div>
								<!-- END SIDEBAR BUTTONS -->
								<!-- SIDEBAR MENU -->
								<div class="wbc-shop-item-cube-container">
									<c:forEach items="${customer.merchantsFollowed}" var="merchant">
										<a href="/merchant/${merchant.id}">
											<div class="square bg" style="background-image: url( ${merchant.logo} )"></div>
										</a>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>