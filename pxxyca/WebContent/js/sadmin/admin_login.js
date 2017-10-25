/**
 * 在绑定新click方法前对元素所绑定的click方法解绑
 * $("#obj").unbind("click").click(function(e){ ***** });
 */

$(function() {
	$("#login").click(function() {
		if (!$('#accound').val() || !$('#password').val()) {
			toastr.warning("含有空信息");
			return;
		}
		$.ajax({
			url : "Sadmin/sadmin_Admin_Adminlogin",
			type : "post",
			timeout : 3000,
			data : {
				adminAccound : $('#accound').val(),
				adminPassword : $('#password').val(),
			},
			dataType : "json",
			success : function(data) {
				if (data.result == 'passerror') {
					toastr.warning('密码错误');
					return;
				} else if (data.result == 'error') {
					toastr.warning('帐号不存在');
					return;
				} else {
					/*$.ajax({
						url : "Sadmin/sadmin_Role_sessionPutRole",
						type : "post",
						timeout : 3000,
						data : {
							roleId : data.result,
						},
						dataType : "json",
						success : function(data) {},
						error : function() {}
					});*/
					//session存管理员角色数据
					location.href = "Sadmin/sadmin_Role_sessionPutRole?roleId=" + data.result;
				}

			},
			error : function() {}
		});
	});
});