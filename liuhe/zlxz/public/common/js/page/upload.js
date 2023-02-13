var imgWrapList = [];
var picIds = [];
var roomPics = ''
var tmpl = '<li class="upload-file pr" style="background-image:url(#url#)"><div class="del-btn">X</div></li>',
    $uploaderInput = $("#uploader-input"),
    $uploaderFiles = $("#upload-files"),
    count = 0,
    oldCode = '',
    formData = new FormData();
var mainPc = 0;


$uploaderInput.on("change", function (e) {
    if (count >= 9) {
        layer.alert('最多上传9张图片')
        return false;
    }
    layer.load(1, {
        shade: [0.01, '#fff'], content: '图片处理中请稍等~', success: function (layero) {
            layero.find('.layui-layer-content').css({
                'line-height': '37px',
                'border': '1px solid #eee',
                'width': '150px',
                'background-position': '4px 4px',
                'padding': '4px 4px 4px 44px',
            });
            layero.find('.layui-layer-ico16, .layui-layer-loading .layui-layer-loading1').css({
                'width': '150px !important',
                'background-position': '2px 0 !important'
            });
        }
    });
    formData.append('file', e.target.files[0])
    $.ajax({
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("Authorization", "token " + JSON.parse(sessionStorage.getItem('userInfo')).token);
            layer.load(1, {
                shade: [0.01, '#fff'], content: '图片上传中...', success: function (layero) {
                    layero.find('.layui-layer-content').css({
                        'line-height': '37px',
                        'border': '1px solid #eee',
                        'width': '150px',
                        'background-position': '4px 4px',
                        'padding': '4px 4px 4px 44px',
                    });
                    layero.find('.layui-layer-ico16, .layui-layer-loading .layui-layer-loading1').css({
                        'width': '150px !important',
                        'background-position': '2px 0 !important'
                    });
                }
            });
        },
        url: request.Url + request.uploadImage,
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (data) {
            count++;
            $uploaderFiles.append($(tmpl.replace('#url#', request.Url + data.fileDownloadUri)));
            picIds.push(data);
        },
        complete: function () {
            formData.delete('file');
            layer.closeAll('loading')
        },
        error: function (data) {
            delHandle($uploaderFiles.length - 1)
            layer.alert('图片上传失败，请重试');
        }
    });
})

var index; //第几张图片
$('#upload-files').on('click', '.del-btn', function () {
    index = $(this).parent('li').index()
    delHandle(index)
})
function delHandle(index) {
    var $li = $uploaderFiles.find("li").eq(index)
    $uploaderFiles.find("li").eq(index).remove();
    imgWrapList.splice(index, 1);
    picIds.splice(index, 1);
    count--;
}
