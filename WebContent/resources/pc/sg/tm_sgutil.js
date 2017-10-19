/*工具类*/
var tmUtil = { 
	_position : function($obj){ /*给对象绝对居中(基于窗口)*/
 		var windowHeight=$(window).height();/*获取浏览器的高度*/
 		var windowWidth=$(window).width();/*获取浏览器的宽度*/
 		var width=$obj.width();
 		var height=$obj.height();
 		var top=(windowHeight-height)/2;
 		var left=(windowWidth-width)/2;
 		$obj.css({left:left,top:top});/*设置对象的位置*/
 		return this;
 	},
	_positionParent : function($obj,$parent){ /*给对象绝对居中(基于父容器)*/
		var parentHeight=$parent.height();/*获取父容器的高度*/
		var parentWidth=$parent.width();/*获取父容器的宽度*/
		var width=$obj.width();
		var height=$obj.height();
		var top=(parentHeight-height)/2;
		var left=(parentWidth-width)/2;
		$obj.css({left:left,top:top});/*设置对象的位置*/
		return this;
 	},
 	resize : function($dom){
 		var $this=this;
 		$(window).resize(function(){/*每当浏览器窗口改变时触发(resize)*/
 			$this._position($dom);
 		});
 	},
 	animates : function($dom,mark){ /*根据传入的值，淡入淡出*/
 		switch(mark){
 			case "slideUp" : $dom.slideUp("slow",function(){$(this).remove();});break;
 			case "slideDown" : $dom.slideDown("slow");break;
 			case "fadeIn" : $dom.fadeIn("slow");break;
 			case "fadeOut" : $dom.fadeOut("slow",function(){$(this).remove();});break;
			case "top" : $dom.animate({top:0},300,function(){$(this).remove();});break;
			case "left" :$dom.animate({left:0},300,function(){$(this).remove();});break;
 		}
 	},
 	_decToHex : function(str) {/*unicode转码*/
		var res=[];  
		for(var i=0;i < str.length;i++)  
			res[i]=("00"+str.charCodeAt(i).toString(16)).slice(-4);  
		return "\\u"+res.join("\\u");  
	},  
	_hexToDec : function(str) { /*unicode解码*/
		str=str.replace(/\\/g,"%");  
		return unescape(str);  
	},
	request : function(options){/*ajax封装,options默认参数,params参数*/
		var opts = $.extend({},{
			url:"",/*必传参数*/
			type:"post",
			async:true,
			data:{},
			before:function(){},
			success:function(){},
			error:function(){},
			path:basePath+"/bk"/*未登录页面要跳转的路径basePath全局变量*/
		},options);
		$.ajax({
			type:opts.type,
			url:opts.url,
			beforeSend:opts.before,
			error:opts.error,
			data:opts.data,
			async:opts.async,
			success:function(data){
				if(data=="logout"){
					location.href=opts.path+'/';
				}else{
					opts.success(data);/*回调方法*/
				}
			}
		});
	},
	/*时间戳转换为日期格式*/
	getDate :function(ns){
		if(nS=="" ||nS == null || (nS+"").trim()=="") return "";
		var date = new Date(parseInt(nS));
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = date.getDate() + ' ';
		//h = date.getHours() + ':';
		//m = date.getMinutes() + ':';
		//s = date.getSeconds(); 
		return Y+M+D; 
	}
};
/************************************工具方法结束***********************************************/
/*给string添加一个方法。空判断*/
String.prototype.isEmpty=function(){
	var val = this;/*获取当前的值*/
	val = $.trim(val);
	if (val == undefined || val == 'undefined')
		return true;
	if (val== null)
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
};
/*非空判断*/
String.prototype.isNotEmpty=function(){
	return !this.isEmpty();
};
/*替换字符串*/
String.prototype.replaceAll=function(str,target){
	return this.replace(new RegExp(str,"ig"),target);
};
/*去空格*/
String.prototype.trim = function(){
	return  this.replace(/(^\s*)|(\s*$)/g,"");  
};
/* 判断一个元素释放包含在数组中。 */
Array.prototype.contains = function(obj) {
	var i = this.length;
	while (i--) {
		if (this[i] === obj) {
			return true;
		}
	}
	return false;
};
/*    */
var isArray = function(obj) { 
	return Object.prototype.toString.call(obj) === '[object Array]'; 
}; 
/*    */
var is_array = function(value) { 
	return value && 
	typeof value === 'object' && 
	typeof value.length === 'number' && 
	typeof value.splice === 'function' && 
	!(value.propertyIsEnumerable('length')); 
}; 
/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
 * 可以用 1-2 个占位符 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) eg: (new
 * Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
 * Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04 (new
 * Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04 (new
 * Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 (new
 * Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function(fmt) {
	var o = {
		"Y+" : this.getFullYear(),
		"M+" : this.getMonth() + 1,
		// 月份
		"d+" : this.getDate(),
		// 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
		// 小时
		"H+" : this.getHours(),
		// 小时
		"m+" : this.getMinutes(),
		// 分
		"s+" : this.getSeconds(),
		// 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		// 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};

/*******************************************************扩展方法结束*********************************************************************/

/**
 * 判断非空
 */
function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
}
//非空
function isNotEmpty(val) {
	return !isEmpty(val);
}
//判断两个元素是否相等
function eqauls(str,tstr){
	if(str == tstr){
		return true;
	}
	return false;
};


/**
 * json对象转换字符串
 */
function jsonToString(obj) {
	var THIS = this;
	switch (typeof (obj)) {
	case 'string':
		return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
	case 'array':
		return '[' + obj.map(THIS.jsonToString).join(',') + ']';
	case 'object':
		if (obj instanceof Array) {
			var strArr = [];
			var len = obj.length;
			for (var i = 0; i < len; i++) {
				strArr.push(THIS.jsonToString(obj[i]));
			}
			return '[' + strArr.join(',') + ']';
		} else if (obj == null) {
			return 'null';

		} else {
			var string = [];
			for ( var property in obj)
				string.push(THIS.jsonToString(property) + ':'
						+ THIS.jsonToString(obj[property]));
			return '{' + string.join(',') + '}';
		}
	case 'number':
		return obj;
	case false:
		return obj;
	}
}

/**
 * 禁止窗体选中
 */
function forbiddenSelect() {
	$(document).bind("selectstart", function() {
		return false;
	});
	document.onselectstart = new Function("event.returnValue=false;");
	$("*").css({
		"-moz-user-select" : "none"
	});
}
/* 窗体允许选中 */
function autoSelect() {
	$(document).bind("selectstart", function() {
		return true;
	});
	document.onselectstart = new Function("event.returnValue=true;");
	$("*").css({
		"-moz-user-select" : ""
	});
};

/**
 * 将数字转换成对应的中文 将阿拉伯数字翻译成中文的大写数字
 * @param {Object} num 比如:1对应一 11：十一 101:一百零一
 * @return {TypeName}
 */
function tm_NumberToChinese(num) {
    var AA = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十");
    var BB = new Array("", "十", "百", "仟", "萬", "億", "点", "");
    var a = ("" + num).replace(/(^0*)/g, "").split("."),
    k = 0,
    re = "";
    for (var i = a[0].length - 1; i >= 0; i--) {
        switch (k) {
        case 0:
            re = BB[7] + re;
            break;
        case 4:
            if (!new RegExp("0{4}//d{" + (a[0].length - i - 1) + "}$").test(a[0])) re = BB[4] + re;
            break;
        case 8:
            re = BB[5] + re;
            BB[7] = BB[5];
            k = 0;
            break;
        }
        if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) re = AA[0] + re;
        if (a[0].charAt(i) != 0) re = AA[a[0].charAt(i)] + BB[k % 4] + re;
        k++;
    }

    if (a.length > 1) // 加上小数部分(如果有小数部分)
    {
        re += BB[6];
        for (var i = 0; i < a[1].length; i++) re += AA[a[1].charAt(i)];
    }
    if (re == '一十') re = "十";
    if (re.match(/^一/) && re.length == 3) re = re.replace("一", "");
    return re;
};

/**
 * 获取窗体可见度高度
 */
function getClientHeight() {
	var clientHeight = 0;
	if (document.body.clientHeight && document.documentElement.clientHeight) {
		clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight
				: document.documentElement.clientHeight;
	} else {
		clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight
				: document.documentElement.clientHeight;
	}
	return clientHeight;
}
/**
 * 获取窗体可见度宽度
 */
function getClientWidth() {
	var clientWidth = 0;
	if (document.body.clientWidth && document.documentElement.clientWidth) {
		clientWidth = (document.body.clientWidth < document.documentElement.clientWidth) ? document.body.clientWidth
				: document.documentElement.clientWidth;
	} else {
		clientWidth = (document.body.clientWidth > document.documentElement.clientWidth) ? document.body.clientWidth
				: document.documentElement.clientWidth;
	}
	return clientWidth;
}
//获取滚动条高度
function getScrollHeight() {
	return Math.max(getClientHeight(), document.body.scrollHeight,document.documentElement.scrollHeight);
}
//获取滚动条据顶部高度
function getScrollTop() {
	var scrollTop = 0;
	if (document.documentElement && document.documentElement.scrollTop) {
		scrollTop = document.documentElement.scrollTop;
	} else if (document.body) {
		scrollTop = document.body.scrollTop;
	}
	return scrollTop;
}
/**********************************************************文件操作start*****************************************************************/
/* 文件大小转换为MB GB KB格式 */
function tm_countFileSize(size) {
	var fsize = parseFloat(size, 2);
	var fileSizeString;
	if (fsize < 1024) {
		fileSizeString = fsize.toFixed(2) + "B";
	} else if (fsize < 1048576) {
		fileSizeString = (fsize / 1024).toFixed(2) + "KB";
	} else if (fsize < 1073741824) {
		fileSizeString = (fsize / 1024 / 1024).toFixed(2) + "MB";
	} else if (fsize < 1024 * 1024 * 1024) {
		fileSizeString = (fsize / 1024 / 1024 / 1024).toFixed(2) + "GB";
	} else {
		fileSizeString = "0B";
	}
	return fileSizeString;
};
/* 获取文件后缀 */
function tm_getExt(fileName) {
	if (fileName.lastIndexOf(".") == -1)
		return fileName;
	var pos = fileName.lastIndexOf(".") + 1;
	return fileName.substring(pos, fileName.length).toLowerCase();
}
/* 获取文件名称 */
function tm_getFileName(fileName) {
	var pos = fileName.lastIndexOf("/") + 1;
	if (pos == -1) {
		return fileName;
	} else {
		return fileName.substring(pos, fileName.length);
	}
}
/**********************************************************文件操作end*****************************************************************/
//获取几秒钟以前 startTime==== Date
function getTimeFormat(startTime) {
	var startTimeMills = startTime.getTime();
	var endTimeMills = new Date().getTime();
	var diff = parseInt((endTimeMills - startTimeMills) / 1000);//秒
	var day_diff = parseInt(Math.floor(diff / 86400));//天
	var buffer = Array();
	if (day_diff < 0) {
		return "[error],时间越界...";
	} else {
		if (day_diff == 0 && diff < 60) {
			if (diff <= 0)
				diff = 1;
			buffer.push(diff + "秒前");
		} else if (day_diff == 0 && diff < 120) {
			buffer.push("1 分钟前");
		} else if (day_diff == 0 && diff < 3600) {
			buffer.push(Math.round(Math.floor(diff / 60)) + "分钟前");
		} else if (day_diff == 0 && diff < 7200) {
			buffer.push("1小时前");
		} else if (day_diff == 0 && diff < 86400) {
			buffer.push(Math.round(Math.floor(diff / 3600)) + "小时前");
		} else if (day_diff == 1) {
			buffer.push("1天前");
		} else if (day_diff < 7) {
			buffer.push(day_diff + "天前");
		} else if (day_diff < 30) {
			buffer.push(Math.round(Math.floor(day_diff / 7)) + " 星期前");
		} else if (day_diff >= 30 && day_diff <= 179) {
			buffer.push(Math.round(Math.floor(day_diff / 30)) + "月前");
		} else if (day_diff >= 180 && day_diff < 365) {
			buffer.push("半年前");
		} else if (day_diff >= 365) {
			buffer.push(Math.round(Math.floor(day_diff / 30 / 12)) + "年前");
		}
	}
	return buffer.toString();
};

/**
 * 阻止事件冒泡
 */
function stopBubble(e) {
	// 如果提供了事件对象，则这是一个非IE浏览器
	if (e && e.stopPropagation)
		// 因此它支持W3C的stopPropagation()方法
		e.stopPropagation();
	else
		// 否则，我们需要使用IE的方式来取消事件冒泡
		window.event.cancelBubble = true;
};

/*******************************************************日期操作start***************************************************************************/
/*日期工具类*/
$.tmDate = {
 /*转换日期*/
 _transferDate : function(date){
	if(typeof date =="string"){
		return new Date(date.replace(/-/ig,"/").replace("T"," "));
	}else{
		return date;
	}
 },
  /*格式化日期*/
 _toString : function(date,pattern){
	var d = this._transferDate(date);
	return d.format(pattern);
 },

 /*获取两个时间相减的时间*/
 _Date : function(date1,date2){
	var dateTime = this._numMillSecond(date1,date2);
	return new Date(dateTime).format("yyyy-MM-dd");
 },
 //获取两个时间相减的年份
 _Datsyear : function(date1,date2){
	 var dateTime = this._numYear(date1,date2);
	 return  dateTime;
 },
//获取两个时间相减的月份
 _Datsmonth : function(date1,date2){
	 var dateTime = this._numMonth(date2,date1);
	 return  dateTime;
 },

 //间隔年份
 _numYear : function(date1,date2){
	var times = this._numDay(date1,date2);
	return  Math.floor(times/365);
 },

  //间隔月份
 _numMonth : function(date1,date2){
	var times = this._numDay(date1,date2);
	return  Math.floor(times/30);
 },

 //间隔天数
 _numDay : function(date1,date2){
	var times = this._numSecond(date1,date2);
	var hour = this._var().hour;
	var mills = this._var().mills;
	return Math.ceil(times/(mills * hour));
 },

//间隔时
 _numHour : function(date1,date2){
	return Math.floor(this._numMillSecond(date1,date2)/(1000*60*60));
 },

 //间隔分
 _numMinute : function(date1,date2){
	return Math.floor(this._numMillSecond(date1,date2)/(1000*60));
 },

 //间隔秒数
 _numSecond : function(date1,date2){
	 return Math.floor(this._numMillSecond(date1,date2) / 1000);
 },

  //间隔毫秒
 _numMillSecond : function(date1,date2){
	var stimes = this._getTime(this._transferDate(date1));
	var etimes = this._getTime(this._transferDate(date2));
	return etimes - stimes;
 },

 _var : function(){
	return {hour:24,second:60,mills:3600,format:"yyyy-MM-dd",dateFormat:"yyyy-MM-dd HH:mm:ss"};
 },

/*某个日期加上多少毫秒*/
 _plusMillisSeconds : function(date,millisSeconds){
	var dateTime = this._getTime(date);
	var mintimes = millisSeconds;
	var rdate = dateTime*1 + mintimes*1;
	return this._format(new Date(rdate));
 },
 /*某个日期加上多少秒*/
 _plusSeconds : function(date,seconds){
	var dateTime = this._getTime(date);
	var mintimes = seconds*1000;
	var rdate = dateTime*1 + mintimes*1;
	return this._format(new Date(rdate));
 },
  /*某个日期加上多少分钟*/
 _plusMinutes : function(date,minutes){
	var dateTime = this._getTime(date);
	var mintimes = minutes*60*1000;
	var rdate = dateTime*1 + mintimes*1;
	return this._format(new Date(rdate));
 },
  /*某个日期加上小时数*/
 _plusHours : function(date,hours){
	var dateTime = this._getTime(date);
	var mintimes = hours*60*60*1000;
	var rdate = dateTime + mintimes;
	return this._format(new Date(rdate));
 },
 /*某个日期加上天数*/
 _plusDays : function(date,days){
	var dateTime = this._getTime(date);
	var mintimes = days*60*60*1000*24;
	var rdate = dateTime*1 + mintimes*1;
	return this._format(new Date(rdate));
 },

 /*某个日期加上多少个月,这里是按照一个月30天来计算天数的*/
 _plusMonths : function(date,months){
	var dateTime = this._getTime(date);
	var mintimes = months*30*60*60*1000*24;
	var rdate = dateTime + mintimes*1;
	return this._format(new Date(rdate));
 },

 /*某个日期加上多少个年,这里是按照一个月365天来计算天数的，如果loop为true则按闰年计算*/
 _plusYears : function(date,years,isLoop){
	var dateTime = this._getTime(date);
	var day = 365;
	if(isLoop)day =366;
	var mintimes = years*day*60*60*1000*24;
	var rdate = dateTime + mintimes;
	return this._format(new Date(rdate));
 },

 /*某个日期加上某个日期，这样的操作视乎没什么意义*/
 _plusDate : function(date1,date2){
	var dateTime = this._getTime(date1);
	var dateTime2 = this._getTime(date2);;
	var rdate = dateTime + dateTime2;
	return this._format(new Date(rdate));
 },

 /*某个日期减去多少毫秒秒*/
 _minusMillisSeconds : function(date,millisSeconds){
	var dateTime = this._getTime(date);
	var mintimes = millisSeconds*1;
	var rdate = dateTime - mintimes;
	return this._format(new Date(rdate));
 },
 /*某个日期减去多少秒*/
 _minusSeconds : function(date,seconds){
	var dateTime = this._getTime(date);
	var mintimes = seconds*1000;
	var rdate = dateTime - mintimes;
	return this._format(new Date(rdate));
 },
  /*某个日期减去多少分钟*/
 _minusMinutes : function(date,minutes){
	var dateTime = this._getTime(date);
	var mintimes = minutes*60*1000;
	var rdate = dateTime - mintimes;
	return this._format(new Date(rdate));
 },
  /*某个日期减去小时数*/
 _minusHours : function(date,hours){
	var dateTime = this._getTime(date);
	var mintimes = hours*60*60*1000;
	var rdate = dateTime - mintimes;
	return this._format(new Date(rdate));
 },
 /*某个日期减去天数*/
 _minusDays : function(date,days){
	var dateTime = this._getTime(date);
	var mintimes = days*60*60*1000*24;
	var rdate = dateTime - mintimes;
	return this._format(new Date(rdate));
 },

 /*某个日期减去多少个月,这里是按照一个月30天来计算天数的*/
 _minusMonths : function(date,months){
	var dateTime = this._getTime(date);
	var mintimes = months*30*60*60*1000*24;
	var rdate = dateTime - mintimes;
	return this._format(new Date(rdate));
 },

 /*某个日期减去多少个年,这里是按照一个月365天来计算天数的*/
 _minusYears : function(date,years,isLoop){
	var dateTime = this._getTime(date);
	var day = 365;
	if(isLoop)day =366;
	var mintimes = years*day*60*60*1000*24;
	var rdate = dateTime - mintimes;
	return this._format(new Date(rdate));
 },

 /*某个日期减去某个日期，这样的操作视乎没什么意义*/
 _minusDate : function(date1,date2){
	var dateTime = this._getTime(date1);
	var dateTime2 = this._getTime(date2);;
	var rdate = dateTime - dateTime2;
	return this._format(new Date(rdate));
 },

 /*获取一个月有多少天*/
 _getMonthOfDay :function(date1){
	var currentMonth = this._getFirstDayOfMonth(date1);
	var nextMonth = this._getNextDayOfMonth(date1);
	return this._numDay(currentMonth,nextMonth);
 },

 /*获取一年又多少天*/
 _getYearOfDay : function(date){
	var firstDayYear = this._getFirstDayOfYear(date);
	var lastDayYear = this._getLastDayOfYear(date);
	return Math.ceil(this._numDay(firstDayYear,lastDayYear));
 },

 /*某个日期是当年中的第几天*/
 _getDayOfYear : function(date1){
	return Math.ceil(this._numDay(this._getFirstDayOfYear(date1),date1));	
 },

 /*某个日期是在当月中的第几天*/
  _getDayOfMonth : function(date1){
	return Math.ceil(this._numDay(this._getFirstDayOfMonth(date1),date1));	
 },

 /*获取某个日期在这一年的第几周*/
 _getDayOfYearWeek : function(date){
	var numdays = this._getDayOfYear(date);
	return Math.ceil(numdays / 7);
 },

  /*某个日期是在当月中的星期几*/
  _getDayOfWeek : function(date1){
	return this._getWeek(date1);
 },

 /*获取在当前日期中的时间*/
 _getHourOfDay : function(date){
	 return this._getHour(date);
 },
 _eq : function(date1,date2){
	 var stime = this._getTime(this._transferDate(date1));
	 var etime = this._getTime(this._transferDate(date2));
	 return stime == etime ? true :false; 
 },
 /*某个日期是否晚于某个日期*/
 _after : function(date1,date2){
	 var stime = this._getTime(this._transferDate(date1));
	 var etime = this._getTime(this._transferDate(date2));
	 return  stime < etime ? true :false; 
 },

  /*某个日期是否早于某个日期*/
 _before : function(date1,date2){
	 var stime = this._getTime(this._transferDate(date1));
	 var etime = this._getTime(this._transferDate(date2));
	 return  stime > etime ? true :false; 
 },
 
 /*获取某年的第一天*/
 _getFirstDayOfYear : function(date){
	var year = this._getYear(date);
	var dateString = year+"-01-01 00:00:00";
	return dateString;
 },

  /*获取某年的最后一天*/
 _getLastDayOfYear : function(date){
	var year = this._getYear(date);
	var dateString = year+"-12-01 00:00:00";
	var endDay = this._getMonthOfDay(dateString);
	return year+"-12-"+endDay+" 23:59:59";
 },

  /*获取某月的第一天*/
 _getFirstDayOfMonth: function(date){
	var year = this._getYear(date);
	var month = this._getMonth(date);
	var dateString = year +"-"+month+"-01 00:00:00";
	return dateString;
 },

 /*获取某月最后一天*/
 _getLastDayOfMonth : function(date){
	var endDay = this._getMonthOfDay(date);
	var year = this._getYear(date);
	var month = this._getMonth(date);
	return year +"-"+month+"-"+endDay+" 23:59:59";
 },
 /*一天的开始时间*/
 _getFirstOfDay : function(date){
	 var year = this._getYear(date);
	 var month = this._getMonth(date);
	 var date = this._getDay(date);
	 return year+"-"+month+"-"+date+" 00:00:00";
 },

 /*一天的结束时间*/
 _getLastOfDay : function(date){
	 var year = this._getYear(date);
	 var month = this._getMonth(date);
	 var date = this._getDay(date);
	 return year+"-"+month+"-"+date+" 23:59:59";
 },
 
 /*获取下个月的第一天*/
 _getNextDayOfMonth: function(date){
	var year = this._getYear(date);
	var month = this._getMonth(date);
	month = month * 1 +1;
	if(month>12){
		year = year+1;
		month = month - 12;
	}
	month = month>9 ? month : "0"+month;
	var dateString = year +"-"+month+"-01 00:00:00";
	return dateString;
 },

 _getFirstOfWeek : function(date1){
	 var week = this._getWeek(date1);
	 var date = this._minusDays(date1,week);
	 var year = this._getYear(date);
	 var month = this._getMonth(date);
	 var date = this._getDay(date);
	 return year+"-"+month+"-"+date+" 00:00:00";
 },
 
 _getLastOfWeek : function(date1){
	 var week = 6-this._getWeek(date1);
	 var date = this._minusDays(date1,week);
	 var year = this._getYear(date);
	 var month = this._getMonth(date);
	 var date = this._getDay(date);
	 return year+"-"+month+"-"+date+" 23:59:59";
 },
 
 _getNow : function(){
	return new Date();	
 },
 _format : function(date){
	return this._getYear(date)+"-"+this._getMonth(date)+"-"+this._getDay(date)+" "+this._getHour(date)+":"+this._getMinute(date)+":"+this._getSecond(date);
 },
 _getDate :function(){
	 return this._getNow();
 },
 /*年*/
 _getYear:function(date){
	 return this._transferDate(date).getFullYear();
 },

 /*月*/
 _getMonth:function(date){
	 var month = this._transferDate(date).getMonth()+1;
	 return month>9 ? month : "0"+month;
 },

 /*日*/
 _getDay:function(date){
	 var day = this._transferDate(date).getDate();
	 return day >9 ? day : "0"+day;
 },

  /*获取今天星期几,如果为0代表星期日*/
 _getWeek : function(date){
	 return this._transferDate(date).getDay();
 },

 /*时*/
 _getHour : function(date){
	 var hour = this._transferDate(date).getHours();
	 return hour >9 ? hour : "0"+hour;
 },

 /*12小时制时*/
 _getHour12 : function(date){
	 var hour = this._transferDate(date).getHours();
	 return hour%12 == 0 ? 12 : hour % 12
 },

 /*分*/
 _getMinute : function(date){
	 var minutes = this._transferDate(date).getMinutes();
	 return minutes >9 ? minutes : "0"+minutes;
 },

 /*秒*/
 _getSecond : function(date){
	var seconds = this._transferDate(date).getSeconds();
	return seconds >9 ? seconds : "0"+seconds;
 },

 /*毫秒*/
 _getMillisecond : function(date){
	return this._transferDate(date).getMilliseconds();
 },

 /*获取今天在当年是第几季度*/
 _getPeriod : function(date){
	var month = this._getMonth(date)*1;
	return  Math.floor((month+3)/3)
 },

 /*星期*/
 _nowWeekChinies : function(date){
	var nowWeek = this._getWeek(date);
	var day = "";
	switch (nowWeek){
		case 0:day="日";break;
		  break;
		case 1:day="一";break;
		  break;
		case 2:day="二";break;
		  break;
		case 3:day="三";break;
		  break;
		case 4:day="四";break;
		  break;
		case 5:day="五";break;
		  break;
		case 6:day="六";break;
	 }
	 return day;
 },
 _getMessage : function(date){
	 var now = date||new Date();
	 var hour = now.getHours() ;
	 if(hour < 6){return "凌晨好！";} 
	 else if (hour < 9){return "早上好！";} 
	 else if (hour < 12){return "上午好！";} 
	 else if (hour < 14){return "中午好！";} 
	 else if (hour < 17){return "下午好！";} 
	 else if (hour < 19){return "傍晚好！";} 
	 else if (hour < 22){return "晚上好！";} 
	 else {return "夜里好！";} 
 },
 /*返回 1970 年 1 月 1 日至今的毫秒数。*/
 _getTime : function(date){
	 return this._transferDate(date).getTime();
 }
};
/*******************************************************日期操作end***************************************************************************/
/*******************************************************cookie操作start**************************************************************************/
/*cookie*/
$.tmCookie = {
	setCookie : function(name, value,time,option){
	    var str=name+'='+escape(value); 
	    var date = new Date();
	    date.setTime(date.getTime()+this.getCookieTime(time)); 
	    str += "; expires=" + date.toGMTString();
	    if(option){ 
	        if(option.path) str+='; path='+option.path; 
	        if(option.domain) str+='; domain='+option.domain; 
	        if(option.secure) str+='; true'; 
	    } 
	    document.cookie=str; 
	},
	getCookie : function(name){
		var arr = document.cookie.split('; '); 
	    if(arr.length==0) return ''; 
	    for(var i=0; i <arr.length; i++){ 
	        tmp = arr[i].split('='); 
	        if(tmp[0]==name) return unescape(tmp[1]); 
	    } 
	    return ''; 
	},
	delCookie : function(name){
		$.tmCookie.setCookie(name,'',-1); 
		var date=new Date();
        date.setTime(date.getTime()-10000);
		document.cookie=name+"=; expire="+date.toGMTString()+"; path=/";
	},
	
	getCookieTime : function(time){
	   if(time<=0)return time;
	   var str1=time.substring(1,time.length)*1;
	   var str2=time.substring(0,1);
	   if (str2=="s"){
	        return str1*1000;
	   }
	   else if (str2=="m"){
	       return str1*60*1000;
	   }
	   else if (str2=="h"){
		   return str1*60*60*1000;
	   }
	   else if (str2=="d"){
	       return str1*24*60*60*1000;
	   }
	}
};
/*******************************************************cookie操作end**************************************************************************/

/*******************************************************集合操作start**************************************************************************/
$.tmArray = {
	/*each和map的功能是一样的*/	
	each : function(arr,fn){
		fn = fn || Function.K;
		var a = [];
		var args = Array.prototype.slice.call(arguments, 1);
		for(var i = 0; i < arr.length; i++){
			var res = fn.apply(arr,[arr[i],i].concat(args));
			if(res != null) a.push(res);
		}
		return a;
	},
	/*each和map的功能是一样的*/	
	map : function(arr,fn,thisObj){
		var scope = thisObj || window;
		var a = [];
		for ( var i=0, j=arr.length; i < j; ++i ) {
			var res = fn.call(scope, arr[i], i, this);
			if(res != null) a.push(res);
		}
		return a;
	},
	orderBy : function(array,sortFlag){
		var $arr = array;
		if(sortFlag=='asc'){
			$arr.sort(this._numAscSort);
		}else if(sortFlag=='desc'){
			$arr.sort(this._numDescSort);
		}else{
			$arr.sort(this._numAscSort);
		}
		return $arr;
	},
	// 求两个集合的并集
	union : function(a, b){
		 var newArr = a.concat(b);
		 return this.unique2(newArr);
	},
	// 求两个集合的补集
	complement :function(a,b){
		return this.minus(this.union(a,b),this.intersect(a,b));	
	},
	// 求两个集合的交集
	intersect : function(a,b){
	   a = this.unique(a);	
		return this.each(a,function(o){
			return b.contains(o) ? o : null;
		});
	},
	//求两个集合的差集
	minus : function(a,b){
		a = this.unique(a);	
		return this.each(a,function(o){
			return b.contains(o) ? null : o;
		});
	},
	max : function(arr){
		return Math.max.apply({},arr) ;
	},
	min : function(arr){
		return Math.min.apply({},arr) ;
	},
	unique :function(arr){
		 var ra = new Array();
		 for(var i = 0; i < arr.length; i ++){
			 if(!ra.contains(arr[i])){
			 //if(this.contains(ra,arr[i])){	
				ra.push(arr[i]);
			 }
		 }
		 return ra;
	},
	unique2 : function(arr){
		for ( var i = 0; i < arr.length; i++) {
			for ( var j = i + 1; j < arr.length;) {
				if (arr[j] == arr[i]) {
					arr.splice(j, 1);
				} else {
					j++;
				}
			}
		}
		return arr;
	},
	indexOf : function(arr,val){
		for ( var i = 0; i < arr.length; i++) {
			if (arr[i] == val)
				return i;
		}
		return -1;	
	},
	contains : function(arr,val){
		return this.indexOf(arr,val) !=-1 ? true : false;
	},
	remove : function(arr,index){
		var index = this.indexOf(arr,index);
		if (index > -1) {
			arr.splice(index, 1);
		}
		return arr;
	},
	removeObject : function(arr,item){
		for ( var i = 0; i < arr.length; i++) {
			var jsonData = arr[i];
			for ( var key in jsonData) {
				if (jsonData[key] == item) {
					arr.splice(i, 1);
				}
			}
		}
		return arr;
	},
	toArray : function(arrString,sp){
		if(sp==undefined)sp=",";
		if(arrString==undefined)return this;
		var arrs = arrString.split(sp);
		return arrs;
	},
	_numAscSort :function(a,b){
		 return a-b;
	},
	_numDescSort :function(a,b){
		return b-a;
	},
	_sortAsc : function(x, y){
		if(x>y){
			return 1;
		}else{
			return -1;
		}
	},
	_sortDesc : function (x, y){
		if(x>y){
			return -1;
		}else{
			return 1;
		}
	}
};
/*******************************************************集合操作end**************************************************************************/

/*******************************************************正则表达式操作start**************************************************************************/
/*手机*/
function isPhone(str){
    var regExp = /^(\+86)?(13|15|17|18|)\d{11}(?!\d)$/;
    return regExp.test(str);
}

 /*邮件格式*/ 
function isEmail(str){ 
    var regExp = /^([\w\.])+@\w+\.([\w\.])+$/;
    return regExp.test(str);
}

/*******************************************************正则表达式操作end**************************************************************************/



$(function(){
	$(".tm_number").on("keyup",function(e) {
	    return tm_numberKey($(this), e);
	});
	//判断是否IE9以下浏览器
	var v = getBroswerVersion().version;
	if(v=="ie_6" || v=="ie_7" || v=="ie_8"){
		$("body").append("<div id='tipnobox' style='background:#1F53A0;padding:20% 0px;text-align:center;font-size:36px;line-height:46px;font-weight:700;color:#fff;position:fixed;width:100%;height:100%;top:0;left:0;z-index:9999'>很是遗憾，作者关闭了IE6,7,8,为推动互联的发展做贡献....请下载ie9+浏览器,推荐使用google和火狐浏览器.</div>");
		shakeCharacter("tipnobox");	
	}
});
/* 获取浏览器的版本 */
function getBroswerVersion() {
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    if (ua) {
        window.ActiveXObject ? Sys.version = "ie_" + ua.match(/msie ([\d]+)/)[1] : document.getBoxObjectFor ? Sys.version = "firefox_" + ua.match(/firefox\/([\d.]+)/)[1] : window.MessageEvent && !document.getBoxObjectFor ? Sys.version = "chrome": window.opera ? Sys.version = "opera_" + ua.match(/opera.([\d.]+)/)[1] : window.openDatabase ? Sys.version = ua.match(/version\/([\d.]+)/)[1] : 0;
    }
    return Sys;
}
/*本地缓存*/
var tmMap ={
	put:function(key,value,mark){
		if(window.localStorage){
			var storage = (mark==1?window.localStorage:window.sessionStorage);
			storage.setItem(key, value);
		}else{
			//cookie
		}
	},
	get:function(key,mark){
		if(window.localStorage){
			var storage = (mark==1?window.localStorage:window.sessionStorage);
			return storage.getItem(key);
		}else{
			//cookie
		}
	},
	remove:function(key,mark){
		if(window.localStorage){
			var storage = (mark==1?window.localStorage:window.sessionStorage);
			storage.removeItem(key);
		}else{
			//cookie
		}
	}
};

