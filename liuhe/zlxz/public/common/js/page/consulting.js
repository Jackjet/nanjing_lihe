let request = new Call_Request()
$(function () {
    // $('#codeImg').click(function () {
    //     verificationCodeFun();
    // })
    // verificationCodeFun()
    // 验证码函数
    // function verificationCodeFun() {
    //     request.url = request.Url + request.publicVerifyCode
    //     request.data = {
    //         verificationCodeId: JSON.parse(sessionStorage.getItem('userInfo')).id
    //     }
    //     request.method = "get"
    //     request.redata = function (res) {
    //         if (res.code === 1) {
    //             $('#codeImg').attr('src', 'data:image/png;base64,' + res.data);
    //         } else {
    //             layer.msg(data.msg);
    //         }
    //     }
    //     request.run()
    // }

    // 上传图片

    // console.log(imgWrapList)
    $('#confirm-btn').click(function () {
        var enterpriseName = $('.enterprise-name').val()
        var phone = $('.phone').val()
        var yzm = $('#yzm').val()
        // var verificationCode = $('#codeImg').attr('code')
        var address = $('.address').val()
        var remark = $('.remark').val()
        var phoneName = $('.phoneName').val()
        var title = $('.title').val()
        if (!enterpriseName) {
            layer.tips('请填写企业名称', '.enterprise-name');
            $('.enterprise-name').focus()
            return false
        }
        if (!title) {
            layer.tips('请输入标题', '.title');
            $('.title').focus()
            return false
        }
        if (!phoneName) {
            layer.tips('请输入联系人姓名', '.phoneName');
            $('.phoneName').focus()
            return false
        }
        if (!phone) {
            layer.tips('请输入联系电话', '.phone');
            $('.phone').focus()
            return false
        }
        if (!DcUtils.isMobile(phone)) {
            layer.tips('请输入正确的联系电话', '.phone');
            $('.phone').focus()
            return false
        }
        // if (imgWrapList.length == 0) {
        //     layer.tips('请上传图片', '.upload-btn');
        //     return false
        // }
        // if (!yzm) {
        //     layer.tips('请输入验证码', '#yzm');
        //     $('#yzm').focus()
        //     return false
        // }
        // if (yzm != verificationCode) {
        //     layer.tips('请输入正确的验证码', '#yzm');
        //     $('#yzm').focus()
        //     return false
        // }
        let imgUrls = "";
        picIds.forEach(item => {
            imgUrls += item.fileDownloadUri + ','
        })
        request.url = request.Url + request.consultSave
        request.method = "post"
        request.data = JSON.stringify({
            enterpriseName: enterpriseName,
            title: title,
            liaisonName: phoneName,
            imagesPahts: imgUrls.slice(0, -1),
            liaisonTel: phone,
            address: address,
            leavingMessage: remark,
            // verificationCode: yzm
        })
        request.redata = function (data) {
            layer.msg(data.msg);
        }
        request.run()

    })
})