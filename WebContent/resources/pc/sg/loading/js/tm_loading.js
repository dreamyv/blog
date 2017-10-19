/*
	版本:v1.0
	作者:j
	时间:2016/6/19
	描述:提示框
	调用方式:
 	loading("这是我写的第一个插件！,点我消失。");
 	loading("这是我写的第一个插件！3秒消失",3,true);
    loading("这是我写的第一个插件！3秒消失+阴影层",3,true);
	new tzLoading("3秒后消失，设置宽300高100！",{timeout:3,width:300,height:100});
 	new tzLoading("设置1秒后消失！",{timeout:1});
 **/
//jqery插件定义方式		2.51.00
var tzLoading =function(message,options){
	//alert("宽:"+options.width+"高:"+options.height);
	this.init(message,options.timeout,options);
};
/*prototype:动态添加方法*/
tzLoading.prototype={
	init : function(message,timeout,opts){ /*初始化方法*/
		/*创建对象*/
		var $loading = this.template(message,opts); 
		/*绝对居中*/
		tmUtil._position($loading).resize($loading);
		/*绑定方法*/
		this.events($loading,opts);
		/*根据时间移除loading*/
		this.timeout($loading,timeout);
	},
	template : function(content,opts){ /*模板界面*/
		var $loading=$("#loading");
		/*判断是否新创建*/
		if($loading.length==0){	
			var $loading =$("<div id='loading' title='点我消失' ></div>");
			var $loading_gif =$("<div class='loading_gif fl'></div>");
			/*  http://localhost/spring_cms/plugin/  */
			$loading_gif.html("<img src='images/loader2.gif' />");
			var $loading_cnt =$("<div class='loading_cnt '></div>");
			$loading_cnt.html(content);
			$loading.append($loading_gif).append($loading_cnt);
			$("body").append($loading);	/*给创建好的对象添加到页面上*/
			/*添加阴影层*/
			if(opts.overlay){
				$("body").append("<div class='tmui-loading-overlay'></div>");
				/*遮罩层绑定点击消失事件*/
				$loading.next(".tmui-loading-overlay").click(function(){
					//$(this).remove();
					$loading.trigger("click");/*事件触发*/
				});
			}
		}else{
			$loading.find(".loading_cnt").html(content);
		}
		/*设置宽高*/
		if(opts.height)$loading.height(opts.height);/*宽度赋值*/
		if(opts.width){
			$loading.width(opts.width);/*宽度赋值*/
		}else{
			/*如果没有传入宽度值，宽度自动换算*/
			var width=$loading.find(".loading_cnt").width()+45;/*获取loading的宽度*/
			if(width>360)width=360;
			$loading.width(width);/*宽度赋值*/
		}
 		return $loading;//给对象返回出去
 	},
 	events : function($loading,opts){	/*事件绑定*/
 		/*点击消失事件*/
 		$loading.click(function(){
 			//tmUtil.animates($(this).next(".tmui-loading-overlay"),"fadeOut");
 			//tmUtil.animates($(this),"slideUp");
 			/*消失时的回调函数*/
 	 		if(opts.callback)opts.callback($loading);
 		});
 	},
 	timeout : function($loading,timeout){	/*根据时间移除loading*/
 		var timr=null;
 		if(timeout+"".isNotEmpty()){
 			clearTimeout(timr);
 			timr=setTimeout(function(){
 				tmUtil.animates($loading.next(".tmui-loading-overlay"),"fadeOut");
 	 			tmUtil.animates($loading,"slideUp");
 				$loading.trigger("click");/*事件触发*/
 			},timeout*1000);
 		};
 	}
};
/*
 * 调用loading方法.自动宽度。
 * 设置宽度调用 :new tzLoading(str,{timeout:timeout,widht:1,height:1});
 * str:		提示文本
 * timeout: 消失时间(s),不写代表不自动消失
 * overlay: 是否有阴影层
 * */
var loading = function(str,timeout,overlay,width){
	new tzLoading(str,{timeout:timeout,overlay:overlay,width:width});/*初始化对象*/
};