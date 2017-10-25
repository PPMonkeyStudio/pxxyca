/**
 * jQuery会将事件都缓存起来,进行事件的判断防止事件的重复添加 1.为了防止内存溢出以及页面unload的时候的速度，也包括多函数触发，
 * 2.jQuery会在window.unload的时候卸载所有绑定过的事件，释放内存。 3.方便管理等
 */
// 权限数据记录
var permissions_ = {
	Databasefield : [ "rolePremissionSnews", "rolePremissionScarousel",
			"rolePremissionSadmin", "rolePremissionSguestbook",
			"rolePremissionSlink", "rolePremissionSdatabase" ],
	translation : [ "新闻管理子系统", "轮播图管理子系统", "管理员管理子系统", "留言薄管理子系统", "链接管理子系统",
			"数据库管理子系统" ]
}

$(function() {

	/**
	 * ajax传递的参数
	 */
	var ajax_data = {
		haveRetrieve : 'no', // 参数中是否含有索引,yes为含有，no为不含
		querycondition : '', // 索引方式
		querykey : '', // 索引关键字
		pages : '1' // 页码(即将要跳转到的页码)"当前页码由后台所给的json值确定"
	};

	/**
	 * 通过ajax获取数据
	 */
	ajax_getAllRoleByPaging(ajax_data);

	/**
	 * 设置查询下拉框的监听事件,改变时触发
	 */
	$("#queryselection").change(function() {
		var querycondition = $("#queryselection").val(); // 查询方式
		var querykey = $("#querykey").val(); // 查询关键字
		// 如果选择的是第一个，不触发任何事件
		if (querycondition == 'default') {
			return;
		}
		if (querykey == '') {
			toastr.error.error('查询内容为空!');
			return;
		} else {
			ajax_data.haveRetrieve = "yes";
			ajax_data.querycondition = "," + querycondition;
			ajax_data.querykey = "," + querykey;
			ajax_getAllRoleByPaging(ajax_data);
			after_retrieve();
		}
	});

	/**
	 * 当用户通过关键词查询时，给与"取消查询"按钮点击事件
	 */
	function after_retrieve() {
		// 显示取消查询
		$('#retrieve').show();
		// 判断是否含有绑定事件
		var objEvt = ($._data($("#retrieve")[0], "events"));
		if (!(objEvt && objEvt["click"])) {
			$("#retrieve").click(function() {
				if ($(".home_screening_results").text()) {
					$(".home_screening_results").text('');
				}
				ajax_data.haveRetrieve = 'no';
				$("#queryselection").get(0).selectedIndex = 0;
				ajax_data.querycondition = ajax_data.querykey = '';
				ajax_getAllRoleByPaging(ajax_data);
				$('#retrieve').hide();
			});
		}
	}

	/**
	 * 通过ajax获取管理员数据
	 */
	function ajax_getAllRoleByPaging(str) {
		$.ajax({
			url : "Sadmin/sadmin_Role_getAllRoleByPaging",
			type : "post",
			timeout : 3000,
			data : str,
			dataType : "json",
			/* contentType : false, */
			/* processData : false, */
			success : function(data) {
				// 显示查询出来的元素
				addElement(data);
				// 修改查询后分页的不同情况
				pageing_button(data);
			},
			error : function() {
			}
		});
	}

	/**
	 * 分页等元素
	 */
	function pageing_button(dat) {
		var table = $('table.table-pageing');
		if (dat.pageIndex == 1) {
			$(table.find('button.home')).addClass('disabled');
		} else {
			$(table.find('button.home')).removeClass('disabled');
		}
		if (!dat.HavePrePage) {
			$(table.find('button.pre')).addClass('disabled');
		} else {
			$(table.find('button.pre')).removeClass('disabled');
		}
		if (!dat.HaveNextPage) {

			$(table.find('button.next')).addClass('disabled');
		} else {

			$(table.find('button.next')).removeClass('disabled');
		}
		if (dat.pageIndex == dat.totalPages) {
			$(table.find('button.back')).addClass('disabled');
		} else {
			$(table.find('button.back')).removeClass('disabled');
		}
		$(table.find('a.pagedat')).html(dat.pageIndex);
		/**
		 * 判断是否已经有绑定事件,如果没有就执行进行时间绑定,如果已经有绑定事件则跳过(此处判断是否含有click事件)
		 */
		var objEvt = ($._data($(table.find('button:not(.disabled)'))[0],
				"events"));
		if (!(objEvt && objEvt["click"])) {
			$(table.find('button:not(.disabled)')).click(function() {
				var className = $(this).attr('class').split(' ')[0];
				switch (className) {
				case 'home':
					ajax_data.pages = 1; // 首页
					ajax_getAllRoleByPaging(ajax_data);
					break;
				case 'pre':
					ajax_data.pages = dat.pageIndex - 1; // 上一页
					ajax_getAllRoleByPaging(ajax_data);
					break;
				case 'next':
					ajax_data.pages = dat.pageIndex + 1; // 下一页
					ajax_getAllRoleByPaging(ajax_data);
					break;
				case 'back':
					ajax_data.pages = dat.totalPages; // 尾页
					ajax_getAllRoleByPaging(ajax_data);
					break;
				default:
					break;
				}
			});
		}
	}

	/**
	 * 成功获取数据后添加到页面中
	 */
	function addElement(data) {
		// 判断是否具有管理权限
		var premission = $("#premission").val();
		// 获得记录数据的表格
		var table = $('.table-data')[0];
		// 移除已有信息
		$(table).find('tbody').remove();
		// 创建tbody元素,开始添加查询的内容
		var tbody = document.createElement("tbody");
		for (var i = 0; i < data.ObjDatas.length; i++) {
			var tr = document.createElement("tr");
			var td1 = document.createElement("td"); // 序号(和角色信息并无关联)
			td1.innerHTML = (data.pageIndex - 1) * 10 + i + 1;
			tr.appendChild(td1);
			var td2 = document.createElement("td"); // 角色名称栏
			td2.innerHTML = data.ObjDatas[i].roleName; // 角色名称数据
			tr.appendChild(td2);
			var td3 = document.createElement("td"); // 新闻管理子系统权限
			td3.innerHTML = data.ObjDatas[i].rolePremissionSnews;
			tr.appendChild(td3);
			var td4 = document.createElement("td"); // 轮播图管理子系统权限
			td4.innerHTML = data.ObjDatas[i].rolePremissionScarousel;
			tr.appendChild(td4);
			var td5 = document.createElement("td"); // 管理员管理子系统权限
			td5.innerHTML = data.ObjDatas[i].rolePremissionSadmin;
			tr.appendChild(td5);
			var td6 = document.createElement("td"); // 留言薄管理子系统权限
			td6.innerHTML = data.ObjDatas[i].rolePremissionSguestbook;
			tr.appendChild(td6);

			var td6 = document.createElement("td"); // 链接管理子系统权限
			td6.innerHTML = data.ObjDatas[i].rolePremissionSlink;
			tr.appendChild(td6);
			var td6 = document.createElement("td"); // 数据库管理子系统权限
			td6.innerHTML = data.ObjDatas[i].rolePremissionSdatabase;
			tr.appendChild(td6);
			var td6 = document.createElement("td"); // 角色创建日期
			td6.innerHTML = data.ObjDatas[i].roleGmtCreate;
			tr.appendChild(td6);
			var td6 = document.createElement("td"); // 角色修改日期
			td6.innerHTML = data.ObjDatas[i].roleGmtModified;
			tr.appendChild(td6);

			if (premission == 'premission_management') {
				// 创建td
				var td7 = document.createElement("td");
				// 为每一行创建查看按钮
				var but_check = document.createElement("button");
				but_check.setAttribute("class", "btn btn-green btn-check");
				but_check.setAttribute("value",
						data.ObjDatas[i].jsjSadminRoleId);
				but_check.innerHTML = "查看";
				td7.appendChild(but_check);
				// 为每一行创建删除按钮
				var but_del = document.createElement("button");
				but_del.setAttribute("class", "btn btn-danger btn-del");
				but_del.setAttribute("value", data.ObjDatas[i].jsjSadminRoleId);
				but_del.setAttribute("name", data.ObjDatas[i].roleName);
				but_del.innerHTML = "删除";
				td7.appendChild(but_del);
				// 将td加入到tr当中
				tr.appendChild(td7);
			} else {
				var td7 = document.createElement("td");
				var but_check = document.createElement("button");
				but_check.setAttribute("class", "btn btn-blue btn-check");
				but_check.setAttribute("value",
						data.ObjDatas[i].jsjSadminRoleId);
				but_check.innerHTML = "查看";
				td7.appendChild(but_check);
				tr.appendChild(td7);
			}

			tbody.appendChild(tr); // tr添加到tbody中
		}
		table.appendChild(tbody);
		if (premission == 'premission_management') {
			// 为所有删除按钮设置绑定事件
			role_del()
		}
		// 为所有查看按钮设置绑定事件
		role_check()

	}

	/**
	 * 角色删除功能
	 */
	function role_del() {
		// 判断是否已经含有click事件
		var objEvt = ($._data($(".btn-del")[0], "events"));
		if (!(objEvt && objEvt['click']))
			$(".btn-del")
					.click(
							function() {
								var roleId = this.value;
								$
										.confirm({
											title : '确认删除!',
											content : this.name,
											type : 'red',
											animation : 'scaleX',
											typeAnimated : true,
											icon : 'fa fa-warning',
											buttons : {
												tryAgain : {
													text : '确认',
													btnClass : 'btn-red',
													action : function() {
														$
																.ajax({
																	url : "Sadmin/sadmin_Role_del_role",
																	type : "post",
																	timeout : 3000,
																	data : {
																		roleId : roleId,
																	},
																	dataType : "json",
																	success : function(
																			data) {
																		// 删除是否成功
																		if (data.result == "success") {
																			ajax_getAllRoleByPaging(ajax_data);
																			toastr
																					.success("角色已删除");
																			$
																					.ajax({
																						url : "Sadmin/sadmin_Admin_roleEmpty",
																						type : "post",
																						timeout : 3000,
																						data : {
																							adminRole : roleId,
																						},
																						dataType : "json",
																						success : function(
																								data) {

																							if (data.result == "success") {
																								toastr
																										.success("所有含有該角色的管理員的角色已置空");
																							}

																						},
																						error : function() {
																							toastr
																									.error("服务器繁忙，请稍后再试.");
																						}
																					});
																		}
																	},
																	error : function() {
																		toastr
																				.error("服务器繁忙，请稍后再试.");
																	}
																});
													}
												},
												close : {
													text : '取消',
													action : function() {
													}
												}
											}
										});
							});
	}

	/**
	 * 查看角色信息
	 */
	function role_check() {
		// 判断是否已经含有click事件
		var objEvt = ($._data($(".btn-check")[0], "events"));
		if (!(objEvt && objEvt['click']))
			$(".btn-check")
					.click(
							function() {
								var data = new Array();
								for (var i = 1; i < 10; i++) {
									data[i - 1] = $(this).parent().parent()
											.find('td:eq(' + i + ')').text();
								}
								data[9] = this.value;
								$
										.confirm({
											icon : 'fa fa-spinner fa-spin',
											title : '查看',
											buttons : {
												关闭 : {}
											},
											columnClass : 'col-md-12',
											content : function() {
												var self = this;
												setTimeout(
														function() {
															self
																	.setContent(create_Content(data));
															self
																	.setTitle('管理员：'
																			+ data[0]);
															self
																	.setIcon('fa fa-check');
														}, 0);
											}
										});
								function create_Content(dat) {

									var str = '<table class="table table-bordered" style="text-align: center;"><thead><tr><th>角色名称</th><td>';

									str = str
											.concat(dat[0]
													+ '</td></tr><tr><th>新闻管理子系统权限</th><td>');
									str = str
											.concat(dat[1]
													+ '</td></tr><tr><th>轮播图管理子系统权限</th><td>');
									str = str
											.concat(dat[2]
													+ '</td></tr><tr><th>管理员管理子系统权限</th><td>');
									str = str
											.concat(dat[3]
													+ '</td></tr><tr><th>留言薄管理子系统权限</th><td>');
									str = str
											.concat(dat[4]
													+ '</td></tr><tr><th>链接管理子系统权限</th><td>');
									str = str
											.concat(dat[5]
													+ '</td></tr><tr><th>数据库管理子系统权限</th><td>');
									str = str
											.concat(dat[6]
													+ '</td></tr><tr><th>角色创建日期</th><td>');
									str = str
											.concat(dat[7]
													+ '</td></tr><tr><th>角色修改日期</th><td>');
									str = str.concat(dat[8]
											+ '</td></tr></thead></table>');
									return str;
								}
							});
	}

	/**
	 * 筛选的弹出框
	 */
	$('#screen')
			.click(
					function() {
						var confirm = $
								.confirm({
									columnClass : 'col-md-12',
									icon : 'fa fa-check',
									title : '查询筛选',
									buttons : {
										sure : {
											text : '确认',
											btnClass : 'btn-danger',
											action : function() {
												$(".home_screening_results")
														.html(
																$(
																		".screening-results")
																		.html());
												var dat = $(
														".home_screening_results")
														.text().toString()
														.replace(/×|-/g, ',')
														.trim();

												// 除去第一个逗号
												dat = dat.substring(1);

												var strs = new Array();
												strs = dat.split(",");
												for (i = 0; i < strs.length; i++) {
													if (i % 2 == 0) {
														ajax_data.querycondition += ","
																+ conversion(strs[i]);
													} else {
														ajax_data.querykey += ","
																+ conversion(strs[i]);
													}
												}
												ajax_data.haveRetrieve = "yes";
												ajax_getAllRoleByPaging(ajax_data);
												after_retrieve();
												// 执行完查询后清空已经选择好的筛选
												ajax_data.haveRetrieve = 'no';
												ajax_data.querycondition = '';
												ajax_data.querykey = '';
											}
										},
										取消 : {
											action : function() {
												return;
											}
										}
									},
									content : function() {

										// 创建提供筛选的元素
										var str = '<div style="width:auto; height:250px;" class="navbar navbar-default" role="navigation">';
										for (var i = 0; i < permissions_.translation.length; i++) {
											str = str
													.concat('<ul class="nav navbar-nav"> <li class="dropdown"> <a href="#" id="dropdown-toggle" class="dropdown-toggle" data-toggle="dropdown">')
											str = str
													.concat(permissions_.translation[i]);
											str = str
													.concat('<b class="caret"> </b> </a> <ul class="dropdown-menu"> ');
											str = str
													.concat('<li class="a-category none"><a onclick="a_clickFunction(this)">无</a></li>');
											str = str
													.concat('<li class="a-category browse"><a onclick="a_clickFunction(this)">浏览</a></li>');
											str = str
													.concat('<li class="divider"></li>');
											str = str
													.concat('<li class="a-category management"><a onclick="a_clickFunction(this)">管理</a></li>');
											str = str
													.concat('</ul> </li> </ul>');
										}
										str = str.concat('</div>');
										str = str
												.concat('<div style="width:auto; height:auto;" class="screening-results"></div>');
										return str;
									},
								});
					});
});

// 为(无权限。浏览权限。管理权限)创建绑定事件
function a_clickFunction(obj) {
	var permissions = $(obj).text();
	var databasefield = $(obj).parent().parent().parent().children(":first")
			.text().trim(); // 获取文本值
	var Class = '';
	Class = conversion(databasefield);

	// 判断是否已经存在该子系统所选择的权限，存在则提示是否删除已经含有的权限
	if ($(".screening-results").children("div").hasClass(Class)) {
		$.confirm({
			title : '',
			content : '确认用"' + databasefield + '-' + permissions
					+ '"权限替换已选择的权限吗？',
			buttons : {
				sure : {
					text : '确认',
					btnClass : 'btn-danger',
					action : function() {
						$("." + Class).remove();
						add_div();
					}
				},
				cancel : {
					text : '取消',
					btnClass : 'btn-blue',
					action : function() {
						return;
					}
				},
			}
		});
	} else {
		add_div();
	}

	// 方便和确认框起作用，将组成为方法
	function add_div() {
		switch (permissions) {
		case '无':
			Class = Class.concat(' alert-info');
			break;
		case '浏览':
			Class = Class.concat(' alert-success');
			break;
		case '管理':
			Class = Class.concat(' alert-danger');
			break;
		default:
			break;
		}

		var str = '<div class="alert '
				+ Class
				+ ' alert-dismissible" style="height:50px; width:260px; margin-left:40px; float:left;" role="alert"> <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
		str = str.concat(databasefield + '-' + permissions + '权限');
		str = str.concat('</div>');
		$(".screening-results").append(str);

	}
}

// 字符转换
function conversion(data) {
	var str;
	switch (data.trim()) {
	case "新闻管理子系统":
		str = "rolePremissionSnews";
		break;
	case '轮播图管理子系统':
		str = "rolePremissionScarousel";
		break;
	case '管理员管理子系统':
		str = "rolePremissionSadmin";
		break;
	case '留言薄管理子系统':
		str = "rolePremissionSguestbook";
		break;
	case '链接管理子系统':
		str = "rolePremissionSlink";
		break;
	case '数据库管理子系统':
		str = "rolePremissionSdatabase";
		break;
	case '无权限':
		str = "premission_none";
		break;
	case '浏览权限':
		str = "premission_browse";
		break;
	case '管理权限':
		str = "premission_management";
		break;
	default:
		break;
	}
	return str;
}