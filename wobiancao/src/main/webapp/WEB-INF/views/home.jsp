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

	<link rel="stylesheet" type="text/css" href="/assets/admin/pages/css/tasks.css">
	<link rel="stylesheet" type="text/css" href="/assets/admin/pages/css/profile.css">
	<script type="text/javascript" src="/assets/global/plugins/jquery.sparkline.min.js"></script>
	<script type="text/javascript" src="/assets/global/scripts/metronic.js"></script>
	<script type="text/javascript" src="/assets/admin/layout3/scripts/layout.js"></script>
	<script type="text/javascript" src="/assets/admin/layout3/scripts/demo.js"></script>
	<script type="text/javascript" src="/assets/admin/pages/scripts/profile.js"></script>
	<div class="page-container">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content">
			<div class="container">
				<div class="row text-center" style="background: white; margin-top: 10px;">
					<div class="col-xs-4 text-center" style="padding-top: 8px">
						<a href="http://wbc.izhuomi.com/Coupons/indexByCategory/1""="">
							<img style="width: 30px; height: 30px" src="/resources/image/category_pic/onway.png">
							<div class="caption">
								<p style="color: black">出行</p>
							</div>
						</a>
					</div>
					<div class="col-xs-4 text-center" style="padding-top: 8px">
						<a href="http://wbc.izhuomi.com/Coupons/indexByCategory/2""="">
							<img style="width: 30px; height: 30px" src="/resources/image/category_pic/home.png">
							<div class="caption">
								<p style="color: black">家政</p>
							</div>
						</a>
					</div>
					<div class="col-xs-4 text-center" style="padding-top: 8px">
						<a href="http://wbc.izhuomi.com/Coupons/indexByCategory/3""="">
							<img style="width: 30px; height: 30px" src="/resources/image/category_pic/others.png">
							<div class="caption">
								<p style="color: black">其它</p>
							</div>
						</a>
					</div>
					<!-- /.row -->
				</div>
			</div>
			<!-- END SIDEBAR BUTTONS -->
			<!-- SIDEBAR MENU -->
			<div class="wbc-profile-usermenu">
				<ul class="nav">
					<li class="" style="display: block; position: relative">
						<div class="coupon-main">
							<div class="col-xs-1 text-center coupon-main-rank">1</div>
							<div class="col-xs-2 coupon-shop-logo">
								<a href="http://wbc.izhuomi.com/Shops/ViewShop/6">
									<img class="coupon-logo-img" src="/resources/image/shop_pic/滴滴出行.png">
								</a>
								<div class="coupon-geted-heart">
									<i id="coupon_id_like_8" class="fa fa-heart" style="font-size: 14px; width: 100%; color: #e84b3c">281</i>
								</div>
							</div>
							<div class="col-xs-7 coupon-desp">
								<p class="coupon-desp-description coupon-line">滴滴百元优惠礼包1</p>
								<p class="coupon-desp-discount coupon-line">100元</p>
							</div>
							<div id="coupon_id_8" class="coupon-heart coupon-liked" onclick="pressLikeInViewCoupon(8)">
								<a>
									<i class="fa fa-heart"></i>
								</a>
							</div>
						</div>
						<div class="coupon-right">
							<div class="coupon-right wbc-profile-userbuttons">
								<a href="http://wbc.izhuomi.com/Coupons/addHasCouponRedirect/8" class="btn btn-default btn-circle wbc-btn-item">
									去领券
								</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</li>
					<li class="" style="display: block; position: relative">
						<div class="coupon-main">
							<div class="col-xs-1 text-center coupon-main-rank">2</div>
							<div class="col-xs-2 coupon-shop-logo">
								<a href="http://wbc.izhuomi.com/Shops/ViewShop/16">
									<img class="coupon-logo-img" src="/resources/image/shop_pic/途牛网副本.png">
								</a>
								<div class="coupon-geted-heart">
									<i id="coupon_id_like_19" class="fa fa-heart" style="font-size: 14px; width: 100%; color: #e84b3c">211</i>
								</div>
							</div>
							<div class="col-xs-7 coupon-desp">
								<p class="coupon-desp-description coupon-line">九周年万元红包</p>
								<p class="coupon-desp-discount coupon-line">1万元</p>
							</div>
							<div id="coupon_id_19" class="coupon-heart coupon-unliked" onclick="pressLikeInViewCoupon(19)">
								<a>
									<i class="fa fa-heart"></i>
								</a>
							</div>
						</div>
						<div class="coupon-right">
							<div class="coupon-right wbc-profile-userbuttons">
								<a href="http://wbc.izhuomi.com/Coupons/addHasCouponRedirect/19" class="btn btn-default btn-circle wbc-btn-item">
									去领券
								</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</li>
					<li class="" style="display: block; position: relative">
						<div class="coupon-main">
							<div class="col-xs-1 text-center coupon-main-rank">3</div>
							<div class="col-xs-2 coupon-shop-logo">
								<a href="http://wbc.izhuomi.com/Shops/ViewShop/7">
									<img class="coupon-logo-img" src="/resources/image/shop_pic/kfc副本.png">
								</a>
								<div class="coupon-geted-heart">
									<i id="coupon_id_like_9" class="fa fa-heart" style="font-size: 14px; width: 100%; color: #e84b3c">199</i>
								</div>
							</div>
							<div class="col-xs-7 coupon-desp">
								<p class="coupon-desp-description coupon-line">手机优惠券</p>
								<p class="coupon-desp-discount coupon-line">80% Off</p>
							</div>
							<div id="coupon_id_9" class="coupon-heart coupon-unliked" onclick="pressLikeInViewCoupon(9)">
								<a>
									<i class="fa fa-heart"></i>
								</a>
							</div>
						</div>
						<div class="coupon-right">
							<div class="coupon-right wbc-profile-userbuttons">
								<a href="http://wbc.izhuomi.com/Coupons/addHasCouponRedirect/9" class="btn btn-default btn-circle wbc-btn-item">
									去领券
								</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			Metronic.init(); // init metronic core components
			Layout.init(); // init current layout
			Demo.init(); // init demo features\
			Profile.init(); // init page demo
		});
	</script>

</body>
</html>