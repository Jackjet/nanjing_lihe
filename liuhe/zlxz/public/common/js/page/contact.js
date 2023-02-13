$(document).ready(function () {
    //初始化地图
    var map = new BMapGL.Map("map-box");          // 创建地图实例 
    var point = new BMapGL.Point(118.828922, 32.330062);  // 创建点坐标 
    map.centerAndZoom(point, 11);
    var marker = new BMapGL.Marker(point);  // 创建标注
    map.addOverlay(marker);
    var opts = {
        width: 250,     // 信息窗口宽度
        height: 100,    // 信息窗口高度
        title: "地址"  // 信息窗口标题
    }
    var infoWindow = new BMapGL.InfoWindow("六合经济开发区科创园B3一层", opts);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow, map.getCenter());
})