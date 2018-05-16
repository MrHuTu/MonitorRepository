	

	$('#projectTable').bootstrapTable({
	    url: '/page/queryProject',
	    queryParams: function (params) {
	        return {
	            offset: params.offset,
	            limit: params.limit
	        }
	    },
	    columns: [{
	        field: 'projectId',
	        title: '项目Id'
	    }, {
	    	field: 'projectName',
	        title: '项目名称'
		    
	    }, {
	        field: 'projectTypeName',
	        title: '项目类型名称',
	    },{
	        field: 'projectAddress',
	        title: '项目地点'
	    }, {
	        field: 'weatherAddress',
	        title: '天气地点'
	    }, {
	        field: 'projectLongitude',
	        title: '经度'
	    }, {
	        field: 'projectLatitude',
	        title: '纬度'
	    }, {
	        field: 'projectBeginTime',
	        title: '开始时间'
	    }, {
	        field: 'projectEndTime',
	        title: '结束时间'
	    }, {
	        field: 'projectStatusName',
	        title: '项目状态'
	    }, {
	        formatter: function (value, row, index) {
	            return [
	                /*'<a href="javascript:modifyPer(' + row.projectId + ",'" + row.projectName + "'," + row.projectTypeName + ",'" + row.projectStatusName + "'" + ')">' +
	                    '<i class="glyphicon glyphicon-pencil"></i>修改' +
	                '</a>',*/
	                '<a href="javascript:delPer(' + row.projectId + ')">' +
	                    '<i class="glyphicon glyphicon-remove"></i>删除' +
	                '</a>'
	            ].join('');
	        },
	        title: '操作'
	    }],
	    striped: true,
	    pagination: true,
	    sidePagination: 'server',
	    pageSize: 10,
	    pageList: [5, 10, 25, 50, 100],
	    
	    toolbar: '#toolbar',                //工具按钮用哪个容器
	    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    sortable: false,                     //是否启用排序
	    sortOrder: "asc",                   //排序方式
	    pageNumber: 1,                       //初始化加载第一页，默认第一页
	    search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    contentType: "application/x-www-form-urlencoded",
	    strictSearch: true,
	    showColumns: true,                  //是否显示所有的列
	    showRefresh: true,                  //是否显示刷新按钮
	    minimumCountColumns: 2,             //最少允许的列数
	    clickToSelect: true,                //是否启用点击选中行
	    height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	    uniqueId: "no",                     //每一行的唯一标识，一般为主键列
	    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
	    cardView: false,                    //是否显示详细视图
	    detailView: false, 
	    
	    rowStyle: function (row, index) {
	        var ageClass = '';
	        if (row.age < 18) {
	            ageClass = 'text-danger';
	        }
	        return {classes: ageClass}
	    },
	});
	
		//删除函数
		function delPer(projectId){
			$.ajax({
				  url:'/manage/deleteProjects',
				  dataType:'json',
				  contentType : "application/json;charset=utf-8",
				  data : 'projectId='+projectId,
				  success : function(data){
					  if(data){
						  $('#projectTable').bootstrapTable('refresh',{url:'/page/queryProject'});
					  }else{
						  alert("删除失败呀！")
					  }
				  },
				  error : function(){
					  alert("删除失败！")
				  }
			})
		}
	
		//表格监听
	 	$('#projectTable').on('click-row.bs.table', function (e, row, element,field)   
	    {  
	        if(field!=10){
	        	$("#projetModels").modal('show');
	        }
	          
	    }); 
		
