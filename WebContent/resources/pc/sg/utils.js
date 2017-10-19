//获取url参数
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return '';
};
//unicode 转 str f
function decToHex(str) {  
	var res=[];  
	for(var i=0;i < str.length;i++)  
		res[i]=("00"+str.charCodeAt(i).toString(16)).slice(-4);  
	return "\\u"+res.join("\\u");  
}  
// str 转  unicode
function hexToDec(str) {  
	str=str.replace(/\\/g,"%");  
	return unescape(str);  
}  
/**
*  传入时间，获取年
*/
function dateToYear(date){
    if(date.length>4){
        return date.substring(0,4);
    }
    return "0";
}
/**
*  传入时间，获取天
*/
function dateToDay(date){
    if(date.length>10){
        return date.substring(8,10);
    }
    return "0";
}
/**
*  传入时间，获取月
*/
function dateToChMonth(date){
    var mon=0;
    var monthArray=new Array("","January","February","March","April","May","June","July","August",
        "September","October","November","December");
    if(date.length>7){
        mon= date.substring(5,7);
    }
    if(mon.indexOf('0')==0){
        mon=mon.substring(1,2);
    }

    return monthArray[mon];
}
function dateToCNFormat(dt) {
    //var myDate = new Date(dt);
    var y = dateToYear(dt);
    var mon = "";
    if (dt.length > 7) {
        mon = dt.substring(5, 7);
    }
    if(mon.indexOf('0')==0){
        mon=mon.substring(1,2);
    }
    var d = dateToDay(dt);
    return y + "年" + mon + "月" + d + "日";
}
function dateToCNFormat3nian(dt) {
    var y = dateToYear(dt);
    var mon = "";
    if (dt.length > 7) {
        mon = dt.substring(5, 7);
    }
    if (mon.indexOf('0') == 0) {
        mon = mon.substring(1, 2);
    }
    var d = dateToDay(dt);
    return (parseInt(y) + 3) + "年" + mon + "月" + d + "日";
}

function submitAjaxJsonp(suburl, parm, asyncflag, callback,postget) {
    $.ajax({
        url: suburl,
        contentType: "application/json;",
        dataType: 'JSONP',
        jsonpCallback: callback,
        async: asyncflag,
        type: postget,
        data: parm,
        success: function (date) {
            //if (date.success == false) {
            //    window.location.href = "index.html";
            // }
			alert("进入success方法:"+date);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //var jj=JSON.stringify(XMLHttpRequest);
            //$("#btn3").html("1"+XMLHttpRequest.responseText);
            eval(XMLHttpRequest.responseText);
			console.log(textStatus+"   "+errorThrown);
        }
    });
}

function submitAjaxJson(suburl, asyncflag, datatype,type,parm,callback) {
	$.ajax({
	    url: suburl,
	    dataType: datatype,
	    async: asyncflag,
	    type: type,
	    data:parm,
	    success: function (date) {
	        callback(date);
	    }
	});
};

function xmlToJson(xml) {
    var obj = {};
    if (xml.nodeType == 1) { // element
        // do attributes
        if (xml.attributes.length > 0) {
            obj["@attributes"] = {};
            for (var j = 0; j < xml.attributes.length; j++) {
                var attribute = xml.attributes.item(j);
                obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
            }
        }
    } else if (xml.nodeType == 3) { // text
        obj = xml.nodeValue;
    }
    // do children
    if (xml.hasChildNodes()) {
        for(var i = 0; i < xml.childNodes.length; i++) {
            var item = xml.childNodes.item(i);
            var nodeName = item.nodeName;
            if (typeof(obj[nodeName]) == "undefined") {
                obj[nodeName] = xmlToJson(item);
            } else {
                if (typeof(obj[nodeName].length) == "undefined") {
                    var old = obj[nodeName];
                    obj[nodeName] = [];
                    obj[nodeName].push(old);
                }
                obj[nodeName].push(xmlToJson(item));
            }
        }
    }
    return obj;
}

function replaceStrNsap(s){
    return s;
}
function replacebr(str) {
    str = str.replace(/\r\n/ig, "<br/>");
    return str;
}

function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return ""
}

function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) +((expiredays == null) ? ";path=/" : "; expires=" + exdate.toGMTString()+";path=/");
}

function checkCookie() {
    return false;
    nahongcookie = getCookie('nahongcookie');
    if (nahongcookie != null && nahongcookie != "") {
        return false;
    }
    else {
        setCookie('nahongcookie', "yes", null);
        return true;
    }
}

function clearCookie() {
    setCookie('nahongcookie', "", -1);
}




//控制后台图片合理显示
function setImage($img, $div) {
    var imgw = $img.width();
    var imgh = $img.height();
    var w = $div.width();
    var h = $div.height();
    var ratio = imgw / imgh;
    if (ratio > (w / h)) {
        //只要控制高
        $img.height(h + "px");
        imgw = $img.width();
        $img.css("margin-left", (imgw / -2) + "px");
        $img.css("left", "50%");
    } else {
        //只要控制宽
        $img.width(w + "px");
        imgh = $img.height();
        $img.css("margin-top", (imgh / -2) + "px");
        $img.css("top", "50%");
    }
}


function checkBrowser() {
    if (!window.applicationCache) {
        if (typeof (Worker) == "undefined") {
            window.location.href = "other_browser.html";
//            if (navigator.userAgent.indexOf("MSIE") > -1) {
//                var ss = navigator.userAgent.substring(navigator.userAgent.indexOf("MSIE") + 4, navigator.userAgent.indexOf("MSIE") + 8);
//                if (parseInt(ss) <= 9) {
//                    alert("不行");
//                }
//            }
        }
    }
}



function goto_scroll(ele) {
    var scroll_offset = $("#" + ele).offset();  //得到pos这个div层的offset，包含两个值，top和left
    $("body,html").animate({
        scrollTop: scroll_offset.top -90   //让body的scrollTop等于pos的top，就实现了滚动
    }, 0);
}



function getOnlinetime(oldtime) {
    var newtime = oldtime;
    if (newtime != null && newtime != "" && newtime.length > 9) {
        newtime = newtime.substring(0, 10);
        return newtime;
    } else {
        return "-";
    }
}


function hideDialog() {
    $(".webDialog").hide();
    $(".webDialog_bg").hide();
}

function getNewsId() {
    try {
        var url = window.location.href;
        var page = url.substring(url.lastIndexOf("/") + 1);
        page = page.substr(0, page.lastIndexOf("."));
        var id = page.substring(10);
        return id;
    } catch (e) {
        return "";
    }

}

function callbackErrorAlert(json) {
    var url = window.location.href;
    var mpath = "/m/";
    var urltype = url.indexOf(mpath);
    if (urltype > 1) {
        //$("#d1").append("<br/>3:访问的是手机版");
        if (json.errorCode == 3009) {
            alert(json.msg);
            window.location.href = "/m/user/login.html";
        } else if (json.errorCode == 4006) {
            setCookie('nahonguser', "", -1);
            alert(json.msg);
            window.location.href = "/m/user/login.html";
        }
        else {
            alert(json.msg);
        }
    } else {
        //$("#d1").append("<br/>3:访问的是电脑版");
        if (json.errorCode == 3009) {//未登陆
            window.wxc.xcConfirm(json.msg, window.wxc.xcConfirm.typeEnum.error, {
                onOk: function () {
                    window.location.href = "/user/login.html";
                }
            });
        } else if (json.errorCode == 4006) {//登陆超时
            window.wxc.xcConfirm(json.msg, window.wxc.xcConfirm.typeEnum.error, {
                onOk: function () {
                    setCookie('nahonguser', "", -1);
                    window.location.href = "/user/login.html";
                }
            });
        } else if(json.errorCode == 2002) {//未验证身份证
            window.wxc.xcConfirm("请您先认证您的身份证信息！", window.wxc.xcConfirm.typeEnum.info, {
                onOk: function () {
                    window.location.href = "/user/user_information.html";
                }
            });
        }else{
            window.wxc.xcConfirm(json.msg, window.wxc.xcConfirm.typeEnum.error);
        }
    }
}

function checkLogin() {
    var url = window.location.href;
    var mpath = "/m/";
    var urltype = url.indexOf(mpath);
    if (urltype > 1) {
        //$("#d1").append("<br/>3:访问的是手机版");
        if (getCookie('nahonguser') == null || getCookie('nahonguser') == '') {
            window.location.href = "/m/user/login.html";
        }
    } else {
        //$("#d1").append("<br/>3:访问的是电脑版");
        if (getCookie('nahonguser') == null || getCookie('nahonguser') == '') {
            window.wxc.xcConfirm("请先登录！", window.wxc.xcConfirm.typeEnum.error, {
                onOk: function () {
                    window.location.href = "/user/login.html";
                }
            });
        }
    }
}


function getstringindexof(str, c) {
    var lastindex = str.indexOf(c);
    if (lastindex != -1) {
        str = str.substring(0, lastindex);
    }
    return str;
}
