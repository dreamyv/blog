$(document).ready(function() {
	//粒子背景特效
	$('body').particleground({
		dotColor : '#5cbdaa',/*点背景色*/
		lineColor : '#5cbdaa'/*线背景色*/
	});
	//测试提交，对接程序删除即可
	$(".submit_btn").click(function() {
		login();
	});
	/*键盘按下*/
	document.onkeydown=function(event){
	    var e = event || window.event || arguments.callee.caller.arguments[0];
	    if(e && e.keyCode==113){ // 按 F2 
	        return false;
	    }            
	    if(e && e.keyCode==13){ // enter 键
	    	 login();
	    }
	}; 
});

function login(){
	var fail = validate();
	if(!fail) return false;
	var params = {
		"account":$("#account").val(),
		"pwd":$("#pwd").val(),
		"code":$("#J_codetext").val()
	};
	console.log("000",params);
	/*tmUtil.request({
		url:basePath+"/h/n/toLogin",
		data:params,
		success:function(data){
			console.log("123",data);
			//获取上个页面的路径
			var ref = document.referrer;
			if(ref.isNotEmpty()){
				console.log("123",ref);
				location.href = ref;
			}else{
				alert(data);
			}
		}
	});*/
	
	$.ajax({
		url:basePath+"/bk/n/user/toLogin",
		data:params,
		type:"post",
		async:false,
		success:function(data){
			console.log(data);
			if(data == "200"){
				//获取上个页面的路径
				var ref = document.referrer;
				console.log(ref);
				if(ref.isNotEmpty() && ref.indexOf('bk/n/user/login')==-1){
					location.href = ref;
				}else{
					location.href=basePath+"/bk/l/index/index";
				}
			}else if(data == "500"){
				alert("验证码错误")
			}else if(data == "501"){
				alert("账户名或密码错误")
			}else if(data == "502"){
				alert("用户状态已销户")
			}else if(data == "503"){
				alert("该用户不允许登录")
			}else if(data == "504"){
				alert("登录验证码失效")
			}
		}
	});
}
function createCode(){
	var src = basePath+"/bk/n/user/code?d="+new Date();
	$("#code_img").attr("src",src);
	return false;
}
function validate() {
    var inputCode = document.getElementById("J_codetext").value.toUpperCase();
    var codeToUp=inputCode.toUpperCase();
    if(inputCode.length <=0) {
      document.getElementById("J_codetext").setAttribute("placeholder","请输入验证码");
      //createCode();
      return false;
    }
    else if(inputCode != codeToUp ){
      document.getElementById("J_codetext").value="";
      document.getElementById("J_codetext").setAttribute("placeholder","验证码错误");
      //createCode();
      return false;
    }
    else {
//      window.open(document.getElementById("J_down").getAttribute("data-link"));
//      document.getElementById("J_codetext").value="";
      createCode();
      return true;
    }
}