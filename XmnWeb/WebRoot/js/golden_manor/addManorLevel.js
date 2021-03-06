var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
var liveRecordSelected;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			no:{
				required: true,
				digits:true,
				min:0,
			},
			name:{
				required: true,
			},
			roses : {
				required : true,
				digits:true,
				min:0,
			},
			orchids:{
				required:true,
				digits:true,
				min:0,
			},
			sunflowers:{
				required:true,
				digits:true,
				min:0,
			},
			dailyNectar:{
				required:true,
				digits:true,
				min:0,
			}
		},
		messages:{
		}
	}, save);
	
	// 等级图片
	$("#levelLogoImg").uploadImg({
		urlId : "levelLogo",
		showImg : $('#levelLogo').val()
	});
	
	// 等级图片
	$("#levelPicImg").uploadImg({
		urlId : "levelPic",
		showImg : $('#levelPic').val()
	});
});




/**
 * 保存信息
 */
function save() {
	var data = jsonFromt($('#editFrom').serializeArray());
	if(valiImgData("#editFrom",data)){
		return false;
	}
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").val() == "" ? true : false;
	if (isAdd) {// 添加操作
		url = "manor/level/add" + suffix;
	} else {// 修改操作
		url = "manor/level/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
		
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
				//提交后禁用提交按钮，防止重复提交表单
				$('#submitBtn').attr("disabled","disabled");
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					if (isAdd) {
						recordList.reset();
					} else {
						recordList.reload();
					}
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


