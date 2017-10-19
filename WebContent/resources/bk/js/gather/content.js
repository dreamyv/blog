var oTable;/*表格对象*/
var button_submit = "addRole";
var lock_gather = true;/*爬虫锁*/
/*表格数据*/
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
    	$("#tb_departments").bootstrapTable('destroy');//清空老数据
        $('#tb_departments').bootstrapTable({
            url: basePath+'/bk/l/gather/contentList',  //请求后台的URL（*）       
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
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
           //	height: tableHeight(),               //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [
//	            {checkbox: false},
	            {
	                field: 'id',
	                title: 'ID'
	            }, {
	                field: 'title',
	                title: '标题'
	            }, {
	                field: 'introduction',
	                title: '描述'
	            }, {
	                field: 'isEnable',
	                title: '是否启用'
	            }, {
	                field: 'isStatic',
	                title: '是否静态化'
	            }, {
	                field: 'createTime',
	                title: '创建时间'
	            }, {
	                field: 'updateTime',
	                title: '修改时间'
	            }, {
	                field: 'use',
	                title: '操作',
	                width: '230'
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
                       //是否静态化
                       var staticLink = content["staticLink"];
                       if(isNotEmpty(staticLink)){
                    		content["isStatic"] = "<div style='text-align:center'><span class='fa fa-check'></div>";
                       }
                       var	s = '<div class="table-o-tooltip">';
							s += '<button type="button" class="btn btn-warning btn-sm" onclick="update('+content["id"]+')" style="margin-right:5px;">修改</button>';
							s += '<button type="button" class="btn btn-info btn-sm" onclick="statcHtml('+content["id"]+')" style="margin-right:5px;">静态化</button>';
							if(isNotEmpty(staticLink)){
								s += '<a href="'+basePath+'/h/l/statichtml/'+content["id"]+'" class="btn-link" target="_blank" >查看</a>';
	                        }
							s += '<a href="JavaScript:void(0)" class="btn-link" onclick="del('+content["id"]+')"  >删除</a>';
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
        	startDate:$("#crstartTime").val(),
        	endDate:$("#crendTime").val()
        };
        return temp;
    };
    return oTableInit;
};

$(function(){
	//1.初始化Table
    oTable = new TableInit();
    oTable.Init();
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
	/*点击新增按钮*/
	$("#btn_create").click(function(){
//		retForm();
//    	$("#myModal").modal('show');
		var b = "";
		$('input[name="btSelectItem"]:checked').each(function(){ 
			b += $(this).val()+","; 
		}); 
		alert(b)
	});
	/*点击爬虫按钮*/
	$("#btn_gather").click(function(){
		$("#myModal_gather").modal('show');
	});
	/*开始爬虫*/
	$("#sub_gather").click(function(){
		if(!validator.checkAll($("#form_gather"))){
			return false;
		}
		if(!lock_gather){
			return false;
		}
		lock_gather = false;/*爬虫锁*/
		$("#sub_gather").text("进行中...");
		$("#dig").show();
		tmUtil.request({
			url:basePath+"/bk/l/gather/gatherContent",
			data:$("#form_gather").serialize(),
			success:function(data){
				lock_gather = true;/*爬虫锁*/
				$("#sub_gather").text("提交");
				$("#myModal_gather").modal('hide');
				$("#dig").hide();
				oTable.Init();
				console.log(data);
				var code = data.code;
				if(code==200){
					var info = "匹配的URL条数:"+data.urlSize+",保存成功的条数:"+data.count;
					new PNotify({
						title: '提示消息',
						text: info,
						type: 'success',
						styling: 'bootstrap3'
					});	
				}else if(code == 500){
					new PNotify({
						title: '提示消息',
						text: "传递参数错误",
						type: 'waring',
						styling: 'bootstrap3'
					});	
				}
				
			}
		});
	});
	
	/*添加*/
	$("#sub_data").click(function(){
		if(!validator.checkAll($("#form_role"))){
			return false;
		}
		tmUtil.request({
			url:basePath+"/bk/l/sys/"+button_submit,
			data:$("#form_role").serialize(),
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
				}
			}
		});
	});
	
});
/*清空表单*/
function retForm(){
	$("#roleDesc").val(""); 
	$("#roleName").val("");
	$("#id").val("");
}
/*点击修改按钮*/
function update(id){
	retForm();
	button_submit = "eidtRole";
	tmUtil.request({
		url:basePath+"/bk/l/sys/getRoleById",
		data:{"id":id},
		success:function(data){
			retForm();
			$("#roleDesc").val(data.roleDesc); 
			$("#roleName").val(data.roleName);
			$("#id").val(data.id);
		}
	});
	$("#myModal").modal('show');
}

/*静态化*/
function statcHtml(id){
	var param={
		"url":basePath+"/h/l/statichtml/"+id,
		"id":id
	}
	tmUtil.request({
		url:basePath+"/bk/l/gather/staticHtml",
		data:param,
		success:function(data){
			if(data=="200"){
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
			        type: 'error',
			        styling: 'bootstrap3'
			    });	
			}
		}
	});
}

/*删除数据*/
function del(id){
	tmUtil.request({
		url:basePath+"/bk/l/gather/del",
		data:{"id":id},
		success:function(data){
			console.log(data);
			if(data.code==200){
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
			        type: 'error',
			        styling: 'bootstrap3'
			    });	
			}
		}
	});
}

