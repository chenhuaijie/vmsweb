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
		//修改 
	  	function goEdit(userid){
			window.parent.frames["browerFrame"].location.href = "${path}/supers/goEdit.html?userid="+userid;
	    }
		
		//删除
		function doDel(carid){
			$.ligerDialog.confirm('确定删除？', 
				function (yes){
					if(yes){
						window.parent.frames["browerFrame"].location.href = "${path}/admin/car/doDel.html?carid="+carid;
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
					 			url:"${path}/super/admin/resetPwd.html", 
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
	  		$('#search_plate_num').val('');
	  		$('#search_type').val('');
	  		$('#search_status').val('default');
	  		$('#search_origin').val('default');
	  	}
	  	
	  	function validatePlate_num(obj){
	  		var plate_num = obj.value;
			if(plate_num==''){
				$.ligerDialog.warn("车牌号不能为空！"); 
			}else{
				$.ajax({
		 			url:"${path}/admin/car/isPalteNumExists.html",
		 			type:"post",
		 			dataType:"json",
		 			data:"plate_num="+plate_num, 
		 			success:function(data){
		 				if(data.success){
		 					$.ligerDialog.warn("车牌号重复！");  
		 					$("#plate_num").val('');
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
					title : "增加车辆 ",
					target : $("#add"),
					width : 500,
					height : 150,
					buttons : [ {
						text : '提交',
						onclick : function(item, d) {
							if($('#name').val()==''){
					  			$.ligerDialog.warn("设备名称名称不能为空！");  
					  			return;
					  		}else if($('#plate_num').val()==''){
					  			$.ligerDialog.warn("车牌号不能为空！");  
					  			return;
					  		}else if($('#type').val()==''){
					  			$.ligerDialog.warn("车辆类型不能为空！"); 
					  			return; 
					  		}else if($('#origin').val()=='default'){
					  			$.ligerDialog.warn("车辆来源不能为空！");  
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
							$('#plate_num').val('');
							$('#type').val('');
							$('#origin').val('default');
						}
					}]
			});
	  	}
	  	//修改车辆信息
	  	function edit(carid,name,plate_num,type,origin){
	  		$('#edit_carid').val(carid);
	  		$('#edit_name').val(name);
	  		$('#edit_plate_num').val(plate_num);
	  		$('#edit_type').val(type);
	  		$('#edit_origin').val(origin);
	  		$.ligerDialog.open({
				title : "修改车辆信息 ", 
				target : $("#edit"),
				width : 500,
				height : 150,
				buttons : [ {
					text : '提交',
					onclick : function(item, d) {
						if($('#edit_name').val()==''){
				  			$.ligerDialog.warn("设备名称名称不能为空！");  
				  			return;
				  		}else if($('#edit_plate_num').val()==''){
				  			$.ligerDialog.warn("车牌号不能为空！");  
				  			return;
				  		}else if($('#edit_type').val()==''){
				  			$.ligerDialog.warn("车辆类型不能为空！"); 
				  			return; 
				  		}else if($('#edit_origin').val()=='default'){
				  			$.ligerDialog.warn("车辆来源不能为空！");  
				  			return; 
				  		}else{
				  			$('#editForm').submit();
				  			d.close();
				  		}
					}
				},{
					text : '重置',
					onclick : function(item, d) {
						$('#edit_name').val('');
						$('#edit_plate_num').val('');
						$('#edit_type').val('');
						$('#edit_origin').val('default');
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
			<div id="search">
			<form id="searchForm" action="${path}/admin/car/goMain.html" method="post">
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
   	 				<td style="text-align: right;">汽车名称：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.name" id="search_name"/></td>
   	 				<td style="text-align: right;">车牌号：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.plate_num" id="search_plate_num"/></td>
   	 				<td style="text-align: right;">汽车类型：</td>
   	 				<td style="text-align: left;"><s:textfield name="search.type" id="search_type"/></td>
   	 				<td style="text-align: right;">汽车状态：</td>
   	 				<td style="text-align: left;">
   	 					<s:select list="status" style="width:135px" name="search.status" id="search_status"
   								listKey="enValue" listValue="enName" headerKey="default" headerValue="-----请选择状态-----"></s:select>
   	 				</td>
   	 				<td></td>
   	 				<td></td>
   	 			</tr>
   	 			<tr>
   	 				<td style="text-align: right;">汽车来源：</td>
   	 				<td style="text-align: left;">
   	 					<s:select list="origins" style="width:135px" name="search.origin" id="search_origin"
   								listKey="enValue" listValue="enName" headerKey="default" headerValue="-----请选择来源-----"></s:select>
   	 				</td>
   	 				<td></td>
   	 				<td></td>
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
	   		<col width="16%""/>
	   		<col width="16%""/>
	   		<col width="16%""/>
	   		<col width="16%""/>
	   		<col width="16%""/>
	   		<col width="22%""/>
			<thead>
				<tr>
		            <th style="text-align: center">汽车名称</th>
		            <th style="text-align: center">车牌号</th>
		            <th style="text-align: center">车辆类型</th>
		            <th style="text-align: center">汽车状态</th>
		            <th style="text-align: center">汽车来源</th>
		            <th style="text-align: center">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="cars" status="s">
					<tr>
						<td style="text-align: center"><s:property value="name"/></td>
						<td style="text-align: center"><s:property value="plate_num"/></td>
						<td style="text-align: center"><s:property value="type"/></td>
						<td style="text-align: center"><s:property value="statusName"/></td>
						<td style="text-align: center"><s:property value="originName"/></td>
						<td style="text-align: center">
							 <a href="javascript:void(0)" onclick="doDel('${carid}');" >删除</a>
							 <a href="javascript:void(0)" 
							 	onclick="edit('${carid}','${name}','${plate_num}','${type}','${origin}');" >修改</a>
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
	<!-- add -->
	<div id="add" style="display:none">
   	 	<form id="addForm" method="post" action="${path}/admin/car/doAdd.html">
   	 		<table>
   	 			<col style="width: 20%;" />
				<col style="width: 30%;"/>
				<col style="width: 20%;" />
				<col style="width: 30%;"/>
   	 			<tr>
   	 				<td style="text-align: right;">设备名称：</td>
   	 				<td style="text-align: left;"><input type="text" id="name" name="car.name"/></td>
   	 				<td style="text-align: right;">车牌号：</td>
   	 				<td style="text-align: left;"><input type="text" id="plate_num" name="car.plate_num" onblur="validatePlate_num(this)"/></td>
   	 			</tr>
   	 			<tr>
   	 				<td style="text-align: right;">型号：</td>
   	 				<td style="text-align: left;"><input type="text" id="type" name="car.type"/></td>
   	 				<td style="text-align: right;">来源：</td>
   	 				<td style="text-align: left;">
   	 					<s:select list="origins" style="width:135px" name="car.origin" id="origin"
   								listKey="enValue" listValue="enName" headerKey="default" headerValue="-----请选择来源-----"></s:select>
   	 				</td>
   	 			</tr>
   	 		</table>
   	 	</form>
   	 </div>
   	 
 	 <!-- edit -->
	<div id="edit" style="display:none">
   	 	<form id="editForm" method="post" action="${path}/admin/car/doEdit.html">
   	 		<table>
   	 			<col style="width: 20%;" />
				<col style="width: 30%;"/>
				<col style="width: 20%;" />
				<col style="width: 30%;"/>
				<input type="hidden" id="edit_carid" name="car.carid"></input>
   	 			<tr>
   	 				<td style="text-align: right;">设备名称：</td>
   	 				<td style="text-align: left;"><input type="text" id="edit_name" name="car.name"/></td>
   	 				<td style="text-align: right;">车牌号：</td>
   	 				<td style="text-align: left;"><input type="text" id="edit_plate_num" name="car.plate_num" onblur="validatePlate_num(this)"/></td>
   	 			</tr>
   	 			<tr>
   	 				<td style="text-align: right;">型号：</td>
   	 				<td style="text-align: left;"><input type="text" id="edit_type" name="car.type"/></td>
   	 				<td style="text-align: right;">来源：</td>
   	 				<td style="text-align: left;">
   	 					<s:select list="origins" style="width:135px" name="car.origin" id="edit_origin"
   								listKey="enValue" listValue="enName" headerKey="default" headerValue="-----请选择来源-----"></s:select>
   	 				</td>
   	 			</tr>
   	 		</table>
   	 	</form>
   	 </div>
</body>
</html>
