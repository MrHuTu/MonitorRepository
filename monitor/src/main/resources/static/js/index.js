

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
		  $("#projetModel").trigger('click');
	  })
	  
	  //td用户设置动态绑定事件
	  $(document).on("click",".usertd",function(){
		  var proid=$(this).parent().find("th").eq(0).text();
		  $("#userModel").trigger('click');
	  })
})

