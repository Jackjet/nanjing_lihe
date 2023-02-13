/*纯前端 的验证码 生成
* inputId: 验证码输入框的id
* id:获取HTML端画布 id
* imgId: canvas转换为图片的 图片标签id
* options:{
*   width:生成验证码的 宽度
*   height:生成验证码的高度
*   themeColor:主题颜色
* }
*/
function getVerificationCode(inputId,id,imgId,options) {
   console.log(inputId)
    var width = options.width || 100
    var height = options.height || 40
    var nums = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0", 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z'];

    drawCode(id)
    // 绘制验证码
    function drawCode(id) {
        var canvas = document.getElementById(id)  //
        var context = canvas.getContext("2d")                 //获取画布2D上下文
        context.fillStyle = options.themeColor || '#ff8200';                  //画布填充色
        context.fillRect(0, 0, canvas.width, canvas.height)   //清空画布
        context.fillStyle = "white";                           //设置字体颜色
        context.font = "25px Arial";                           //设置字体
        var rand = new Array()
        var x = new Array()
        var y = new Array()
        for (var i = 0; i < 4; i++) {
            rand[i] = nums[Math.floor(Math.random() * nums.length)]
            x[i] = i * 16 + 5;
            y[i] = Math.random() * (height*0.47) + height/2;
            context.fillText(rand[i], x[i], y[i])
        }
        //alert(rand)
        //画3条随机线
        for (var i = 0; i < 3; i++) {
            drawline(canvas, context)
        }

        // 画30个随机点
        for (var i = 0; i < 30; i++) {
            drawDot(canvas, context)
        }
        convertCanvasToImage(canvas,rand)
        return rand.join('').toLocaleLowerCase()
    }

    // 随机线
    function drawline(canvas, context) {
        context.moveTo(Math.floor(Math.random() * canvas.width), Math.floor(Math.random() * canvas.height))             //随机线的起点x坐标是画布x坐标0位置，y坐标是画布高度的随机数
        context.lineTo(Math.floor(Math.random() * canvas.width), Math.floor(Math.random() * canvas.height))  //随机线的终点x坐标是画布宽度，y坐标是画布高度的随机数
        context.lineWidth = 0.5;                                                  //随机线宽
        context.strokeStyle = 'rgba(50,50,50,0.3)';                               //随机线描边属性
        context.stroke()                                                         //描边，即起点描到终点
    }
    // 随机点(所谓画点其实就是画1px像素的线，方法不再赘述)
    function drawDot(canvas, context) {
        var px = Math.floor(Math.random() * canvas.width)
        var py = Math.floor(Math.random() * canvas.height)
        context.moveTo(px, py)
        context.lineTo(px + 1, py + 1)
        context.lineWidth = 0.2;
        context.stroke()

    }
    // 绘制图片
    function convertCanvasToImage(canvas,strArr) {
        document.getElementById(id).style.display = "none";
        var image = document.getElementById(imgId)
        image.src = canvas.toDataURL("image/png")
        image.setAttribute('code',strArr.join(''))
        return image;
    }

    // 点击图片刷新
    document.getElementById(imgId).onclick = function () {
        $('#'+id).remove()
        $('#'+inputId).parent().next().prepend('<canvas width="'+width+'" height="'+height+'" id="'+id+'"></canvas>')
        drawCode(id)
    }
}