<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加通告</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>
<body>
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<form id="editFrom" role="form" class="form-horizontal">
		<input type="hidden"  name="payOrderToken" value="${payOrderToken}">
		<c:if test="${!empty livePayOrder}">
			<input type="hidden" name="id" value="${livePayOrder.id}">
		</c:if>
		
		<div class="form-group">
			<label class="col-md-4 control-label">充值渠道：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="objectOriented" value="0" type="radio" ${anchor.objectOriented==0?"checked":""} ><span style="font-size: 12px;">常规</span>
				<%-- <input name="objectOriented" value="1" type="radio" ${anchor.objectOriented==1?"checked":""} ><span style="font-size: 12px;">VIP</span>
				<input name="objectOriented" value="2" type="radio" ${anchor.objectOriented==2?"checked":""} ><span style="font-size: 12px;">商家</span> 
				<input name="objectOriented" value="3" type="radio" ${anchor.objectOriented==3?"checked":""} ><span style="font-size: 12px;">直销</span> --%>
				<input name="objectOriented" value="4" type="radio" ${anchor.objectOriented==4?"checked":""} ><span style="font-size: 12px;">营业厅会员</span>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">选择会员：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="anchorId" name="anchorId" style="width:100%;">
				</select> 
				<input type="hidden" class="form-control" id="uid" name="uid"
					value="${livePayOrder.uid}">
				<input type="hidden" class="form-control" id="uidRelationChain" name="uidRelationChain"
					value="${livePayOrder.uidRelationChain}">
					<!-- 会员类型 -->
				<input type="hidden" id="uObjectOriented">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">上级：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="superior" id="superior"
					value="${livePayOrder.superior}" readonly="readonly">
				<input type="hidden" id="superiorUid">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">上级粉丝级别：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="superiorRankName" id="superiorRankName"
					value="" readonly="readonly">
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="col-md-4 control-label">充值金额：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="payment" id="payment"
					value="${livePayOrder.payment}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">支付方式：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" name="payType" >
				<option value="10000000">赠送充值</option>
<!--  				<option value="100005">鸟币充值</option>
				<option value="1000000">余额充值</option>-->
				<option value="1000021">线下转账</option>
				</select>
			</div>
		</div>	
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">充值鸟豆：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" id="realCoin" name="realCoin" value="${livePayOrder.realCoin}" >
			</div>
		</div>	
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success" id="submitBtn" >
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/livePayOrderEdit.js"></script>
</body>
</html>