function pressLikeInViewShop(id) {

    console.log($('#'+'coupon_id_'+id));
    var addUrl = "/coupon/like?id="+ id;
    var cancleUrl = "/coupon/unlike?id="+ id;
    var url;

    if($('#'+'coupon_id_'+id).hasClass("coupon-unliked")) {//add
        $('#'+'coupon_id_'+id).removeClass("coupon-unliked");
        $('#'+'coupon_id_'+id).addClass("coupon-liked");
        url = addUrl;
    } else {//delete
        $('#'+'coupon_id_'+id).removeClass("coupon-liked");
        $('#'+'coupon_id_'+id).addClass("coupon-unliked");
        url = cancleUrl;
    }

    $.ajax({
        type:'GET',
        url: url,
        dataType : 'json',
    }).done(function(data){
        if(data.status == 'OK'){
            //refresh
            console.log("post OK");
        } else if (data.msg != undefined) {
            ;
        } else {
            console.log("post ERROR");
        }
    }).fail(function(data){
            console.log("post ERROR");
    });

}


function pressLikeInViewCoupon(id) {

    console.log($('#'+'coupon_id_'+id));
    console.log($('#'+'coupon_id_like_'+id).html());
    var addUrl = "/coupon/like?id="+ id;
    var cancleUrl = "/coupon/unlike?id="+ id;
    var url;

    if($('#'+'coupon_id_'+id).hasClass("coupon-unliked")) {//add
        $('#'+'coupon_id_'+id).removeClass("coupon-unliked");
        $('#'+'coupon_id_'+id).addClass("coupon-liked");
        var c = Number($('#'+'coupon_id_like_'+id).html());
        $('#'+'coupon_id_like_'+id).html(c+1);
        url = addUrl;
    } else {//delete
        $('#'+'coupon_id_'+id).removeClass("coupon-liked");
        $('#'+'coupon_id_'+id).addClass("coupon-unliked");
        var c = Number($('#'+'coupon_id_like_'+id).html());
        $('#'+'coupon_id_like_'+id).html(c-1);
        url = cancleUrl;
    }

    $.ajax({
        type:'GET',
        url: url,
        dataType : 'json',
    }).done(function(data){
        if(data.status == 'OK'){
            //refresh
            console.log("post OK");
        } else if (data.msg != undefined) {
            ;
        } else {
            console.log("post ERROR");
        }
    }).fail(function(data){
            console.log("post ERROR");
    });

}

function changeFollow(id) {
	var addUrl = "/merchant/follow?id="+ id;
	var cancleUrl = "/merchant/unfollow?id="+ id;
	var url;
	if ($(".wbc-profile-userbuttons button").html() == "+ 关注") {
		$(".wbc-profile-userbuttons button").html("已关注");
		url = addUrl;
	} else if ($(".wbc-profile-userbuttons button").html() == "已关注") {
		$(".wbc-profile-userbuttons button").html("+ 关注");
		url = cancleUrl;
	}

	$.ajax({
		type : 'GET',
		url : url,
		dataType : 'json',
	}).done(function(data) {
		if (data.status == 'OK') {
			//refresh
			console.log("post OK");
			$('#post-div textarea').val('');
		} else if (data.msg != undefined) {
			;
		} else {
			console.log("post ERROR");
		}
	}).fail(function(data) {
		console.log("post ERROR");
	});
}
