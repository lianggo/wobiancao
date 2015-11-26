<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<!-- saved from url=(0039)http://wbc.izhuomi.com/Shops/viewShop/6 -->
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
<link rel="stylesheet" type="text/css" href="/resources/css/profile.css">
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
		<div class="page-content" style="min-height: 1067px;">
			<div class="container">
				<div class="row margin-top-10">
					<div class="col-md-12">
						<div class="profile-sidebar" style="width: 250px;">
							<div class="portlet wbc-profile-sidebar-portlet">
								<div class="wbc-profile-userpic">
									<img src="${merchant.logo}" class="img-responsive" alt="">
								</div>
								<div class="wbc-profile-userbuttons">
									<button type="button" class="btn btn-circle green-follow wbc-btn-sm" onclick="changeFollow(${merchant.id})">${isFollowed ? '已关注' : '+ 关注'}</button>
								</div>
								<div class="wbc-profile-usermenu">
									<ul class="nav">
										<c:forEach items="${merchant.coupons}" var="coupon" varStatus="couponStatus">
											<li class="" style="display: block; position: relative">
												<div class="coupon-main">
													<div class="col-xs-1 text-center coupon-main-rank">${couponStatus.count}</div>
													<div class="col-xs-2 coupon-shop-logo">
														<img class="coupon-logo-img" src="${merchant.logo}">
														<div class="coupon-geted-heart">
															<div id="coupon_id_like_${coupon.id}" style="width: 100%; text-align: center;">
																<span style="font-size: 9px; color: #797979">${couponsGotMap[coupon.id] != null ? '已领' : '未领'}</span>
																<span style="font-size: 11px; color: #e84b3c">${coupon.getCount}</span>
															</div>
														</div>
													</div>
													<div class="col-xs-8 coupon-desp">
														<p class="coupon-desp-description coupon-line">${coupon.name}</p>
														<p class="coupon-desp-discount coupon-line">${coupon.slogan}</p>
													</div>
												</div>
												<div class="coupon-right">
													<div class="coupon-right wbc-profile-userbuttons">
														<a href="/coupon/get?id=${coupon.id}" class="btn btn-default btn-circle wbc-btn-item"> 去领券 </a>
													</div>
												</div>
												<div class="clearfix"></div>
											</li>
										</c:forEach>
									</ul>
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