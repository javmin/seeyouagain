<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>银行</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<input type="hidden" id="checkable" value="${ btnAu['liveAndroid/manage/delete']}">
			<form class="form-horizontal" role="form" id="searchForm">
				<div class="form-group">
					<label class="col-md-1 control-label" >机器人昵称：</label>
					<div class="col-md-3" style="width: 15%;">
						<input type="text" class="form-control" name="robotName"
							value="" />
					</div>
					<div class="submit">
						<input class="submit radius-3" type="button" value="查询全部"
							data-bus='query' /> <input type="reset" class="reset radius-3"
							value="重置全部" data-bus='reset' />
					</div>
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if
					test="${null!=btnAu['liveAndroid/manage/add'] && btnAu['liveAndroid/manage/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-title="生成机器人" data-url="liveAndroid/manage/add/init.jhtml"
						data-toggle="modal" data-width="auto">
						<span class="icon-plus"></span>&nbsp;生成机器人
					</button>
				</c:if>
				<c:if
					test="${null!=btnAu['liveAndroid/manage/addAvatar'] && btnAu['liveAndroid/manage/addAvatar']}">
					<a type="button" class="btn btn-success"
						 href="liveAndroid/manage/addAvatar/init.jhtml" >
						<span class="icon-plus"></span>&nbsp;添加头像
					</a>
				</c:if>
				
				<c:if test="${ btnAu['liveAndroid/manage/delete']}">
					<button type="button" class="btn btn-danger" id="delete">
						<span class="icon-remove"></span>&nbsp;删除
					</button>
				</c:if>
				<c:if test="${ btnAu['liveAndroid/manage/delete']}">
					<button type="button" class="btn btn-warning" id="deleteAll">
						<span class="icon-remove"></span>&nbsp;清除机器人
					</button>
				</c:if>
				<c:if test="${ btnAu['liveAndroid/manage/delete']}">
					<button type="button" class="btn btn-warning" id="deleteImages">
						<span class="icon-remove"></span>&nbsp;清除机器人头像
					</button>
				</c:if>
			</div>
			<div id="androidList"></div>
		</div>
	</div>
	
	<script type="text/json" id="permissions">{
			update:'${ btnAu['liveAndroid/manage/update/init']}',
			delete:'${ btnAu['liveAndroid/manage/delete']}'
		}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/androidManage.js?v=1.0.4"></script>
</body>
</html>
