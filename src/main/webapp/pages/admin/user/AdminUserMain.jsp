<%@ page isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="/pages/common/common.jsp" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>上海宝冶集团汽车租赁管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="${path}/js/ligerUI/skins/Aqua/css/ligerui-all.css" />
	<script type="text/javascript" src="${path}/js/jquery-1.8.2.min.js"></script>
	<script src="${path}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${path}/js/ligerUI/js/plugins/ligerDialog.js"  type="text/javascript"></script>
	<style type="text/css">
		.current_page
			{
			    background: #FFF;
			    border: #89bdd8 solid 1px;
			    color: #067db5;
			}
			 a {text-decoration:none;}
				table.gridtable {
				font-family: verdana,arial,sans-serif;
				font-size:11px;
				color:#333333;
				border-width: 1px;
				border-color: #666666;
				border-collapse: collapse;
			}
			table.gridtable th {
				border-width: 1px;
				padding: 5px;
				border-style: solid;
				border-color: #666666;
				background-color: #dedede;
			}
			table.gridtable td {
				border-width: 1px;
				padding: 4px;
				border-style: solid;
				border-color: #666666;
				background-color: #ffffff;
			}
	</style>
	<script type="text/javascript">
		//页面加载完成时的事件 
		window.onload = function(){
			var msg = '${request.msg}';
			var tipType = '${request.tipType}';
			if(msg==''||tipType==''){
			}else{
				if(tipType=='success'){
					$.ligerDialog.success(msg);
				}else if(tipType=='warn'){
					$.ligerDialog.warn(msg);
				}else if(tipType=='error'){
					$.ligerDialog.error(msg);
				}else{
				}
			}
			return;
		}
		
		//删除
		function doDel(userid){
			$.ligerDialog.confirm('确定删除？', 
				function (yes){
					if(yes){
						window.parent.frames["browerFrame"].location.href = "${path}/admin/user/doDel.html?userid="+userid;
					}
				}
			);
		}
		
		//密码重置 
		function pwdReset(userid){
			$.ligerDialog.confirm('确定重置密码？', 
					function (yes){
						if(yes){
							$.ajax({
					 			url:"${path}/admin/user/resetPwd.html", 
					 			type:"post",
					 			dataType:"json",
					 			data:"userid="+userid, 
					 			success:function(data){
					 				if(data.success){
					 					$.ligerDialog.success('密码重置成功！');  
		 							}else{
		 								$.ligerDialog.error('密码重置失败！'); 
					 				}
					 			},
					 			error:function(){
					 				$.ligerDialog.error('出错！');  
					 			}
					 		});
						}
					}
				);
		}
		
	  	//上一页 
	  	function previous(){
	  		var toPage = parseInt($('#currentPage').val())-1;
	  		if(toPage>0){
	  	  		requestForThePage(toPage);
	  		}
		}
	  	//下一页 
	  	function next(){
	  		var toPage = parseInt($('#currentPage').val())+1;
	  		var totalPage = $('#totalPage').val();
	  		if(toPage<=totalPage){
	  	  		requestForThePage(toPage);
	  		}
	  	} 
	  	//搜索栏的隐藏与显示 
	  	function showAndHide(){
	  		var temp=$("#searchForm").is(":hidden");
	  		if(temp){
	  			$("#searchForm").css('display','block'); 
	  		}else{
	  			$("#searchForm").css('display','none'); 
	  		}
	  	}
	  	//发出请求
	  	function requestForThePage(toPage){
	  		$('#toPage').val(toPage);
	  		$('#searchForm').submit();
  		}
	  	function search_reset(){
	  		$('#search_name').val('');
	  		$('#search_nickname').val('');
	  		$('#search_dept').val('');
	  		$('#search_company').val('');
	  		$('#search_phonenumber').val('');
	  		$('#search_email').val('');
	  	}
	  	
	  	function validateName(){
			var name = $("#name").val();
			if(name==''){
				$.ligerDialog.warn("用户名不能为空！"); 
			}else{
				$.ajax({
		 			url:"${path}/admin/user/isUserExists.html",
		 			type:"post",
		 			dataType:"json",
		 			data:"name="+name,
		 			success:function(data){
		 				if(data.success){
		 					$.ligerDialog.warn("用户名已经存在，换一个名吧！"); 
		 					//$('#name').focus();
		 					$("#name").val('');
		 				}else{
		 					
		 				}
		 			},
		 			error:function(){
		 				$.ligerDialog.error("查询出错！");  
		 			}
		 		});
			}
		}	
	  	
	  //增加管理员 
	  	function add(){
	  		$.ligerDialog.open({
					title : "增加管理员",
					target : $("#add"),
					width : 500,
					height : 150,
					buttons : [ {
						text : '提交',
						onclick : function(item, d) {
							if($('#name').val()==''){
					  			$.ligerDialog.warn("用户名称不能为空！"); 
					  			return;
					  		}else if($('#nickname').val()==''){
					  			$.ligerDialog.warn("用户昵称不能为空！");  
					  			return;
					  		}else if($('#email').val()==''){
					  			$.ligerDialog.warn("邮箱不能为空！"); 
					  			return; 
					  		}else if($('#phonenumber').val()==''){
					  			$.ligerDialog.warn("电话不能为空！"); 
					  			return; 
					  		}else if($('#company').val()==''){
					  			$.ligerDialog.warn("公司不能为空！"); 
					  			return; 
					  		}else if($('#dept').val()==''){
					  			$.ligerDialog.warn("部门不能为空！"); 
					  			return; 
					  		}else{
					  			$('#addForm').submit();
					  			d.close();
					  		}
						}
					},{
						text : '重置',
						onclick : function(item, d) {
							$('#name').val('');
							$('#nickname').val('');
							$('#email').val('');
							$('#phonenumber').val('');
							$('#company').val('');
							$('#dept').val('');
						}
					}]
			});
	  	}
   	</script>
</head>
<body>
	<div>
		<span style="color:blue">当前位置：超级管理员显示管理员列表</span>
		<a href="javascript:void(0)" style="font-size: 10px"onclick="add()">增加</a>
	</div>
	<!-- 搜索栏 -->
	<div style="font-size: 10px">
		<fieldset style="border:1px dashed blue" >
			<legend><a href="javascript:void(0)" onclick="showAndHide()"><b>快速查询</b></a></legend>
			<div id="searchForm">
			<form id="searchForm" action="${path}/admin/user/goMain.html" method="post">
			<input type="hidden" name="pageModel.toPage" id="toPage"></input>
			<table>
				<col width="6%""/>
		   		<col width="14%""/>
		   		<col width="6%""/>
		   		<col width="14%""/>
		   		<col width="6%""/>
		   		<col width="14%""/>
		   		<col width="6%""/>
		   		<col width="14%""/>
		   		<col width="6%""/>
		   		<col width="14%""/>
				<tr>
   	 				<td style="text-align: right;">用户名称：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.name" id="search_name"/></td>
   	 				<td style="text-align: right;">用户昵称：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.nickname" id="search_nickname"/></td>
   	 				<td style="text-align: right;">部门：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.dept" id="search_dept"/></td>
   	 				<td style="text-align: right;">公司：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.company" id="search_company"/></td>
   	 				<td></td>
   	 				<td></td>
   	 			</tr>
   	 			<tr>
   	 				<td style="text-align: right;">手机号码：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.phonenumber" id="search_phonenumber"/></td>
   	 				<td style="text-align: right;">邮箱：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.email" id="search_email"/></td>
   	 				<td></td>
   	 				<td></td>
   	 				<td></td>
   	 				<td></td>
   	 				<td><input type="submit" style="width:100px;"value="查询"></input></td>
   	 				<td><input type="button" onclick="search_reset()" style="width:100px;" value="重置"></input></td>
   	 			</tr>
			</table>
		</form>
		</div>
	</fieldset>
	</div>
	</br>
	<!-- 列表区 -->
	<div>
	   	<table border="1" style="width:1150px;" class="gridtable">
	   		<col width="13%""/>
	   		<col width="13%""/>
	   		<col width="13%""/>
	   		<col width="13%""/>
	   		<col width="13%""/>
	   		<col width="13%""/>
	   		<col width="22%""/>
			<thead>
				<tr>
		            <th style="text-align: center">用户名称</th>
		            <th style="text-align: center">用户昵称</th>
		            <th style="text-align: center">邮箱</th>
		            <th style="text-align: center">手机号码</th>
		            <th style="text-align: center">部门</th>
		            <th style="text-align: center">公司</th>
		            <th style="text-align: center">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="users" status="s">
					<tr>
						<td style="text-align: center"><s:property value="name"/></td>
						<td style="text-align: center"><s:property value="nickname"/></td>
						<td style="text-align: center"><s:property value="email"/></td>
						<td style="text-align: center"><s:property value="phonenumber"/></td>
						<td style="text-align: center"><s:property value="dept"/></td>
						<td style="text-align: center"><s:property value="company"/></td>
						<td style="text-align: center">
							 <a href="javascript:void(0)" onclick="doDel('${userid}');" >删除</a>
							 <a href="javascript:void(0)" onclick="pwdReset('${userid}');" >重置密码</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>	
	</br>
	<!-- 分页 -->
	<div id="pages" style="text-align: center">
		<s:hidden id="currentPage" name="pageModel.current_page" />
		<s:hidden id="totalPage" name="pageModel.total_page" />
		<a href="javascript:void(0)" onclick="previous()">上一页</a>
		<c:forEach begin="1" end="${pageModel.total_page}" var="p">
			<c:choose>
       	      		<c:when test="${p==pageModel.current_page}">
       	      			<a href="javascript:void(0)" class="current_page" onclick="requestForThePage(${p})">&nbsp;${p}&nbsp;</a>
       	      		</c:when>
     	      		<c:otherwise>
     	      			<a href="javascript:void(0)" onclick="requestForThePage(${p})">&nbsp;${p}&nbsp;</a>
     	      		</c:otherwise>
     	    </c:choose>
		</c:forEach>
		<a href="javascript:void(0)" onclick="next()">下一页</a>
	</div>
	<!--增加 -->
	<div id="add" style="display: none">
		<form id="addForm" action="${path}/admin/user/doAdd.html" method="post">
			<table>
   	 			<col style="width: 20%;" />
				<col style="width: 30%;"/>
				<col style="width: 20%;" />
				<col style="width: 30%;"/>
   	 			<tr>
   	 				<td style="text-align: right;">用户名：</td>
   	 				<td style="text-align: left;"><input type="text" id="name" name="user.name" onblur="validateName();"/></td>
   	 				<td style="text-align: right;">用户昵称：</td>
   	 				<td style="text-align: left;"><input type="text" id="nickname" name="user.nickname"/></td>
   	 			</tr>
   	 			<tr>
   	 				<td style="text-align: right;">邮箱：</td>
   	 				<td style="text-align: left;"><input type="text" id="email" name="user.email"/></td>
   	 				<td style="text-align: right;">手机号码：</td>
   	 				<td style="text-align: left;"><input type="text" id="phonenumber" name="user.phonenumber"/></td>
   	 			</tr>
   	 			<tr>
   	 				<td style="text-align: right;">公司：</td>
   	 				<td style="text-align: left;"><input type="text" id="company" name="user.company"/></td>
   	 				<td style="text-align: right;">部门：</td>
   	 				<td style="text-align: left;"><input type="text" id="dept" name="user.dept"/></td>
   	 			</tr>
   	 		</table>
		</form>
	</div>
</body>
</html>
