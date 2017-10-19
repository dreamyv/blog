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
							<h4>树形菜单</h4>
						</div>
					</div>
					<!-- 面板start -->
                    <div id="tree" class="ztree" style=""></div>
					<!-- 面板end -->
					<button id="sub">提交</button>
				</div>
					
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">

var zTree;
var demoIframe;

function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button remove' id='removeBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";

    addStr += "<span class='button add' id='addBtn_" + treeNode.tId + "'></span>";
    addStr += "<span class='button edit' id='editBtn_" + treeNode.tId + "'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.addNodes(treeNode, {id:(1000 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
    $("#removeBtn_"+treeNode.tId).unbind().remove();
    $("#editBtn_"+treeNode.tId).unbind().remove();
};

var setting = {
    check: {
        enable: true//设置 zTree 的节点上是否显示 checkbox / radio
    },
    view: {
//         addHoverDom: addHoverDom,//用于在节点上固定显示用户自定义控件
        //removeHoverDom: removeHoverDom,//用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
        dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
        showLine: true,//设置 zTree 是否显示节点之间的连线。
        selectedMulti: false,//设置是否允许同时选中多个节点。
        showIcon: false	//设置 zTree 是否显示节点的图标。
    },
    data: {
        simpleData: {
            enable:true,//是否异步加载
            idKey: "id",//节点数据中保存唯一标识的属性名称
            pIdKey: "pId",//节点数据中保存其父节点唯一标识的属性名称
            rootPId: ""//用于修正根节点父节点数据，即 pIdKey 指定的属性值,默认null
        }
    },
    callback: {
        beforeClick: function(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree");
            if (treeNode.isParent) {
            	//点击父节点
                zTree.expandNode(treeNode);
                return false;
            } else {
                var checked = treeNode.checked;
                console.log("123",checked)
                if(checked){
                	zTree.checkNode(treeNode, false,false);
                }else{
	                zTree.checkNode(treeNode, true, true);
                }
                return true;
            }
        }
    }
};
//事例json数据
var zNodes =[
    {id:1, pId:0, name:"[core] 基本功能 演示", open:false,checked:true},
    {id:101, pId:1, name:"最简单的树 --  标准 JSON 数据", file:"core/standardData",checked:true },
    {id:102, pId:1, name:"最简单的树 --  简单 JSON 数据", file:"core/simpleData",checked:true },
    {id:103, pId:1, name:"不显示 连接线", file:"core/noline",checked:false},

    {id:2, pId:0, name:"[excheck] 复/单选框功能 演示", open:false},
    {id:201, pId:2, name:"Checkbox 勾选操作", file:"excheck/checkbox"},
    {id:206, pId:2, name:"Checkbox nocheck 演示", file:"excheck/checkbox_nocheck"},
    {id:207, pId:2, name:"Checkbox chkDisabled 演示", file:"excheck/checkbox_chkDisabled"},

    {id:3, pId:0, name:"[exedit] 编辑功能 演示", open:false},
    {id:301, pId:3, name:"拖拽 节点 基本控制", file:"exedit/drag"},
    {id:302, pId:3, name:"拖拽 节点 高级控制", file:"exedit/drag_super"},
    {id:303, pId:3, name:"用 zTree 方法 移动 / 复制 节点", file:"exedit/drag_fun"},

    {id:4, pId:0, name:"大数据量 演示", open:false},
    {id:401, pId:4, name:"一次性加载大数据量", file:"bigdata/common"},
    {id:402, pId:4, name:"分批异步加载大数据量", file:"bigdata/diy_async"},
    {id:403, pId:4, name:"分批异步加载大数据量", file:"bigdata/page"},

    {id:5, pId:0, name:"组合功能 演示", open:false},
    {id:501, pId:5, name:"冻结根节点", file:"super/oneroot"},

    {id:6, pId:0, name:"其他扩展功能 演示", open:false},
    {id:601, pId:6, name:"隐藏普通节点", file:"exhide/common"},
    {id:602, pId:6, name:"配合 checkbox 的隐藏", file:"exhide/checkbox"},
    {id:603, pId:6, name:"配合 radio 的隐藏", file:"exhide/radio"}
];
function loadReady() {
    var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
    if (h < 530){
    	h = 530;
    }
    demoIframe.height(h);
}

$(document).ready(function(){
    var t = $("#tree");
   var zNodess;
    $.ajax({
    	url:basePath+"/bk/l/sys/ztree",
    	data:{},
    	type:"post",
    	async:false,
    	success:function(data){
    		zNodess = data; 
    	}
    });
    console.log(JSON.stringify(zNodess));
    t = $.fn.zTree.init(t, setting, zNodess);
//     demoIframe = $("#testIframe");
//     demoIframe.bind("load", loadReady);
//     var zTree = $.fn.zTree.getZTreeObj("tree");
//     zTree.selectNode(zTree.getNodeByParam("id", 101));

	$("#sub").click(function(){
// 		var treeObj = $.fn.zTree.getZTreeObj("tree");
// 		var nodes = treeObj.getChangeCheckedNodes();//获取输入框勾选状态被改变的节点集合（与原始数据 checkedOld 对比）
// 		var treeObj = $.fn.zTree.getZTreeObj("tree");
// 		var nodes = treeObj.getCheckedNodes(true);//获取输入框被勾选 或 未勾选的节点集合
// 		if(nodes!=""){
// 			console.log(nodes);
// // 			console.log(JSON.stringify(nodes));
// 			var len = nodes.length;	
// 			for(var i=0;i<len;i++){
// 				var name = nodes[i].name;
// 				var id = nodes[i].id;
// 				console.log("id:"+id+" name:"+name);
// 			}
// 		}else{
// 			console.log('1',nodes);
// 		}
		var obj = [1,512,603];
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getNodes();//获取所有节点
		checkNodes(nodes,obj,treeObj)
	});
});
/**
 * nodes:节点对象
 * obj:勾选节点的数组id
 * treeObj:ztree对象树
 * 作用:
 */
function checkNodes(nodes,obj,treeObj){
	var l=nodes.length;
	var lenjson = obj.length;//要选中的节点
	for (var i=0; i < l; i++) {
		var name = nodes[i].name;
		var id = nodes[i].id;
		for(var j=0;j<lenjson;j++){
			var ids = obj[j];
			if(ids==id){
				treeObj.checkNode(nodes[i], true, true);
				break;
			}
		}
		var childrenNodes = nodes[i].children;
		if(childrenNodes!=undefined && childrenNodes!= "childrenNodes" && childrenNodes.length>0){
			checkNodes(childrenNodes,obj,treeObj);			
		}
	}
}

</script>
</html>