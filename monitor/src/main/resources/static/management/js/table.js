	
	
	//项目表
	$('#projectTable').bootstrapTable({
	    url: '/page/queryProject',
	    queryParams: function (params) {
	        return {
	            offset: params.offset,
	            limit: params.limit,
	            condition:params.search
	        }
	    },
	    columns: [{
	        field: 'projectId',
	        title: '项目Id', 
	    }, {
	    	field: 'projectName',
	        title: '项目名称',
	        editable: {
                type: 'text',
                title: '项目名称',
                placement: 'right',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            },
	    }, {
	        field: 'projectType',
	        title: '项目类型名称',
	        editable: {
                type: 'select',
                title: '项目类型名称',
                source:function(){
                	var result = [];
                	$(projectType).each(function(index,value){
                		result.push({ value: value.scId, text: value.itemName });
                	});
                	return result;
                },
            },
	    },{
	        field: 'projectAddress',
	        title: '项目地点',
	        editable: {
                type: 'text',
                title: '项目地点',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'weatherAddress',
	        title: '天气地点',
	        editable: {
                type: 'text',
                title: '天气地点',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'projectLongitude',
	        title: '经度',
	        editable: {
                type: 'text',
                title: '经度',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'projectLatitude',
	        title: '纬度',
	        editable: {
                type: 'text',
                title: '纬度',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'projectBeginTime',
	        title: '开始时间',
            editable: {
                type: 'datetime',
                placement: 'left',
                format: 'yyyy-mm-dd hh:ii:ss',
                viewformat:'yyyy-mm-dd hh:ii:ss',
                title: '选择开始时间',
                datetimepicker: {
                    weekStart: 1,
                    language: 'zh-CN'
               }
            }
	    }, {
	        field: 'projectEndTime',
	        title: '结束时间',
	        editable: {
                type: 'datetime',
                placement: 'left',
                format: 'yyyy-mm-dd hh:ii:ss',
                viewformat:'yyyy-mm-dd hh:ii:ss',
                title: '选择结束时间',
                datetimepicker: {
                    weekStart: 1,
                    language: 'zh-CN'
               }
            }
	    }, {
	        field: 'projectStatus',
	        title: '项目状态',
	        editable: {
                type: 'select',
                title: '项目状态',
                source:function(){
                	var result = [];
                	$(projectStatus).each(function(index,value){
                		result.push({ value: value.scId, text: value.itemName });
                	});
                	return result;
                },
            }
	    }, {
	        formatter: function (value, row, index) {
	            return [
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
	    pageSize: 5,
	    pageList: [5, 10, 25, 50, 100],
	    clickToSelect: true,
	    toolbar: '#enableproject',                //工具按钮用哪个容器
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
	    uniqueId: "no",                     //每一行的唯一标识，一般为主键列
	    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
	    cardView: false,                    //是否显示详细视图
	    detailView: false, 
	    onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                type: "post",
                url: "/manage/updateProject",
                data: row,
                dataType: 'JSON',
                success: function (data) {
                    if(data){
                    	swal({
    						title: "修改成功",
    			            text: "小手一抖就成功了",
    			            type: "success",
    	                    confirmButtonColor: "#A7D5EA",
    	                    confirmButtonText: "OK",
    	                });
                    }else{
                    	swal({
    	                    title: "操作失败",
    	                    text: "添加失败，请尝试重新操作",
    	                    type: "error"
    	               });
                    }
                },
                error: function () {
                },
                complete: function () {

                }

            });
        }
	});
	
		
	var projectId;
	var monitorType;
	var searchCondition;
	//传感器信息表
	function sensortables(){
		$('#sensorTable').bootstrapTable({
		    url: '/page/querySensor',
		    queryParams: function (params) {
		        return {
		            offset: params.offset,
		            limit: params.limit,
		            projectId:projectId,
		            monitorType:monitorType,
		            condition:params.search
		        }
		    },
		    columns: [{
		        field: 'sensorId',
		        title: '传感器表ID',
		        visible:false,
		    }, {
		    	field: 'monitorPoint',
		        title: '测点名称',
		        	editable: {
		                type: 'text',
		                title: '用户名称',
		                validate: function (v) {
		                    if (!v) return '用户名不能为空';

		                }
		            }
		    }, {
		        field: 'monitorTypeName',
		        title: '检测指标',
		    },{
		        field: 'smuNumber',
		        title: '采集器编号',
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        field: 'smuChannel',
		        title: '采集器通道',
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        field: 'sensorNumber',
		        title: '传感器ID',
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        field: 'sensorType',
		        title: '传感器类型',
		        visible:false,
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        field: 'sensorModel',
		        title: '传感器型号',
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        field: 'sensorLongitude',
		        title: '经度',
		        visible:false,
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        field: 'sensorLatitude',
		        title: '纬度',
		        visible:false,
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        field: 'sensorPlace',
		        title: '传感器地点',
		        visible:false,
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        field: 'sensorDepth',
		        title: '传感器深度',
		        visible:false,
		        editable: {
	                type: 'text',
	                title: '用户名称',
	                validate: function (v) {
	                    if (!v) return '用户名不能为空';

	                }
	            }
		    }, {
		        formatter: function (value, row, index) {
		            return [
		                '<a href="javascript:delsensor(' + row.sensorId+','+row.monitorType+','+row.projectId + ')">' +
		                    '<i class="glyphicon glyphicon-remove"></i>删除' +
		                '</a>'
		            ].join('');
		        },
		        title: '操作'
		    }],
		    striped: true,
		    pagination: true,
		    sidePagination: 'server',
		    pageSize: 5,
		    pageList: [5, 10, 25, 50, 100],
		    
		    toolbar: '#divmonitorType',                //工具按钮用哪个容器
		    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		    sortable: false,                     //是否启用排序
		    sortOrder: "asc",                   //排序方式
		    pageNumber: 1,                       //初始化加载第一页，默认第一页
		    search: true,                     //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		    contentType: "application/x-www-form-urlencoded",
		    strictSearch: true,
		    showColumns: true,                  //是否显示所有的列
		    showRefresh: true,                  //是否显示刷新按钮
		    minimumCountColumns: 2,             //最少允许的列数
		    clickToSelect: true,                //是否启用点击选中行
		    uniqueId: "no",                     //每一行的唯一标识，一般为主键列
		    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
		    cardView: false,                    //是否显示详细视图
		    detailView: false,
		    onEditableSave: function (field, row, oldValue, $el) {
	            $.ajax({
	                type: "post",
	                url: "/manage/updateSensor",
	                data: row,
	                dataType: 'JSON',
	                success: function (data) {
	                    if(data){
	                    	swal({
	    						title: "修改成功",
	    			            text: "小手一抖就成功了",
	    			            type: "success",
	    	                    confirmButtonColor: "#A7D5EA",
	    	                    confirmButtonText: "OK",
	    	                });
	                    }else{
	                    	swal({
	    	                    title: "操作失败",
	    	                    text: "添加失败，请尝试重新操作",
	    	                    type: "error"
	    	               });
	                    }
	                },
	                error: function () {
	                },
	                complete: function () {

	                }

	            });
	        }
		});
	}
	
	//用户表
	$('#userTable').bootstrapTable({
	    url: '/page/queryUser',
	    queryParams: function (params) {
	        return {
	            offset: params.offset,
	            limit: params.limit,
	            condition:params.search
	        }
	    },
	    columns: [{
	        field: 'userId',
	        title: '传感器表ID',
	        visible:false,
	    }, {
	    	field: 'userName',
	        title: '用户名称',
	        editable: {
                type: 'text',
                title: '用户名称',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'password',
	        title: '密码',
	        visible:false,
	        editable: {
                type: 'text',
                title: '密码',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    },{
	        field: 'phone',
	        title: '电话',
	        editable: {
                type: 'text',
                title: '电话',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'email',
	        title: '邮箱',
	        editable: {
                type: 'text',
                title: '邮箱',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'company',
	        title: '所属公司',
	        editable: {
                type: 'text',
                title: '所属公司',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'realName',
	        title: '负责人',
	        editable: {
                type: 'text',
                title: '负责人',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        field: 'createTime',
	        title: '创建时间',
	        editable: {
                type: 'datetime',
                placement: 'left',
                format: 'yyyy-mm-dd hh:ii:ss',
                viewformat:'yyyy-mm-dd hh:ii:ss',
                title: '选择创建时间',
                datetimepicker: {
                    weekStart: 1,
                    language: 'zh-CN'
               }
            }
	    }, {
	        field: 'status',
	        title: '用户状态',
	        editable: {
                type: 'text',
                title: '用户状态',
                validate: function (v) {
                    if (!v) return '用户名不能为空';

                }
            }
	    }, {
	        formatter: function (value, row, index) {
	            return [
	                '<a href="javascript:deluser(' + row.userId + ')">' +
	                    '<i class="glyphicon glyphicon-remove"></i>删除' +
	                '</a>'
	            ].join('');
	        },
	        title: '操作'
	    }],
	    striped: true,
	    pagination: true,
	    sidePagination: 'server',
	    pageSize: 5,
	    pageList: [5, 10, 25, 50, 100],
	    
	    toolbar: '#enableuser',                //工具按钮用哪个容器
	    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    sortable: false,                     //是否启用排序
	    sortOrder: "asc",                   //排序方式
	    pageNumber: 1,                       //初始化加载第一页，默认第一页
	    search: true,                     //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    contentType: "application/x-www-form-urlencoded",
	    strictSearch: true,
	    showColumns: true,                  //是否显示所有的列
	    showRefresh: true,                  //是否显示刷新按钮
	    minimumCountColumns: 2,             //最少允许的列数
	    clickToSelect: true,                //是否启用点击选中行
	    uniqueId: "no",                     //每一行的唯一标识，一般为主键列
	    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
	    cardView: false,                    //是否显示详细视图
	    detailView: false, 
	    onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                type: "post",
                url: "/manage/updateUser",
                data: row,
                dataType: 'JSON',
                success: function (data) {
                    if(data){
                    	swal({
    						title: "修改成功",
    			            text: "小手一抖就成功了",
    			            type: "success",
    	                    confirmButtonColor: "#A7D5EA",
    	                    confirmButtonText: "OK",
    	                });
                    }else{
                    	swal({
    	                    title: "操作失败",
    	                    text: "添加失败，请尝试重新操作",
    	                    type: "error"
    	               });
                    }
                },
                error: function () {
                },
                complete: function () {

                }

            });
        }
	});
	
	
	
	var s_tablename; 
		//查询传感器数据
	$('#querySensorDataTable').bootstrapTable({
	    url: '/page/querySensorData',
	    queryParams: function (params) {
	    	var SensorDatass={
					'sensorNumber':$("#s_sensorNumber").val(),
					'smuNumber':$("#s_smuNumber").val(),
					'smuChannel':$("#s_smuChannel").val(),
					'beginTimes':$("#beginTimes").val(),
					'endTimes':$("#endTimes").val()
					};
			var SensorDatas =JSON.stringify(SensorDatass);
	        return {
	        	sensorData:SensorDatas,
	            offset: params.offset,
	            limit: params.limit,
	            tableName:s_tablename,
	        }
	    },
	    columns: [{
	        field: 'id',
	        title: '传感器表ID',
	        visible:false,
	    }, {
	    	field: 'sensorNumber',
	        title: '传感器编号',
	    }, {
	        field: 'firstTime',
	        title: '第一次传数据时间',
	        visible:false,
	    },{
	        field: 'firstData',
	        title: '第一次传入数据',
	        visible:false,
	    }, {
	        field: 'previousTime',
	        title: '上一次传入数据时间',
	        visible:false,
	    }, {
	        field: 'previousData',
	        title: '上一次传入数据',
	        visible:false,
	    }, {
	        field: 'currentTimes',
	        title: '当前传入数据时间',
	    }, {
	        field: 'currentData',
	        title: '当前传入数据',
	    }, {
	        field: 'currentTemperature',
	        title: '当前传入温度',
	        visible:false,
	    },{
	        field: 'currentLaserChange',
	        title: '单次变化量',
	    },
	    {
	        field: 'totalLaserChange',
	        title: '累计变化量',
	    },{
	        field: 'speedChange',
	        title: '变化速率',
	    },{
	        field: 'sensorStatus',
	        title: '传感器状态',
	        visible:false,
	    },{
	        field: 'createType',
	        title: '自动 手动',
	        visible:false,
	    },{
	        field: 'smuNumber',
	        title: '采集器的编号',
	    },{
	        field: 'smuChannel',
	        title: '采集器的通道',
	    },{
	        field: 'smuStatus',
	        title: '采集器状态',
	        visible:false,
	    }],
	    striped: true,
	    pagination: true,
	    sidePagination: 'server',
	    pageSize: 10,
	    pageList: [ 10, 25, 50, 100],
	    searchOnEnterKey:true,              //按回车触发搜索框
	    toolbar: '#divsensorDataquery',                //工具按钮用哪个容器
	    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    sortable: false,                     //是否启用排序
	    sortOrder: "asc",                   //排序方式
	    pageNumber: 1,                       //初始化加载第一页，默认第一页
	    search: true,                     //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    contentType: "application/x-www-form-urlencoded",
	    strictSearch: true,
	    showColumns: true,                  //是否显示所有的列
	    showRefresh: true,                  //是否显示刷新按钮
	    minimumCountColumns: 2,             //最少允许的列数
	    clickToSelect: true,                //是否启用点击选中行
	    uniqueId: "no",                     //每一行的唯一标识，一般为主键列
	    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
	    cardView: false,                    //是否显示详细视图
	    detailView: false, 
	});
	
		//删除函数
		function delPer(projectId){
			swal({
		        title: "您确定要删除这条信息吗",
		        text: "删除后将无法恢复，请谨慎操作！",
		        type: "warning",
		        showCancelButton: true,
		        confirmButtonColor: "#DD6B55",
		        confirmButtonText: "是的，我要删除！",
		        cancelButtonText: "取消",
		        closeOnConfirm: false,
		        closeOnCancel: false
		    },function (isConfirm) {
		        if (isConfirm) {
		        	$.ajax({
		        		  url:'/manage/deleteProjects',
						  dataType:'json',
						  contentType : "application/json;charset=utf-8",
						  data : 'projectId='+projectId,
						  success : function(data){
							  if(data){
								  swal("删除成功！", "您已经永久删除了这条信息。", "success");
								  $('#projectTable').bootstrapTable('refresh',{url:'/page/queryProject'});
							  }else{
								  swal("删除失败", "请您重新尝试。", "error");
							  }
						  },
						  error : function(){
							  swal("删除失败", "请您重新尝试。", "error");
						  }
					})
		        } else {
		            swal("已取消", "您取消了删除操作！", "error");
		        }
		    });
		}
	
		//传感器表删除函数
		function delsensor(sensorId,monitorType,projectId){
			swal({
		        title: "您确定要删除这条信息吗",
		        text: "删除后将无法恢复，请谨慎操作！",
		        type: "warning",
		        showCancelButton: true,
		        confirmButtonColor: "#DD6B55",
		        confirmButtonText: "是的，我要删除！",
		        cancelButtonText: "取消",
		        closeOnConfirm: false,
		        closeOnCancel: false
		    },function (isConfirm) {
		        if (isConfirm) {
		        	$.ajax({
						  url:'/manage/deleteSensor',
						  dataType:'json',
						  contentType : "application/json;charset=utf-8",
						  data : {'sensorId':sensorId,'monitorType':monitorType,'projectId':projectId},
						  success : function(data){
							  if(data){
								  swal("删除成功！", "您已经永久删除了这条信息。", "success");
								  $('#sensorTable').bootstrapTable('refresh',{url:'/page/querySensor'});
							  }else{
								  swal("删除失败", "请您重新尝试。", "error");
							  }
						  },
						  error : function(){
							  swal("删除失败", "请您重新尝试。", "error");
						  }
					})
		        } else {
		            swal("已取消", "您取消了删除操作！", "error");
		        }
		    });
			
		}
		
		//用户删除
		function deluser(userId){
			swal({
		        title: "您确定要删除这条信息吗",
		        text: "删除后将无法恢复，请谨慎操作！",
		        type: "warning",
		        showCancelButton: true,
		        confirmButtonColor: "#DD6B55",
		        confirmButtonText: "是的，我要删除！",
		        cancelButtonText: "取消",
		        closeOnConfirm: false,
		        closeOnCancel: false
		    },function (isConfirm) {
		        if (isConfirm) {
		        	$.ajax({
		        		  url:'/manage/deleteUsers',
						  dataType:'json',
						  contentType : "application/json;charset=utf-8",
						  data : {'userId':userId},
						  success : function(data){
							  if(data){
								  swal("删除成功！", "您已经永久删除了这条信息。", "success");
								  $('#userTable').bootstrapTable('refresh',{url:'/page/queryUser'});
							  }else{
								  swal("删除失败", "请您重新尝试。", "error");
							  }
						  },
						  error : function(){
							  swal("删除失败", "请您重新尝试。", "error");
						  }
					})
		        } else {
		            swal("已取消", "您取消了删除操作！", "error");
		        }
		    });
			
		}
		
		//表格监听
		var isopen=true;
		function projectTableclickrow(){
			$('#projectTable').on('click-row.bs.table', function (e, row, $element,field)   
					{  
					if(field!=10){
						$("#projetModels").modal('show');
						$("[name='projsenName']").text(row.projectName);
						projectId=row.projectId;
						$("#sensorProId").attr('value',projectId);
						$.ajax({
							url:'/page/queryMoniType',
							dataType:'json',
							data : 'projectId='+projectId,
							success : function(data){
								if(data.length>0){
									$("#divmonitorType").css("display", "block");
									$("#selectmonitorType").children().filter("option").remove();
									$("#selectmonitorType").append('<option value="0">所有</option>');
									$(data).each(function(index,value){
										var label='<option value="'+value.monitorType+'">'+value.monitorTypeName+'</option>' ;
										$("#selectmonitorType").append(label);
									});
									monitorType=null;
									$('#sensorTable').bootstrapTable('refresh',{url:'/page/querySensor'});
									if(isopen){
										sensortables();
									}
									isopen=false;
								}else{
									$("#ps_info").text("当前项目没有绑定传感器！");
									$('#sensorTable').bootstrapTable('removeAll');
								}
							},
							error : function(){
								alert("加载失败");
							}
						});
						
					}
			}); 
			
		}
		projectTableclickrow();
		
		
	 	//检测指标事件
	 	 $("#selectmonitorType").change(function(){
	 		monitorType=$(this).val();
	 		if(monitorType==0){
	 			monitorType=null;
	 		}
	 		$('#sensorTable').bootstrapTable('refresh',{url:'/page/querySensor'});
	 	 });
	 	 
	 	 //查询条件变化事件
	 	 $("#searchCondition").change(function(){
	 		searchCondition=$(this).val();
	 	 });
	 	 
	 	 
		//项目表格编辑
	 	function enableProject(){
	 		$('#projectTable .editable').editable('toggleDisabled');
	 		if($("#enableproject").text()=="编辑"){
	 			$('#projectTable').off('click-row.bs.table');
	 			proSwitch=false;
	 			$("#enableproject").text("完成");
	 		}else{
	 			projectTableclickrow();
	 			$("#enableproject").text('编辑');
	 		}
	 	}
	 	
	 	$('#projectTable').on('load-success.bs.table', function (params){
	 		if($("#enableproject").text()=="编辑"){
	 			$('#projectTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	
	 	$('#projectTable').on('column-switch.bs.table', function (params){
	 		if($("#enableproject").text()=="编辑"){
	 			$('#projectTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	
	 	$('#projectTable').on('toggle.bs.table', function (params){
	 		if($("#enableproject").text()=="编辑"){
	 			$('#projectTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	
	 	//用户表格编辑
	 	function enableuser(){
	 		$('#userTable .editable').editable('toggleDisabled');
	 		if($("#enableuser").text()=="编辑"){
	 			$("#enableuser").text("完成");
	 		}else{
	 			$("#enableuser").text('编辑');
	 		}
	 	}
	 	
	 	
	 	$('#userTable').on('load-success.bs.table', function (params){
	 		if($("#enableuser").text()=="编辑"){
	 			$('#userTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	$('#userTable').on('column-switch.bs.table', function (params){
	 		if($("#enableuser").text()=="编辑"){
	 			$('#userTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	$('#userTable').on('toggle.bs.table', function (params){
	 		if($("#enableuser").text()=="编辑"){
	 			$('#userTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	
	 	//传感器表格编辑
	 	function enableSensors(){
	 		$('#sensorTable .editable').editable('toggleDisabled');
	 		if($("#enableSensor").text()=="编辑"){
	 			$("#enableSensor").text("完成");
	 		}else{
	 			$("#enableSensor").text('编辑');
	 		}
	 	}
	 	
	 	
	 	$('#sensorTable').on('load-success.bs.table', function (params){
	 		if($("#enableSensor").text()=="编辑"){
	 			$('#sensorTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	$('#sensorTable').on('column-switch.bs.table', function (params){
	 		if($("#enableSensor").text()=="编辑"){
	 			$('#sensorTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	$('#sensorTable').on('toggle.bs.table', function (params){
	 		if($("#enableSensor").text()=="编辑"){
	 			$('#sensorTable .editable').editable('toggleDisabled');
	 		}
	 	});
	 	
	 	//查询传感器数据
		function querySensotData(){
			
			$(senMT).each(function(index,value){
				  if($("#s_tableName").val()==value.scId){
					  s_tablename=value.tableName;
				  }
			});
			if(s_tablename==null){
				alert("此检测指标无相关表，请联系相关人员建立表！")
			}else{
				$('#querySensorDataTable').bootstrapTable('refresh',{url:'/page/querySensorData'});
			}
		}
		
		$('#beginTimes').datetimepicker({
		    //从今天开始
		    format: 'yyyy-mm-dd hh:ii:ss',
		    language:  'zh-CN',
		    todayBtn:  1,
		    autoclose: 1,
		    todayHighlight: 1,
		    startView: 2,
		    forceParse: 0,
		    showMeridian: 1,
		    minuteStep : 1,
		});
		$('#endTimes').datetimepicker({
		    //到今天结束
		    format: 'yyyy-mm-dd hh:ii:ss',
		    language:  'zh-CN',
		    todayBtn:  1,
		    autoclose: 1,
		    todayHighlight: 1,
		    startView: 2,
		    forceParse: 0,
		    showMeridian: 1,
		    minuteStep : 1,
		});
		
		$('#up_beginTimes').datetimepicker({
		    //从今天开始
		    format: 'yyyy-mm-dd hh:ii:ss',
		    language:  'zh-CN',
		    todayBtn:  1,
		    autoclose: 1,
		    todayHighlight: 1,
		    startView: 2,
		    forceParse: 0,
		    showMeridian: 1,
		    minuteStep : 1,
		});
		$('#up_endTimes').datetimepicker({
		    //到今天结束
		    format: 'yyyy-mm-dd hh:ii:ss',
		    language:  'zh-CN',
		    todayBtn:  1,
		    autoclose: 1,
		    todayHighlight: 1,
		    startView: 2,
		    forceParse: 0,
		    showMeridian: 1,
		    minuteStep : 1,
		});
		
		$('#beginTimes').on('changeDate', function(ev){
		     $('#endTimes').datetimepicker('setStartDate', $("#beginTimes").val());
		});
		
		$('#up_beginTimes').on('changeDate', function(ev){
	        $('#up_endTimes').datetimepicker('setStartDate', $("#up_beginTimes").val());
	        updateVali(tableName);
		});
		$('#up_endTimes').on('changeDate', function(ev){
			$('#up_beginTimes').datetimepicker('setEndDate', $("#up_endTimes").val());
			updateVali(tableName);
		});
		$('#up_sensorNumber').on('change',function(){
			updateVali(tableName);
		});
		
		//修改数据界面检测指标改变事件
		var once=true;
		var tableName;
		$('#up_tableName').on('change',function(){
			if(once){
				$("#one").remove();
				once=false;
			}
        	$(senMT).each(function(index,value){
				  if($("#up_tableName").val()==value.scId){
					  tableName=value.tableName;
				  }
			});
        	if(tableName==null){
        		$("#up_mo_error").css("display", "block");
        		$("#up_sensorNumber").val("");
        		$("#up_sensorNumber").prop('disabled',true);
        		$("#up_smuNumber").empty();
        		updateVali(tableName);
			}else{
				$("#up_mo_error").css("display", "none");
				updateVali(tableName);
				appendSmunumber(tableName);
			}
		});
		
		
		function appendSmunumber(tableName){
			$("#up_smuNumber").empty();
			$.ajax({
				  url:'/page/querySmuId',
				  dataType:'json',
				  contentType : "application/json;charset=utf-8",
				  data : 'tableName='+tableName,
				  success : function(data){
					  if(data.length>0){
						  var table;
						  $(data).each(function(index,value){
							  table="<option>"+value.smuNumber+"</option>"
							  $("#up_smuNumber").append(table);
						  })
						  $("#up_sensorNumber").removeAttr('disabled');
					  }
				  },
				  error : function(){
					  alert("终端加载失败！")
				  }
			})
		}
		
		//修改第一次数据校验
		function updateVali(tableName){
			if( tableName==null || $( '#up_sensorNumber').val()=="" || $('#up_smuNumber').val()=="" || $('#up_beginTimes').val()=="" || $('#up_endTimes').val()==""){
				$("#updata_").prop('disabled',true);
				return false;
			}else{
				$("#updata_").prop('disabled',false);
				return true;
			}
		}
		
		//修改第一次数据
		function up_firstdata(){
				swal({
			        title: "您确定要修改吗",
			        text: "请确认修改信息是否有误，请谨慎操作！",
			        type: "warning",
			        showCancelButton: true,
			        confirmButtonColor: "#DD6B55",
			        confirmButtonText: "是的，我要修改！",
			        cancelButtonText: "取消",
			        closeOnConfirm: false,
			        closeOnCancel: false
			    },function (isConfirm) {
			        if (isConfirm) {
			        	$.ajax({
							  url:'/manage/updateFirstData',
							  dataType:'json',
							  contentType : "application/json;charset=utf-8",
							  data : {'tableName':tableName,'sensorNumber':$('#up_sensorNumber').val(),'smuNumber':$('#up_smuNumber').val(),'beginTimes':$('#up_beginTimes').val(),'endTimes':$('#up_endTimes').val()},
							  success : function(data){
								  if(data){
									  swal("修改成功！", "您已经成功修改第一次数据。", "success");
								  }else{
									  swal("修改失败", "请您重新尝试。", "error");
								  }
							  },
							  error : function(){
								  swal("修改失败", "请您重新尝试。", "error");
							  }
						})
			        } else {
			            swal("已取消", "您取消了修改操作！", "error");
			        }
			    });
		}
		//用户和项目--by kx
		//定义项目id
		var proId;
		//初始化双向关系项目下拉框
		$.ajax({
			url:'/page/queryProjectNames',
			  dataType:'json',
			  success : function(datas){
				  for(var data in datas){
					  $('#enableProjectAndUser').append(
							 "<option value='"+datas[data].projectId+"'>"+datas[data].projectName+"</option>"
					  )
				  };
				  proId = $("#enableProjectAndUser").find("option:selected").attr("value");
				  refreshInProject();
			  }
		})
		
		//项目下拉绑定查询事件
		 $('#enableProjectAndUser').change(function(){
//			 alert($("#enableProjectAndUser").find("option:selected").attr("value"));
			 proId = $("#enableProjectAndUser").find("option:selected").attr("value");
			 refreshInProject();
		 });
		
		//初始化项目用户表
		$('#projectAndUserTable').bootstrapTable({
			url: '/page/queryUsersByProject',
		    queryParams: function (params) {
		        var param =  {
		            offset: params.offset,
		            limit: params.limit,
		            condition:params.search,
		            projectId:proId,
		        };
		        return param;
		    },
		    columns: [{
		        field: 'userId',
		        title: '用户Id', 
		    }, {
		    	field: 'userName',
		        title: '用户名称',
		       
		    },  {
		        field: 'realName',
		        title: '真实姓名',
		        
		    },{
		        field: 'status',
		        title: '用户状态',
	            
		    }, {
		        formatter: function (value, row, index) {
		            return [
		                '<a href="javascript:delPerInProject(' + row.userId + ')">' +
		                    '<i class="glyphicon glyphicon-remove"></i>移出当前项目' +
		                '</a>'
		            ].join('');
		        },
		        title: '操作'
		    }],
		    striped: true,
		    pagination: true,
		    sidePagination: 'server',
		    pageSize: 5,
		    pageList: [5, 10, 25, 50, 100],
		    clickToSelect: true,
		    toolbar: '#enableProjectAndUserTool',                //工具按钮用哪个容器
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
		    uniqueId: "no",                     //每一行的唯一标识，一般为主键列
		    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
		    cardView: false,                    //是否显示详细视图
		    detailView: false, 
		});
		
		//刷新项目和用户双向中的项目
		function refreshInProject(){
			$('#projectAndUserTable').bootstrapTable("refresh",'/page/queryUsersByProject');
			$('#projectAndUserTableForAdd').bootstrapTable("refresh",'/page/queryNoUsersByProject');
		};
		//移除项目下已有用户
		function delPerInProject(userId){
			swal({
		        title: "您确定要移除该用户吗",
		        text: "移除后如需添加，请通过下方添加操作！",
		        type: "warning",
		        showCancelButton: true,
		        confirmButtonColor: "#DD6B55",
		        confirmButtonText: "是的，我要移除！",
		        cancelButtonText: "取消",
		        closeOnConfirm: false,
		        closeOnCancel: false
		    },function (isConfirm) {
		        if (isConfirm) {
		        	$.ajax({
		        		  url:'/manage/delPerInProject',
						  dataType:'json',
						  contentType : "application/json;charset=utf-8",
						  data : 'userId='+userId+'&projectId='+proId,
						  success : function(data){
							  if(data){
								  swal("移除成功！", "success");
								  refreshInProject();
							  }else{
								  swal("移除失败", "请您重新尝试。", "error");
							  }
						  },
						  error : function(){
							  swal("移除失败", "请您重新尝试。", "error");
						  }
					})
		        } else {
		            swal("已取消", "您取消了移除操作！", "error");
		        }
		    });
		}
		
		//初始化添加项目用户表
		$('#projectAndUserTableForAdd').bootstrapTable({
			url: '/page/queryNoUsersByProject',
		    queryParams: function (params) {
		        var param =  {
		            offset: params.offset,
		            limit: params.limit,
		            condition:params.search,
		            projectId:proId,
		        };
		        return param;
		    },
		    columns: [{
		        field: 'userId',
		        title: '用户Id', 
		    }, {
		    	field: 'userName',
		        title: '用户名称',
		       
		    }, {
		        field: 'realName',
		        title: '真实姓名',
		        
		    }, {
		        field: 'status',
		        title: '用户状态',
	            
		    }, {
		        formatter: function (value, row, index) {
		            return [
		                '<a href="javascript:addUserInProject(' + row.userId + ')">' +
		                    '<i class="glyphicon glyphicon-check"></i>添入当前项目' +
		                '</a>'
		            ].join('');
		        },
		        title: '操作'
		    }],
		    striped: true,
		    pagination: true,
		    sidePagination: 'server',
		    pageSize: 5,
		    pageList: [5, 10, 25, 50, 100],
		    clickToSelect: true,
		    toolbar: '#enableUserAndProjectTool',                //工具按钮用哪个容器
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
		    uniqueId: "no",                     //每一行的唯一标识，一般为主键列
		    showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
		    cardView: false,                    //是否显示详细视图
		    detailView: false, 
		});
		
		//添加用户到当前项目
		function addUserInProject(userId){
			swal({
		        title: "您确定要添加该用户吗",
		        type: "warning",
		        showCancelButton: true,
		        confirmButtonColor: "#DD6B55",
		        confirmButtonText: "是的，我要添加！",
		        cancelButtonText: "取消",
		        closeOnConfirm: false,
		        closeOnCancel: false
		    },function (isConfirm) {
		        if (isConfirm) {
		        	$.ajax({
		        		  url:'/manage/addUserInProject',
						  dataType:'json',
						  contentType : "application/json;charset=utf-8",
						  data : 'userId='+userId+'&projectId='+proId,
						  success : function(data){
							  if(data){
								  swal("添加成功！", "success");
								  refreshInProject();
							  }else{
								  swal("添加失败", "请您重新尝试。", "error");
							  }
						  },
						  error : function(){
							  swal("添加失败", "请您重新尝试。", "error");
						  }
					})
		        } else {
		            swal("已取消", "您取消了添加操作！", "error");
		        }
		    });
		}
		
		//定义用户id
		var userId;
		//初始化双向关系用户下拉框
		$.ajax({
			url:'/page/queryUserNames',
			  dataType:'json',
			  success : function(datas){
				  for(var data in datas){
					  $('#enableProjectAndUser2').append(
							 "<option value='"+datas[data].userId+"'>"+datas[data].userName+"---"+datas[data].realName+"</option>"
					  )
				  };
				  userId = $("#enableProjectAndUser2").find("option:selected").attr("value");
				  //引用刷新
				  refreshInUser();
			  }
		})
		//刷新双向中用户一栏数据
		function refreshInUser(){
			$('#userAndProjectTable').bootstrapTable("refresh",'/page/queryProjectsByUser');
			$('#userAndProjectTableForAdd').bootstrapTable("refresh",'/page/queryNoProjectsByUser');
		};
		//用户下拉绑定查询事件
		 $('#enableProjectAndUser2').change(function(){
//			 alert($("#enableProjectAndUser").find("option:selected").attr("value"));
			 userId = $("#enableProjectAndUser2").find("option:selected").attr("value");
			 refreshInUser();
		 });
		//用户项目表初始化
		$('#userAndProjectTable').bootstrapTable({
			url: '/page/queryProjectsByUser',
			queryParams: function (params) {
				var param =  {
						offset: params.offset,
						limit: params.limit,
						condition:params.search,
						userId:userId,
				};
				return param;
			},
			columns: [{
				field: 'projectId',
				title: '项目id', 
			}, {
				field: 'projectName',
				title: '项目名称',
				
			}, {
				field: 'projectAddress',
				title: '项目地址',
				
			}, {
				formatter: function (value, row, index) {
					return [
					        '<a href="javascript:delProjectInUser(' + row.projectId + ')">' +
					        '<i class="glyphicon glyphicon-remove"></i>移除' +
					        '</a>'
					        ].join('');
				},
				title: '操作'
			}],
			striped: true,
			pagination: true,
			sidePagination: 'server',
			pageSize: 5,
			pageList: [5, 10, 25, 50, 100],
			clickToSelect: true,
			toolbar: '#enableProjectAndUserTool2',                //工具按钮用哪个容器
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
			uniqueId: "no",                     //每一行的唯一标识，一般为主键列
			showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
			cardView: false,                    //是否显示详细视图
			detailView: false, 
		});
		
		//用户项目添加表初始化
		$('#userAndProjectTableForAdd').bootstrapTable({
			url: '/page/queryNoProjectsByUser',
			queryParams: function (params) {
				var param =  {
						offset: params.offset,
						limit: params.limit,
						condition:params.search,
						userId:userId,
				};
				return param;
			},
			columns: [{
				field: 'projectId',
				title: '项目id', 
			}, {
				field: 'projectName',
				title: '项目名称',
				
			}, {
				field: 'projectAddress',
				title: '项目地址',
				
			}, {
				formatter: function (value, row, index) {
					return [
					        '<a href="javascript:addProjectInUser(' + row.projectId + ')">' +
					        '<i class="glyphicon glyphicon-check"></i>加入当前用户' +
					        '</a>'
					        ].join('');
				},
				title: '操作'
			}],
			striped: true,
			pagination: true,
			sidePagination: 'server',
			pageSize: 5,
			pageList: [5, 10, 25, 50, 100],
			clickToSelect: true,
			toolbar: '#enableUserAndProjectTool2',                //工具按钮用哪个容器
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
			uniqueId: "no",                     //每一行的唯一标识，一般为主键列
			showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
			cardView: false,                    //是否显示详细视图
			detailView: false, 
		});
		
		//添加项目到当前用户
		function addProjectInUser(projectId){
			swal({
		        title: "您确定要添加该项目吗",
		        type: "warning",
		        showCancelButton: true,
		        confirmButtonColor: "#DD6B55",
		        confirmButtonText: "是的，我要添加！",
		        cancelButtonText: "取消",
		        closeOnConfirm: false,
		        closeOnCancel: false
		    },function (isConfirm) {
		        if (isConfirm) {
		        	$.ajax({
		        		  url:'/manage/addProjectInUser',
						  dataType:'json',
						  contentType : "application/json;charset=utf-8",
						  data : 'projectId='+projectId+'&userId='+userId,
						  success : function(data){
							  if(data){
								  swal("添加成功！", "success");
								  refreshInUser();
							  }else{
								  swal("添加失败", "请您重新尝试。", "error");
							  }
						  },
						  error : function(){
							  swal("添加失败", "请您重新尝试。", "error");
						  }
					})
		        } else {
		            swal("已取消", "您取消了添加操作！", "error");
		        }
		    });
		}
		
		//移除用户下已有项目
		function delProjectInUser(projectId){
			swal({
		        title: "您确定要移除该项目吗",
		        text: "移除后如需添加，请通过下方添加操作！",
		        type: "warning",
		        showCancelButton: true,
		        confirmButtonColor: "#DD6B55",
		        confirmButtonText: "是的，我要移除！",
		        cancelButtonText: "取消",
		        closeOnConfirm: false,
		        closeOnCancel: false
		    },function (isConfirm) {
		        if (isConfirm) {
		        	$.ajax({
		        		  url:'/manage/delProjectInUser',
						  dataType:'json',
						  contentType : "application/json;charset=utf-8",
						  data : 'projectId='+projectId+'&userId='+userId,
						  success : function(data){
							  if(data){
								  swal("移除成功！", "success");
								  refreshInUser();
							  }else{
								  swal("移除失败", "请您重新尝试。", "error");
							  }
						  },
						  error : function(){
							  swal("移除失败", "请您重新尝试。", "error");
						  }
					})
		        } else {
		            swal("已取消", "您取消了移除操作！", "error");
		        }
		    });
		}
		//用户和项目 end--by kx
		
		