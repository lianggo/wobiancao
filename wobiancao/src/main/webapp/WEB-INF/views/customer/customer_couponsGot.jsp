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
<%@ include file="/WEB-INF/views/header.jsp"%>
</head>
<body>
	<div class="page-container">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content" style="min-height: 1015px;">
			<div class="container">
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="row margin-top-10">
					<div class="row">
						<!-- BEGIN PROFILE SIDEBAR -->
						<div class="col-md-12" style="width: 100%">
							<!-- PORTLET MAIN -->
							<div class="portlet wbc-profile-sidebar-portlet">
								<!-- SIDEBAR USERPIC -->
								<div class="wbc-user-coupon-head" style="position: relative">
									<div class="wbc-user-coupon-userpic">
										<img src="${customer.avatar}" class="img-responsive" alt="">
										<div class="clearfix"></div>
									</div>
									<div class="wbc-user-coupon-userdesp" style="">
										<div class="wbc-user-coupon-userdesp-name">${customer.nickname}</div>
										<div class="wbc-user-coupon-userdesp-times">已经节省${customer.couponsGot.size()}次</div>
									</div>
									<div class="clearfix"></div>
								</div>
								<!-- END SIDEBAR USERPIC -->


								<!-- SIDEBAR MENU -->
								<div class="wbc-user-coupon-list">
									<ul class="nav">
										<c:forEach items="${customer.couponsGot}" var="coupon" varStatus="couponStatus">
											<li class="user-coupon-list-item" style="display: block; position: relative">
												<div class="my-coupon-list" style="height: 100%">
													<a href="/merchant/${coupon.merchant.id}">
														<div class="user-coupon-list-shop-logo col-md-3 col-sm-3 col-xs-3">
															<img src="${coupon.merchant.logo}">
														</div>
													</a>
													<div class="user-coupon-list-shop-name col-md-3 col-sm-3 col-xs-3">
														<span class="">${coupon.merchant.name}</span>
													</div>
													<div class="user-coupon-list-coupon-desp coupon-line col-md-6 col-sm-6 col-xs-6">
														<span class="coupon-line">${coupon.name}</span>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
								<!-- END MENU -->
							</div>
							<!-- END PORTLET MAIN -->
						</div>
						<!-- END BEGIN PROFILE SIDEBAR -->
					</div>
				</div>
				<!-- END PAGE CONTENT INNER -->
			</div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>

</body>
</html>