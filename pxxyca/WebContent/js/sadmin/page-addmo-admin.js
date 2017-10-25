/**
 * 在绑定新click方法前对元素所绑定的click方法解绑 $("#obj").unbind("click").click(function(e){
 * ***** });
 */

$(function() {

	var ismouseover = true;

	$('.create')
			.unbind("click")
			.click(
					function() {
						// 清楚原先含有的table
						if ($('.div-main').children("table").length > 0) {
							$('.div-main').children("table:last").remove();
						}

						var str = '<table style="width:700px;margin:0 auto;text-align:center;" class="table table-striped table-bordered table-hover"><thead><tr><th>';
						str += '管理员帐号'; // --
						str += '</th><td>';
						str += '<input class="form-control adminAccound">'; // --
						str += '</td></tr><tr><th>';
						str += '管理员密码' // --
						str += '</th><td>'
						str += '<input class="form-control newpassword">' // --
						str += '</td></tr><tr><th>'
						str += '管理员角色'; // --
						str += '</th><td class="select-data">'
						str += '</td></tr></thead></table>';

						var button = document.createElement("button");
						button.className = 'btn btn-primary sure-create';
						button.innerHTML = "确认创建";
						button.style.float = "right";
						button.style.margin = "20px calc(( 100% - 700px)/2) 0 0";

						$('.div-main').children("button").remove();

						$('.div-main').append(button);

						$('.div-main').append(str);

						// 添加角色下拉,并设置确定按钮事件
						getAllRole_select('', '', '', 'create');

						$(this).attr("disabled", true);
						$('.modi').attr("disabled", false);
					});

	$('.modi')
			.unbind("click")
			.click(
					function() {
						$
								.confirm({
									icon : 'fa fa-check',
									title : '',
									buttons : {
										sure : {
											text : '确认',
											btnClass : 'btn-red',
											action : function() {
												// 输入为空时候
												if (!$('.domi-acc').val()) {
													toastr.warning("输入为空");
													return;
												}
												// 异步获取管理员信息
												$
														.ajax({
															url : "Sadmin/sadmin_Admin_getAdminByAccount",
															type : "post",
															timeout : 3000,
															data : {
																adminAccound : $(
																		'.domi-acc')
																		.val(),
															},
															dataType : "json",
															success : function(
																	data) {
																if (data.result == 'error') {
																	toastr
																			.warning("未查询到又该帐号的管理员。");
																	return;
																}
																// 清楚原先含有的table
																$('.div-main')
																		.children(
																				"table:last")
																		.remove();

																var str = '<table  style="width:700px;margin:0 auto;text-align:center;" class="table table-striped table-bordered table-hover"><thead><tr><th>';
																str += '管理员帐号'; // --
																str += '</th><td>';
																str += '<input class="form-control" value='
																		+ data.adminAccount
																		+ '>'; // --
																str += '</td></tr>';

																str += '<tr><th>';
																str += '管理员原密码'; // --
																str += '</th><td>';
																str += '<input class="form-control oldpassword">'; // --
																str += '</td></tr>';

																str += '<tr><th>';
																str += '管理员新密码'; // --
																str += '</th><td>';
																str += '<input class="form-control newpassword">'; // --
																str += '</td></tr>';
																str += '<tr><th>';
																str += '确认密码'; // --
																str += '</th><td>';
																str += '<input class="form-control surepassword">'; // --
																str += '</td></tr>';

																str += '<tr><th>';
																str += '管理员角色'; // --
																str += '</th><td class="select-data">';
																str += '</td></tr></thead></table>';
																// --

																// var but =
																// '<button
																// style="float:right;"
																// class="">确认修改</button>';
																var button = document
																		.createElement("button");
																button.className = 'btn btn-primary sure-modi';
																button.innerHTML = "确认修改";
																button.style.float = "right";
																button.style.margin = "20px calc(( 100% - 700px)/2) 0 0";

																$('.div-main')
																		.children(
																				"button")
																		.remove();

																$('.div-main')
																		.append(
																				str);
																$('.div-main')
																		.append(
																				button);
																// 添加角色下拉,并设置修改按钮事件
																getAllRole_select(
																		data.jsjSadminAdminId,
																		data.adminRole,
																		data.adminPassword,
																		'modi');
																// 修改不可点击
																$(this)
																		.attr(
																				"disabled",
																				true);
																// 创建变为可点击
																$('.create')
																		.attr(
																				"disabled",
																				false);
															},
															error : function() {
																toastr
																		.error("服务器繁忙");
															}
														});
											}
										},
										close : {
											keys : [ 'Esc' ],
											text : '取消'
										}
									},
									content : function() {
										return '<div style="height:30px;">输入管理员帐号：</div><input class="form-control domi-acc">';
									}
								});
					});

	/**
	 * 创建角色select
	 */
	function getAllRole_select(id, role, pass, buttype) {
		$.ajax({
			url : "Sadmin/sadmin_Role_getAllRole",
			type : "post",
			timeout : 3000,
			data : {},
			dataType : "json",
			success : function(data) {
				var str = '';
				str += '<select id="secletRes" data-live-search="true">'; // selectpicker
				for (i = 0; i < data.length; i++) {
					str += '<option value="' + data[i].jsjSadminRoleId
							+ '" data-tokens="' + data[i].roleName + '"';
					if (data[i].jsjSadminRoleId == role) {
						str += ' selected="selected" ';
					}
					str += '>' + data[i].roleName + '</option>';
				}
				str += '</select>';
				$('.select-data').html(str);

				/*
				 * $('#secletRes').children().mousemove(function(e) { if
				 * (ismouseover) { $('.option-role-info').remove(); var top =
				 * parseInt($(this).offset().top) - 120; var left =
				 * parseInt($(this).offset().left) + 114;
				 * tooltip_Content(this.value, top, left); ismouseover = false; }
				 * });
				 * 
				 * $('#secletRes').children().mouseout(function(e) {
				 * $('.option-role-info').remove(); ismouseover = true; })
				 */
				$('select').selectpicker('refresh');
			},
			error : function() {
			}
		});

		// 设置修改按钮事件
		if (buttype == 'modi') {
			var objEvt = ($._data(($('.sure-modi'))[0], "events"));
			if (!(objEvt && objEvt["click"])) {
				$('.sure-modi')
						.click(
								function() { // 后台密码比对
									$
											.ajax({
												url : "Sadmin/sadmin_Admin_getMd5Pass",
												type : "post",
												timeout : 3000,
												data : {
													adminPassword : $(
															'.oldpassword')
															.val(),
												},
												dataType : "json",
												success : function(data) {
													if (data.pass != pass) {
														toastr.warning('原密码不对');
														return;
													}
													if ($('.newpassword').val() != $(
															'.surepassword')
															.val()) {
														toastr.warning('密码不一致');
														return;
													}
													$
															.ajax({
																url : "Sadmin/sadmin_Admin_modiAdmin",
																type : "post",
																timeout : 3000,
																data : {
																	adminId : id,
																	adminPassword : $(
																			'.newpassword')
																			.val(),
																	adminRole : $(
																			'#secletRes')
																			.val(),
																},
																dataType : "json",
																success : function(
																		data) {
																	if (data.result == 'success') {
																		toastr
																				.warning('修改成功');
																		window.location = "/xxyjsjgcxy/Sadmin/sadmin_Admin_page_list_admin";
																	} else if (data.result == 'premission_none') {
																		toastr
																				.warning('无权限');
																	} else {
																		toastr
																				.warning('修改失败');
																	}

																},
																error : function() {
																}
															}); // 修改ajax结束
												},
												error : function() {
												}
											});
								});
			}
		} else if (buttype == 'create') {
			var objEvt = ($._data(($('.sure-create'))[0], "events"));
			if (!(objEvt && objEvt["click"])) {
				$('.sure-create')
						.click(
								function() {
									if ($('.adminAccound').val() == ''
											|| $('.newpassword').val() == ''
											|| $('.secletRes').val() == '') {
										toastr.warning('不能有空项!');
										return;
									}
									$
											.ajax({
												url : "Sadmin/sadmin_Admin_createAdmin",
												type : "post",
												timeout : 3000,
												data : {
													adminAccound : $(
															'.adminAccound')
															.val(),
													adminPassword : $(
															'.newpassword')
															.val(),
													adminRole : $('#secletRes')
															.val(),
												},
												dataType : "json",
												success : function(data) {
													if (data.result == 'success') {
														toastr.warning('创建成功');
														window.location = "/xxyjsjgcxy/Sadmin/sadmin_Admin_page_list_admin";
													} else if (data.result == 'premission_none') {
														toastr.warning('无权限');
													} else {
														toastr.warning('已有该帐号');
													}
												},
												error : function() {
													toastr.error("服务器繁忙");
												}
											});
								});
			}
		}
	}

	function tooltip_Content(id, top, left) {
		$
				.ajax({
					url : "Sadmin/sadmin_Role_getRoleById",
					type : "post",
					timeout : 3000,
					data : {
						roleId : id,
					},
					dataType : "json",
					success : function(data) {

						var div = document.createElement("div");
						div.setAttribute('class', 'option-role-info');

						var str = '';
						str += '<div class="popover fade right in" role="tooltip" style="top: '
								+ top
								+ 'px; left: '
								+ left
								+ 'px; display: block;"><div class="arrow" style="top: 50%;"></div><h3 class="popover-title" style="display: none;"></h3><div class="popover-content">';
						str += '<table class="table table-bordered table-hover "><thead><tr><th>新闻管理</th><th>轮播图管理</th><th>管理员管理</th><th>留言薄管理</th><th>链接管理</th><th>数据库管理</th></tr></thead><tbody><tr>'
						var i = 0;
						for ( var p in data) {
							if (i > 1 && i < 8)
								str += '<td>' + data[p] + '</td>';
							i++;
						}
						str += '</tr></tbody></table>';
						str += '</div></div>';
						div.innerHTML = str;
						$('.div-main').append(div);
					},
					error : function() {
						toastr.error("服务器繁忙");
					}
				});
	}

	$('.create').click();

});
