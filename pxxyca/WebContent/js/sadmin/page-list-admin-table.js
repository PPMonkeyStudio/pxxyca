/**
 * jQuery会将事件都缓存起来,进行事件的判断防止事件的重复添加 1.为了防止内存溢出以及页面unload的时候的速度，也包括多函数触发，
 * 2.jQuery会在window.unload的时候卸载所有绑定过的事件，释放内存。 3.方便管理等
 */
$(function() {

	/**
	 * ajax传递的参数
	 */
	var ajax_data = {
		haveRetrieve : 'no', // 参数中是否含有索引,yes为含有，no为不含
		querycondition : 'condition', // 索引方式
		querykey : 'key', // 索引关键字
		pages : '1' // 页码(即将要跳转到的页码)
	};

	/**
	 * 通过ajax获取数据
	 */
	ajax_getAllAdminByPaging(ajax_data);

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
			toastr.warning('查询内容为空!');
			return;
		} else {
			ajax_data.haveRetrieve = "yes";
			ajax_data.querycondition = querycondition;
			ajax_data.querykey = querykey;
			ajax_getAllAdminByPaging(ajax_data);
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
				ajax_data.haveRetrieve = 'no';
				ajax_getAllAdminByPaging(ajax_data);
				$('#retrieve').hide();
			});
		}
	}

	/**
	 * 通过ajax获取管理员数据
	 */
	function ajax_getAllAdminByPaging(str) {
		$.ajax({
			url : "Sadmin/sadmin_Admin_getAllAdminByPaging",
			type : "post",
			timeout : 3000,
			data : str,
			dataType : "json",
			/* contentType : false, */
			/* processData : false, */
			success : function(data) {

				if (data == "无权限") {
					toastr.warning(data);
				} else {
					// 显示查询出来的元素
					addElement(data);
					// 修改查询后分页的不同情况
					pageing_button(data);
				}

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
					ajax_getAllAdminByPaging(ajax_data);
					break;
				case 'pre':
					ajax_data.pages = dat.pageIndex - 1; // 上一页
					ajax_getAllAdminByPaging(ajax_data);
					break;
				case 'next':
					ajax_data.pages = dat.pageIndex + 1; // 下一页
					ajax_getAllAdminByPaging(ajax_data);
					break;
				case 'back':
					ajax_data.pages = dat.totalPages; // 尾页
					ajax_getAllAdminByPaging(ajax_data);
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
		table.lastChild.remove();
		// 创建tbody元素,开始添加查询的内容
		var tbody = document.createElement("tbody");
		for (var i = 0; i < data.ObjDatas.length; i++) {
			var tr = document.createElement("tr");
			var td1 = document.createElement("td"); // 序号(和管理员信息并无关联)
			td1.innerHTML = (data.pageIndex - 1) * 10 + i + 1;
			tr.appendChild(td1);
			var td2 = document.createElement("td"); // 管理员账号栏
			td2.innerHTML = data.ObjDatas[i].adminAccount; // 管理员账号数据
			tr.appendChild(td2);
			var td3 = document.createElement("td"); // 管理员密码栏
			td3.innerHTML = data.ObjDatas[i].adminPassword; // 管理员密码数据
			tr.appendChild(td3);
			var td4 = document.createElement("td"); // 管理员角色栏
			td4.innerHTML = data.ObjDatas[i].adminRole; // 管理员角色数据
			tr.appendChild(td4);
			var td5 = document.createElement("td"); // 管理员创建时期栏
			td5.innerHTML = data.ObjDatas[i].adminGmtCreate; // 管理员创建时期数据
			tr.appendChild(td5);
			var td6 = document.createElement("td"); // 管理员修改日期栏
			td6.innerHTML = data.ObjDatas[i].adminGmtModified; // 管理员修改日期数据
			tr.appendChild(td6);

			/*
			 * 
			 */

			if (premission == 'premission_management') {
				// 创建td
				var td7 = document.createElement("td");
				// 为每一行创建查看按钮
				var but_check = document.createElement("button");
				but_check.setAttribute("class", "btn btn-green btn-check");
				but_check.setAttribute("value",
						data.ObjDatas[i].jsjSadminAdminId);
				but_check.innerHTML = "查看";
				td7.appendChild(but_check);
				// 为每一行创建删除按钮
				var but_del = document.createElement("button");
				but_del.setAttribute("class", "btn btn-danger btn-del");
				but_del
						.setAttribute("value",
								data.ObjDatas[i].jsjSadminAdminId);
				but_del.setAttribute("name", data.ObjDatas[i].adminAccount);
				but_del.innerHTML = "删除";
				td7.appendChild(but_del);
				// 将td加入到tr当中
				tr.appendChild(td7);
			} else {
				var td7 = document.createElement("td");
				var but_check = document.createElement("button");
				but_check.setAttribute("class", "btn btn-blue btn-check");
				but_check.setAttribute("value",
						data.ObjDatas[i].jsjSadminAdminId);
				but_check.innerHTML = "查看";
				td7.appendChild(but_check);
				tr.appendChild(td7);
			}
			tbody.appendChild(tr); // tr添加到tbody中
		}
		table.appendChild(tbody);

		if (premission == 'premission_management') {
			// 为所有删除按钮设置绑定事件
			admin_del()
			// 为所有查看按钮设置绑定事件
			admin_check()
		} else {
			// 为所有查看按钮设置绑定事件
			admin_check()
		}
	}

	/**
	 * 管理员删除功能
	 */
	function admin_del() {
		// 判断是否已经含有click事件
		var objEvt = ($._data($(".btn-del")[0], "events"));
		if (!(objEvt && objEvt['click']))
			$(".btn-del")
					.click(
							function() {

								var adminid = this.value;
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
																	url : "Sadmin/sadmin_Admin_del_admin",
																	type : "post",
																	timeout : 3000,
																	data : {
																		adminId : adminid,
																	},
																	dataType : "json",
																	success : function(
																			data) {
																		if (data == "deletesuccess") {
																			ajax_getAllAdminByPaging(ajax_data);
																		} else if (data == "无权限") {
																			toastr
																					.warning(data);
																		}
																	},
																	error : function() {
																		toastr.warning("服务器繁忙，请稍后再试.");
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
	 * 查看管理员信息
	 */
	function admin_check() {
		// 判断是否已经含有click事件
		var objEvt = ($._data($(".btn-check")[0], "events"));
		if (!(objEvt && objEvt['click']))
			$(".btn-check")
					.click(
							function() {
								var data = new Array();
								for (var i = 1; i < 6; i++) {
									data[i - 1] = $(this).parent().parent()
											.find('td:eq(' + i + ')').text();
								}
								data[5] = this.value;
								$
										.confirm({
											columnClass : 'col-md-12',
											icon : 'fa fa-spinner fa-spin',
											title : '查看',
											buttons : {
												关闭 : {}
											},
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
									var str = '<table class="table table-bordered" style="text-align: center;"><thead><tr><th>管理员帐号</th><td>';
									str = str
											.concat(dat[0]
													+ '</td></tr><tr><th>管理员密码</th><td>');
									str = str
											.concat(dat[1]
													+ '</td></tr><tr><th>管理员角色</th><td>');
									str = str
											.concat(dat[2]
													+ '</td></tr><tr><th>管理员创建日期</th><td>');
									str = str
											.concat(dat[3]
													+ '</td></tr><tr><th>管理员修改日期</th><td>');
									str = str.concat(dat[4]
											+ '</td></tr></thead></table>');
									return str;
								}
							});
	}

	$('select').selectpicker('refresh');
});