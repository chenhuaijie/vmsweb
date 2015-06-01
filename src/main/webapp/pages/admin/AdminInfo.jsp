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
	  	//修改我的信息 
   		function edit(userid,name,nickname,email,phonenumber,dept,company){
   			$('#userid').val(userid);
			$('#name').val(name);
			$('#nickname').val(nickname);
			$('#email').val(email);
			$('#phonenumber').val(phonenumber);
			$('#dept').val(dept);
			$('#company').val(company);
   			$.ligerDialog.open({
				title : "修改我的信息", 
				target : $("#edit"),
				width : 500,
				height : 180,
				buttons : [ {
					text : '提交',
					onclick : function(item, d) {
						if($('#name').val()==''){
							$.ligerDialog.warn('用户名称不能为空！');  
							$('#name').focus();
						}else if($('#nickname').val()==''){
							$.ligerDialog.warn('用户昵称不能为空！'); 
							$('#nickname').focus();
						}else if($('#email').val()==''){
							$.ligerDialog.warn('Email不能为空！'); 
							$('#email').focus();
						}else if($('#phonenumber').val()==''){
							$.ligerDialog.warn('手机号码不能为空！'); 
							$('#phonenumber').focus();
						}else if($('#dept').val()==''){
							$.ligerDialog.warn('用户部门不能为空！'); 
							$('#dept').focus();
						}else if($('#company').val()==''){
							$.ligerDialog.warn('用户公司不能为空！'); 
							$('#company').focus(); 
						}else{
							$('#editForm').submit();
							d.close();
						}
				  			
					}
				},{
					text : '重置',
					onclick : function(item, d) {
						$('#nickname').val('');
						$('#email').val('');
						$('#phonenumber').val('');
						$('#dept').val('');
						$('#company').val('');
					}
				}]
			});
   		}
		
   	</script>
</head>
<body>
	<span style="color:blue"> 当前位置：超级管理员-我的信息</span>
	<!-- 列表区 -->
	<div>
	   	<table border="1" style="width:1150px;" class="gridtable">
	   		<col width="12%""/>
	   		<col width="12%""/>
	   		<col width="12%""/>
	   		<col width="12%""/>
	   		<col width="12%""/>
	   		<col width="12%""/>
	   		<col width="12%""/>
	   		<col width="16%""/>
			<thead>
				<tr>
		            <th style="text-align: center">用户名称</th>
		            <th style="text-align: center">用户昵称</th>
		            <th style="text-align: center">邮箱</th>
		            <th style="text-align: center">手机号码</th>
		            <th style="text-align: center">部门</th>
		            <th style="text-align: center">公司</th>
		            <th style="text-align: center">角色</th>
		            <th style="text-align: center">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="list" status="s">
					<tr>
						<td style="text-align: center"><s:property value="name"/></td>
						<td style="text-align: center"><s:property value="nickname"/></td>
						<td style="text-align: center"><s:property value="email"/></td>
						<td style="text-align: center"><s:property value="phonenumber"/></td>
						<td style="text-align: center"><s:property value="dept"/></td>
						<td style="text-align: center"><s:property value="company"/></td>
						<td style="text-align: center"><s:property value="roleName"/></td>
						<td style="text-align: center">
							 <a href="javascript:void(0)" 
							 onclick="edit('${userid}','${name}','${nickname}','${email}','${phonenumber}','${dept}','${company}');" >修改我的信息</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>	
   	<!--edit -->
	<div id="edit" style="display:none">
	<br/>
		<form id="editForm" action="${path}/admin/mine/myInfoEdit.html" method="post">
			<table style="width: 100%">
				<col style="width: 20%;" />
				<col style="width: 30%;"/>
				<col style="width: 20%;" />
				<col style="width: 30%;"/>
				<tr>
					<input type="hidden" id="userid" name="admin.userid"></input>
					<td style="text-align: right">用户名：</td>
					<td style="text-align: left"><input name="admin.name" id="name" readonly="true"/></td>
					<td style="text-align: right">用户昵称：</td>
					<td style="text-align: left"><input type="text" name="admin.nickname" id="nickname"/></td>
				</tr>
				<tr>
					<td style="text-align: right">邮箱：</td>
					<td style="text-align: left"><input type="text" name="admin.email" id="email"/></td>
					<td style="text-align: right">手机号码：</td>
					<td style="text-align: left"><input type="text" name="admin.phonenumber" id="phonenumber"/></td>
				</tr>
				<tr>
					<td style="text-align: right">部门：</td>
					<td style="text-align: left"><input type="text" name="admin.dept" id="dept"/></td>
					<td style="text-align: right">公司：</td>
					<td style="text-align: left"><input type="text"name="admin.company" id="company"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
