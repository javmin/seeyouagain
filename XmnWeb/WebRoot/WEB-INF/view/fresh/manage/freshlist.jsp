<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>生鲜列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<style>
	.submit{float: left;}
	.btn-add{
	    background: #FF5C5C;
    	width: 160px;
		margin-right: 20px;
		border: 1px solid #FF5C5C;
		line-height: 20px;
		text-align: center;
		font-size:16px;
	}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchBillForm">
				<input type="hidden" name="pid" value="${pid}"/> 
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>产品名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="pname" value="${productInfo.pname}" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>商品名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="goodsName" value="${productInfo.goodsName}" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>产品编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="codeId" value="${productInfo.codeId}" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>产品状态:</h5></td>
							<td style="width:24%;">
								<select class="form-control" name="pstatus" style = "width:83%;">
									<option value="">请选择</option>
									<option value="0" ${productInfo.pstatus==0?"selected":""}>待上线</option>
									<option value="1" ${productInfo.pstatus==1?"selected":""}>已上线</option>
									<option value="2" ${productInfo.pstatus==2?"selected":""}>已售罄</option>
									<option value="3" ${productInfo.pstatus==3?"selected":""}>已下线</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>供货方电话:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="tel"  value="${productInfo.tel}" style = "width:90%;" placeholder=""/></td>
						  	<td style="width:5%;"><h5>产品售价:</h5></td>
						 	<td style="width:18%;">
							  <input type="text" class="form-control"  name="minPrice"   value="${productInfo.minPrice}" style="width:39%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  name="maxPrice" value="${productInfo.maxPrice}"   style="width:39%;float:left"/>
							</td>	
							<td style="width:5%;"><h5>所属分类：</h5></td>
							<td style="width:18%">
								<div class="container">
							        <div class="dropdown">
							            <select  role="button" data-toggle="dropdown" class="btn form-control" data-target="#" name="classa" >
							               <option  value="" style="display:none;" >请选择</option>
							            	<option id="dLabel"  value="" style="display:none;" ></option>
							            </select>
							            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu" >
							            	<li><a href="javascript:;" id="" onclick="confirmType(this)">请选择</a></li>
							            	<c:forEach items="${freshTypes}" var="freshType">
											 <li class="dropdown-submenu">`
							                    <a tabindex="-1" href="javascript:;" onclick="confirmType(this)" id="${freshType.id}">${freshType.name}</a>
							                    <c:if test="${freshType.childs!=null}">
							                    <ul class="dropdown-menu">
							                    <c:forEach items="${freshType.childs}" var="type">
							                        <li><a tabindex="-1" href="javascript:;" onclick="confirmType(this)" id="${type.id}">${type.name}</a></li>
							                       </c:forEach>
							                    </ul>
							                    </c:if>
							                </li>
											</c:forEach>
							            </ul>
							        </div>
    							</div>

							</td>
						 	<%-- <td style="width:18%;">
						 	<div class="input-group" id="ptypeld" style="width:90%" 
								 <c:choose>
								    <c:when test="${!empty freshInfo.secondary}">
								        initValue="${freshInfo.secondary}"
								    </c:when>
								    <c:otherwise>  
								    	initValue="${freshInfo.classa}"
								    </c:otherwise>
								 </c:choose>
								>
							</div>
							</td> --%>
							<%-- <td style="width:5%;"><h5>销售城市</h5></td>
							<td style="width:24%;">
							<div class="input-group" id="ld" style="width:83%" 
									<c:choose>
									    <c:when test="${!empty freshInfo.city}">
									    	initValue=" ${freshInfo.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${freshInfo.province}"
									    </c:otherwise>
									 </c:choose>
								 >
								</div>
							</td> --%>
						</tr>
						<tr>
							<td style="width:5%;"></td>
							<td style="width:18%;"></td>
						  	<td style="width:5%;"></td>
						 	<td style="width:18%;"></td>
						 	<td style="width:5%;"></td>
						 	<td style="width:18%;"></td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>						
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['fresh/manage/add'] && btnAu['fresh/manage/add']}">
					<a type="button" class="btn btn-success" id="addFresh_btn"  href="javascript:;" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<c:if test="${!empty btnAu['fresh/manage/beachOnLine'] && btnAu['fresh/manage/beachOnLine']}">
					<button type="button" class="btn btn-info" id="beachOnLine" ><span class="icon-hand-up"></span>&nbsp;上线</button>
				</c:if>
				<c:if test="${!empty btnAu['fresh/manage/downLine']}">
					<button type="button" class="btn btn-danger" id="beachDownLine" ><span class="icon-hand-down"></span>&nbsp;下线</button>
				</c:if>
				<%-- <c:if test="${null!=btnAu['fresh/manage/export'] && btnAu['fresh/manage/export']}"> --%>
					<!-- <button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button> -->
				<%-- </c:if> --%>
				<c:if test="${!empty btnAu['fresh/manage/confirmhotsale'] && btnAu['fresh/manage/confirmhotsale']}">
					<button type="button" id="confirmHotSale" class="btn btn-default" ><span class="icon-ok-sign"></span>&nbsp;设为精选</button>
				</c:if>
				<c:if test="${btnAu['fresh/manage/importProduct']}">
					<a  class="btn btn-default" data-toggle="modal" data-url="fresh/manage/importProduct/init.jhtml" data-type="ajax" href="javascript:;" ><span class="icon-download-alt"></span>&nbsp;导入产品</a>
					<button type="button" id="exportProductFailingBtn" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出无效产品</button>
				</c:if>
			</div>
			<div id="freshList"></div>
			<div id="scrollTablel"></div>
		</div>
	</div>
	
	<!-- 导出无效产品模态框 begin -->
	<div class="modal fade" id="exportProductFailingModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeSubExportModal" type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请选择导入序列号</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="exportform">
						<div class="form-group">
						<table>
							<tr>
								<!-- <td style="width:50%">
									<label for="courierNumber" class="col-sm-2 control-label" style="width:30%"><h5>导入日期:</h5></label>
									<div class="col-sm-10" style="width:50%">
									  <input type="text" class="form-control form-datetime" name="rdate" value="" >
									</div>
								</td> -->
								<td style="width:50%">
									<label for="courierNumber" class="col-sm-2 control-label" style="width:30%"><h5>序列号:</h5></label>
									<div class="col-sm-10" style="width:50%">
									  <select class="form-control" id="importserial" name="importserial"  initValue="" style="width:100%;"></select>
									</div>
								</td>
							</tr>
						</table>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="exportconcel" type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="exportconfirm" type="button" class="btn btn-default">确认</button>
				</div>
			</div>
		</div>
     </div>
	<!-- 导出无效产品模态框 end  -->
	<!-- 修改热卖产品排序模态框 start -->
	<div class="modal fade" id="updateSortModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 24%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改产品排序</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="updateSortForm">
						<div class="form-group">
							<input type="hidden" id="freshPid"> 
							<label for="courierNumber" class="col-sm-2 control-label">排序：</label>
							<div class="col-sm-10">
								<input id="sortId" name="sort" type="text" style="width:80%;" class="form-control" placeholder="输入非负整数数值">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="updateSortConfirm" type="button" class="btn btn-default">确认</button>
				</div>
			</div>
		</div>
     </div>
	<!-- 修改热卖产品排序模态框 end -->
	
	<script type="text/json" id="permissions">{detail:'${ btnAu['fresh/manage/details']}',update:'${ btnAu['fresh/manage/update']}',init:'${btnAu['fresh/manage/list']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/fresh/freshmanage.js"></script>
	<script type="text/javascript">
	var  brandTypeId= "${productInfo.classa}";
		if(brandTypeId){
			var freshType= $("#"+brandTypeId);
			if(freshType.attr("id")){
				$("#dLabel").text(freshType.text()).val(freshType.attr("id")).attr("selected","selected");
			}
		}
		function confirmType(item){
	$("#dLabel").text($(item).text()).val($(item).attr("id")).attr("selected","selected");
	}
	$("input[data-bus=reset]").click(function(){
		$("#dLabel").text("请选择").val("");
	});	
	var page="${productInfo.page==null?'1':productInfo.page}";
	</script>
  </body>
</html>