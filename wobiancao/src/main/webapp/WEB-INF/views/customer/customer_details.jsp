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
<title>: 窝边草:</title>
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
		<div class="page-content" style="min-height: 1015px;">
			<div class="container">
				<div class="row margin-top-10">
					<div class="">
						<div class="" style="width: 100%; height: 100%">
							<div class="portlet wbc-profile-sidebar-portlet">
								<div class="wbc-profile-userpic">
									<img src="${customer.avatar}" class="img-responsive" alt="">
								</div>
								<div class="wbc-usermain-username">${customer.nickname}</div>
								<div class="wbc-usermain-container">
									<div class="usermain-jieshengguanzhu">
										<ul class="nav">
											<li style="display: block; position: relative" class="usermain-list-li"><a href="/customer/couponsGot">
													<div style="display: inline-block" class="usermain-list-item-icon col-md-2 col-sm-2 col-xs-2">
														<img class="coupon-icon" src="/resources/image/customer_pic/save_icon.png">
													</div>
													<div style="display: inline-block" class="user-coupon-list-shop-name col-md-7 col-sm-7 col-xs-7">
														<span class="">节省</span>
													</div>
													<div style="display: inline-block" class="user-coupon-list-coupon-desp col-md-2 col-sm-2 col-xs-2">
														<span class="">${customer.couponsGot.size()}次</span>
													</div>
													<div style="display: inline-block" class="user-coupon-list-coupon-desp col-md-1 col-sm-1 col-xs-1">
														<img class="coupon-icon-arrow" src="/resources/image/customer_pic/right_arrow.png">
													</div>
												</a></li>
											<li style="display: block; position: relative" class="usermain-list-li"><a href="/customer/merchantFollowed">
													<div style="display: inline-block" class="usermain-list-item-icon col-md-2 col-sm-2 col-xs-2">
														<img class="coupon-icon" src="/resources/image/customer_pic/follow_icon.png">
													</div>
													<div style="display: inline-block" class="user-coupon-list-shop-name col-md-7 col-sm-7 col-xs-7">
														<span class="">关注</span>
													</div>
													<div style="display: inline-block" class="user-coupon-list-coupon-desp col-md-2 col-sm-2 col-xs-2">
														<span class=""></span>
													</div>
													<div style="display: inline-block" class="user-coupon-list-coupon-desp col-md-1 col-sm-1 col-xs-1">
														<img class="coupon-icon-arrow" src="/resources/image/customer_pic/right_arrow.png">
													</div>
												</a></li>
										</ul>
									</div>
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