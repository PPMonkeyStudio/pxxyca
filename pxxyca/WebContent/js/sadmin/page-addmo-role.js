$(function() {

	// 角色表信息
	var role_data = {
		/*
		 * eng_info : [ 'role_name', 'role_premission_snews',
		 * 'role_premission_scarousel', 'role_premission_sadmin',
		 * 'role_premission_sguestbook', 'role_premission_slink',
		 * 'role_premission_sdatabase', ],
		 */
		ch_nifo : [ '角色名', '新闻管理子系统权限', '轮播图管理子系统权限', '管理员管理子系统权限',
				'留言薄管理子系统权限', '链接管理子系统权限', '数据库管理子系统权限', ]
	}

	$('.create').unbind("click").click(function() {
		// 清楚原先含有的table
		if ($('.div-main').children("table").length > 0) {
			$('.div-main').children("table:last").remove();
		}

		$('.div-main').append(createSelect('create'));

		$('select').selectpicker('refresh');

		// 设置确定按钮事件
		create_modi_clickfunc('', 'create');

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
												// 如果输入角色名称为空
												if (!$('.domi-rolename').val()) {
													toastr.warning('输入角色名称为空');
													return;
												}

												// 异步获取管理员信息
												$
														.ajax({
															url : "Sadmin/sadmin_Role_getRoleByName",
															type : "post",
															timeout : 3000,
															data : {
																rolename : $(
																		'.domi-rolename')
																		.val(),
															},
															dataType : "json",
															success : function(
																	data) {

																if (data.result == 'error') {
																	toastr.warning("未查询到有该帐号的管理员。");
																	return;
																}

																// 清楚原先含有的table
																$('.div-main')
																		.children(
																				"table:last")
																		.remove();

																var datarray = new Array();
																var i = 0;
																for ( var p in data) {
																	datarray[i] = data[p];
																	i++;
																}
																// 添加下拉元素
																$('.div-main')
																		.append(
																				createSelect('modi'));
																// 赋予各项值
																for (i = 0; i < role_data.ch_nifo.length; i++) {
																	$(
																			'#dat'
																					+ i)
																			.val(
																					datarray[i + 1]);
																}

																$('select')
																		.selectpicker(
																				'refresh');
																// 为修改按钮添加事件
																create_modi_clickfunc(
																		datarray[0],
																		'modi');
																// 修改按钮内文字
																$(".sure-modi")
																		.text(
																				"确认修改");
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
															}
														});
											}
										},
										close : {
											text : '取消'
										}
									},
									content : function() {
										return '<input placeholder="角色名称" class="form-control domi-rolename">';
									}
								});
					});

	// 创建选择框
	function createSelect(type) {
		var table = document.createElement("table");
		table.className = "table table-striped table-bordered table-hover";
		table.style.width = "700px";
		table.style.margin = "0 auto";
		table.style.textAlign = "center";

		// var thead = document.createElement("thead");
		// table.appendChild(thead);
		// 其中包含6中权限，，所以循环7次，权限数量修改时候需要进行修改
		var str = '';
		for (i = 0; i < role_data.ch_nifo.length; i++) {
			if (i == 0) {
				str += '<tr><th>';
				str += role_data.ch_nifo[i];
				str += '</th><td>';
				str += '<input class="form-control" id="dat' + i + '">';
				str += '</td></tr>';
			} else {
				str += '<tr><th>';
				str += role_data.ch_nifo[i];
				str += '</th><td>';
				str += '<select id="dat'
						+ i
						+ '"><option>无权限</option><option>浏览权限</option><option>管理权限</option></select>'; // --
				str += '</td></tr>';
			}
		}

		var Type = type == 'modi' ? 'sure-modi' : 'sure-create';

		var button = document.createElement("button");
		button.className = 'btn btn-primary ' + Type;
		button.innerHTML = "确认创建";
		button.style.float = "right";
		button.style.margin = "20px calc(( 100% - 700px)/2) 0 0";

		table.innerHTML = str;

		$('.div-main').children("button").remove();
		$('.div-main').append(button);

		return table;

	}

	function create_modi_clickfunc(id, buttype) {

		// 设置修改按钮事件
		if (buttype == 'modi') {
			var objEvt = ($._data(($('.sure-modi'))[0], "events"));
			if (!(objEvt && objEvt["click"])) {
				$('.sure-modi').click(function() {
					var data = new Array();
					for (i = 0; i < role_data.ch_nifo.length; i++) {
						data[i] = $('#dat' + i).val();
					}
					$.ajax({
						url : "Sadmin/sadmin_Role_modiRole",
						type : "post",
						timeout : 3000,
						data : {
							roleId : id,
							roleAllInfo : data.toString(),
						},
						dataType : "json",
						success : function(data) {
							if (data.result == 'ModiSuccess') {
								toastr.success('修改成功');
								window.location="/xxyjsjgcxy/Sadmin/sadmin_Role_page_list_role";
							}else if (data.result == 'premission_none') {
								toastr.warning('无权限');
							} else {
								toastr.warning('修改失败');
							}
						},
						error : function() {
						}
					});
				});
			}
		} else if (buttype == 'create') {
			var objEvt = ($._data(($('.sure-create'))[0], "events"));
			if (!(objEvt && objEvt["click"])) {
				$('.sure-create').click(function() {
					if ($('#dat0').val() == '') {
						toastr.warning('不能有空项!');
						return;
					}
					var data = new Array();
					for (i = 0; i < role_data.ch_nifo.length; i++) {
						data[i] = $('#dat' + i).val();
					}
					$.ajax({
						url : "Sadmin/sadmin_Role_createRole",
						type : "post",
						timeout : 3000,
						data : {
							roleAllInfo : data.toString()
						},
						dataType : "json",
						success : function(data) {
							if (data.result == 'success') {
								toastr.success('创建成功');
								window.location="/xxyjsjgcxy/Sadmin/sadmin_Role_page_list_role";
							} else if (data.result == 'premission_none') {
								toastr.warning('无权限');
							} else {
								toastr.warning('已有该角色');
							}
						},
						error : function() {
						}
					});
				});
			}
		}
	}

	$('.create').click();
	$('select').selectpicker('refresh');
});