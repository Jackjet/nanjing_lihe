
var current_prov;
var current_city;
var current_country;
let request = new Call_Request();
$(function () {
    //省市区
    /* 自动加载省份列表 */
    showPro();
    $('#fsyzm').click(function () {
        let phones = $('.info-box .contact-phone').val();
        if (!phones) {
            layer.tips('请输入注册手机号电话', '.contact-phone');
            $('.info-box .contact-phone').focus()
            return false
        }
        request.url = request.Url + request.publicSendSms
        request.method = "post"
        request.data = JSON.stringify({
            phone: phones,
            smsToken: hex_md5(phones + 'JSfudUS8d3zCCbTe')
        })
        request.redata = function (res) {
            if (res.code === 1) {
                layer.msg('企业注册成功，是否跳转登录', {
                    time: 20000, //20s后自动关闭
                    btn: ['确定', '取消'],
                    yes: function () {
                        location.href = "login.html";
                    }
                });
            } else {
                layer.msg(res.msg);
            }
        }
        request.run()
    })
    $('#confirm').click(function () {
        var name = $('.info-box .company').val();
        var contactName = $('.info-box .contact-name').val();
        var phone = $('.info-box .contact-phone').val();
        var prov = $('#prov option:selected').text();
        var city = $('#city option:selected').text();
        var country = $('#country option:selected').text();
        var address = $('.info-box .detailed-address').val();
        var smsCode = $('.sms-code').val();
        var password = $('.password').val();
        var liaisonTel = $('.liaisonTel').val();
        // var industy = $('.industry-list li input[name=1]:checked').val()
        if (!name) {
            layer.tips('请输入企业名称', '.company');
            $('.info-box .company').focus()
            return false
        }
        if (!phone) {
            layer.tips('请输入注册手机号电话', '.contact-phone');
            $('.info-box .contact-phone').focus()
            return false
        }
        if (!password) {
            layer.tips('请输入密码', '.password');
            $('.info-box .password').focus()
            return false
        }
        if (!contactName) {
            layer.tips('请输入联系人', '.contact-name');
            $('.info-box .contact-name').focus()
            return false
        }

        if (!DcUtils.isMobile(phone)) {
            layer.tips('请输入正确的联系电话', '.contact-phone');
            $('.info-box .contact-phone').focus()
            return false
        }
        if (!liaisonTel) {
            layer.tips('请输入咨询电话', '.liaisonTel');
            $('.info-box .liaisonTel').focus()
            return false
        }
        // let tel = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
        // if (!DcUtils.isMobile(liaisonTel) && !tel.test(liaisonTel)) {
        //     layer.tips('请输入正确号码', '.liaisonTel');
        //     $('.info-box .liaisonTel').focus()
        //     return false
        // }
        let tel = /^\d{7,}$/
        if (!tel.test(liaisonTel)) {
            layer.tips('请输入正确号码', '.liaisonTel');
            $('.info-box .liaisonTel').focus()
            return false
        }
        if (!smsCode) {
            layer.tips('请输入验证码', '.sms-code');
            $('.info-box .sms-code').focus()
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
        //     layer.tips('请选择所属行业', '.industry-list');
        //     return false
        // }
        // if (!$('#upload-files li').length) {
        //     layer.tips('请上传企业logo', '.upload-btn');
        //     return false
        // }
        request.url = request.Url + request.userRegister
        request.method = "post"
        request.data = JSON.stringify({
            phone: phone,
            password: password,
            enterpriseName: name,
            liaisonName: contactName,
            liaisonTel: liaisonTel,
            province: prov,
            city: city,
            area: country,
            address: address,
            // industry: industy,
            // logoPath: "../../public/images/personalCenter/icon.png",
            smsCode: smsCode
        })
        request.redata = function (res) {
            if (res.code === 1) {
                layer.msg("注册企业成功！");
                window.location.href = "login.html"
            } else {
                layer.msg(res.msg);
            }
        }
        request.run()
    })


    //发送验证码
    $('#send-btn').click(function () {
        var verificationCode = $('.change-verification-code').val()
        //调验证码接口
        layer.msg('发送验证码成功');
    })
})
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