<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图文报表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/pages/backend/common/common.jsp" %>
<script type="text/javascript" src="${basePath}/resources/bk/sg/echarts/echarts.js" ></script>
<script type="text/javascript" src="${basePath}/resources/bk/sg/echarts/china.js" ></script>
</head>
<body>
<!-- 头部 -->
<%@include file="/WEB-INF/pages/backend/frame/top.jsp" %>
	<div class="down-main">
	<!-- 左侧导航栏 -->
	<%@include file="/WEB-INF/pages/backend/frame/left.jsp" %>
	<!-- 主体start -->		
		<div class="right-product view-product right-full">
			<div class="container-fluid">
				<div class="manage account-manage info-center">
					<div class="page-header">
						<div class="pull-left">
							<h4>图文报表</h4>
						</div>
					</div>
					<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
					<div id="main" style="width:100%;height:600px;"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var data = [
	{name: '洛阳', value: 20},
];
var geoCoordMap = {
'洛阳':[112.42648,34.627715],
};

var convertData = function (data) {
var res = [];
for (var i = 0; i < data.length; i++) {
var geoCoord = geoCoordMap[data[i].name];
if (geoCoord) {
    res.push({
        name: data[i].name,
        value: geoCoord.concat(data[i].value)
    });
}
}
return res;
};

option = {
backgroundColor: '#404a59',
title: {
text: '到过的城市',
subtext: '走遍世界',
sublink: 'http://www.baidu.com',
left: 'center',
textStyle: {
    color: '#fff'
}
},
tooltip : {
trigger: 'item'
},
legend: {
orient: 'vertical',
y: 'bottom',
x:'right',
data:['作者-金'],
textStyle: {
    color: '#fff'
}
},
geo: {
map: 'china',
label: {
    emphasis: {
        show: false
    }
},
roam: true,
itemStyle: {
    normal: {
        areaColor: '#323c48',
        borderColor: '#111'
    },
    emphasis: {
        areaColor: '#2a333d'
    }
}
},
series : [
{
    name: '作者-金',
    type: 'scatter',
    coordinateSystem: 'geo',
    data: convertData(data),
    symbolSize: function (val) {
        return val[2] / 10;
    },
    label: {
        normal: {
            formatter: '{b}',
            position: 'right',
            show: false
        },
        emphasis: {
            show: true
        }
    },
    itemStyle: {
        normal: {
            color: '#ddb926'
        }
    }
},
{
    name: 'Top 5',
    type: 'effectScatter',
    coordinateSystem: 'geo',
    data: convertData(data.sort(function (a, b) {
        return b.value - a.value;
    }).slice(0, 6)),
    symbolSize: function (val) {
        return val[2] / 10;
    },
    showEffectOn: 'render',
    rippleEffect: {
        brushType: 'stroke'
    },
    hoverAnimation: true,
    label: {
        normal: {
            formatter: '{b}',
            position: 'right',
            show: true
        }
    },
    itemStyle: {
        normal: {
            color: '#f4e925',
            shadowBlur: 10,
            shadowColor: '#333'
        }
    },
    zlevel: 1
}
]
};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);   
</script>
</html>