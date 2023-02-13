/**
 * @callback url 服务器接口
 */

function Call_Request() {
    // 测试服务器
    this.Url = "http://39.102.73.127/nanjing_liuhe";
    // 图片路径
    this.imagesUrl = 'http://39.102.73.127/nanjing_liuhe';
    // 服务器接口
    this.rotationChartFindAll = "/zlxz/home/rotationChart/findAll"; // 轮播图
    this.providerFindAll = "/zlxz/home/service/provider/findAll";//服务商
    this.announcementFindAll = "/zlxz/news/announcement/findAll";//新闻公告
    this.publicVerifyCode = "/public/verifyCode";//验证
    this.uploadImage = "/zlxz/consult/upload/image";//上传图片
    this.publicSendSms = "/public/sendSms";//短信验证码
    this.userRegister = "/zlxz/user/register";//注册
    this.userSignIn = "/zlxz/user/signIn";//密码登录
    this.userSmsSignIn = "/zlxz/user/smsSignIn";//短信登录
    this.consultSave = "/zlxz/consult/save";//发起咨询
    this.findById = "/zlxz/consult/findById";//咨询详情
    this.leavingMessage = "/zlxz/consult/leavingMessage";//咨询回复
    this.findUserByToken = "/zlxz/user/findUserByToken";//企业详情
    this.userUpdate = "/zlxz/user/update";//修改企业信息
    this.updatePwd = "/zlxz/user/updatePwd";//修改密码
    this.providerFindById = "/zlxz/home/service/provider/findById";//服务商详情
    this.announcementFindById = "/zlxz/news/announcement/findById";//新闻公告详情
    this.consultFindAll = "/zlxz/consult/findAll";//消息列表

    // ajax请求配置参数
    this.url = "";
    this.method = "";
    this.data;
    this.token = "";
    this.dataType = "json";

    this.reinfo = function () { };
    this.status = 0;
    this.redata = function () { };

    this.run = function () {
        if (this.url == "") {
            console.log("url未配置");
            return -1;
        }

        var redata = this.redata;
        var reinfo = this.reinfo;

        $.ajax({
            type: this.method,
            url: this.url,
            // headers: this.token,
            data: this.data,
            dataType: this.dataType,
            contentType: 'application/json; charset=utf-8',
            beforeSend: function (XMLHttpRequest) {
                let tokenStr = sessionStorage.getItem('userInfo') ? "token " + JSON.parse(sessionStorage.getItem('userInfo')).token : null;
                XMLHttpRequest.setRequestHeader("Authorization", tokenStr);
            },
            success: function (data) {
                if (data.status == 0) {
                    reinfo(data);
                    this.status = -1
                } else {
                    redata(data);
                    this.status = 0;
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                this.status = -1;
            }
        })
    }
}