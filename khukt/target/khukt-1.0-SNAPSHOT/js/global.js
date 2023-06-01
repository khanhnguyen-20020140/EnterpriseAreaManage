/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var hostname = "localhost";//"10.168.1.196";
var port = "8086";
var portweb = "8080";
var map;
var activeId = "Outdoor";
var activedHtml = "";
var taoduan_dautusanxuat = 0;
var taoduan_hatangkythuat = 0;
var active_zoomin = 0;
var lsHighlightHTPolygon = [];
var lsHighlightQHPolygon = [];
var lsHighlightQHLine = [];
var lsHighlightQHPoint = [];
var idEditData = -1;
var dataResponse = {};//dành cho SQL động
var xx = 0;
var arrStyle = [];
var idDuanHTKTSelected = -1;
var hotedithtkt, hotaddhtkt;
var hotdn, hotdadtsx, hotdaxdht, hotkqdtsx, hotnv;
var kqdtsxreport_select, kqdtsxreport_select_id;
var columnDownloadKQSXKD, destFileKQSXKD;
var duandautusanxuatreport_select, duandautusanxuatreport_select_id;
var columnDownloadduandautusanxuat, destFileduandautusanxuat;
var duanxaydunghatangreport_select, duanxaydunghatangreport_select_id;
var columnDownloadduanxaydunghatang, destFileduanxaydunghatang;
var doanhnghiepreport_select, doanhnghiepreport_select_id;
var columnDownloaddoanhnghiep, destFiledoanhnghiep;
var id_duanXaydunghatangs_selectInthanhphanXDHT;
var destFileTDXDHT, columnDownloadTDXDHT, idtiendoChitiet, idduanHTKTbytiendo;
var hottiendoXDHT,hottiendoXDHTCS, destFileTDXDHTCS;



var is_project_creating = function () {
    var rc = false;
    if (taoduan_dautusanxuat > 0 || taoduan_hatangkythuat > 0) {
        alert("Dự án đang được tạo!");
        rc = true;
    }
    return rc;
}

var cleardraw_hatangkythuat = function () {
    taoduan_hatangkythuat = 0;
    lsHighlightQHPolygon = [];
    lsHighlightQHLine = [];
    lsHighlightQHPoint = [];
    map.setFilter('khucn_hatangkythuat_polygon_highlighted', ['in', 'highlight', ...lsHighlightQHPolygon]);
    map.setFilter('khucn_hatangkythuat_line_highlighted', ['in', 'highlight', ...lsHighlightQHLine]);
    map.setFilter('khucn_hatangkythuat_circle_highlighted', ['in', 'highlight', ...lsHighlightQHPoint]);
};

var loadHtml = function (idHtml, path2html) {
    if (is_project_creating()) return;
    if (activedHtml === idHtml)
        return;
    if ($('#' + idHtml).text().length > 0) {
        $('#' + idHtml).show();
        if (activedHtml !== "")
            $('#' + activedHtml).hide();
    } else {
        $('#' + idHtml).load(path2html);
        $('#' + idHtml).show();
        if (activedHtml !== "")
            $('#' + activedHtml).hide();
    }
    activedHtml = idHtml;
};

var msgFeature = function (toado, title) {
    popup
        .setLngLat(toado)//var lngLat = [-77.0353, 38.8895];
        .setText(title)
        .addTo(map);
};

// Create a popup, but don't add it to the map yet.
const popup = new maplibregl.Popup({
    closeButton: false
});

var reloadHtml = function (idHtml, path2html) {
    $('#' + idHtml).empty();
    $('#' + idHtml).load(path2html);
};
// here
var loadCbthanphanXDHTHtml = function (idControlSelect, id, text) {
    var keys = [];
    if ($('#' + idControlSelect + ' option').length > 1) {
        //        if (id !== -1)
        console.log($('#' + idControlSelect + ' option').length)
        $('#' + idControlSelect).val(text);
        return;
    }
    console.log("???")
    console.log(idControlSelect + "   " + id + "   " + text)

    var url = 'http://' + hostname + ':' + port + '/hatangkythuatpolygon/duanhatangkythuat/tenthanhphan/' + id;
    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function (data) {
            if (data.length === 0)
                return;
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                if (keys.length === 0) {
                    for (var k in jsonData)
                        keys.push(k);
                }
                console.log(jsonData)
                // console.log(jsonData[keys[0]])
                $('#' + idControlSelect).append($('<option>', {
                    value: jsonData,
                    text: jsonData
                }));
            });
            //            if (id !== -1)

        }
    });
    $('#' + idControlSelect).val(text);
    console.log(text)
    console.log($('#' + idControlSelect).val())

};


var loadCbthanphanXDHTCSHtml = function (idControlSelect, id, text) {
    var keys = [];
    if ($('#' + idControlSelect + ' option').length > 1) {
        //        if (id !== -1)
        console.log($('#' + idControlSelect + ' option').length)
        $('#' + idControlSelect).val(text);
        return;
    }
    console.log("???")
    console.log(idControlSelect + "   " + id + "   " + text)

    var url = 'http://' + hostname + ':' + port + '/hatangkythuatpolygon/duandautusanxuat/tenthanhphan/' + id;
    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function (data) {
            if (data.length === 0)
                return;
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                if (keys.length === 0) {
                    for (var k in jsonData)
                        keys.push(k);
                }
                console.log(jsonData)
                // console.log(jsonData[keys[0]])
                $('#' + idControlSelect).append($('<option>', {
                    value: jsonData,
                    text: jsonData
                }));
            });
            //            if (id !== -1)

        }
    });
    $('#' + idControlSelect).val(text);
    console.log(text)
    console.log($('#' + idControlSelect).val())

};

var loadCbtiendodtsxHtmlOnChange = function (idControlSelect, id) {
    var keys = [];
    document.getElementById(idControlSelect).innerHTML = "<option>Chọn ngày báo cáo</option>";


    
    var url = 'http://' + hostname + ':' + port + '/tiendoXDHT/getNgaybaocaobyIdduandtsx/' + id;

    console.log(url)
    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function (data) {
            if (data.length === 0)
                return;
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                if (keys.length === 0) {
                    for (var k in jsonData)
                        keys.push(k);
                }
                console.log(jsonData)
                const myArray = jsonData.split("-");
                var newDate = "";
                newDate = newDate.concat(myArray[2]);
                newDate = newDate.concat("/");
                newDate = newDate.concat(myArray[1]);
                newDate = newDate.concat("/");

                newDate = newDate.concat(myArray[0]);
                $('#' + idControlSelect).append($('<option>', {
                    value: jsonData,
                    text: newDate
                }));
            });
            //            if (id !== -1)
            // $('#' + idControlSelect).val(text);
        }
    });


};



var loadCbtiendoHtmlOnChange = function (idControlSelect, id) {
    var keys = [];
    document.getElementById(idControlSelect).innerHTML = "<option>Chọn ngày báo cáo</option>";


    
    var url = 'http://' + hostname + ':' + port + '/tiendoXDHT/getNgaybaocaobyIdduan/' + id;
    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function (data) {
            if (data.length === 0)
                return;
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                if (keys.length === 0) {
                    for (var k in jsonData)
                        keys.push(k);
                }
                console.log(jsonData)
                const myArray = jsonData.split("-");
                var newDate = "";
                newDate = newDate.concat(myArray[2]);
                newDate = newDate.concat("/");
                newDate = newDate.concat(myArray[1]);
                newDate = newDate.concat("/");

                newDate = newDate.concat(myArray[0]);
                $('#' + idControlSelect).append($('<option>', {
                    value: jsonData,
                    text: newDate
                }));
            });
            //            if (id !== -1)
            // $('#' + idControlSelect).val(text);
        }
    });


};



var loadCbthanphanXDHTCSHtmlOnChange = function (idControlSelect, id) {
    var keys = [];
    document.getElementById(idControlSelect).innerHTML = "<option>Chọn tên thành phần</option>";


    var url = 'http://' + hostname + ':' + port + '/hatangkythuatpolygon/duandautusanxuat/tenthanhphan/' + id;
    console.log(url)
    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function (data) {
            if (data.length === 0)
                return;
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                if (keys.length === 0) {
                    for (var k in jsonData)
                        keys.push(k);
                }
                console.log(jsonData)
                console.log(jsonData[keys[0]])
                $('#' + idControlSelect).append($('<option>', {
                    value: jsonData,
                    text: jsonData
                }));
            });
            //            if (id !== -1)
            // $('#' + idControlSelect).val(text);
        }
    });


};

var loadCbthanphanXDHTHtmlOnChange = function (idControlSelect, id) {
    var keys = [];
    document.getElementById(idControlSelect).innerHTML = "<option>Chọn tên thành phần</option>";


    // http://localhost:8086/hatangkythuatpolygon/duanhatangkythuat/tenthanhphan/33
    var url = 'http://' + hostname + ':' + port + '/hatangkythuatpolygon/duanhatangkythuat/tenthanhphan/' + id;
    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function (data) {
            if (data.length === 0)
                return;
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                if (keys.length === 0) {
                    for (var k in jsonData)
                        keys.push(k);
                }
                console.log(jsonData)
                console.log(jsonData[keys[0]])
                $('#' + idControlSelect).append($('<option>', {
                    value: jsonData,
                    text: jsonData
                }));
            });
            //            if (id !== -1)
            // $('#' + idControlSelect).val(text);
        }
    });


};




var loadCbHtml = function (idControlSelect, url, id) {
    var keys = [];
    if ($('#' + idControlSelect + ' option').length > 1) {
        //        if (id !== -1)
        $('#' + idControlSelect).val(id);
        return;
    }

    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function (data) {
            if (data.length === 0)
                return;
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                if (keys.length === 0) {
                    for (var k in jsonData)
                        keys.push(k);
                }

                $('#' + idControlSelect).append($('<option>', {
                    value: jsonData[keys[0]],
                    text: jsonData[keys[1]]
                }));
            });
            //            if (id !== -1)
            $('#' + idControlSelect).val(id);
        }
    });


};





var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
    return false;
};

/*
 * 
 * @param {type} url là Restful API
 * @param {type} objs là mảng javacsript được trả về
 * @returns {Array} objs
 */
var dynamicSQL = function (url) {
    dataResponse = {};
    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function (data) {
            //console.log('data:' + data);
            if (data === undefined) {
                dataResponse = data;
            } else {
                Object.keys(data).forEach(function (key) {
                    dataResponse[key] = data[key];
                });
            }
        }
    });
};



$("#dm_baocao_duandautusanxuat").click(function () {


    var mapView = document.getElementById("mapView");
    var content = document.getElementById("content");
    //var button = document.getElementById("change-button");

    var x = windowWidth - $("#leftSide").width();
    mapView.style.width = '0%';
    content.style.width = x + 'px';
    content.style.display = 'block';


    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_baocao_duandautusanxuat", 'duandautusanxuat-report.html');

});

$("#dm_tiendoxaydunghatang_duandautusanxuat").click(function () {


    if ($(window).width() > 1250) {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }


    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_tiendoxaydunghatangcoso_mini", 'tiendoxaydunghatangcoso-mini.html');

});


$("#dm_baocaotiendoxaydunghatang_duanxaydunghatang").click(function () {


    var mapView = document.getElementById("mapView");
    var content = document.getElementById("content");
    //var button = document.getElementById("change-button");

    var x = windowWidth - $("#leftSide").width();
    mapView.style.width = '0%';
    content.style.width = x + 'px';
    content.style.display = 'block';


    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_baocaotiendoxaydunghatang_duanxaydunghatang", 'tiendoxaydunghatang-duanxaydunghatang-report.html');

});


$("#dm_baocaotiendoxaydunghatang_duandautusanxuat").click(function () {


    var mapView = document.getElementById("mapView");
    var content = document.getElementById("content");
    //var button = document.getElementById("change-button");

    var x = windowWidth - $("#leftSide").width();
    mapView.style.width = '0%';
    content.style.width = x + 'px';
    content.style.display = 'block';


    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_baocaotiendoxaydunghatang_duandautusanxuat", 'tiendoxaydunghatang-duandautusanxuat-report.html');

});

$("#dm_baocaoketquadautusanxuat").click(function () {


    var mapView = document.getElementById("mapView");
    var content = document.getElementById("content");
    //var button = document.getElementById("change-button");

    var x = windowWidth - $("#leftSide").width();
    mapView.style.width = '0%';
    content.style.width = x + 'px';
    content.style.display = 'block';


    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_baocaoketquadautusanxuat", 'ketquaSanxuatkinhdoanh-report.html');
});

$("#dm_khucn").click(function () {

    if ($(window).width() > 1250) {
        $('#mapView').css("width", "60%");
        $('#content').css("width", "40%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }

    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_khucn_mini", 'khucn-mini.html');
});

$("#dm_thanhphanxaydunghatang").click(function () {

    if ($(window).width() > 1250) {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }


    $(".doanhnghiep_open").css("display", "none");
    console.log("???")
    loadHtml("window_thanhphanxaydunghatang_mini", 'thanhphanxaydunghatang-mini.html');
});

$("#dm_duandautusanxuat").click(function () {

    if ($(window).width() > 1250) {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }


    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_duandautusanxuat_mini", 'duandautusanxuat-mini.html');
});
//here
$("#dm_tiendoxaydunghatangkythuat").click(function () {

    if ($(window).width() > 1250) {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }


    $(".doanhnghiep_open").css("display", "none");

    loadHtml("window_tiendoxaydunghatangkythuat_mini", 'tiendoxaydunghatangkythuat-mini.html');
});

$("#dm_ketquadautusanxuat").click(function () {

    if ($(window).width() > 1250) {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }


    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_ketquadautusanxuat_mini", 'ketquadautusanxuat-mini.html');
});

$("#dm_duanxaydunghatang").click(function () {

    if ($(window).width() > 1250) {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }


    $(".doanhnghiep_open").css("display", "none");
    loadHtml("window_duanxaydunghatang_mini", 'duanxaydunghatang-mini.html');
});

$("#dm_duanxaydunghatang_create").click(function () {
    $('#mapView').css("width", "70%");
    $('#content').css("width", "30%");
    $(".doanhnghiep_open").css("display", "none");
    loadHtml('window_thanhphanxaydunghatang_add', 'duanxaydunghatang-create.html');
    taoduan_hatangkythuat = 2;
    lsHighlightQHPolygon = []; lsHighlightQHLine = []; lsHighlightQHPoint = [];
});

$("#dm_doanhnghiep").click(function () {

    if ($(window).width() > 1250) {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }


    $(".doanhnghiep_open").css("display", "none");
    loadHtml('window_doanhnghiep_mini', 'doanhnghiep-mini.html');
});

$("#dm_nguonvon").click(function () {

    if ($(window).width() > 1250) {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
    }

    else if ($(window).width() < 1250 && $(window).width() > 991) {
        $('#mapView').css("width", "50%");
        $('#content').css("width", "50%");
    }

    else {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "100%");
        $("#leftSide").removeClass("expanded");
    }


    $(".doanhnghiep_open").css("display", "none");
    loadHtml('window_nguonvon_mini', 'nguonvon-mini.html');
});

$('.doanhnghiep_open').click(function () {
    event.preventDefault();


    if (activedHtml === 'window_thanhphanxaydunghatang') {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_thanhphanxaydunghatang', 'thanhphanxaydunghatang.html');
    }

    if (activedHtml === 'window_thanhphanxaydunghatang_mini') {
        if ($(window).width() > 1250) {
            $('#mapView').css("width", "70%");
            $('#content').css("width", "30%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_thanhphanxaydunghatang_mini', 'thanhphanxaydunghatang-mini.html');
    }



    if (activedHtml === 'window_tiendoxaydunghatangkythuat') {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_tiendoxaydunghatangkythuat', 'tiendoxaydunghatangkythuat.html');
    }

    if (activedHtml === 'window_tiendoxaydunghatangkythuat_mini') {
        if ($(window).width() > 1250) {
            $('#mapView').css("width", "70%");
            $('#content').css("width", "30%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_tiendoxaydunghatangkythuat_mini', 'tiendoxaydunghatangkythuat-mini.html');
    }





    if (activedHtml === 'window_doanhnghiep') {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_doanhnghiep', 'doanhnghiep.html');
    }
    if (activedHtml === 'window_doanhnghiep_mini') {
        if ($(window).width() > 1250) {
            $('#mapView').css("width", "70%");
            $('#content').css("width", "30%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_doanhnghiep_mini', 'doanhnghiep-mini.html');
    }
    if (activedHtml === 'window_thanhphanxaydunghatang_add') {
        $('#mapView').css("width", "70%");
        $('#content').css("width", "30%");
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_thanhphanxaydunghatang_add', 'duanxaydunghatang-create.html');
    }
    if (activedHtml === 'window_khucn_mini') {

        if ($(window).width() > 1250) {
            $('#mapView').css("width", "60%");
            $('#content').css("width", "40%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }

        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_khucn_mini', 'khucn-mini.html');
    }

    if (activedHtml === 'window_thanhphanxaydunghatang') {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_thanhphanxaydunghatang', 'thanhphanxaydunghatang.html');
    }
    if (activedHtml === 'window_thanhphanxaydunghatang_mini') {
        if ($(window).width() > 1250) {
            $('#mapView').css("width", "70%");
            $('#content').css("width", "30%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_thanhphanxaydunghatang_mini', 'thanhphanxaydunghatang-mini.html');
    }

    if (activedHtml === 'window_duandautusanxuat') {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_duandautusanxuat', 'duandautusanxuat.html');
    }
    if (activedHtml === 'window_duandautusanxuat_mini') {
        if ($(window).width() > 1250) {
            $('#mapView').css("width", "70%");
            $('#content').css("width", "30%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_duandautusanxuat_mini', 'duandautusanxuat-mini.html');
    }

    if (activedHtml === 'window_ketquadautusanxuat') {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_ketquadautusanxuat', 'ketquadautusanxuat.html');
    }
    if (activedHtml === 'window_ketquadautusanxuat_mini') {
        if ($(window).width() > 1250) {
            $('#mapView').css("width", "70%");
            $('#content').css("width", "30%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_ketquadautusanxuat_mini', 'ketquadautusanxuat-mini.html');
    }

    if (activedHtml === 'window_duanxaydunghatang') {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_duanxaydunghatang', 'duanxaydunghatang.html');
    }
    if (activedHtml === 'window_duanxaydunghatang_mini') {
        if ($(window).width() > 1250) {
            $('#mapView').css("width", "70%");
            $('#content').css("width", "30%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_duanxaydunghatang_mini', 'duanxaydunghatang-mini.html');
    }

    if (activedHtml === 'window_nguonvon_mini') {
        if ($(window).width() > 1250) {
            $('#mapView').css("width", "70%");
            $('#content').css("width", "30%");
        }

        else if ($(window).width() < 1250 && $(window).width() > 991) {
            $('#mapView').css("width", "50%");
            $('#content').css("width", "50%");
        }

        else {
            $('#mapView').css("width", "0%");
            $('#content').css("width", "100%");
        }
        $(".doanhnghiep_open").css("display", "none");
        loadHtml('window_nguonvon_mini', 'nguonvon-mini.html');
    }

    if (activedHtml === "window_baocaoketquadautusanxuat") {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");

        loadHtml("window_baocaoketquadautusanxuat", 'ketquaSanxuatkinhdoanh-report.html');

    }

    if (activedHtml === "window_baocao_duandautusanxuat") {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");

        loadHtml("window_baocao_duandautusanxuat", 'duandautusanxuat-report.html');

    }


    if (activedHtml === "window_baocaotiendoxaydunghatang_duanxaydunghatang") {
        $('#mapView').css("width", "0%");
        $('#content').css("width", "calc(100% - 131px)");
        $(".doanhnghiep_open").css("display", "none");

        loadHtml("window_baocaotiendoxaydunghatang_duanxaydunghatang", 'tiendoxaydunghatang-duanxaydunghatang-report.html');

    }
});

$('.doanhnghiep_open').click(function () {
    event.preventDefault();
    $(".doanhnghiep_open").css("display", "none");
    if ($('.doanhnghiep_open').hasClass("active")) {
        $(".doanhnghiep_open").css("display", "none");
        $(".doanhnghiep_open").removeClass("active");
    }
    var addfont = document.getElementById("radius");
    addfont.style.display = 'block';
});
/*
 * map functions
 */
var getUrlWMTS = function (storename, layername, epsg) {
    return "http://" + hostname + ":" + portweb + "/geoserver/gwc/service/wmts?" +
        "REQUEST=GetTile&SERVICE=WMTS&VERSION=1.0.0&LAYER=" + storename + ":" + layername +
        "&STYLE=&TILEMATRIX=EPSG:" + epsg + ":{z}&TILEMATRIXSET=EPSG:" + epsg + "&" +
        "FORMAT=application/vnd.mapbox-vector-tile&TILECOL={x}&TILEROW={y}";
}
var getSource = function (nameid, type, storename, layername, epsg, paint, filter, layout, minzoom, maxzoom) {
    return {
        "id": nameid,
        "type": type,
        'source': {
            'type': 'vector',
            'tiles': [
                getUrlWMTS(storename, layername, epsg)
            ]
        },
        "source-layer": layername,
        "paint": paint,
        "filter": filter,
        "layout": layout,
        "minzoom": minzoom,
        "maxzoom": maxzoom
    }
};

var getMapQuyhoachPolygon = function (nameid, type, storename, layername, epsg, outlinecolor, filter, visible) {
    var t = null;
    $.ajax({
        url: 'http://' + hostname + ':' + port + '/stylefill/stylefills/',
        type: "GET",
        async: false,
        success: function (data) {
            let mang = ['interpolate', ['linear'], ['get', 'id_loaiquyhoach']];
            let mangOpacity = ['interpolate', ['linear'], ['get', 'id_loaiquyhoach']];
            var ketquaObjs = [];
            Object.keys(data).forEach(function (key) {
                //console.log(key, data[key]); 
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                var element = {};
                element.id_loaiquyhoach = jsonData.quyhoachId.id;
                element.fillColor = jsonData.fillColor;
                ketquaObjs.push(element);
            });
            ketquaObjs.sort(function (a, b) {
                return a.id_loaiquyhoach < b.id_loaiquyhoach;
            });

            //console.clear();
            for (let i = 0; i < ketquaObjs.length; i++) {
                //console.log('in: '+JSON.stringify(ketquaObjs[i]));
                mang.push(ketquaObjs[i].id_loaiquyhoach);
                mang.push(ketquaObjs[i].fillColor);
                mangOpacity.push(ketquaObjs[i].id_loaiquyhoach);
                mangOpacity.push(1);
            }
            t = {
                "id": nameid,
                "type": type,
                "source": {
                    "type": "vector",
                    "tiles": [
                        getUrlWMTS(storename, layername, epsg)
                    ]
                },
                "source-layer": layername,
                "paint": {
                    "fill-color": mang,
                    "fill-outline-color": outlinecolor,
                    "fill-opacity": mangOpacity
                },
                "filter": filter,
                "layout": {
                    "visibility": visible
                }
            }
        }
    });
    return t;
};

var getMapQuyhoachLine = function (nameid, type, storename, layername, epsg, width, dasharray, filter, linecap, linejoin) {
    var t = null;
    $.ajax({
        url: 'http://' + hostname + ':' + port + '/styleline/stylelines/',
        type: "GET",
        async: false,
        success: function (data) {
            let mang = ['interpolate', ['linear'], ['get', 'id_loaiquyhoach']];
            let mangOpacity = ['interpolate', ['linear'], ['get', 'id_loaiquyhoach']];
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                //console.log(jsonData.fillColor, jsonData.quyhoachId.id);
                mang.push(jsonData.quyhoachId.id);
                mang.push(jsonData.lineColor);
                mangOpacity.push(jsonData.quyhoachId.id);
                mangOpacity.push(1);
            });
            t = {
                "id": nameid,
                "type": type,
                'source': {
                    'type': 'vector',
                    'tiles': [
                        getUrlWMTS(storename, layername, epsg)
                    ]
                },
                "source-layer": layername,
                "paint": {
                    "line-color": mang,
                    "line-width": width,
                    "line-dasharray": dasharray,
                    "line-opacity": mangOpacity
                },
                "filter": filter,
                "layout": {
                    "line-cap": linecap,
                    "line-join": linejoin,
                    "visibility": "visible"
                }
            }
        }
    });
    return t;
};

var getMapQuyhoachPoint = function (nameid1, type, storename, layername, epsg, radius) {
    var t = null;
    $.ajax({
        url: 'http://' + hostname + ':' + port + '/styleicon/styleicons/',
        type: "GET",
        async: false,
        success: function (data) {
            let mang = ['interpolate', ['linear'], ['get', 'id_loaiquyhoach']];
            let mangOpacity = ['interpolate', ['linear'], ['get', 'id_loaiquyhoach']];
            Object.keys(data).forEach(function (key) {
                let str = JSON.stringify(data[key]);
                var jsonData = JSON.parse(str);
                mang.push(jsonData.quyhoachId.id);
                mang.push(jsonData.iconColor);
                mangOpacity.push(jsonData.quyhoachId.id);
                mangOpacity.push(1);
            });
            t = {
                "id": nameid1,
                "type": type,
                'source': {
                    'type': 'vector',
                    'tiles': [
                        getUrlWMTS(storename, layername, epsg)
                    ]
                },
                "source-layer": layername,
                "paint": {
                    "circle-color": mang,
                    "circle-radius": radius,
                    "circle-opacity": mangOpacity
                },
                "layout": {
                    "visibility": "visible"
                }
            }
        }
    });
    return t;
};

var changeColorLayer = function (e, id_loaiquyhoach, layername) {
    //console.log('color: '+e.value);     
    var tenbien = "";
    if (layername === 'khucn_quyhoach_polygon' || layername === 'khucn_hatangkythuat_polygon' || layername === 'khucn_hientrang_polygon') {
        tenbien = "fill-color";
    } else if (layername === 'khucn_quyhoach_line' || layername === 'khucn_hatangkythuat_line') {
        tenbien = "line-color";
    } else if (layername === 'khucn_quyhoach_circle' || layername === 'khucn_hatangkythuat_circle') {
        tenbien = "circle-color";
    }
    var layer = map.getLayer(layername);
    var ta = layer.getPaintProperty(tenbien);
    for (var i = 0; i < ta.length; i++) {
        if (ta[i] === id_loaiquyhoach)
            ta[i + 1] = e.value;
    }
    map.setPaintProperty(layername, tenbien, ta);
    map.style._updateLayer(layer);
};

var changeOpacityLayer = function (e, id_loaiquyhoach, layername) {
    //console.log('opacity: ' + e.value);
    var tenbien = "";
    if (layername === 'khucn_quyhoach_polygon' || layername === 'khucn_hatangkythuat_polygon' || layername === 'khucn_hientrang_polygon') {
        tenbien = "fill-opacity";
    } else if (layername === 'khucn_quyhoach_line' || layername === 'khucn_hatangkythuat_line') {
        tenbien = "line-opacity";
    } else if (layername === 'khucn_quyhoach_circle' || layername === 'khucn_hatangkythuat_circle') {
        tenbien = "circle-opacity";
    }
    var layer = map.getLayer(layername);
    var ta = layer.getPaintProperty(tenbien);
    for (var i = 0; i < ta.length; i++) {
        if (ta[i] === id_loaiquyhoach)
            ta[i + 1] = parseFloat(e.value);
    }
    map.setPaintProperty(layername, tenbien, ta);
    map.style._updateLayer(layer);
};

var getFeatures_QHPolygonHighlight = function () {
    const bounds = map.getBounds();
    var features = map.queryRenderedFeatures(bounds);
    //console.clear();

    var rc = [];
    for (let j = 0; j < lsHighlightQHPolygon.length; j++) {
        for (let i = 0; i < features.length; i++) {
            if (features[i].source != "khucn_quyhoach_polygon")
                continue;
            if (lsHighlightQHPolygon[j] === features[i].properties.highlight) {
                var element = {};
                element.gid = features[i].id;
                element.ten = features[i].properties.ten;
                element.id_loaiquyhoach = features[i].properties.id_loaiquyhoach;

                rc.push(element);
                //console.log(element);
                break;
            }
        }
    }

    return rc;
};

//nameid, type, storename, layername, epsg, paint, filter, layout, minzoom, maxzoom
var bandopolygon_nghean = [
    getMapQuyhoachPolygon("khucn_quyhoach_polygon", "fill", "khukt", "tbl_quyhoach_polygon", 900913, "#000000", ["all"], "visible"),
    getMapQuyhoachPolygon("khucn_hatangkythuat_polygon", "fill", "khukt", "tbl_hatangkythuat_polygon", 900913, "#000000", ["all"], "visible"),
    getMapQuyhoachPolygon("khucn_hientrang_polygon", "fill", "khukt", "tbl_hientrang_polygon", 900913, "#000000", ["all"], "visible")
];

var bando_highlight = [
    //getSource = function (nameid, type, storename, layername, epsg, paint, filter, layout, minzoom, maxzoom)        'fill-outline-color': '#484896'        
    getSource("khucn_hatangkythuat_polygon_highlighted", "fill", "khukt", "tbl_hatangkythuat_polygon", 900913, { 'fill-outline-color': 'yellow', 'fill-color': '#6e599f', 'fill-opacity': 0.75 }, ['in', 'highlight', ''], { 'visibility': 'visible' }, 0, 23),
    getSource("khucn_hatangkythuat_line_highlighted", "line", "khukt", "tbl_hatangkythuat_line", 900913, { "line-color": '#6e599f', "line-width": 2, "line-dasharray": [1], 'line-opacity': 0.75 }, ['in', 'highlight', ''], { 'visibility': 'visible' }, 0, 23),
    getSource("khucn_hatangkythuat_circle_highlighted", "circle", "khukt", "tbl_hatangkythuat_point", 900913, { 'circle-color': '#6e599f', 'circle-radius': 5, 'circle-opacity': 0.75 }, ['in', 'highlight', ''], { 'visibility': 'visible' }, 0, 23)
];

var bandoline_nghean = [
    getSource("line_huyen", "line", "khukt", "line_huyen_4326", 900913, { 'line-color': 'purple', 'line-width': 2, 'line-dasharray': [8, 3] }, ["all"], { 'line-cap': 'butt', 'line-join': 'bevel', 'visibility': 'visible' }, 0, 23),
    getSource("line_xa", "line", "khukt", "line_xa_4326", 900913, { 'line-color': 'purple', 'line-width': 0.5, 'line-dasharray': [8, 3] }, ["all"], { 'line-cap': 'butt', 'line-join': 'bevel', 'visibility': 'visible' }, 0, 23),
    getMapQuyhoachLine("khucn_quyhoach_line", "line", "khukt", "tbl_quyhoach_line", 900913, 2, [1], ["all"], "butt", "bevel"),
    getMapQuyhoachLine("khucn_hatangkythuat_line", "line", "khukt", "tbl_hatangkythuat_line", 900913, 2, [1], ["all"], "butt", "bevel")
];

var bandopoint_nghean = [
    getMapQuyhoachPoint("khucn_quyhoach_circle", "circle", "khukt", "tbl_quyhoach_point", 900913, 4),
    getMapQuyhoachPoint("khucn_hatangkythuat_circle", "circle", "khukt", "tbl_hatangkythuat_point", 900913, 4)
];

var switchMap = function (id) {
    if (activeId !== id) {
        activeId = id;
        if (map) {
            //console.log(map.style.sourceCaches);
            //console.log(map.getStyle().layers);
            //const ownerLayer = map.getLayer('khucn');                
            if (map.getLayer("khucn_quyhoach_polygon")) {
                map.removeLayer("khucn_quyhoach_polygon");
                map.removeSource("khucn_quyhoach_polygon");
            }
            if (map.getLayer("khucn_quyhoach_line")) {
                map.removeLayer("khucn_quyhoach_line");
                map.removeSource("khucn_quyhoach_line");
            }
            if (map.getLayer("khucn_quyhoach_circle")) {
                map.removeLayer("khucn_quyhoach_circle");
                map.removeSource("khucn_quyhoach_circle");
            }
            if (map.getLayer("khucn_hatangkythuat_polygon")) {
                map.removeLayer("khucn_hatangkythuat_polygon");
                map.removeSource("khucn_hatangkythuat_polygon");
            }
            if (map.getLayer("khucn_hatangkythuat_line")) {
                map.removeLayer("khucn_hatangkythuat_line");
                map.removeSource("khucn_hatangkythuat_line");
            }
            if (map.getLayer("khucn_hatangkythuat_circle")) {
                map.removeLayer("khucn_hatangkythuat_circle");
                map.removeSource("khucn_hatangkythuat_circle");
            }
            if (map.getLayer("khucn_hientrang_polygon")) {
                map.removeLayer("khucn_hientrang_polygon");
                map.removeSource("khucn_hientrang_polygon");
            }
            map.setStyle('http://' + hostname + ':' + portweb + '/khukt/style/' + activeId + '.json');
            map.on("styledata", function () {
                map.addLayer(bandopolygon_nghean[0]);
                map.addLayer(bandopolygon_nghean[1]);
                //map.addLayer(bandopolygon_nghean[2]);
                map.addLayer(bandoline_nghean[2]);
                map.addLayer(bandoline_nghean[3]);
                map.addLayer(bandopoint_nghean[0]);
                map.addLayer(bandopoint_nghean[1]);
                map.addLayer(bando_highlight[0]);
                map.addLayer(bando_highlight[1]);
                map.addLayer(bando_highlight[2]);
            });
        }
    }
};

try {
    dynamicSQL('http://' + hostname + ':' + port + '/hatangkythuatpolygon/hatangkythuatpolygon/1');
    Object.keys(dataResponse).forEach(function (key) {
        let json = JSON.parse(JSON.stringify(dataResponse[key]));
        //console.log(json);
    });
} catch (e) {
    console.log(e);
}
try {
    dynamicSQL('http://' + hostname + ':' + port + '/stylefill/stylefills/');
    Object.keys(dataResponse).forEach(function (key) {
        let json = JSON.parse(JSON.stringify(dataResponse[key]));
        var e = {};
        e.quyhoach_id = json.quyhoachId.id;
        e.quyhoach_ten = json.quyhoachId.ten;
        e.color = json.fillColor;
        e.chon = 0;
        e.layer = '';
        arrStyle.push(e);
    });
    dynamicSQL('http://' + hostname + ':' + port + '/styleline/stylelines/');
    Object.keys(dataResponse).forEach(function (key) {
        let json = JSON.parse(JSON.stringify(dataResponse[key]));
        var e = {};
        e.quyhoach_id = json.quyhoachId.id;
        e.quyhoach_ten = json.quyhoachId.ten;
        e.color = json.lineColor;
        e.chon = 0;
        e.layer = '';
        arrStyle.push(e);
    });
    dynamicSQL('http://' + hostname + ':' + port + '/styleicon/styleicons/');
    Object.keys(dataResponse).forEach(function (key) {
        let json = JSON.parse(JSON.stringify(dataResponse[key]));
        var e = {};
        e.quyhoach_id = json.quyhoachId.id;
        e.quyhoach_ten = json.quyhoachId.ten;
        e.color = json.iconColor;
        e.chon = 0;
        e.layer = '';
        arrStyle.push(e);
    });
    arrStyle.sort(function (a, b) {
        return a.quyhoach_ten.localeCompare(b.quyhoach_ten);
    });
} catch (e) {
    console.log(e);
}