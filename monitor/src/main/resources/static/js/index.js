$(function() {
	  
	  $('#myTab a').click(function (e) {  
	    e.preventDefault();//阻止a链接的跳转行为  
	    $(this).tab('show');//显示当前选中的链接及关联的content  
	  });
	  //项目
	  var pros=$('.popovers');
	  $(pros).each(function(index,value){
		  var contents="";
		  var projectId= $(value).parent().parent().find("th").eq(0).text();
		  $(pUser[projectId]).each(function(index,value){
			  contents+="<h4>"+value+"</h4>"
		  })
		  //初始化可隐藏按钮
		  $(value).popover({
				html : true,
				content: contents
		  });
	  });
	  //用户
	  var upros=$('.userpover');
	  $(upros).each(function(index,value){
		  var contents="";
		  var userId= $(value).parent().parent().find("th").eq(0).text();
		  if(uPro[userId]==undefined){
			  contents="该用户下无项目！";
		  }else{
			  $(uPro[userId]).each(function(index,value){
				  contents+="<h4>"+value+"</h4>"
			  })
		  }
		  //初始化可隐藏按钮
		  $(value).popover({
				html : true,
				content: contents
		  });
	  });
	  
	  //td项目设置动态绑定事件
	  $(document).on("click",".protd",function(){
		  var proid=$(this).parent().find("th").eq(0).text();
		  $("input[name='sensor_projectId']").removeAttr("value");
		  $("input[name='sensor_projectId']").attr("value",proid);
		  $("#projetModel").trigger('click');
	  })
	  
	  //td用户设置动态绑定事件
	  $(document).on("click",".usertd",function(){
		  var proid=$(this).parent().find("th").eq(0).text();
		  $("#userModel").trigger('click');
	  })
	  
	  $("#addProjectModal").bootstrapValidator({
		  message:'This value is not valid',
		  feedbackIcons: {/*输入框不同状态，显示图片的样式*/
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
		  fields:{//项目地址验证规则
			  projectAddress:{
				  message:'项目地址验证失败',
				  validators:{
					  notEmpty:{
						  message:'项目地址不能为空'
					  },
					  stringLength: {
                          min: 3,
                          max: 120,
                          message: '项目地址限制在3-120长度之间'
                      },
                      regexp: {
                          regexp: /[\u4e00-\u9fa5]/,
                          message: '项目地址必须包含中文'
                      }
				  }
			  },//项目名称验证规则，非空
			  projectName:{
				  message:'项目名称验证失败',
				  validators:{
					  notEmpty:{
						  message:'项目名称不能为空'
					  },
				  }
			  },//天气地点验证规则，非空1-10位中文字符
			  weatherAddress:{
				  message:'天气地点验证失败',
				  validators:{
					  notEmpty:{
						  message:'天气地点为具体市级名称'
					  },
					  regexp: {
                          regexp: /^[\u4e00-\u9fa5]{2,10}$/,
                          message: '天气地点为市级名称，必须为中文字符，且在2-10位'
                      }
				  }
			  },//项目经度验证
			  projectLongitude:{
				  message:'项目经度验证失败',
				  validators:{
					  notEmpty:{
						  message:'项目经度不能为空'
					  },
					  regexp:{
						  regexp:/^[\-\+]?(0?\d{1,2}\.\d{1,5}|1[0-7]?\d{1}\.\d{1,5}|180\.0{1,5})$/,
						  message:'整数部分为0～180，必须输入1到5位小数'
					  }
				  }
			  },//项目纬度验证
			  projectLatitude:{
				  message:'项目纬度验证失败',
				  validators:{
					  notEmpty:{
						  message:'项目纬度不能为空'
					  },
					  regexp:{
						  regexp:/^[\-\+]?([0-8]?\d{1}\.\d{1,5}|90\.0{1,5})$/,
						  message:'整数部分为0～90，必须输入1到5位小数'
					  }
				  }
			  },//项目起始时间验证
			  projectBeginTime:{
				  message:'项目起始时间验证失败',
				  validators:{
					  regexp:{
						  regexp:/\d{4}\-\d{2}\-\d{2}/,
						  message:'项目起始时间格式必须为yyyy-mm-dd'
					  },
					  notEmpty:{
						  message:'项目起始时间不能为空'
					  },
					  callback:{
						  message:'起始时间不能大于结束时间',
						  callback:function(value,validator,$field,options){
							  var end = $("#addProjectModal").find("input[name='projectEndTime']").val();
							  if(""==end)
								  return true;
							  var endTime = new Date(end);
							  var beginTime = new Date(value);
							  return beginTime<endTime;
						  }
					  }
				  }
			  },//项目结束时间验证
			  projectEndTime:{
				  message:'项目结束时间验证失败',
				  validators:{
					  regexp:{
						  regexp:/\d{4}\-\d{2}\-\d{2}/,
						  message:'项目结束时间格式必须为yyyy-mm-dd'
					  },
					  notEmpty:{
						  message:'项目结束时间不能为空'
					  },
					  callback:{
						  message:'结束时间不能小于起始时间',
						  callback:function(value,validator,$field,options){
							  var begin = $("#addProjectModal").find("input[name='projectBeginTime']").val();
							  if(""==begin)
								  return true;
							  var beginTime = new Date(begin);
							  var endTime = new Date(value);
							  return beginTime<endTime;
						  }
					  }
				  }
			  }
		  }
	  });
	  /*添加项目模块提交验证*/
	  $("#addProjectModal").submit(function(ev){ev.preventDefault();});
	  $("#addProjectButton").on("click", function(){
	  var bootstrapValidator = $("#addProjectModal").data('bootstrapValidator');
	  bootstrapValidator.validate();
	  if(bootstrapValidator.isValid()){
		  var project = {
				  projectName : $("[name='projectName']").val(),
				  projectType : $("[name='projectType']").val(),
				  projectAddress : $("[name='projectAddress']").val(),
				  weatherAddress : $("[name='weatherAddress']").val(),
				  projectLongitude : $("[name='projectLongitude']").val(),
				  projectLatitude : $("[name='projectLatitude']").val(),
				  projectBeginTime :$("[name='projectBeginTime']").val()+" 00:00:00",
				  projectEndTime : $("[name='projectEndTime']").val()+" 00:00:00",
				  projectStatus : $("input[name='inlineRadioOptions']:checked").val()
		  }
		  $.ajax({
			  type:'post',
			  url:'/manage/addProject',
			  dataType:'json',
			  contentType : "application/json;charset=utf-8",
			  data : JSON.stringify(project),
			  success : function(data){
				  alert("添加项目成功");
			  },
			  error : function(){
				  alert("添加项目失败");
			  }
		  });
		  //重置表单验证状态以及表单数据
//		  alert(project.projectName+"|"+project.projectType+"|"+project.projectAddress+"|"+project.weatherAddress+"|"+project.projectLongitude+"|"+project.projectLatitude+"|"+project.projectBeginTime+"|"+project.projectEndTime+"|"+project.projectStatus);
		  $("#addProjectModal").bootstrapValidator('resetForm'); 
		  document.getElementById("addProjectModal").reset();}
	  else return;
	  });
	  /*
	   * 添加用户模块验证
	   */
	  $("#addUserModal").bootstrapValidator({
		  message:'This value is not valid',
		  feedbackIcons: {/*输入框不同状态，显示图片的样式*/
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
		  fields:{
			  userName:{
				  message:'用户名验证失败',
				  validators:{
					  notEmpty:{
						  message:'用户名不能为空'
					  },
					  stringLength: {
                          min: 1,
                          max: 30,
                          message: '用户名限制在30个字符长度以内'
                      },
				  }
			  },
			  password:{
				  message:'密码验证失败',
				  validators:{
					  notEmpty:{
						  message:'密码不能为空'
					  },
					  stringLength:{
						  min:1,
						  max:255,
						  message:'密码限制在255个字符以内'
					  }
				  }
			  },
			  phone:{
				  message:'电话验证失败',
				  validators:{
					  notEmpty:{
						  message:'电话不能为空'
					  },
					  regexp:{
						  message:'请输入正确的11位手机号码',
						  regexp:/^1[3|4|5|8][0-9]\d{8}$/
					  }
				  }
			  },
			  email:{
				  message:'邮箱验证失败',
				  validators:{
					  regexp:{
						  regexp:/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/,
						  message:'请输入正确的邮箱'
					  },
					  notEmpty:{
						  message:'邮箱不能为空'
					  },
				  }
			  },
			  company:{
				  message:'公司名称不能为空',
				  validators:{
					  notEmpty:{
						  message:'公司名称不能为空'
					  },
					  stringLength:{
						  min:4,
						  max:100,
						  message:'公司名称限制在100字符以内'
					  }
				  }
			  },
			  realName:{
				  message:'负责人姓名验证失败',
				  validators:{
					  regexp: {
                          regexp: /^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/,
                          message: '负责人姓名可以是中文，可以是英文，允许输入点，允许输入空格，中文和英文不能同时出现长度在20个字符以内'
                      },
                      notEmpty:{
                    	  message:'负责人姓名不能为空',
                      }
				  }
			  },
		  }
	  });
	  /*添加用户模块提交验证*/
	  $("#addUserModal").submit(function(ev){ev.preventDefault();});
	  $("#addUserButton").on("click", function(){
	  var bootstrapValidator = $("#addUserModal").data('bootstrapValidator');
	  bootstrapValidator.validate();
	  if(bootstrapValidator.isValid()){
		  //重置表单验证状态以及表单数据
		  var user = {
				  userName : $("[name='userName']").val(),
				  password : $("[name='password']").val(),
				  phone : $("[name='phone']").val(),
				  email : $("[name='email']").val(),
				  company : $("[name='company']").val(),
				  realName : $("[name='realName']").val(),
				  status : $("input[name='inlineRadioOptionsUser']:checked").val(),
		  };
		  $.ajax({
			  type:'post',
			  url:'/manage/addUser',
			  dataType:'json',
			  contentType : "application/json;charset=utf-8",
			  data : JSON.stringify(user),
			  success :function(data){
				  alert(data);
				  },
			  });
		  $("#addUserModal").bootstrapValidator('resetForm'); 
		  document.getElementById("addUserModal").reset();}
	  else return;
	  });
	  
	  $("#addSensorModal").bootstrapValidator({
		  message:'This value is not valid',
		  feedbackIcons: {/*输入框不同状态，显示图片的样式*/
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
		  fields:{
        	  monitorPoint:{
        		  message:'测点名称验证失败',
        		  validators:{
        			  notEmpty:{
            			  message:'测点名称不能为空，如：SL02'
            		  },
            		  stringLength:{
            			  min:1,
            			  max:30,
            			  message:'测点名称不能超过30个字符，如：SL02'
            		  }
        		  }
        	  },
        	  smuNumber:{
        		  message:'采集器编号验证失败',
        		  validators:{
        			  notEmpty:{
            			  message:'采集器编号不能为空，如：2017100005'
            		  },
            		  stringLength:{
            			  min:1,
            			  max:50,
            			  message:'采集器编号限制在50个字符内，如：2017100005'
            		  }
        		  }
        	  },
        	  smuChannel:{
        		  message:'采集器通道验证失败',
        		  validators:{
        			  notEmpty:{
            			  message:'采集器通道不能为空，如：00'
            		  },
            		  stringLength:{
            			  min:1,
            			  max:50,
            			  message:'采集器通道限制在50个字符内，如：00'
            		  }
        		  }
        	  },
        	  sensorNumber:{
        		  message:'传感器编号验证失败',
        		  validators:{
        			  notEmpty:{
            			  message:'传感器编号不能为空，如：02'
            		  },
            		  stringLength:{
            			  min:1,
            			  max:50,
            			  message:'传感器编号限制在50个字符内，如：02'
            		  }
        		  }
        	  },
        	  sensorType:{
        		  message:'传感器类型验证失败',
        		  validators:{
        			  notEmpty:{
            			  message:'传感器类型不能为空，如：隧道测试传感器'
            		  },
            		  stringLength:{
            			  min:1,
            			  max:30,
            			  message:'传感器类型限制在30个字符内，如：隧道测试传感器'
            		  }
        		  }
        	  },
        	  sensorModel:{
        		  message:'传感器型号验证失败',
        		  validators:{
        			  notEmpty:{
            			  message:'传感器型号不能为空，如：激光测距'
            		  },
            		  stringLength:{
            			  min:1,
            			  max:30,
            			  message:'传感器型号限制在30个字符内，如：激光测距'
            		  }
        		  }
        	  },
        	  sensorLongitude:{
				  message:'经度验证失败',
				  validators:{
					  notEmpty:{
						  message:'经度不能为空'
					  },
					  regexp:{
						  regexp:/^[\-\+]?(0?\d{1,2}\.\d{1,5}|1[0-7]?\d{1}\.\d{1,5}|180\.0{1,5})$/,
						  message:'整数部分为0～180，必须输入1到5位小数'
					  }
				  }
			  },
			  sensorLatitude:{
				  message:'纬度验证失败',
				  validators:{
					  notEmpty:{
						  message:'纬度不能为空'
					  },
					  regexp:{
						  regexp:/^[\-\+]?([0-8]?\d{1}\.\d{1,5}|90\.0{1,5})$/,
						  message:'整数部分为0～90，必须输入1到5位小数'
					  }
				  }
			  },
			  sensorPlace:{
				  message:'传感器地点验证失败',
				  validators:{
					  regexp:{
						  regexp:/[\s\S]/,
						  message:'请描述规范'
					  }
				  }
			  },
			  sensorDepth:{
				  message:'传感器深度验证失败',
				  validators:{
					  regexp:{
						  regexp:/^(-?\d+)(\.\d+)?$/,
						  message:'只能为规范的数字形式，可有负数及一个小数点'
					  }
				  }
			  }
          }
	  });
	  /*添加传感器模块提交验证*/
	  $("#addSensorModal").submit(function(ev){ev.preventDefault();});
	  $("#addSensorButton").on("click", function(){
	  var bootstrapValidator = $("#addSensorModal").data('bootstrapValidator');
	  bootstrapValidator.validate();
	  if(bootstrapValidator.isValid()){
		  var sensor = {
				  projectId : $("input[name='sensor_projectId']").attr("value"),
				  monitorPoint : $("input[name='monitorPoint']").val(),
				  monitorType : $("select[name='monitorType'] option:selected").val(),
				  smuNumber : $("input[name='smuNumber']").val(),
				  smuChannel : $("input[name='smuChannel']").val(),
				  sensorNumber : $("input[name='sensorNumber']").val(),
				  sensorType : $("input[name='sensorType']").val(),
				  sensorModel : $("input[name='sensorModel']").val(),
				  sensorLongitude : $("input[name='sensorLongitude']").val(),
				  sensorLatitude : $("input[name='sensorLatitude']").val(),
				  sensorPlace : $("input[name='sensorPlace']").val(),
				  sensorDepth : $("input[name='sensorDepth']").val(),
		  };
		  $.ajax({
			  type:'post',
			  url:'/manage/addSensor',
			  dataType:'json',
			  contentType : "application/json;charset=utf-8",
			  data : JSON.stringify(sensor),
			  success :function(data){
				  alert(data);
				  },
			  });
		  //重置表单验证状态以及表单数据
		  $("#addSensorModal").bootstrapValidator('resetForm'); 
		  document.getElementById("addSensorModal").reset();}
	  else return;
	  });
	  /*删除选中的项目或者用户*/
	  $(".deleteButton").click(function(){
		  //判断当前栏是否为项目栏
		  if($("#myTab").find("[class='active']").find("a").attr("href")=="#projects"){
			  //获得待删除的项目名称。
			  var deleteNames="";
			  var deleteIds="";
			  $("input[class='projectForWait']:checked").each(function(){
				  deleteNames += $(this).parent().parent().find("td").eq(0).text()+",";
				  deleteIds += $(this).parent().parent().find("[class='hidden']").eq(0).text()+",";
			  })
			  //未选中任何项目，return,清除
			  if(deleteNames==""||deleteIds==""){
				  alert("当前未选中任何项目");
				  $(".deleteButton").removeAttr("data-target");
				  return;
			  }else{
				  //显示对应的删除项目模态窗
				  $(".deleteButton").removeAttr("data-target");
				  $(".deleteButton").attr("data-target","#deleteProjectModal");
				  //清空并显示待删除名单
				  $(".modalFordeleteProject").empty();
				  deleteNames = deleteNames.substr(0,deleteNames.length-1);
				  deleteIds = deleteIds.substring(0, deleteIds.length-1);
				  var names = deleteNames.split(",");
				  for(var i=0;i<names.length;i++){
					  $(".modalFordeleteProject").append(
								"<h4>"+names[i]+"</h4>"	  
					);
				  };
				  //删除项目添加异步
				  $(".deleteProjectsButton").off("click").on("click",function(){
					  $.post("/manage/deleteProjects",{idsForDelete:deleteIds},function(data){
						  alert(data);
						  $(".closeDeleteProjectButton").trigger("click");
					  });
				  });
			  }
			 
		  }else{
			//获得待删除的项目名称。
			  var deleteNames="";
			  var deleteIds="";
			  $("input[class='userForWait']:checked").each(function(){
				  deleteNames += $(this).parent().parent().find("td").eq(0).text()+",";
				  deleteIds += $(this).parent().parent().find("[class='hidden']").eq(0).text()+",";
			  })
			  //未选中任何项目，return,清除
			  if(deleteNames==""||deleteIds==""){
				  alert("当前未选中任何用户");
				  $(".deleteButton").removeAttr("data-target");
				  return;
			  }else{
				  //显示对应的删除用户模态窗
				  $(".deleteButton").removeAttr("data-target");
				  $(".deleteButton").attr("data-target","#deleteUserModal");
				//清空并显示待删除名单
				  $(".modalFordeleteUser").empty();
				  deleteNames = deleteNames.substr(0,deleteNames.length-1);
				  deleteIds = deleteIds.substring(0, deleteIds.length-1);
				  var names = deleteNames.split(",");
				  for(var i=0;i<names.length;i++){
					  $(".modalFordeleteUser").append(
								"<h4>"+names[i]+"</h4>"	  
					);
				  };
				//删除用户添加异步
				  $(".deleteUsersButton").off("click").on("click",function(){
					  $.post("/manage/deleteUsers",{idsForDelete:deleteIds},function(data){
						  alert(data);
						  $(".closeDeleteUserButton").trigger("click");
					  });
				  });
			  }
		  }
	  })
});

