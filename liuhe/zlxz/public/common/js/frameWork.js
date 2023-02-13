/**
 * Created by 关宁 on 2019.05.08
 */

(function () {
    var Index = null;
    var obj = {};
    
    // 判断当前URL是否跟有参数
    if (location.search === '' || location.search === undefined) {
        Index = '../'
    } else {
        // 解析当前揭秘URL
        var BreakString = new RegExp("(^|&)level=([^&]*)(&|$)");
        var GetUrl = window.location.search.substr(1).match(BreakString)
 
        if (window.atob(unescape(GetUrl[2])) === 'Secondlevel') {
            Index = '../../../'
        }

        if (window.atob(unescape(GetUrl[2])) === 'Levelthree') {
            Index = '../../../../'
        }
    }

    /**
    * 动态加载脚本函数
    * @param url 要加载的脚本路径
    * @param callback  回调函数
    */

    obj.loadScript =function(url,callback){
        var doc = document;
        var script = doc.createElement("script");
        script.type = "text/javascript";

        if (script.readyState) {
            script.onreadystatechange = function () {
                if (script.readyState === "load" || script.readyState === "complete") {
                    script.onreadystatechange = null;
                    callback();
                }
            }
        } else {
            script.onload = function(){
                callback();
            };
        }
        
        script.src = url;
    };
    
    var jsList = [];

    function callback () {
        jsList.length ? obj.loadScript(jsList.shift(), callback) : (function () { 
            time = null
        })();
    }

    var time = setTimeout(function(){
        obj.loadScript(jsList.shift(), callback)
    }, 25);

    document.write('<script type=\"text/javascript\" src=\"'+ Index +'plugin/jQuery/jQuery.min.js\"></script>');
    document.write('<script type=\"text/javascript\" src=\"'+ Index +'plugin/layui/layui.all.js\"></script>');
    document.write('<script type=\"text/javascript\" src=\"'+ Index +'plugin/templateWeb/templateWeb.js\"></script>');
    document.write('<script type=\"text/javascript\" src=\"'+ Index +'plugin/jQueryCookie/jquery.cookie.js\"></script>');
    document.write('<script type=\"text/javascript\" src=\"'+ Index +'public/common/js/libs/request.js\"></script>');
    document.write('<script type=\"text/javascript\" src=\"'+ Index +'public/common/js/libs/global.js\"></script>');
    document.write('<script type=\"text/javascript\" src=\"'+ Index +'public/common/js/libs/common.js\"></script>');
    document.write('<script type=\"text/javascript\" src=\"'+ Index +'public/common/js/libs/publicModule.js\"></script>');
})();
