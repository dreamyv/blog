var button_submit = "addMusic";
var oTable;
var zTree;
/*树形菜单配置*/
var setting = {
    check: {
        enable: true//设置 zTree 的节点上是否显示 checkbox / radio
    },
    view: {
		//addHoverDom: addHoverDom,//用于在节点上固定显示用户自定义控件
        //removeHoverDom: removeHoverDom,//用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
        dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
        showLine: false,//设置 zTree 是否显示节点之间的连线。
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
$(function () {
    //1.初始化Table
    oTable = new TableInit();
    oTable.Init();
    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
    
    /*初始化条件查询时间框*/
	var tp = new timePlugins("crstartTime","crendTime","reportrange1");
	tp.init();
	
    //查询按钮
    $("#transfer_frmSearch").click(function(){
    	oTable.Init();
    	new PNotify({
            title: '提示消息',
            text: '查找成功',
            type: 'success',
            styling: 'bootstrap3'
        });
    });
    
    /*新增按钮*/
	$("#btn_create_user").click(function(){
		retFrom();
		button_submit = "addMusic";
		$("#myModal").modal('show');
	});
	
	//提交按钮
	$("#sub_data").click(function(){
		if(!validator.checkAll($("#form_user"))){
			return false;
		}
//		console.log($("#form_user").serialize());
		var form = new FormData(document.getElementById("form_user"));
//		console.log(form);
		$.ajax({
			url:basePath+"/bk/l/music/addOrEditMusic",
			data:form,
			type:"post",
			async:true,
			processData:false,
            contentType:false,
			success:function(data){
				if(data=="200"){
					$("#myModal").modal('hide');
					oTable.Init();
					new PNotify({
				        title: '提示消息',
				        text: '操作成功',
				        type: 'success',
				        styling: 'bootstrap3'
				    });	
				}else if(data == "301"){
					new PNotify({
				        title: '提示消息',
				        text: '账号不能为空',
				        type: 'error',
				        styling: 'bootstrap3'
				    });	
				}else if(data == "302"){
					new PNotify({
				        title: '提示消息',
				        text: '密码不能为空',
				        type: 'error',
				        styling: 'bootstrap3'
				    });	
				}else if(data == "401"){
					new PNotify({
				        title: '提示消息',
				        text: '账号已存在',
				        type: 'error',
				        styling: 'bootstrap3'
				    });	
				}else if(data == "501"){
					new PNotify({
				        title: '提示消息',
				        text: '操作失败',
				        type: 'error',
				        styling: 'bootstrap3'
				    });	
				}
			}
		});
	});
	
	/*时间控件*/
	$("#datetimeStart").datetimepicker({
	        format: 'yyyy-mm-dd',//显示格式
	        minView:'month',//设置只显示到月份
	        language: 'zh-CN',//显示中文
	        autoclose:true,	//当选择一个日期之后是否立即关闭此日期时间选择器。
	        todayBtn: true,//显示今日按钮
	        clearBtn:true// 自定义属性,true 显示 清空按钮 false 隐藏 默认:true
	        //startDate:new Date() //开始时间范围
    }).on("click",function(){
        $("#datetimeStart").datetimepicker("setEndDate",$("#datetimeEnd").val());
    });
    $("#datetimeEnd").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        todayBtn: true,//显示今日按钮
        clearBtn:true// 自定义属性,true 显示 清空按钮 false 隐藏 默认:true
    }).on("click",function(){
        $("#datetimeEnd").datetimepicker("setStartDate",$("#datetimeStart").val());
    });
    
	
	/*文件上传按钮*/
	$("#imgile").change(function(){
		$("#musicImgText").val($(this).val())
	});
	$("#musicfile").change(function(){
		$("#musicfileText").val($(this).val())
	});
	
	/*弹出框确定按钮*/
	$("#sub_dialog").click(function(){
		var state = $(this).data("type");
		var id = $(this).data("id");
		$(this).data("type","");
		$(this).data("id","");
		console.log(state)
		/*下架*/
		if(state==1){
			updateState(id,state);
			$("#my_dialog").modal("hide");
		}
	});
});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
    	$("#tb_departments").bootstrapTable('destroy');//清空老数据
        $('#tb_departments').bootstrapTable({
            url: basePath+'/bk/l/music/musicList',  //请求后台的URL（*）       
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 15,                       //每页的记录行数（*）
            pageList: [20],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
           //	height: tableHeight(),               //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [
//	            {
//	                checkbox: false
//	            },
	            {
	                field: 'id',
	                title: 'ID'
	            },{
	                field: 'musicName',
	                title: '音乐名称'
	            }, {
	                field: 'musicAuther',
	                title: '歌手'
	            },{
	                field: 'musicAlbum',
	                title: '专辑名称'
	            }, {
	                field: 'clickAmount',
	                title: '点击量'
	            }, {
	                field: 'isState',
	                title: '状态'
	            }, {
	                field: 'createTime',
	                title: '创建时间'
	            }, {
	                field: 'updateTime',
	                title: '修改时间'
	            }, {
	                field: 'use',
	                title: '操作'//,
	                //width: '230'
//	                formatter: operateFormatter
	            }, 
            ],
            onLoadSuccess:function(data){
				//行内数据格式转换                
                if(data["rows"] != ""){
                    var result = data["rows"];
                    $.each(result,function(index,content){
                       var date1 = content["createTime"];
                       var date2 = content["updateTime"];
                       if(isNotEmpty(date1)){
	                       content["createTime"] = new Date(date1).format("yyyy-MM-dd  HH:mm:ss");
                       }
                       if(isNotEmpty(date2)){
	                       content["updateTime"] = new Date(date2).format("yyyy-MM-dd  HH:mm:ss");
                       }
                       var isState = content["isState"];
                       content["isState"] = (isState==0?"发布":"未发布");
                       var isTrue = content["isEnable"];//状态
                       var s = '<div class="table-o-tooltip">';
							s += '<button type="button" class="btn btn-warning btn-sm" onclick="update('+content["id"]+')" style="margin-right:5px;">修改</button>';
							if(isTrue==1){/*启用*/
								content["isEnable"] = '<div style="text-align:center"><span class="fa fa-check"></span></div>';
								s += '<button type="button" class="btn btn-primary btn-sm" onclick="updateState('+content["id"]+',0)" style="margin-right:5px;" >停用</button>';
							}else if(isTrue==0){
								content["isEnable"] = "";
								s += '<button type="button" class="btn btn-info btn-sm"onclick="updateState('+content["id"]+',1)" style="margin-right:5px;">启用</button>';
							}
							var obj = {
									"id":content["id"],
									"account":content["account"]
							}
							if(isState==0){
								s += '<button type="button" class="btn btn-success btn-sm" style="margin-right:5px;" onclick="editMusicState('+content["id"]+',1);" >下架</button>';
							}else{
								s += '<button type="button" class="btn btn-success btn-sm" style="margin-right:5px;" onclick="editMusicState('+content["id"]+',0);" >发布</button>';
							}
							s += '<button type="button" class="btn btn-danger btn-sm" style="margin-right:5px;" onclick="deleteRole('+content["id"]+');" >删除</button>';	
							s += '</div>';
							content['use'] = s;
                    });
                    $("#tb_departments").bootstrapTable("load",data)
                }
            }
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        	pageSize:params.limit,   //页面大小
        	pageNumber:params.offset,  //页码
        	name:$("#musicName").val(),
        	startDate:$("#crstartTime").val(),
        	endDate:$("#crendTime").val()
        };
        return temp;
    };
    return oTableInit;
};
function deleteRole(obj){
	$("#my_dialog").find(".modal-body").html("<p>确定删除吗?<p>");
	$("#my_dialog").modal("show");
}
/*点击修改按钮*/
function update(id){
	retFrom();
//	button_submit = "editMusic";
	tmUtil.request({
		url:basePath+"/bk/l/music/getMusicById",
		data:{
			"id":id
		},
		success:function(data){
			$("#id").val(data.id);
			$("#name").val(data.musicName);
			$("#auther").val(data.musicAuther);
			$("#album").val(data.musicAlbum);
			$("#lyric").html(data.musicLyric);
			//$("#type").val(data.type);
			$("#musicImgText").val(data.musicImg);
			$("#musicfileText").val(data.musicSrc);
		}
	});
	$("#myModal").modal('show');
}

/*换算表格高度*/
function tableHeight() {
	var window_height = $(window).height();
	var obj_off_y = $(".fit-body").offset().top;
	var result_height = window_height - obj_off_y;
	return result_height;
}
//Modal验证销毁重构
$('#myModal').on('hidden.bs.modal', function() {
//    $("#form_user").data('bootstrapValidator').destroy();
//    $('#form_user').data('bootstrapValidator', null);
});
/*清空表单*/
function  retFrom(){
	$("#id").val("");
	$("#name").val("");
	$("#auther").val("");
	$("#album").val("");
	$("#lyric").html("");
	$("#type").val("");
	$("#musicImgText").val("")
	$("#musicfileText").val("")
	$('input[id=musicfile]').val("")
	$('input[id=imgile]').val("")
}
/*停用启用*/
function updateState(id,state){
	tmUtil.request({
    	url:basePath+"/bk/l/music/editMusicState",
    	data:{
    		"id":id,
    		"state":state
    		},
    	type:"post",
    	async:false,
    	success:function(data){
    		if(data){
    			oTable.Init();
				new PNotify({
			        title: '提示消息',
			        text: '操作成功',
			        type: 'success',
			        styling: 'bootstrap3'
			    });	
    		}else{
    			new PNotify({
			        title: '提示消息',
			        text: '操作失败',
			        type: 'waring',
			        styling: 'bootstrap3'
			    });	
    		}
    	}
    });
}
/*点击上下架按钮*/
function editMusicState(id,state){
	if(state==1){
		$("#sub_dialog").data("type",1);
		$("#sub_dialog").data("id",id);
		$("#my_dialog").find(".modal-body").html("<p>确定下架吗?<p>");
		$("#my_dialog").modal("show");
	}else{
		updateState(id,state);
	}
}


var ButtonInit = function () {
	var oInit = new Object();
	var postdata = {};
	oInit.Init = function () {
		//初始化页面上面的按钮事件
	};
	return oInit;
};


