var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			robotName : {
				required : true
			},
			avatar : {
				required : true
			}
		},
		messages:{
			robotName:{
				required:"昵称不能为空!"
			}
		}
	}, androidSave);
	
	
	// 头像
	$("#avatarImg").uploadImg({
		urlId : "avatar",
		showImg : $('#avatar').val()
	});
	
});


/**
 * 保存机器人信息
 */
function androidSave() {
	var url;
	var suffix = ".jhtml";
	url = "liveAndroid/manage/update" + suffix;
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	if (jsonTextInit != jsonText) {// 修改数据才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
//						anchorList.reset();//重置查询条件提交查询
						androidList.reload();//重新加载(参数为查询条件，页数不变)
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	} else {
		$('#prompt').hide();
		$('#triggerModal').modal('hide');
		showSmReslutWindow(false, "没做任何修改！");
	}
}
