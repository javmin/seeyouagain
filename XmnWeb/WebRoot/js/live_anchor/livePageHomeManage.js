var advanceList;
var underwayList;
//var playbackList;
var anchorVideoList;
var liveRecommendList;
var advanceRecommendList;

var anchorVideoRecommendList;  //精彩视频
var freshmanRecommendLabelList; //新人推荐

var entertainmentEnlistList;  // 缤纷娱乐-抢先报名
var entertainmentProgramList;  // 缤纷娱乐-节目频道
var entertainmentHotPlaybackList;  // 缤纷娱乐-热门回放

var deliciousList;  //美味撩味-美食撩味
var deliciousHotPlaybackList;  //美味撩味-热门回放

var initListUrl = "livePageHome/manage/init/list.jhtml";
var anchorVideoListUrl = "livePageHome/manage/init/anchorVideoList.jhtml";
var imgRoot = $("#fastfdsHttp").val();

var modalId;
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="livePageHome/manage/init.jhtml" target="right">首页推荐管理</a>',
			'userSpan', true);
	
	//直播日期控件初始化
	liveDateInit();
	
	//加载页面数据
	pageInit();
	
	//加载美食撩味 标签
	liveRecordClassifyIdInit();
	
	// 图片
	$("#activityImg").uploadImg({
		urlId : "imageUrl",
		showImg : $('#imageUrl').val()
	});
	
	
	//导出
	$("#export").click(function(){
		var path="livePageHome/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

/**
 * 加载页面数据
 */
function pageInit(){
	advanceList = $("#advanceList").page({
		url : initListUrl,
		success : advanceListCallBack,
		pageBtnNum : 10,
		paramForm : 'advanceSearchForm',
		param : {
			activityType : "5"
		}
	});
	
	underwayList = $("#underwayList").page({
		url : initListUrl,
		success : underwayListCallBack,
		pageBtnNum : 10,
		paramForm : 'underwaySearchForm',
		param : {
			activityType : "5"
		}
	});
	
	anchorVideoList = $("#anchorVideoList").page({
		url : anchorVideoListUrl,
		success : anchorVideoListCallBack,
		pageBtnNum : 10,
		paramForm : 'anchorVideoSearchForm',
		param : {
			activityType : "5"
		}
	});

	
	/***************************新人推荐**************************/
	freshmanRecommendLabelList = $("#freshmanRecommendLabelList").page({
		url : "livePageHome/manage/init/deliciousList.jhtml",
		success : freshmanRecommendSuccess,
		pageBtnNum : 10,
		paramForm : 'freshmanRecommendSearchForm',
		param : {
			rtype : 1  //模块类型 1-新人推荐,2-美味撩味
		}
	});			
	
	liveRecommendList = $("#liveRecommendList").page({  //新人报道(直播推荐)
		url : "livePageHome/manage/init/freshmanRecommendList.jhtml",
		success : liveRecommendSuccess,
		pageBtnNum : 10,
		paramForm : 'liveRecommendSearchForm',
		param : {
			rtype : 2,  //直播推荐
			moduleType: 1  //新人推荐
		}
	});	
	
	advanceRecommendList = $("#advanceRecommendList").page({ //直播预告(预告推荐)
		url : "livePageHome/manage/init/freshmanRecommendList.jhtml",
		success : advanceRecommendSuccess,
		pageBtnNum : 10,
		paramForm : 'advanceRecommendSearchForm',
		param : {
			rtype : 3,  //直播预告
			moduleType: 1  //新人推荐
		}
	});	
	
	anchorVideoRecommendList = $("#anchorVideoRecommendList").page({  //精彩视频推荐
		url : "livePageHome/manage/init/freshmanVideoRecommendList.jhtml",
		success : anchorVideoRecommendSuccess,
		pageBtnNum : 10,
		paramForm : 'advanceRecommendSearchForm',
		param : {
			rtype : 4,  //精彩视频推荐
			moduleType: 1  //新人推荐
		}
	});		
	
	// 缤纷娱乐-热门回放
	entertainmentHotPlaybackList = $("#entertainmentHotPlaybackList").page({
		url : "livePageHome/manage/init/freshmanRecommendList.jhtml",
		success : entertainmentHotPlaybackSuccess,
		pageBtnNum : 10,
		paramForm : 'entertainmentHotPlaybackSearchForm',
		param : {
			rtype : 5,  //精彩视频推荐
			moduleType: 2  //缤纷娱乐
		}
	});		
	
	// 缤纷娱乐-抢先报名
	entertainmentEnlistList = $("#entertainmentEnlistList").page({
		url : "livePageHome/manage/init/entertainmentList.jhtml",
		success : entertainmentSuccess,
		pageBtnNum : 10,
		paramForm : 'advanceRecommendSearchForm',
		param : {
			type : 1 //抢先报名
		}
	});		
	
	// 缤纷娱乐-节目频道
	entertainmentProgramList = $("#entertainmentProgramList").page({
		url : "livePageHome/manage/init/entertainmentList.jhtml",
		success : entertainmentProjectSuccess,
		pageBtnNum : 10,
		paramForm : 'advanceRecommendSearchForm',
		param : {
		    type : 2 //节目频道
		}
	});		
	
	//美味撩味-美食撩味
	deliciousList = $("#deliciousList").page({
		url : "livePageHome/manage/init/deliciousList.jhtml",
		success : deliciousSuccess,
		pageBtnNum : 10,
		paramForm : 'advanceRecommendSearchForm',
		param : {
			rtype : 2  //模块类型 1-新人推荐,2-美味撩味
		}
	});		
	
	//美味撩味-热门回放
	deliciousHotPlaybackList = $("#deliciousHotPlaybackList").page({
		url : "livePageHome/manage/init/freshmanRecommendList.jhtml",
		success : deliciousHotPlaybackSuccess,
		pageBtnNum : 10,
		paramForm : 'deliciousHotPlaybackSearchForm',
		param : {
			rtype : 5,  //精彩视频推荐
			moduleType: 3  //美味撩味
		}
	});		
}

function advanceListCallBack(data, obj) {
	var formId = "shareForm", title = "预告推荐列表", subtitle = "个预告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+ data.total+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "livePageHome/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
				{
					title : "修改排序",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "livePageHome/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改排序"
						},
						param : [ "id" ],
						permission : "update"
					}
				}
			]
		},
		cols : [  {
			title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 150
		},{
			title : "预告名称",
			name : "zhiboTitle",
			type : "string",
			width : 180
		},{
			title : "主播",
			name : "nname",
			type : "string",
			width : 150
		},{
			title : "商家",
			name : "sellername",
			type : "string",
			width : 150
		} ]
	}, permissions);
}


function underwayListCallBack(data, obj) {
	var formId = "shareForm", title = "直播推荐列表", subtitle = "个直播";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete"],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveVideo/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
			            var value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
			            return value;
					}
				 },
				 {
						title : "修改排序",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "livePageHome/manage/update/init.jhtml",
								position : "",
								width : "auto",
								title : "修改排序"
							},
							param : [ "id" ],
							permission : "update"
						}
					}
				
			]
		},
		cols : [ {
			title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 150
		}, {
			title : "直播名称",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "主播",
			name : "nname",
			type : "string",
			width : 150
		}, {
			title : "商家",
			name : "sellername",
			type : "string",
			width : 150
		} 
	  ]
	}, permissions);
}

/**
 * 
 * @param data
 * @param obj
 */
function anchorVideoListCallBack(data, obj) {
	var formId = "shareForm", title = "精彩视频推荐列表", subtitle = "个精彩视频";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveVideo/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDeleteAnchorVideo("+data.id+")'>" + "删除" + "</a>";
				            return value;
				    }
				 },{
						title : "修改排序",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "livePageHome/manage/updateAnchorVideo/init.jhtml",
								position : "",
								width : "auto",
								title : "修改排序"
							},
							param : [ "id" ],
							permission : "update"
						}
					}
			]
		},
		cols : [ {
			title : "视频名称",
			name : "title",
			type : "string",
			width : 180
		},{
			title : "主播",
			name : "anchorName",
			type : "string",
			width : 150
		},{
			title : "商家",
			name : "sellername",
			type : "string",
			width : 150
		}, {
			title : "推荐排序",
			name : "sort",
			type : "string",
			width : 150
		}]
	}, permissions);
}


/**
 * 删除首页通告推荐状态
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "livePageHome/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 pageInit();
					 }, 2000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
 /**
  * 删除首页精彩视频推荐状态
  */
  function confirmDeleteAnchorVideo(id){
 	 showSmConfirmWindow(function (){
 		 $.ajax({
 			 url : "livePageHome/manage/deleteAnchorVideo.jhtml",
 			 type : "post",
 			 dataType : "json",
 			 data:'id=' + id,
 			 success : function(result) {
 				 if (result.success) {
 					 showSmReslutWindow(result.success, result.msg);
 					 setTimeout(function() {
 						 pageInit();
 					 }, 2000);
 				 } else {
 					 window.messager.warning(result.msg);
 				 }
 			 }
 		 });
 	 },"确定要删除吗？");
  }
 
 /**
  * 直播日期控件初始化
  */
 function liveDateInit(){
	    $('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd hh:ii'
		});

	    $('input[name="activityTime"]').datetimepicker({
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd hh:ii'
		});

 }
 
 /**
  * 切换选项卡
  */
 function changeTab(){
	/*$("#myTab li.active").removeClass("active");
	$("#myTab li").eq(operationType).addClass("active");
	$(".tab-content .active").removeClass("active");
	$(".tab-content .tab-pane").eq(operationType).addClass("active");
//	$('#myTab li:eq(1) a').tab('show');
	$("#choice li.active").removeClass("active");
	$("#choice li").eq(operationType).addClass("active");*/
 }
 
 
//******************************新人推荐******************************
 
    function freshmanRecommendSuccess(data, obj) {  //新人推荐
	   	var formId = "freshmanRecommendSearchForm", title = "明星活动列表", subtitle = "个记录";
	   	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
	   			+ title + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
	   	var callbackParam = "isBackButton=true&callbackParam="
	   			+ getFormParam($("#" + formId).serialize());

	   	obj.find('div').eq(0).scrollTablel({
	   		identifier : "id",
	   		callbackParam : callbackParam,
	   		data : data.content,
	   		caption : captionInfo,
	   		// checkable : checkable,
	   		// 操作列
	   		handleCols : {
	   			title : "操作",// 标题
	   			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
	   			width : 250,// 宽度
	   			// 当前列的中元素
	   			cols : [ 
	   				 {
	   					title : "删除",// 标题
	   					linkInfoName : "href",
	   					linkInfo : {
	   						href : "livePageHome/manage/delete.jhtml",// url
	   						param : ["id"],// 参数
	   						permission : "delete"// 列权限
	   					},
	   					customMethod : function(value, data){
	   			            var value = "<a href='javascript:confirmDeleteDelicious("+data.id+")'>" + "删除" + "</a>";
	   			            return value;
	   					}
	   				 },
	   				 {
	   					title : "修改排序",// 标题
	   					linkInfoName : "modal",
	   					linkInfo : {
	   						modal : {
	   							url : "livePageHome/manage/update/init.jhtml",
	   							position : "",
	   							width : "auto",
	   							title : "修改排序"
	   						},
	   						param : [ "id" ],
	   						permission : "update"
	   					},
	   					customMethod : function(value, data){
	   			            var value = "<a href='javascript:editDeliciousSort("+data.id+", "+data.sort+")'>" + "修改排序" + "</a>";
	   			            return value;
	   					}
	   				}
	   			]
	   		},
	   		cols : [{
	   			title : "主题",
	   			name : "title",
	   			type : "string",
	   			width : 180
	   		},{
	   			title : "关联标签",
	   			name : "tagName",
	   			type : "string",
	   			width : 150
	   		},{
	   			title : "推荐排序",
	   			name : "sort",
	   			type : "string",
	   			width : 150
	   		} ]
	   	  }, permissions);
	   }   
 
  /**
   * 
   * @param data
   * @param obj
   */
  function liveRecommendSuccess(data, obj) {
  	var formId = "liveRecommendSearchForm", title = "直播推荐列表", subtitle = "个直播推荐";
  	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
  			+ title+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
  	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

  	obj.find('div').eq(0).scrollTablel({
  		identifier : "id",
  		callbackParam : callbackParam,
  		data : data.content,
  		caption : captionInfo,
  		// checkable : checkable,
  		// 操作列
  		handleCols : {
  			title : "操作",// 标题
  			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
  			width : 250,// 宽度
  			// 当前列的中元素
  			cols : [ 
  				 {
  					title : "删除",// 标题
  					linkInfoName : "href",
  					linkInfo : {
  						href : "liveVideo/manage/delete.jhtml",// url
  						param : ["id"],// 参数
  						permission : "delete"// 列权限
  					},
  					customMethod : function(value, data){
  				            var value = "<a href='javascript:confirmDeleteFreshmanRecommend("+data.id+")'>" + "删除" + "</a>";
  				            return value;
  				    }
  				 },{
  						title : "修改排序",// 标题
  						linkInfoName : "modal",
  						linkInfo : {
  							modal : {
//  								url : "livePageHome/manage/updateAnchorVideo/init.jhtml",
  								position : "",
  								width : "auto",
  								title : "修改排序"
  							},
  							param : [ "id" ],
  							permission : "update"
  						},
  					    customMethod : function(value, data){
  				            var value = "<a href='javascript:editFreshmanRecommendSort("+data.id+", "+data.homeSort+")'>" + "修改排序" + "</a>";
  				            return value;
  				        }
  					}
  			]
  		},
  		cols : [ {
  			title : "直播名称",
  			name : "zhiboTitle",
  			type : "string",
  			width : 180
  			
  		},{
  			title : "主播",
  			name : "nname",
  			type : "string",
  			width : 150
  		},{
  			title : "商家",
  			name : "sellername",
  			type : "string",
  			width : 150
  		},{  			
  			title : "排序",
  			name : "homeSort",
  			type : "string",
  			width : 150
  		}]
  	}, permissions);
  }
  
  function advanceRecommendSuccess(data, obj) {
	  	var formId = "advanceRecommendSearchForm", title = "直播预告推荐列表", subtitle = "个直播预告推荐";
	  	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
	  			+ title+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
	  	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	  	obj.find('div').eq(0).scrollTablel({
	  		identifier : "id",
	  		callbackParam : callbackParam,
	  		data : data.content,
	  		caption : captionInfo,
	  		// checkable : checkable,
	  		// 操作列
	  		handleCols : {
	  			title : "操作",// 标题
	  			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
	  			width : 250,// 宽度
	  			// 当前列的中元素
	  			cols : [ 
	  				 {
	  					title : "删除",// 标题
	  					linkInfoName : "href",
	  					linkInfo : {
	  						href : "liveVideo/manage/delete.jhtml",// url
	  						param : ["id"],// 参数
	  						permission : "delete"// 列权限
	  					},
	  					customMethod : function(value, data){
	  				            var value = "<a href='javascript:confirmDeleteFreshmanRecommend("+data.id+")'>" + "删除" + "</a>";
	  				            return value;
	  				    }
	  				 },{
	  						title : "修改排序",// 标题
	  						linkInfoName : "modal",
	  						linkInfo : {
	  							modal : {
//	  								url : "livePageHome/manage/updateAnchorVideo/init.jhtml",
	  								position : "",
	  								width : "auto",
	  								title : "修改排序"
	  							},
	  							param : [ "id" ],
	  							permission : "update"
	  						},
	  					    customMethod : function(value, data){
	  				            var value = "<a href='javascript:editFreshmanRecommendSort("+data.id+", "+data.homeSort+")'>" + "修改排序" + "</a>";
	  				            return value;
	  				        }
	  					}
	  			]
	  		},
	  		cols : [ {
	  			title : "预告名称",
	  			name : "zhiboTitle",
	  			type : "string",
	  			width : 180
	  			
	  		},{
	  			title : "主播",
	  			name : "nname",
	  			type : "string",
	  			width : 150
	  		},{
	  			title : "商家",
	  			name : "sellername",
	  			type : "string",
	  			width : 150
	  		},{  			
	  			title : "排序",
	  			name : "homeSort",
	  			type : "string",
	  			width : 150
	  		}]
	  	}, permissions);
	  }
  
  
  function anchorVideoRecommendSuccess(data, obj) {
	  	var formId = "anchorVideoRecommendSearchForm", title = "品质优选列表", subtitle = "个记录";
	  	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
	  			+ title+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【' + data.total + '】' + subtitle + '&nbsp;</font></caption>';
	  	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	  	obj.find('div').eq(0).scrollTablel({
	  		identifier : "id",
	  		callbackParam : callbackParam,
	  		data : data.content,
	  		caption : captionInfo,
	  		// checkable : checkable,
	  		// 操作列
	  		handleCols : {
	  			title : "操作",// 标题
	  			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
	  			width : 250,// 宽度
	  			// 当前列的中元素
	  			cols : [ 
	  				 {
	  					title : "删除",// 标题
	  					linkInfoName : "href",
	  					linkInfo : {
	  						href : "liveVideo/manage/delete.jhtml",// url
	  						param : ["id"],// 参数
	  						permission : "delete"// 列权限
	  					},
	  					customMethod : function(value, data){
	  				            var value = "<a href='javascript:confirmDeleteFreshmanRecommend("+data.id+")'>" + "删除" + "</a>";
	  				            return value;
	  				    }
	  				 },{
	  						title : "修改排序",// 标题
	  						linkInfoName : "modal",
	  						linkInfo : {
	  							modal : {
//	  								url : "livePageHome/manage/updateAnchorVideo/init.jhtml",
	  								position : "",
	  								width : "auto",
	  								title : "修改排序"
	  							},
	  							param : [ "id" ],
	  							permission : "update"
	  						},
	  					    customMethod : function(value, data){
	  				            var value = "<a href='javascript:editFreshmanRecommendSort("+data.id+", "+data.homeSort+")'>" + "修改排序" + "</a>";
	  				            return value;
	  				        }
	  					}
	  			]
	  		},
	  		cols : [ {
	  			title : "视频名称",
	  			name : "zhiboTitle",
	  			type : "string",
	  			width : 180
	  			
	  		},{
	  			title : "主播",
	  			name : "nname",
	  			type : "string",
	  			width : 150
	  		},{
	  			title : "商家",
	  			name : "sellername",
	  			type : "string",
	  			width : 150
	  		},{  			
	  			title : "排序",
	  			name : "homeSort",
	  			type : "string",
	  			width : 150
	  		}]
	  	}, permissions);
	  }

	//初始化直播数据
	function initLiveRecommendRecordId(rtype){
		if(rtype){  
			var col= $("#col8").html('');
			col.html('<select class="form-control" name="liveRecommend_choose" style="width: 60%; float: left;" ></select>');
			if (rtype == 4){
				var url="livePageHome/manage/initFreshmanVideoId.jhtml";
				url+="?rtype="+rtype;
				col.find("[name=liveRecommend_choose]").chosenObject({
					hideValue : "id",
					showValue : "showValue",//输入查询值
					showType:"multiple",//选项显示形式
					showParams:["anchorName","title"],//showType为multiple时生效,phone|nickname
					url : url,
					isChosen:true,//是否支持模糊查询
					isCode:true,//是否显示编号
					isHistorical:false,//是否使用历史已加载数据
					width:"100%"
				});
				
			}else{
				var url="livePageHome/manage/initFreshmanRecordId.jhtml";
				//直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播
				var zhiboType = getZhiboType(rtype);
				url=url+"?zhiboType="+zhiboType;
				url+="&rtype="+rtype;
					// 是否是签约主播，signType: 1 代表签约，0 代表未签约
				if (rtype == 2)
						url+="&signType="+0;
				
				
				col.find("[name=liveRecommend_choose]").chosenObject({
					hideValue : "id",
					showValue : "showValue",//输入查询值
					showType:"multiple",//选项显示形式
					showParams:["nname","zhiboTitle"],//showType为multiple时生效,phone|nickname
					url : url,
					isChosen:true,//是否支持模糊查询
					isCode:true,//是否显示编号
					isHistorical:false,//是否使用历史已加载数据
					width:"100%"
				});
			}
		}

	}
	
	
	   /**
	    * 推荐类型转换为直播表里的类型
	    */
    function getZhiboType(rtype) {
	   //直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播
		var zhiboType;
		//推荐类型 1-好看推荐, 2-直播推荐, 3-精选预告, 4-精彩视频推荐, 5-热门回放
		switch (rtype) {
		case 2:
			zhiboType = 1;
//			break;
		case 3:
			zhiboType = 0;
			break;
		case 5:
			zhiboType = 3;
			break;
		default:
			zhiboType = 0;
			break;
		}
		return zhiboType;
	 }
  

    // 新增新人推荐
	function editFreshmanRecommend(rtype) {
	    //推荐类型(rtype) 1-好看推荐, 2-直播推荐, 3-精选预告, 4-精彩视频推荐, 5-热门回放
		initLiveRecommendRecordId(rtype); // 初始化主播选择
		
		if (rtype == 2)  //初始化标题
		    $("#editFreshmanModal").find("h4").html("添加直播推荐");
		else if (rtype == 3)
			$("#editFreshmanModal").find("[class='modal-title']").html("添加预告推荐");
		else if (rtype == 4)	
			$("#editFreshmanModal").find("[class='modal-title']").html("添加推荐视频");
		$("#editFreshmanModal").find("[name=moduleType]").val("1");  //模块类型: 1-新人推荐, 2-缤纷娱乐, 3-美味撩味
		$("#editFreshmanModal").find("[name=rtype]").val(rtype);  //初始化类型
		
		//显示窗口
		$('#editFreshmanModal').modal('show');
	}

	  $("#editFreshmanRecommendSubmit").on("click", function() {
//			var rid = $("#live_choose").val();
			var rid = $("#editFreshmanModal").find("[name=liveRecommend_choose]").val();
			if (!rid) {
				showWarningWindow("warning", "请选择直播!", 9999);
				return;
			}
			var homeSort = $("#editFreshmanModal").find("[name=homeSort]").val();
			if (homeSort == "" || homeSort < 0) {
				showWarningWindow("warning", "请填写正确排序!", 9999);
				return;
			}
			var reg = /^\d+$/;
			if(!reg.test(homeSort)){
				submitDataError($("#editFreshmanModal").find("[name=homeSort]"), "请输入整数排序数值!");
				return false;
			}
			var moduleType = $("#editFreshmanModal").find("[name=moduleType]").val();  //模块类型
			var rtype = $("#editFreshmanModal").find("[name=rtype]").val();  //推荐类型
			var data = {
				"rid" : rid,
				"rtype" : rtype,
				"moduleType" : moduleType,
				"homeSort" : homeSort
			};
		
			$.ajax({
				url : "livePageHome/manage/addFreshmanRecommend.jhtml",
				type : "post",
				dataType : "json",
				data : data,
				success : function(result) {
					if (result.success) {
						showSmReslutWindow(result.success, result.msg);
						setTimeout(function() {
							pageInit();
						}, 1000);
					}
				}
			});
		
			$('#editFreshmanModal').modal('hide');
	});

  
  /**
	 * 删除首页新人推荐
	 */
   function confirmDeleteFreshmanRecommend(id){
  	 showSmConfirmWindow(function (){
  		 $.ajax({
  			 url : "livePageHome/manage/deleteFreshmanRecommendById.jhtml",
  			 type : "post",
  			 dataType : "json",
  			 data:'id=' + id,
  			 success : function(result) {
  				 if (result.success) {
  					 showSmReslutWindow(result.success, result.msg);
  					 setTimeout(function() {
  						 pageInit();
  					 }, 1000);
  				 } else {
  					 window.messager.warning(result.msg);
  				 }
  			 }
  		 });
  	 },"确定要删除吗？");
   }
   
   function editFreshmanRecommendSort(id, sort){
 	    modalId=id;
 	    $("#editFreshmanSortModal").find("[name=editSort_inp]").val(sort); 
 		$("#editFreshmanSortModal").modal();
 	}
   
   
   $("#editFreshmanRecommendSortSubmit").on("click",function(){
 		var sort=$("#editFreshmanSortModal").find("[name=editSort_inp]").val();
 		if(sort<0){
 			showWarningWindow("warning", "请正确填写排序!", 9999);
 			return;
 		}
		var reg = /^\d+$/;
		if(!reg.test(sort)){
			submitDataError($("#editFreshmanSortModal").find("[name=editSort_inp]"), "请输入整数排序数值!");
			return false;
		} 		
 		var url="livePageHome/manage/updateFreshmanRecommendSort.jhtml";
 		$.ajax({
 			 url : url,
 			 type : "post",
 			 dataType : "json",
 			 async: false,
 			 data:{"id": modalId, "homeSort":sort},
 			 success : function(data) {
 				 if (data.success) {
 					 $('#editFreshmanSortModal').modal('hide');
  					 setTimeout(function() {
  						 pageInit();
  					 }, 1000);
 				 }
 			 }
 		});
 	});
   
   /** *************************缤纷娱乐************************** */
   
   /**
    * 热门回放
    * @param data
    * @param obj
    */
   function entertainmentHotPlaybackSuccess(data, obj) {
   	var formId = "entertainmentHotPlaybackSearchForm", title = "回放推荐列表", subtitle = "个回放";
   	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
   			+ title
   			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
   			+ data.total
   			+ '】' + subtitle + '&nbsp;</font></caption>';
   	var callbackParam = "isBackButton=true&callbackParam="
   			+ getFormParam($("#" + formId).serialize());

   	obj.find('div').eq(0).scrollTablel({
   		identifier : "id",
   		callbackParam : callbackParam,
   		data : data.content,
   		caption : captionInfo,
   		// checkable : checkable,
   		// 操作列
   		handleCols : {
   			title : "操作",// 标题
   			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
   			width : 250,// 宽度
   			// 当前列的中元素
   			cols : [ 
   				 {
   					title : "删除",// 标题
   					linkInfoName : "href",
   					linkInfo : {
   						href : "livePageHome/manage/delete.jhtml",// url
   						param : ["id"],// 参数
   						permission : "delete"// 列权限
   					},
   					customMethod : function(value, data){
   			            var value = "<a href='javascript:confirmDeleteHotPlayback("+data.id+")'>" + "删除" + "</a>";
   			            return value;
   					}
   				 },
   				 {
   					title : "修改排序",// 标题
   					linkInfoName : "modal",
   					linkInfo : {
   						modal : {
   							url : "livePageHome/manage/update/init.jhtml",
   							position : "",
   							width : "auto",
   							title : "修改排序"
   						},
   						param : [ "id" ],
   						permission : "update"
   					},
   					customMethod : function(value, data){
   			            var value = "<a href='javascript:editHotPlaybackSort("+data.id+", "+data.homeSort+")'>" + "修改排序" + "</a>";
   			            return value;
   					}
   				}
   			]
   		},
   		cols : [{
   			title : "视频名称",
   			name : "zhiboTitle",
   			type : "string",
   			width : 180
   		},{
   			title : "主播",
   			name : "nname",
   			type : "string",
   			width : 150
   		},{
   			title : "商家",
   			name : "sellername",
   			type : "string",
   			width : 150
   		},{
   			title : "推荐排序",
   			name : "homeSort",
   			type : "string",
   			width : 150
   		} ]
   	  }, permissions);
   }  
   
   /**
    * 缤纷娱乐 抢先报名
    * 
    * @param data
    * @param obj
    */
   function entertainmentSuccess(data, obj) {
   	var formId = "shareForm", title = "主播专区列表", subtitle = "个记录";
   	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
   			+ title
   			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
   			+ data.total
   			+ '】' + subtitle + '&nbsp;</font></caption>';
   	var callbackParam = "isBackButton=true&callbackParam="
   			+ getFormParam($("#" + formId).serialize());

   	obj.find('div').eq(0).scrollTablel({
   		identifier : "id",
   		callbackParam : callbackParam,
   		data : data.content,
   		caption : captionInfo,
   		// checkable : checkable,
   		// 操作列
   		handleCols : {
   			title : "操作",// 标题
   			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
   			width : 250,// 宽度
   			// 当前列的中元素
   			cols : [ 
   				 {
   					title : "删除",// 标题
   					linkInfoName : "href",
   					linkInfo : {
   						href : "livePageHome/manage/delete.jhtml",// url
   						param : ["id"],// 参数
   						permission : "delete"// 列权限
   					},
   					customMethod : function(value, data){
   			            var value = "<a href='javascript:confirmDeleteEntertainment("+data.id+")'>" + "删除" + "</a>";
   			            return value;
   					}
   				 },
   				 {
   					title : "修改排序",// 标题
   					linkInfoName : "modal",
   					linkInfo : {
   						modal : {
   							url : "livePageHome/manage/update/init.jhtml",
   							position : "",
   							width : "auto",
   							title : "修改排序"
   						},
   						param : [ "id" ],
   						permission : "update"
   					},
   					customMethod : function(value, data){
   			            var value = "<a href='javascript:editEntertainmentSort("+data.id+", "+data.sort+")'>" + "修改排序" + "</a>";
   			            return value;
   					}
   				}
   			]
   		},
   		cols : [ {
   			title : "标题",
   			name : "title",
   			type : "string",
   			width : 180
   		},{
   			title : "活动日期",
   			name : "activityTime",
   			type : "string",
   			width : 150
   		},{
   			title : "图片",
   			name : "imageUrl",
   			type : "string",
   			width : 150,
   			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
   		}, {
   			title : "排序",
   			name : "sort",
   			type : "string",
   			width : 150
   		}]
   	  }, permissions);
   }   
   
   /**
    * 缤纷娱乐 节目频道
    * 
    * @param data
    * @param obj
    */
   function entertainmentProjectSuccess(data, obj) {
   	var formId = "shareForm", title = "栏目专区列表", subtitle = "个记录";
   	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
   			+ title
   			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
   			+ data.total
   			+ '】' + subtitle + '&nbsp;</font></caption>';
   	var callbackParam = "isBackButton=true&callbackParam="
   			+ getFormParam($("#" + formId).serialize());

   	obj.find('div').eq(0).scrollTablel({
   		identifier : "id",
   		callbackParam : callbackParam,
   		data : data.content,
   		caption : captionInfo,
   		// checkable : checkable,
   		// 操作列
   		handleCols : {
   			title : "操作",// 标题
   			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
   			width : 250,// 宽度
   			// 当前列的中元素
   			cols : [ 
   				 {
   					title : "删除",// 标题
   					linkInfoName : "href",
   					linkInfo : {
   						href : "livePageHome/manage/delete.jhtml",// url
   						param : ["id"],// 参数
   						permission : "delete"// 列权限
   					},
   					customMethod : function(value, data){
   			            var value = "<a href='javascript:confirmDeleteEntertainment("+data.id+")'>" + "删除" + "</a>";
   			            return value;
   					}
   				 },
   				 {
   					title : "修改排序",// 标题
   					linkInfoName : "modal",
   					linkInfo : {
   						modal : {
   							url : "livePageHome/manage/update/init.jhtml",
   							position : "",
   							width : "auto",
   							title : "修改排序"
   						},
   						param : [ "id" ],
   						permission : "update"
   					},
   					customMethod : function(value, data){
   			            var value = "<a href='javascript:editEntertainmentSort("+data.id+", "+data.sort+")'>" + "修改排序" + "</a>";
   			            return value;
   					}
   				}
   			]
   		},
   		cols : [ {
   			title : "标题",
   			name : "title",
   			type : "string",
   			width : 180
   		},{
   			title : "图片",
   			name : "imageUrl",
   			type : "string",
   			width : 150,
   			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
   		},{
   			title : "排序",
   			name : "sort",
   			type : "string",
   			width : 150
   		} ]
   	  }, permissions);
   } 
   
   /* 缤纷娱乐 */
   function editEntertainment(type) {
	 	if (type == 1) { //抢先报名
		   $("#numberInfo").hide();
		   $("#subTitleInfo").hide();
		   $("#activityTimeInfo").show();
		  
		   $("#editEntertainmentModal").find("div[class='modal-body example']").css("height", "420px");//通过设置CSS属性来设置元素的高
	 	}else{
		   $("#activityTimeInfo").hide();
		   $("#numberInfo").show();
		   $("#subTitleInfo").show();
		   
		   $("#editEntertainmentModal").find("div[class='modal-body example']").css("height", "480px");//通过设置CSS属性来设置元素的高
	 	}
	 	//初台化控件显示
		$("#editEntertainmentModal :input").each(function() {
		     $(this).val("");
	    }); 
		$("#imageUrl").val("");
    	// 图片
		$("#activityImg").uploadImg({
			urlId : "imageUrl",
			showImg : $('#imageUrl').val()
		});
	   
 		$("#editEntertainmentModal").find("[name=type]").val(type);
 		$("#editEntertainmentModal").find("[name=moduleType]").val("2");  //模块类型 1-新人推荐, 2-缤纷娱乐, 3-美味撩味
 		
 		$('#editEntertainmentModal').modal('show');  //'show'
   }
  
   /*确认保存数据*/
   $("#editEntertainmentSubmit").on("click",function(){
 		var type = $("#editEntertainmentModal").find("[name=type]").val();  //缤纷娱乐类型 1.抢先报名 2.节目频道
 		var title= $("#editEntertainmentModal").find("[name=title]").val();
  		if(!title){
  			showWarningWindow("warning", "请填写标题!", 9999);
  			return;
  		}
  		var imageUrl = $("#imageUrl").val();  //图片
  		var homeSort=$("#editEntertainmentModal").find("[name=homeSort]").val();//排序
  		if(homeSort == "" || homeSort<0){
  			showWarningWindow("warning", "请正确填写排序!", 9999);
  			return;
  		}
		var reg = /^\d+$/;
		if(!reg.test(homeSort)){
			submitDataError($("#editEntertainmentModal").find("[name=homeSort]"), "请输入整数排序数值!");
			return false;
		}
  		
  		var subTitle = $("#subTitle").val(); //副标题
  		var url = $("#url").val(); //链接
  		var number = $("#number").val();  //期数
  		var activityTime = $("input[name='activityTime']").val(); //活动时间
  		
  		var moduleType = $("#editEntertainmentModal").find("[name=moduleType]").val(); //模块类型 1-新人推荐, 2-缤纷娱乐, 3-美味撩味
  		
  		var data={
  				"title": title,
  				"type": type,
  				"url": url,
  				"imageUrl": imageUrl,
  				"moduleType": moduleType,
  				
  				"activityTime": activityTime,  	
  				"number": number,
 				"subTitle": subTitle,
  				"sort": homeSort
  		};

  		$.ajax({
  			 url : "livePageHome/manage/addEntertainment.jhtml",
  			 type : "post",
  			 dataType : "json",
  			 data:data,
  			 success : function(result) {
  				 if(result.success){
   					 showSmReslutWindow(result.success, result.msg);
   					 setTimeout(function() {
   						pageInit();
   					 }, 2000);
  				 }
  			 }
  		});
  	    
  		$('#editEntertainmentModal').modal('hide');
  	});

   
   /**
 	 * 删除缤纷娱乐
 	 */
    function confirmDeleteEntertainment(id) {
		showSmConfirmWindow(function() {
			$.ajax({
				url : "livePageHome/manage/deleteEntertainmentById.jhtml",
				type : "post",
				dataType : "json",
				data : 'id=' + id,
				success : function(result) {
					if (result.success) {
						showSmReslutWindow(result.success, result.msg);
						setTimeout(function() {
							pageInit();
						}, 2000);
					} else {
						window.messager.warning(result.msg);
					}
				}
			});
		}, "确定要删除吗？");
   }
    
    /*缤纷娱乐*/
   function editEntertainmentSort(id, sort){
  	    modalId=id;
  	    $("#editEntertainmentSortModal").find("[name=editSort_inp]").val(sort);
  	    
  		$('#editEntertainmentSortModal').modal();
   }
    
   /*确认修改序号*/
   $("#editEntertainmentSortSubmit").on("click",function(){
  		var homeSort=$("#editEntertainmentSortModal").find("[name=editSort_inp]").val();//排序
  		if(homeSort<0){
  			showWarningWindow("warning", "请正确填写排序!", 9999);
  			return;
  		}
		var reg = /^\d+$/;
		if(!reg.test(homeSort)){
			submitDataError($("#editEntertainmentSortModal").find("[name=editSort_inp]"), "请输入整数排序数值!");
			return false;
		}
  		var url="livePageHome/manage/updateEntertainmentSort.jhtml";
  		$.ajax({
  			 url : url,
  			 type : "post",
  			 dataType : "json",
  			 async: false,
  			 data:{"id": modalId, "sort":homeSort},
  			 success : function(data) {
  				 if (data.success) {
  					 $('#editEntertainmentSortModal').modal('hide');
					 setTimeout(function() {
						pageInit();
					 }, 2000);  					 
  				 }
  			 }
  		});
  	});
   
   /***************************美味撩味***************************/
   
   function deliciousSuccess(data, obj) {
	   	var formId = "shareForm", title = "美食撩味列表", subtitle = "个美食撩味";
	   	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
	   			+ title
	   			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
	   			+ data.total
	   			+ '】' + subtitle + '&nbsp;</font></caption>';
	   	var callbackParam = "isBackButton=true&callbackParam="
	   			+ getFormParam($("#" + formId).serialize());

	   	obj.find('div').eq(0).scrollTablel({
	   		identifier : "id",
	   		callbackParam : callbackParam,
	   		data : data.content,
	   		caption : captionInfo,
	   		// checkable : checkable,
	   		// 操作列
	   		handleCols : {
	   			title : "操作",// 标题
	   			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
	   			width : 250,// 宽度
	   			// 当前列的中元素
	   			cols : [ 
	   				 {
	   					title : "删除",// 标题
	   					linkInfoName : "href",
	   					linkInfo : {
	   						href : "livePageHome/manage/delete.jhtml",// url
	   						param : ["id"],// 参数
	   						permission : "delete"// 列权限
	   					},
	   					customMethod : function(value, data){
	   			            var value = "<a href='javascript:confirmDeleteDelicious("+data.id+")'>" + "删除" + "</a>";
	   			            return value;
	   					}
	   				 },
	   				 {
	   					title : "修改排序",// 标题
	   					linkInfoName : "modal",
	   					linkInfo : {
	   						modal : {
	   							url : "livePageHome/manage/update/init.jhtml",
	   							position : "",
	   							width : "auto",
	   							title : "修改排序"
	   						},
	   						param : [ "id" ],
	   						permission : "update"
	   					},
	   					customMethod : function(value, data){
	   			            var value = "<a href='javascript:editDeliciousSort("+data.id+", "+data.sort+")'>" + "修改排序" + "</a>";
	   			            return value;
	   					}
	   				}
	   			]
	   		},
	   		cols : [{
	   			title : "主题",
	   			name : "title",
	   			type : "string",
	   			width : 180
	   		},{
	   			title : "关联标签",
	   			name : "tagName",
	   			type : "string",
	   			width : 150
	   		},{
	   			title : "推荐排序",
	   			name : "sort",
	   			type : "string",
	   			width : 150
	   		} ]
	   	  }, permissions);
	   }   
   
   
   /**
    * 热门回放
    * @param data
    * @param obj
    */
	function deliciousHotPlaybackSuccess(data, obj) {
		var formId = "deliciousHotPlaybackSearchForm", title = "明星活动列表", subtitle = "个记录";
		var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
				+ title
				+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
				+ data.total
				+ '】' + subtitle + '&nbsp;</font></caption>';
		var callbackParam = "isBackButton=true&callbackParam="
				+ getFormParam($("#" + formId).serialize());
		
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			// checkable : checkable,
			// 操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
				width : 250,// 宽度
				// 当前列的中元素
				cols : [ 
					 {
						title : "删除",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "livePageHome/manage/delete.jhtml",// url
							param : ["id"],// 参数
							permission : "delete"// 列权限
						},
						customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDeleteHotPlayback("+data.id+")'>" + "删除" + "</a>";
				            return value;
						}
					 },
					 {
						title : "修改排序",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "livePageHome/manage/update/init.jhtml",
								position : "",
								width : "auto",
								title : "修改排序"
							},
							param : [ "id" ],
							permission : "update"
						},
						customMethod : function(value, data){
				            var value = "<a href='javascript:editHotPlaybackSort("+data.id+", "+data.homeSort+")'>" + "修改排序" + "</a>";
				            return value;
						}
					}
				]
			},
			cols : [ {
				title : "视频名称",
				name : "zhiboTitle",
				type : "string",
				width : 180
			},{
				title : "主播",
				name : "nname",
				type : "string",
				width : 150
			},{
				title : "商家",
				name : "sellername",
				type : "string",
				width : 150
			},{
				title : "推荐排序",
				name : "homeSort",
				type : "string",
				width : 150
			} ]
		  }, permissions);
	   }  
   
   
   /**
    * 初始化直播标签分类
    */
   function liveRecordClassifyIdInit(){
	   	$("#classifyId").chosenObject({
	   		hideValue : "id",
	   		showValue : "classifyName",
	   		url : "businessman/classify/liveRecordClassifyInit.jhtml",
	   		isChosen:true,//是否支持模糊查询
	   		isCode:true,//是否显示编号
	   		isHistorical:false,//是否使用历史已加载数据
	   		width:"40%"
	   	});
   }

   //选择分类后，初始化标签
   $('body').on("click",'#classifyId_chosen .chosen-results li',function(){
   	  //初始化联动标签
   	  liveRecordTagInit();
   	  // 当原始select中的选项发生变化时通知chosen更新选项列表
   	  $('#tagId').trigger('chosen:updated');
   });



   /**
    * 初始化直播标签分类
    */
   function initAddLiveRecordClassifyIdInit(index){
   	   var currentId="#classifyId"+index;
   	   $(currentId).chosenObject({
	   		hideValue : "id",
	   		showValue : "classifyName",
	   		url : "businessman/classify/liveRecordClassifyInit.jhtml",
	   		isChosen:true,//是否支持模糊查询
	   		isCode:true,//是否显示编号
	   		isHistorical:false,//是否使用历史已加载数据
	   		width:"40%"
    	});
   }

   //选择分类后，初始化标签
   function bindClassifyChange(index){
	   	$('body').on("click",'#classifyId'+index+'_chosen .chosen-results li',function(){
	   		//初始化联动标签
	   		addliveRecordTagInit(index);
	   		// 当原始select中的选项发生变化时通知chosen更新选项列表
	   		$('#tagId'+index).trigger('chosen:updated');
	   	});
   }

   /**
    * 初始化直播标签
    */
   function liveRecordTagInit(){
   	  var classifyId=$("#classifyId").val();
      $("#tagId").chosenObject({
	   		hideValue : "id",
	   		showValue : "tagName",
	   		url : "businessman/classify/liveRecordTagInit.jhtml",
	   		isChosen:true,//是否支持模糊查询
	   		isCode:true,//是否显示编号
	   		filterVal:classifyId,////过滤的值 (classifyId=1)
	   		isHistorical:false,//是否使用历史已加载数据
	   		width:"40%"
     });
   }

   /**
    * 初始化直播标签
    */
   function addliveRecordTagInit(index){
	   	var classifyId=$("#classifyId"+index).val();
	   	$("#tagId"+index).chosenObject({
	   		hideValue : "id",
	   		showValue : "tagName",
	   		url : "businessman/classify/liveRecordTagInit.jhtml",
	   		isChosen:true,//是否支持模糊查询
	   		isCode:true,//是否显示编号
	   		filterVal:classifyId,////过滤的值 (classifyId=1)
	   		isHistorical:false,//是否使用历史已加载数据
	   		width:"40%"
	   	});
   }
   
   function editDelicious(rtype) {	   
	    //模块类型 1-新人推荐,2-美味撩味
	    if (rtype == 1)
	    	$("#editDeliciousModal").find("[name=title]").val("新人推荐");  
	    else
	    	$("#editDeliciousModal").find("[name=title]").val("美味撩味");  
	   
		$("#editDeliciousModal").find("[name=rtype]").val(rtype);  
		$("#editDeliciousModal").find("[name=moduleType]").val("3");  //模块类型 1-新人推荐, 2-缤纷娱乐, 3-美味撩味
		$('#editDeliciousModal').modal('show');  //'show'
  }
   
   
   /*保存数据开始*/
   $("#editDeliciousSubmit").on("click",function(){
		var title=$("#editDeliciousModal").find("[name=title]").val();//主题
  		if(!title){
  			showWarningWindow("warning", "请填写主题名称!", 9999);
  			return;
  		}
  		var rtype= $("#editDeliciousModal").find("[name=rtype]").val();
  		
  		var classifyId = $("#classifyId").val();
  		if(!classifyId){
  			showWarningWindow("warning", "请选择直播标签分类!", 9999);
  			return;
  		}
  		var tagId= $("#tagId").val();
  		if(!tagId){
  			showWarningWindow("warning", "请选择直播分类标签!", 9999);
  			return;
  		}
  		var moduleType = $("#editDeliciousModal").find("[name=moduleType]").val();
  		var homeSort=$("#editDeliciousModal").find("[name=homeSort]").val();//排序
  		if(homeSort == "" ||  homeSort<0){
  			showWarningWindow("warning", "请正确填写排序!", 9999);
  			return;
  		}
		var reg = /^\d+$/;
		if(!reg.test(homeSort)){
			submitDataError($("#editDeliciousModal").find("[name=homeSort]"),"请输入整数排序数值!");
			return false;
		}
  		var data={
			"title":title,
			"classifyId": classifyId,
			"moduleType": moduleType,
			"tagId": tagId,
			"rtype": rtype,  //模块类型 1-新人推荐,2-美味撩味
			"sort":homeSort
  		};

  		$.ajax({
  			 url : "livePageHome/manage/addDelicious.jhtml",
  			 type : "post",
  			 dataType : "json",
  			 data:data,
  			 success : function(result) {
  				 if(result.success){
   					 showSmReslutWindow(result.success, result.msg);
   					 setTimeout(function() {
   						pageInit();
   					 }, 2000);

  				 }
  			 }
  		});
  		$('#editDeliciousModal').modal('hide');
  	});

   
   /**
 	 * 删除美味撩味&新人推荐
 	 */
    function confirmDeleteDelicious(id) {
		showSmConfirmWindow(function() {
			$.ajax({
				url : "livePageHome/manage/deleteDeliciousById.jhtml",
				type : "post",
				dataType : "json",
				data : 'id=' + id,
				success : function(result) {
					if (result.success) {
						showSmReslutWindow(result.success, result.msg);
						setTimeout(function() {
							pageInit();
						}, 2000);
					} else {
						window.messager.warning(result.msg);
					}
				}
			});
		}, "确定要删除吗？");
   }
    
    /*美味撩味&新人推荐*/
   function editDeliciousSort(id, sort){
  	    modalId=id;
  		$("#editDeliciousSortModal").find("[name=editSort_inp]").val(sort);
  		$('#editDeliciousSortModal').modal('show');
   }
    
   /*保存美味撩味&新人推荐排序数据*/
   $("#editDeliciousSortSubmit").on("click",function(){
  		var sort= $("#editDeliciousSortModal").find("[name=editSort_inp]").val();
  		if(sort<0){
  			showWarningWindow("warning", "请正确填写排序!", 9999);
  			return;
  		}
		var reg = /^\d+$/;
		if(!reg.test(sort)){
			submitDataError($("#editDeliciousSortModal").find("[name=editSort_inp]"), "请输入整数排序数值!");
			return false;
		}
  		var url="livePageHome/manage/updateDeliciousSort.jhtml";
  		$.ajax({
  			 url : url,
  			 type : "post",
  			 dataType : "json",
  			 async: false,
  			 data:{"id": modalId, "sort":sort},
  			 success : function(data) {
  				 if (data.success) {
  					 $('#editDeliciousSortModal').modal('hide');
  					 //recommendSpecialList.reload();
					 setTimeout(function() {
						pageInit();
					 }, 2000);
  				 }
  			 }
  		});
  	});   
   
   
   /******************** 热门回放 开始***************************/

	//初始化主播房间
	function initHotPlaybackRecordId(rtype, moduleType){
	//	debugger;
		var hotPlaybackcs= $("#hotPlaybackcs").html('');
		hotPlaybackcs.html('<select class="form-control" name="hotPlayback_choose" style="width: 60%; float: left;" ></select>');
		var url="livePageHome/manage/initFreshmanRecordId.jhtml";
		if(rtype){  
			//直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播
			var zhiboType = getZhiboType(rtype);
			url=url+"?zhiboType="+zhiboType;
			url+="&rtype="+rtype +"&moduleType=" + moduleType;
		}
		$("#editHotPlaybackModal").find("[name=hotPlayback_choose]").chosenObject({
			hideValue : "id",
			showValue : "showValue",//输入查询值
			showType:"multiple",//选项显示形式
			showParams:["nname","zhiboTitle"],//showType为multiple时生效,phone|nickname
			url : url,
			isChosen:true,//是否支持模糊查询
			isCode:true,//是否显示编号
			isHistorical:false,//是否使用历史已加载数据
			width:"100%"
		});
	}
   
   //type(模块类型): 1-新人推荐, 2-缤纷娱乐, 3-美味撩味
   function editHotPlayback(moduleType) {	   
	    //直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播
	    initHotPlaybackRecordId(5, moduleType); 
	    //推荐类型 1-好看推荐, 2-直播推荐, 3-精选预告, 4-精彩视频推荐, 5-热门回放
		$("#editHotPlaybackModal").find("[name=rtype]").val(5); 
		
		// 模块类型 1-新人推荐, 2-缤纷娱乐, 3-美味撩味
		$("#editHotPlaybackModal").find("[name=moduleType]").val(moduleType);
		$('#editHotPlaybackModal').modal('show');  //'show'
   }
   
   $("#editHotPlaybackSubmit").on("click", function() {
		var rid = $("#editHotPlaybackModal").find("[name=hotPlayback_choose]").val();
		if (!rid) {
			showWarningWindow("warning", "请选择直播!", 9999);
			return;
		}
		var homeSort = $("#editHotPlaybackModal").find("[name=homeSort]").val();
		if (homeSort == "" || homeSort < 0) {
			showWarningWindow("warning", "请正确填写排序!", 9999);
			return;
		}
		var reg = /^\d+$/;
		if(!reg.test(homeSort)){
			submitDataError($("#editHotPlaybackModal").find("[name=homeSort]"), "请输入整数排序数值!");
			return false;
		}
		var moduleType = $("#editHotPlaybackModal").find("[name=moduleType]").val();
		var rtype = $("#editHotPlaybackModal").find("[name=rtype]").val();
		var data = {
			"rid" : rid,
			"rtype" : rtype,
			"moduleType" : moduleType,
			"homeSort" : homeSort
		};
		$.ajax({
			url : "livePageHome/manage/addFreshmanRecommend.jhtml",
			type : "post",
			dataType : "json",
			data : data,
			success : function(result) {
				if (result.success) {
					showSmReslutWindow(result.success, result.msg);
					setTimeout(function() {
						pageInit();
					}, 1000);
				}
			}
		});
		$('#editHotPlaybackModal').modal('hide');
    });


	/**
	* 删除热门回放推荐
	*/
	function confirmDeleteHotPlayback(id){
		 showSmConfirmWindow(function (){
			 $.ajax({
				 url : "livePageHome/manage/deleteFreshmanRecommendById.jhtml",
				 type : "post",
				 dataType : "json",
				 data:'id=' + id,
				 success : function(result) {
					 if (result.success) {
						 showSmReslutWindow(result.success, result.msg);
						 setTimeout(function() {
							 pageInit();
						 }, 1000);
					 } else {
						 window.messager.warning(result.msg);
					 }
				 }
			 });
		 },"确定要删除吗？");
	}
	
	function editHotPlaybackSort(id, sort){
	    modalId=id;
	    $("#editHotPlaybackSortModal").find("[name=editSort_inp]").val(sort); 
		$("#editHotPlaybackSortModal").modal();
	}
	
	
	$("#editHotPlaybackSortSubmit").on("click",function(){
		var sort=$("#editHotPlaybackSortModal").find("[name=editSort_inp]").val();
		if(sort<0){
			showWarningWindow("warning", "请正确填写排序!", 9999);
			return;
		}
		var reg = /^\d+$/;
		if(!reg.test(sort)){
			submitDataError($("#editHotPlaybackSortModal").find("[name=editSort_inp]"), "请输入整数排序数值!");
			return false;
		}
		var url="livePageHome/manage/updateFreshmanRecommendSort.jhtml";
		$.ajax({
			 url : url,
			 type : "post",
			 dataType : "json",
			 async: false,
			 data:{"id": modalId, "homeSort":sort},
			 success : function(data) {
				 if (data.success) {
					 $('#editHotPlaybackSortModal').modal('hide');
						 setTimeout(function() {
							 pageInit();
						 }, 1000);
				 }
			 }
		});
	});
	
    /******************** 热门回放 结束***************************/
   