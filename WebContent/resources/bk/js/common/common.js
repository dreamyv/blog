$(function() {
	/* 换肤 */
	$("#change_skin .changecolor li").click(function() {
		var style = $(this).attr("id");
		$(".title_skin[title!='']").attr("disabled", "disabled");
		$(".title_skin[title='" + style + "']").removeAttr("disabled");
		$.cookie('mystyle_skin', style, {
			expires : 7
		}); // 存储一个带7天期限的 cookie
	});
	var cookie_style = $.cookie("mystyle_skin");
	if (cookie_style != null) {
		$(".title_skin[title!='']").attr("disabled", "disabled");
		$(".title_skin[title='" + cookie_style + "']").removeAttr("disabled");
//		console.log(cookie_style,"**")
	};
	
	/****************************左侧菜单栏stare***********************************/
	var key = "KEY_MENU";/*左侧菜单SESSIONKEY*/
	var obj = tmMap.get(key,  2);/*获取左侧列表*/
//	console.log("左侧菜单列表",obj)
	if(obj==null||isEmpty(obj)){
		console.log("服务器获取菜单列表");
		getDate();
	}else{
		console.log("本地获取菜单列表");
		showMenu(obj);
	};
	
	/*请求菜单列表*/
	function getDate(){
		tmUtil.request({
			url:basePath+"/bk/l/sys/getRoleByMenu",
			async:false,
			success:function(data){
				//console.log("后台获取左侧菜单列表",data);
				var obj = JSON.stringify(data);
				tmMap.put(key,obj,2);//sessionStorage存储
				showMenu(obj);
			}
		});
	};
	
	/*显示左侧菜单列表*/
	function showMenu(obj){
		//if(data+"".isEmpty()) return false;
		var data = JSON.parse(obj);
		var nowUrl = window.location.href;
		var html = "";
		for(var i=0;i<data.length;i++){
			var menuList = data[i].menuList;/*二级菜单*/
			var url = data[i].url;/*连接*/
			var fail = nowUrl.indexOf(url);
			html+='<div class="sBox">';
			html+='<div  class="subNav ';
			if(fail!=-1){
				html+='sublist-down';
			}else{
				html+='sublist-up';
			}
			html+='"';
			if(fail!=-1){
				html+='data-slide="1"';
			}else{
				html+='data-slide="0"';
			}
			html+=' >';
			html+='<span class="title-icon glyphicon ';
			if(fail!=-1){
				html+='glyphicon-chevron-down';
			}else{
				html+='glyphicon-chevron-up';
			}
			html+='"></span>';
			html+='<span class="sublist-title">'+data[i].operName+'</span>';
			html+='</div>';
			html+='<ul class="navContent" ';
			if(fail!=-1){
				html+='style="display:block"';
			}else{
				html+='style="display:none"';
			}
			html+='>';
			/*循环子菜单*/
			for(var j=0;j<menuList.length;j++){
				var urlC = menuList[j].url;
				var failC = nowUrl.indexOf(urlC);
				var jump = basePath+urlC;/*跳转连接路径*/
				var icon = menuList[j].icon;
				html+='<li';
				if(failC!=-1){
					html+=' class="active" ';
				}
				html+='>';
				html+='<a  href="'+jump+'">';
				html+='<span class="'+icon+'"></span>';
				html+='<span class="sub-title">'+menuList[j].operName+'</span>';
				html+='</a>';
				html+='</li>';
			}
			html+='</ul>';
			html+='</div>';
		}
		$("#muenList").html(html);
	};
	
	/*菜单 收起\合并 */
	$(".subNav").on("click",function(){
		var slide = $(this).data("slide");
		upSlide();/*收起所有二级菜单*/
		/*显示*/
		if(slide == "0"){
			$(this).find("span:first-child").removeClass("glyphicon-chevron-up");/*上箭头*/
			$(this).find("span:first-child").addClass("glyphicon-chevron-down");/*下箭头*/
			$(this).removeClass("sublist-up");/*背景浅灰*/
			$(this).addClass("sublist-down");/*背景深灰*/
			$(this).data("slide","1");/*状态改变*/
			$(this).next(".navContent").stop(true,true).slideDown(300);
		}
	});
	
	/*收起所有二级菜单*/
	function upSlide(){
		$(".subNav").each(function(i,dom){
			var slide = $(dom).data("slide");
			/* 隐藏 */
			if(slide == "1"){
				$(dom).find("span:first-child").removeClass("glyphicon-chevron-down");
				$(dom).find("span:first-child").addClass("glyphicon-chevron-up");
				$(dom).removeClass("sublist-down");
				$(dom).addClass("sublist-up");
				$(dom).data("slide","0");/*状态改变*/
				// 修改数字控制速度， slideUp(500)控制卷起速度
				$(dom).next(".navContent").stop(true,true).slideUp(300);
			}
		});
	};
	
	/* 左侧导航栏缩进功能 */
	$(".left-main .sidebar-fold").on("click",function() {
		if ($(this).parent().attr('class') == "left-main left-full") {
			$(this).parent().removeClass("left-full");
			$(this).parent().addClass("left-off");
			$(this).parent().parent().find(".right-product").removeClass("right-full");
			$(this).parent().parent().find(".right-product").addClass("right-off");
		} else {
			$(this).parent().removeClass("left-off");
			$(this).parent().addClass("left-full");
			$(this).parent().parent().find(".right-product").removeClass("right-off");
			$(this).parent().parent().find(".right-product").addClass("right-full");
		}
	});
	
	/* 左侧鼠标移入提示功能 */
	$(".sBox ul li").on("mouseenter",function() {
		if ($(this).find("span:last-child").css("display") == "none") {
			$(this).find("div").show();
		}
	}).on("mouseleave",function() {
		$(this).find("div").hide();
	});
	/****************************左侧菜单栏end***********************************/
	
});