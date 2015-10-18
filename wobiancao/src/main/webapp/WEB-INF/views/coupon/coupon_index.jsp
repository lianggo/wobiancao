<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>:窝边草:</title>
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
		<div class="page-content">
			<div class="container">
				<div class="row text-center" style="background: white; margin-top: 10px;">
					<c:forEach items="${couponCategories}" var="category">
						<div class="col-xs-4 text-center" style="padding-top: 8px">
							<a href="/coupon?categoryId=${category.id}">
								<img style="width: 30px; height: 30px" src="${category.picUrl}">
								<div class="caption">
									<p style="color:black">${category.name}</p>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="wbc-profile-usermenu">
				<ul class="nav">
					<c:forEach items="${coupons}" var="coupon" varStatus="couponStatus">
						<li class="" style="display: block; position: relative">
							<div class="coupon-main">
								<div class="col-xs-1 text-center coupon-main-rank">${couponStatus.count}</div>
								<div class="col-xs-2 coupon-shop-logo">
									<a href="/merchant/${coupon.merchant.id}">
										<img class="coupon-logo-img" src="${coupon.merchant.logo}">
									</a>
									<div class="coupon-geted-heart">
										<i id="coupon_id_like_${coupon.id}" class="fa fa-heart" style="font-size: 14px; width: 100%; color: #e84b3c">${coupon.likeCount}</i>
									</div>
								</div>
								<div class="col-xs-7 coupon-desp">
									<p class="coupon-desp-description coupon-line">${coupon.name}</p>
									<p class="coupon-desp-discount coupon-line">${coupon.slogan}</p>
								</div>
								<div id="coupon_id_${coupon.id}" class="coupon-heart ${couponsLikedMap[coupon.id] != null ? 'coupon-liked' : 'coupon-unliked'}" onclick="pressLikeInViewCoupon(${coupon.id})">
									<a>
										<i class="fa fa-heart"></i>
									</a>
								</div>
							</div>
							<div class="coupon-right">
								<div class="coupon-right wbc-profile-userbuttons">
									<a href="/coupon/get?id=${coupon.id}" class="btn btn-default btn-circle wbc-btn-item">
										去领券
									</a>
								</div>
							</div>
							<div class="clearfix"></div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>