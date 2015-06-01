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
		<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script><!-- 日期控件 -->
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
		
		function rentCar(carid){
			$('#sbook_carid').val(carid);
				//增加管理员 
		  		$.ligerDialog.open({
						title : "车辆租赁信息录入 ",
						target : $("#rent"),
						width : 500,
						height : 200,
						buttons : [ {
							text : '提交',
							onclick : function(item, d) {
								if($('#sbook_lessee').val()==''){
						  			$.ligerDialog.warn("承租方名称不能为空！"); 
						  			$('#sbook_lessee').focus();
						  			return;
						  		}else if($('#sbook_lease').val()==''){
						  			$.ligerDialog.warn("出租方不能为空！"); 
						  			$('#sbook_lease').focus();
						  			return;
						  		}else if($('#startDate').val()==''){
						  			$.ligerDialog.warn("起租时间不能为空！"); 
						  			$('#startDate').focus();
						  			return; 
						  		}else if($('#endDate').val()=='default'){
						  			$.ligerDialog.warn("到租时间不能为空！");  
						  			$('#endDate').focus();
						  			return; 
						  		}else if($('#dept').val()=='default'){
						  			$.ligerDialog.warn("部门不能为空！"); 
						  			$('#dept').focus();
						  			return; 
						  		}else if($('#driver').val()=='default'){
						  			$.ligerDialog.warn("公司不能为空！");  
						  			$('#driver').focus();
						  			return; 
						  		}else if($('#comment').val()=='default'){
						  			$.ligerDialog.warn("备注不能为空！"); 
						  			$('#comment').focus();
						  			return; 
						  		}else{
						  			$('#rentForm').submit();
						  			d.close();
						  		}
							}
						},{
							text : '重置',
							onclick : function(item, d) {
								$('#sbook_lessee').val('');
								$('#sbook_lease').val('');
								$('#startDate').val('');
								$('#endDate').val('');
								$('#dept').val('');
								$('#driver').val('');
								$('#comment').val('');
							}
						}]
				});
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
   	</script>
</head>
<body>
	<span style="color:blue">当前位置：超级管理员显示管理员列表</span>
	<!-- 搜索栏 -->
	<div style="font-size: 10px">
		<fieldset style="border:1px dashed blue" >
			<legend><a href="javascript:void(0)" onclick="showAndHide()"><b>快速查询</b></a></legend>
			<div id="search">
			<form id="searchForm" action="${path}/user/car/goMain.html" method="post">
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
   	 				<td></td>
   	 				<td></td>
   	 				<td></td>
   	 				<td></td>
   	 			</tr>
   	 			<tr>
   	 				<td style="text-align: right;">汽车状态：</td>
   	 				<td style="text-align: left;">
   	 					<s:select list="status" style="width:135px" name="search.status" id="search_status"
   								listKey="enValue" listValue="enName" headerKey="default" headerValue="-----请选择状态-----"></s:select>
   	 				</td>
   	 				<td style="text-align: right;">汽车来源：</td>
   	 				<td style="text-align: left;">
   	 					<s:select list="origins" style="width:135px" name="search.origin" id="search_origin"
   								listKey="enValue" listValue="enName" headerKey="default" headerValue="-----请选择来源-----"></s:select>
   	 				</td>
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
							<!-- 只有未租车辆才显示‘租用该车辆’ -->
							<c:if test="${status=='N'}">
								<a href="javascript:void(0)" onclick="rentCar('${carid}');">租用该车辆</a>
							</c:if>
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
		<!--edit -->
	<div id="rent" style="display:none">
	<br/>
	<form id="rentForm" action="${path}/user/car/doRentCar.html" method="post">
		<table style="width: 100%">
				<col style="width: 20%;" />
				<col style="width: 30%;"/>
				<col style="width: 20%;" />
				<col style="width: 30%;"/>
				<tr>
					<input type="hidden" value="${session.userid}" name="sbook.userid" id="sbook_userid"/>
					<input type="hidden" name="sbook.carid" id="sbook_carid"/>
					<td style="text-align: right">承租方：</td>
					<td style="text-align: left"><input type="text" name="sbook.lessee" id="sbook_lessee"/></td>
					<td style="text-align: right">出租方：</td>
					<td style="text-align: left"><input type="text" name="sbook.lease" id="sbook_lease"/></td>
				</tr>
					<tr>
					<td style="text-align: right">起租时间：</td>
					<td style="text-align: left">
					<input type="text" name="sbook.startDateString"  id="startDate"  
onclick="JavaScript:new WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',isShowWeek:true,el:startDate,maxDate:'#F{$dp.$D(\'endDate\')}'})" 
   					style="width:130px;" size="12"/>
					</td>
					<td style="text-align: right">到租时间：</td>
					<td style="text-align: left">
					<input type="text" name="sbook.endDateString"  id="endDate" 
onclick="JavaScript:new WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',isShowWeek:true,el:endDate,minDate:'#F{$dp.$D(\'startDate\')}'})" 
   					style="width:130px;" size="12"/>
   					</td>
				</tr>
				<tr>
					<td style="text-align: right">使用部门：</td>
					<td style="text-align: left"><input type="text" name="sbook.usedept" id="dept"/></td>
					<td style="text-align: right">承载人：</td>
					<td style="text-align: left"><input type="text" name="sbook.driver" id="driver"/></td>
				</tr>	
				<tr>
					<td style="text-align: right">备注：</td>
					<td style="text-align: left"><input type="text" name="sbook.comment" id="comment"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
