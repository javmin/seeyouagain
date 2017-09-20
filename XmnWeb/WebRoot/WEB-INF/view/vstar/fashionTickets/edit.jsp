<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加活动</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<style type="text/css">
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none !important;
	margin: 0;
}

input[type="number"] {
	-moz-appearance: textfield;
}
</style>
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<input type="hidden" id="fastfdsHttp"
		value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div id="main">
		<div class="panel panel-primary">
			<div class="panel-heading">添加电子门票</div>
			<div class="panel-body">
				<form id="editFrom" role="form"
					class="form-horizontal scrollbar-hover">
					<input type="hidden" value="${fashionTickets.id}" name="id" id="fashionTicketId" />
					<div class="form-group">
						<label class="col-md-3 control-label">活动名称：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<input type="text" class="form-control" id="title" name="title"
								value="${fashionTickets.title}" style="width:41%;float:left">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">活动城市：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<div class="" id="ld" style="width:83%" initValue="${fashionTickets.city}" ></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">经纬度：</label>
						<div class="col-md-7">
							<input type="number" id="longitude" name="longitude"
								placeholder="经度" class="form-control"
								style="width:41%;float:left" value="${fashionTickets.longitude}"> <input
								type="number" id="latitude" name="latitude" placeholder="纬度"
								class="form-control" style="width:41%;float:left"
								value="${fashionTickets.latitude}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">活动地点：</label>
						<div class="col-md-7" id="place_sum">
							<input type="text" class="form-control" id="address"
								name="address" value="${fashionTickets.address}"
								style="width:41%;float:left">
						</div>
					</div>
					<br />
					<div class="form-group">
						<label class="col-md-3 control-label">活动图片：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="hidden" class="form-control" id="pic" name="pic"
								value="${fashionTickets.pic}">
							<div id="picImg"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-7">
							是否可选座位 <input type="radio" name="supportSelectSeats" value="1" ${fashionTickets.supportSelectSeats==1?'checked="checked"':''} />是      <input type="radio" name="supportSelectSeats" value="0" ${fashionTickets.supportSelectSeats==0?'checked="checked"':''} >否
						</div>
					</div>
					<div style="display:none;" id="details">
					<div id="seatsBody">
					<div class="form-group">
						<label class="col-md-3 control-label">定义座位：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-url="fashionTickets/add/seat/init.jhtml" data-toggle="modal">
								<span class="icon-plus"></span>&nbsp;添加
							</button>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-7">
							<table
								class="table table-hover table-bordered table-striped info">
								<thead>
									<tr>
										<th style="width:130px;">类型</th>
										<th style="width:130px;">单个最大容量</th>
										<th style="width:130px;">数量</th>
										<th style="width:130px;">最大可容纳数量</th>
										<th style="width:130px;">编号区间</th>
										<th style="width:130px;">操作</th>
									</tr>
								</thead>
								<tbody id="seatList">
								</tbody>
							</table>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">座位说明图片：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="hidden" class="form-control" id="seatPic"
								name="seatPic" value="${fashionTickets.seatPic}">
							<div id="seatPicImg"></div>
						</div>
					</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">门票售价：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-url="fashionTickets/add/price/init.jhtml" data-toggle="modal">
								<span class="icon-plus"></span>&nbsp;添加
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-7">
							<table
								class="table table-hover table-bordered table-striped info">
								<thead>
									<tr>
										<th style="width:130px;">类型</th>
										<th style="width:130px;">数量</th>
										<th style="width:130px;">单价</th>
										<th style="width:130px;">操作</th>
									</tr>
								</thead>
								<tbody id="priceList">
								</tbody>
							</table>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-7">
							<input type="checkbox" id="onlyCoin" name="onlyCoin" value="1"  ${fashionTickets.onlyCoin==1?'checked="checked"':''}  />是否只能鸟币支付
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">门票总数：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="number" class="form-control" id="totalSeats" min="0"
								name="totalSeats" value="${activity.basePrice}"
								style="width:41%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-7">
							<input type="checkbox" id="limitEveryone" name="limitEveryone" value="1" ${fashionTickets.limitEveryone==1?'checked="checked"':''} />是否限制每人限购一张
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">活动页面：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<div class="block-content collapse in">
								<textarea id="content" class="ckeditor" name="content"
									width="">${fashionTickets.content}</textarea>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">销售日期：</label>
						<div class="col-md-7">
							<input type="text" id="saleStartTime" name="saleStartTime"
								placeholder="开始时间" class="form-control form-datetime" 
								style="width:41%;float:left" value="<fmt:formatDate value="${fashionTickets.saleStartTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"> <input
								type="text" id="saleEndTime" name="saleEndTime"
								placeholder="结束时间" class="form-control form-datetime"
								style="width:41%;float:left" value="<fmt:formatDate value="${fashionTickets.saleEndTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">使用日期：</label>
						<div class="col-md-7">
							<input type="text" id="useStartTime" name="useStartTime"
								placeholder="开始时间" class="form-control form-datetime"
								style="width:41%;float:left" value="<fmt:formatDate value="${fashionTickets.useStartTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"> <input
								type="text" id="useEndTime" name="useEndTime" placeholder="结束时间"
								class="form-control form-datetime" style="width:41%;float:left"
								value="<fmt:formatDate value="${fashionTickets.useEndTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">活动联系电话：<span
							style="color:red;">*</span></label>
						<div class="col-md-7" style="width:25%;">
							<input type="number" class="form-control" id="tel" name="tel" min="0"
								value="${fashionTickets.tel}" style="width:41%;float:left">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">活动logo：<span
							style="color:red;">*</span></label>
						<div class="col-md-7">
							<input type="hidden" class="form-control" id="logo" name="logo"
								value="${fashionTickets.logo}">
							<div id="logoImg"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="text-center" style="padding:10px 0 10px 0;">
							<button type="submit" class="btn btn-success" id="ensure">
								<span class="icon-ok"></span> 保 存
							</button>
							&nbsp;&nbsp; <a class="btn btn-warning"
								href="fashionTickets/init.jhtml"><span class="icon-remove"></span>&nbsp;取消</a>
						</div>
					</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	<script src="<%=path%>/js/vstar/fashionTickets/edit.js?v=1.0.4"></script>
</body>
</html>