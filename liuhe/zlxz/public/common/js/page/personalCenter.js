
var current_prov;
var current_city;
var current_country;
var request = new Call_Request()
$(function () {
	//退出登录
	$('#loginout').click(function () {
		sessionStorage.clear();
		window.location.href = "../login.html"
	})
	//tab切换
	$('.tab-buttons li').click(function () {
		var index = $(this).index();
		$(this).addClass('active').siblings('li').removeClass('active')
		$('.tab-container .tab-item').hide()
		$('.tab-container .tab-item').eq(index).show()
	})
	//省市区
	/* 自动加载省份列表 */
	showPro();
	//企业资料
	// 详情
	enterpriseDateilsFun();

	$('#confirm').click(function () {
		var name = $('.info-box .company').val();
		var contactName = $('.info-box .contact-name').val();
		var phone = $('.info-box .contact-phone').val();
		var prov = $('#prov option:selected').text();
		var city = $('#city option:selected').text();
		var country = $('#country option:selected').text();
		var address = $('.info-box .detailed-address').val();
		// var industy = $('.industry-list li input[name=1]:checked').val();
		if (!name) {
			layer.tips('请输入企业名称', '.company');
			$('.info-box .company').focus()
			return false
		}
		if (!contactName) {
			layer.tips('请输入联系人', '.contact-name');
			$('.info-box .contact-name').focus()
			return false
		}
		if (!phone) {
			layer.tips('请输入联系电话', '.contact-phone');
			$('.info-box .contact-phone').focus()
			return false
		}
		// let tel = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
		// if (!DcUtils.isMobile(phone) && !tel.test(phone)) {
		// 	layer.tips('请输入正确手机或固定号码', '.contact-phone');
		// 	$('.info-box .contact-phone').focus()
		// 	return false
		// }
		let tel = /^\d{7,}$/;
		if (!tel.test(phone)) {
			layer.tips('请输入正确手机或固定号码', '.contact-phone');
			$('.info-box .contact-phone').focus()
			return false
		}
		if (prov === '-请选择省份-') {
			layer.tips('请选择省份', '#prov');
			$('#prov').focus()
			return false
		}
		if (city === '-请选择城市-') {
			layer.tips('请选择城市', '#city');
			$('#city').focus()
			return false
		}
		if (country === '-请选择县区-') {
			layer.tips('请选择县区', '#country');
			$('#country').focus()
			return false
		}
		if (!address) {
			layer.tips('请输入详细地址', '.detailed-address');
			$('.detailed-addres').focus()
			return false
		}
		// if (!industy) {
		// 	layer.tips('请选择所属行业', '.industry-list');
		// 	return false
		// }
		// if (!$('#upload-files li').length) {
		// 	layer.tips('请上传企业logo', '.upload-btn');
		// 	return false
		// }
		//调取保存接口
		request.url = request.Url + request.userUpdate
		request.method = "put"
		request.data = JSON.stringify({
			enterpriseName: name,
			liaisonName: contactName,
			liaisonTel: phone,
			province: prov,
			city: city,
			area: country,
			address: address,
			// industry: industy
		})
		request.redata = function (data) {
			if (data.code === 1) {
				layer.msg(data.msg);
			} else {
				layer.msg(data.msg);
			}
		}
		request.run()

	})


	//发送验证码
	$('#codeImg').click(function () {
		verificationCodeFun();
	})
	verificationCodeFun()
	// 验证码函数
	function verificationCodeFun() {
		request.url = request.Url + request.publicVerifyCode
		request.data = {
			verificationCodeId: JSON.parse(sessionStorage.getItem('userInfo')).id
		}
		request.method = "get"
		request.redata = function (res) {
			if (res.code === 1) {
				$('#codeImg').attr('src', 'data:image/png;base64,' + res.data);
			} else {
				layer.msg(data.msg);
			}
		}
		request.run()
	}
	// 我的消息
	layui.use(['table', 'laydate'], function () {
		var table = layui.table;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#time',
			format: 'yyyy-MM-dd',
			range: true
		});
		table.render({
			elem: '#tableList',
			url: request.Url + request.consultFindAll,
			cellMinWidth: 80,
			page: true,
			request: {
				limitName: 'size'
			},
			response: {
				statusName: 'code', //规定返回的状态码字段为code
				statusCode: 1 //规定成功的状态码味200
			},
			parseData: function (res) {
				return {
					"code": res.code,
					"msg": res.msg,
					"count": res.data.content.length, //解析数据长度
					"data": res.data.content
				};
			},
			headers: {
				"Authorization": "token " + JSON.parse(sessionStorage.getItem('userInfo')).token
			},
			text: { none: '无数据' },
			cols: [[
				{ field: 'title', title: '标题' },
				{ field: 'createTime', title: '咨询时间' },
				{ field: 'updateTime', title: '更新时间' },
				{ field: 'replyStatus', title: '状态' },
				{ width: 100, title: '操作', templet: '#barDemo' },
			]],
		});
		table.on('tool(tableList)', function (obj) {
			location.href = 'newsDateils.html?id=' + obj.data.id;
		});
		var $ = layui.$, active = {
			reload: function () {
				let timeData = $('#time').val().split(" - ");
				//执行重载
				table.reload('tableList', {
					page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						startDate: timeData[1] ? timeData[0] : "",
						endDate: timeData[1] ? timeData[1] : "",
						replyStatus: $('#status').val()

					}
				});
			}
		};
		$('.demoTable .layui-btn').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	//修改密码
	$('#changeConfirm').click(function () {
		var phone = $('.change-phone').val();
		var verificationCode = $('.change-verification-code').val();
		var newPwd = $('.change-new-pwd').val();
		var againPwd = $('.change-again-pwd').val();
		//密码正则
		// var pwdRex = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
		if (!phone) {
			layer.tips('请输入手机号', '.change-phone');
			$('.change-phone').focus()
			return false
		}
		if (!DcUtils.isMobile(phone)) {
			layer.tips('请输入正确的手机号', '.change-phone');
			$('.change-phone').focus()
			return false
		}
		if (!verificationCode) {
			layer.tips('请输入验证码', '.change-verification-code');
			$('.change-verification-code').focus()
			return false
		}
		if (!newPwd) {
			layer.tips('请输入新密码', '.change-new-pwd');
			$('.change-new-pwd').focus()
			return false
		}
		// if (!pwdRex.test(newPwd)) {
		// 	layer.tips('8-16位数字字母组合', '.change-new-pwd');
		// 	$('.change-new-pwd').focus()
		// 	return false
		// }
		if (!againPwd) {
			layer.tips('请输入确认密码', '.change-again-pwd');
			$('.change-again-pwd').focus()
			return false
		}
		if (againPwd != newPwd) {
			layer.tips('两次密码输入不一致', '.change-again-pwd');
			$('.change-again-pwd').focus()
			return false
		}
		//调修改接口
		request.url = request.Url + request.updatePwd
		request.method = "put"
		request.data = JSON.stringify({
			phone: phone,
			newPassword: newPwd,
			verificationCode: verificationCode
		})
		request.redata = function (data) {
			if (data.code === 1) {
				layer.msg(data.msg);
			} else {
				layer.msg(data.msg);
			}
		}
		request.run()
	})
})
// 企业详情
function enterpriseDateilsFun() {
	request.url = request.Url + request.findUserByToken
	request.method = "get"
	request.redata = function (data) {
		if (data.code === 1) {
			$('#enterpriseName').text(data.data.enterpriseName)
			$('.info-box .company').val(data.data.enterpriseName);
			$('.info-box .contact-name').val(data.data.liaisonName);
			$('.info-box .contact-phone').val(data.data.phone);
			$('#prov option:selected').text(data.data.province);
			$('#city option:selected').text(data.data.city);
			$('#country option:selected').text(data.data.area);
			$('.info-box .detailed-address').val(data.data.address);
			// for (var i = 0; i < $("input[name='1']").length; i++) {
			// 	if ($($("input[name='1']")[i]).val() === data.data.industry) {
			// 		$("input[name='1']")[i].checked = true;
			// 	}
			// }
		} else {
			layer.msg(data.msg);
		}
	}
	request.run()
}

function showPro() {
	var len = province.length;
	for (var i = 0; i < len; i++) {
		var provOpt = document.createElement("option");
		provOpt.innerText = province[i]['name'];
		provOpt.value = i;
		prov.appendChild(provOpt);
	}
};
/*根据所选的省份来显示城市列表*/
function showCity(obj) {
	var val = obj.options[obj.selectedIndex].value;
	current_prov = val;
	if (val != null) {
		city.length = 1;
		if (province[val]) {
			var cityLen = province[val]["city"].length;
		}
		for (var j = 0; j < cityLen; j++) {
			var cityOpt = document.createElement('option');
			cityOpt.innerText = province[val]["city"][j].name;
			cityOpt.value = j;
			city.appendChild(cityOpt);
		}
	}
};
/*根据所选的城市来显示县区列表*/
function showCountry(obj) {
	var val = obj.options[obj.selectedIndex].value;
	current_city = val;
	if (val != null) {
		country.length = 1;
		if (province[current_prov]["city"][val]) {
			var countryLen = province[current_prov]["city"][val].districtAndCounty.length;
		}
		if (countryLen == 0) {
			addrShow.value = province[current_prov].name + '-' + province[current_prov]["city"][val].name;
			return;
		}
		for (var n = 0; n < countryLen; n++) {
			var countryOpt = document.createElement('option');
			countryOpt.innerText = province[current_prov]["city"][val].districtAndCounty[n];
			countryOpt.value = n;
			country.appendChild(countryOpt);
		}
	}
};

function selectCountry(obj) {
	current_country = obj.options[obj.selectedIndex].value;
	$(".btn").disabled = false;
};
function showAddr() {
	var ss = province[current_prov].name + "|" +
		province[current_prov]['city'][current_city].name + "|" +
		province[current_prov]['city'][current_city]['districtAndCounty'][current_country];
	$("#addr-show").val(ss);
}