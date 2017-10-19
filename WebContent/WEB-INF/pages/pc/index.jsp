<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="WEB简历,Java,Java简历,Java高级工程师,招聘Java">
	<meta http-equiv="description" content="WEB简历,Java,Java简历,Java高级工程师,招聘Java,招Java工程师,Java技术总结">
	<%@include file="/WEB-INF/pages/common/common.jsp"%>
	<link rel="stylesheet" type="text/css" href="${basePath}/resources/pc/css/index.css" />
	<link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_360689_3wjovopiwn6x0f6r.css" />
</head>

<body>

<div id="warp"  >
		<div class="box_center">
			<div class="box box1">
				<div class="content">
					<div class="myInfo">
						<img src="${basePath}/resources/pc/img/info.jpg" width="100" height="100" /> 
						<div class="info">Java攻城狮</div>
					</div>
					<p class="sign">朋友问我暗恋是什么感觉，下意识的回答:好像在商店看到喜欢的玩具，想买，钱不够，努力存钱，回头去看的时候发现涨价了，更加拼命的存钱，等我觉得差不多的时候，再回去发现已经被买走了。希望不会在垃圾堆看到这玩具，不然我依然会把它捡起来。</p>
					<p class="sign_my sign_name">我是金梦宇</p>
					<p class="sign_my sign_synopsis">一个有梦想的程序猿</p>
					<p class="sign_my sign_email">hnlyjmy@163.com</p>
				</div>
			</div>
			<div class="box box2">
				<div class="about">
					<div class="about_title">
						<h1>关于我</h1>
						<span></span>
					</div>
					<p class="about_me">我叫金梦宇，23岁，坐标上海，是一名Java软件工程师。我并不是计算机专业出身，却对计算机有着浓厚的兴趣，所以自学了Java、HTML5、CSS3、SQL等相关的技术。今后的职业规划是朝着全栈发展。</p>
					<div id="myEcharts" ></div>
				</div>
			</div>
			
			<div class="box box3" id="box_case">
				<h1 id="demo_title">作品集</h1>
				<div id="perspective">
					<div id="case" >
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<li>demo1</li>
						<p></p>
					</div>
				</div>
			</div>
			
			<div class="box box4">
				<div class="msg">
					<h1>留言板</h1>
					<h2>我很期待与您交流，希望您留下宝贵意见或建议，我会在第一时间回复您。</h2>
					<div class="name">
						<input  type="text" name="name" placeholder="称呼" />
					</div>
					<div class="message">
						<textarea name="message" placeholder="留言" ></textarea>
					</div>
					<style type="text/css">
					.box.box4 .msg .btn .btn_sbu{
						    background: #4b8052;
						    width: 80;
						    height: 50px;
						    font-size: 17px;
						    color: #fff;
						    border-radius: 16px;
						    cursor:pointer;
					}
					</style>
					<div class="btn">
						<button class="btn_sbu">提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="slider">
		<div class="music">
			<div  class="iconfont  icon-music icon_music rotate"></div>
			<p class="tab" id="music_btn">选择音乐</p>
		</div>
		<ul>
			<li><div class="icons" style="display:block;"><span class="iconfont  icon-zhuye"></span></div><p class="tab">主页</p></li>
			<li><div class="icons"><span class="iconfont  icon-guanyuwo"></span></div>
			<p class="tab">关于我</p></li>
			<li><div class="icons"><span class="iconfont  icon-zuopinji"></span></div>
			<p class="tab">作品集</p></li>
			<li><div class="icons"><span class="iconfont  icon-lyb"></span></div><p class="tab">留言</p></li>
		</ul>
	</div>





	<style type="text/css">
		#box_left .banner{height: 300px;border: 1px solid red;}
		#box_left .content{
			width: 70%;
			margin: 0px auto;
			background: pink;
			height: 1000px;
		}
		#box_left .content ul li{
			width: 100px;
			height: 100px;
			float: left;
			margin: 40px 0 0 40px;
			cursor: pointer;
			position: relative;
			border-radius: 100%;
		}
		#box_left .content ul li .m_li{
			width: 100px;
			height: 100px;
			cursor: pointer;
			position: relative;
			overflow: hidden;
			border-radius: 100%;
		}
		#box_left .content ul li img{
			width: 100px;
			height: 100px;
			border-radius: 100%;
			border: 0px;
			transition: 0.8s;
			transform: scale(1);
		}
		#box_left .content ul li .play{
			width: 100%;
			height: 100%;
			background: rgba(0,0,0,.5);
			position: absolute;
			top: 0px;
			left: 0px;
			border-radius: 100%;
			opacity:0;
			transition: 0.5s;
		}
		#box_left .content ul li:hover .play{
			opacity:1;	
		}
		#box_left .content ul li:hover img{
			transform: scale(1.2);
		}
		#box_left .content ul li .icon{
			display: block;
			font-size:22px;
			color: #fff;
			text-align: center;
			line-height:100px;
		}
		#box_left .content ul li .icon .icon-stop-copy{
			display:none;
		}
		#box_left .content ul li img.rotate{
			-webkit-transition-property: -webkit-transform;
		    -webkit-transition-duration: 1s;
		    -moz-transition-property: -moz-transform;
		    -moz-transition-duration: 1s;
		    -webkit-animation: rotate 5s linear infinite;
		    -moz-animation: rotate 5s linear infinite;
		    -o-animation: rotate 5s linear infinite;
		    animation: rotate 5s linear infinite;
		}
		@-webkit-keyframes rotate{from{-webkit-transform: rotate(0deg)}
		    to{-webkit-transform: rotate(360deg)}
		}
		@-moz-keyframes rotate{
			from{-moz-transform: rotate(0deg)}
		    to{-moz-transform: rotate(359deg)}
		}
		@-o-keyframes rotate{
			from{-o-transform: rotate(0deg)}
		    to{-o-transform: rotate(359deg)}
		}
		@keyframes rotate{
			from{transform: rotate(0deg)}
		    to{transform: rotate(359deg)}
		}
		#box_left .content ul li p{
			position: absolute;
			bottom: -21px;
			left: 0px;
			width: 100%;
			text-align: center;
			font-size: 14px;
		}
	</style>
	<div id="box_left">
		<div class="banner"></div>
		<div class="content">
			<ul>
				<li data-play="0" >
					<div class="m_li">
						<img src="http://img3.kwcdn.kuwo.cn/star/userpl2015/10/13/1455342310364_132026710b.jpg" width="100" height="100" />
						<div class="icon play">
							<i class="iconfont icon-Play"></i>
							<i class="iconfont icon-stop-copy"></i>
						</div>
					</div>
					<p class="text">每日最新网络</p>
				</li>

				<li data-play="0" >
					<div class="m_li">
						<img src="http://img3.kwcdn.kuwo.cn/star/userpl2015/10/13/1455342310364_132026710b.jpg" width="100" height="100" />
						<div class="icon play">
							<i class="iconfont icon-Play"></i>
							<i class="iconfont icon-stop-copy"></i>
						</div>
					</div>
					<p class="text">每日最新网络</p>
				</li>
				<li data-play="0" >
					<div class="m_li">
						<img src="http://img3.kwcdn.kuwo.cn/star/userpl2015/10/13/1455342310364_132026710b.jpg" width="100" height="100" />
						<div class="icon play">
							<i class="iconfont icon-Play"></i>
							<i class="iconfont icon-stop-copy"></i>
						</div>
					</div>
					<p class="text">每日最新网络</p>
				</li>
				<li data-play="0" >
					<div class="m_li">
						<img src="http://img3.kwcdn.kuwo.cn/star/userpl2015/10/13/1455342310364_132026710b.jpg" width="100" height="100" />
						<div class="icon play">
							<i class="iconfont icon-Play"></i>
							<i class="iconfont icon-stop-copy"></i>
						</div>
					</div>
					<p class="text">每日最新网络</p>
				</li>
				<li data-play="0" >
					<div class="m_li">
						<img src="http://img3.kwcdn.kuwo.cn/star/userpl2015/10/13/1455342310364_132026710b.jpg" width="100" height="100" />
						<div class="icon play">
							<i class="iconfont icon-Play"></i>
							<i class="iconfont icon-stop-copy"></i>
						</div>
					</div>
					<p class="text">每日最新网络</p>
				</li>
				<li data-play="0" >
					<div class="m_li">
						<img src="http://img3.kwcdn.kuwo.cn/star/userpl2015/10/13/1455342310364_132026710b.jpg" width="100" height="100" />
						<div class="icon play">
							<i class="iconfont icon-Play"></i>
							<i class="iconfont icon-stop-copy"></i>
						</div>
					</div>
					<p class="text">每日最新网络</p>
				</li>
				<li data-play="0" >
					<div class="m_li">
						<img src="http://img3.kwcdn.kuwo.cn/star/userpl2015/10/13/1455342310364_132026710b.jpg" width="100" height="100" />
						<div class="icon play">
							<i class="iconfont icon-Play"></i>
							<i class="iconfont icon-stop-copy"></i>
						</div>
					</div>
					<p class="text">每日最新网络</p>
				</li>



			</ul>
		</div>
		<div class="close">
			<div class="back">Back</div>
		</div>
		<div class="bottom noselect">
			<div class="controller">
				<div class="con_box" >
					<div class="con pre" id="music_pre"></div>
					<div class="con play" id="music_play"></div>
					<div class="con sotp" id="music_stop"></div>
					<div class="con next" id="music_next"></div>		
				</div>
				<div class="con_slider">
					<div class="music_name">回到过去 - 刘亦菲 / 杨洋</div>
					<div class="music_time">
						<span id="sum_time">00:00</span>/<span id="play_time">00:00</span>
					</div>
					<div class="music_slider_op" id="music_slider_cover">
						<i class="music_slider_playe" id="music_move"></i>
					</div>
					<div class="music_slider" id="music_slider"></div>
				</div>
				<div class="con_arge">
					<div class="sound_img sound_img_1" title="顺序播放"></div>
					<div class="sound_img sound_img_2" title="循环单曲"></div>
					<div class="sound_img sound_img_3" title="列表循环"></div>
					<div class="sound_img sound_img_4" title="随机播放"></div>
					<div class="music_volume">
						<div class="volume_img"></div>
						<div class="volume">
							<div class="vol_slider_op" id="perter">
								<i class="vol_slider_playe" id="volume_move"></i>
							</div>
							<div class="vol_slider" id="perClick"></div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<style>
		#dialog{
			width:100%;
			height:100%;
			background:rgba(0,0,0,.3);
			z-index: 9999999;
		    position: fixed;
		    top: 0px;
			left: 0px;
		}
		#dialog .box{
		    width: 300px;
		    min-height: 100px;
		    background: #fff;
		    position: absolute;
		    top: 50%;
		    left: 50%;
		    margin-left: -150px;
		    margin-top: -150px;
		}
		#dialog .box input{
			font-size: 14px;
		    height: 40px;
		    line-height: 26px;
		    padding: 8px 5%;
		    width: 90%;
		    text-indent: 2em;
		    border: none;
		    background: #5cbdaa;
		    color: #fff;
		    font-weight: normal;
		}
		
	</style>
	
	<!-- 弹出框 -->
	<div id="dialog">
		<div class="box">
			<input type="text" id="J_codetext" placeholder="验证码" maxlength="6" class="login_txtbx">
			<div class="codeimg_box" onclick="createCode()">
				<img alt="图片验证码" title="点我刷新" src="${basePath}/bk/n/user/code" width="95" height="40" id="code_img" >
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${basePath}/resources/pc/sg/jquery/jquery.mousewheel.js"></script>
<script type="text/javascript" src="${basePath}/resources/pc/sg/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/pc/sg/echarts/myEcharts.js"></script>
<script type="text/javascript" src="${basePath}/resources/pc/js/index.js" ></script>
<script type="text/javascript">

</script>
</html>