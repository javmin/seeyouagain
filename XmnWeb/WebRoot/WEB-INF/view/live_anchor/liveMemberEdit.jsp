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
	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty anchor}">
			<input type="hidden" name="id" value="${anchor.id}">
		</c:if>
		<input type="hidden" name="uid" id="uid" value="${anchor.uid}">
		<!-- 会员类型 -->
		<input type="hidden" name="objectOriented" id="objectOriented" value="${anchor.objectOriented}">
		<!-- 上级Uid -->
		<input type="hidden" name="superiorUid" id="superiorUid" value="${anchor.superiorUid}">
		<div class="form-group">
			<label class="col-md-4 control-label">会员昵称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="nickname" id="nickname" readOnly="readonly"
					value="${anchor.nickname}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">真实姓名：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="name" id="name" 
					value="${anchor.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">会员手机号码：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="phone" id="phone" readOnly="readonly"
					value="${anchor.phone}">
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-md-4 control-label">推荐人类型：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="referrerType" value="001" type="radio" ${anchor.referrerType=="001"?"checked":""} ><span style="font-size: 12px;">企业推荐人</span>
				<input name="referrerType" value="002" type="radio" ${anchor.referrerType=="002"?"checked":""} ><span style="font-size: 12px;">普通推荐人</span>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-md-4 control-label">会员红包奖励权限：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="redPacketAuthority" value="0" type="radio" ${anchor.redPacketAuthority==0?"checked":""} ><span style="font-size: 12px;">正常可获取</span>
				<input name="redPacketAuthority" value="1" type="radio" ${anchor.redPacketAuthority==1?"checked":""} ><span style="font-size: 12px;">不可获取</span>
				<input name="redPacketAuthority" value="2" type="radio" ${anchor.redPacketAuthority==2?"checked":""} ><span style="font-size: 12px;">可获取但不可入账</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">鸟币消费余额限制：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="restrictive" value="001" type="radio" ${anchor.restrictive=="001"?"checked":""} ><span style="font-size: 12px;">不限制</span>
				<input name="restrictive" value="002" type="radio" ${anchor.restrictive=="002"?"checked":""} ><span style="font-size: 12px;">限制</span>
			</div>
		</div>
		<div class="form-group" style="display:none;" id="limitBalanceDiv">
			<label class="col-md-4 control-label">鸟币限制最低余额：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="limitBalance" id="limitBalance"
					value="${anchor.limitBalance}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">内购外购设置：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="dividendsRole" value="1" type="radio" ${anchor.dividendsRole==1?"checked":""} ><span style="font-size: 12px;">内购</span>
				<input name="dividendsRole" value="2" type="radio" ${anchor.dividendsRole==2?"checked":""} ><span style="font-size: 12px;">外购</span>
			</div>
		</div>
		
		<div class="form-group" id="juniorLimitRatioDiv">
			<label class="col-md-4 control-label">红包下线充值限领比例：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control"  name="juniorLimitRatio" value="${anchor.juniorLimitRatio }" placeholder="红包下线充值限领比例，如：50">
			</div>
		</div>
		
		<div class="form-group" id="superiorIdChosenDiv" style="display:none;">
			<label class="col-md-4 control-label">选择上级：</label>
			<div class="col-md-7">
				<select class="form-control" id="superiorIdChosen" name="superiorIdChosen" style="width:100%;">
				</select> 
			</div>
		</div>
		
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success">
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
	<script src="<%=path%>/js/live_anchor/liveMemberEdit.js?v=1.0.11"></script>
</body>
</html>