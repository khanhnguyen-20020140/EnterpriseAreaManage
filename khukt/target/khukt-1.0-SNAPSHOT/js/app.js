(function ($) {
//    "use strict";     

    // Custom options for map
    var options = {
        zoom: 14,
        mapTypeId: 'Styled',
        disableDefaultUI: true,
        mapTypeControlOptions: {
            mapTypeIds: ['Styled']
        }
    };
    var styles = [{
            stylers: [{
                    hue: "#cccccc"
                }, {
                    saturation: -100
                }]
        }, {
            featureType: "road",
            elementType: "geometry",
            stylers: [{
                    lightness: 100
                }, {
                    visibility: "simplified"
                }]
        }, {
            featureType: "road",
            elementType: "labels",
            stylers: [{
                    visibility: "on"
                }]
        }, {
            featureType: "poi",
            stylers: [{
                    visibility: "off"
                }]
        }];

    var newMarker = null;
    var markers = [];

    // json for properties markers on map
    var props = [{
            title: 'Modern Residence in New York',
            image: '1-1-thmb.png',
            type: 'For Sale',
            price: '$1,550,000',
            address: '39 Remsen St, Brooklyn, NY 11201, USA',
            bedrooms: '3',
            bathrooms: '2',
            area: '3430 Sq Ft',
            position: {
                lat: 40.696047,
                lng: -73.997159
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'Hauntingly Beautiful Estate',
            image: '2-1-thmb.png',
            type: 'For Rent',
            price: '$1,750,000',
            address: '169 Warren St, Brooklyn, NY 11201, USA',
            bedrooms: '2',
            bathrooms: '2',
            area: '4430 Sq Ft',
            position: {
                lat: 40.688042,
                lng: -73.996472
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'Sophisticated Residence',
            image: '3-1-thmb.png',
            type: 'For Sale',
            price: '$1,340,000',
            address: '38-62 Water St, Brooklyn, NY 11201, USA',
            bedrooms: '2',
            bathrooms: '3',
            area: '2640 Sq Ft',
            position: {
                lat: 40.702620,
                lng: -73.989682
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'House With a Lovely Glass-Roofed Pergola',
            image: '4-1-thmb.png',
            type: 'For Sale',
            price: '$1,930,000',
            address: 'Wunsch Bldg, Brooklyn, NY 11201, USA',
            bedrooms: '3',
            bathrooms: '2',
            area: '2800 Sq Ft',
            position: {
                lat: 40.694355,
                lng: -73.985229
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'Luxury Mansion',
            image: '5-1-thmb.png',
            type: 'For Rent',
            price: '$2,350,000',
            address: '95 Butler St, Brooklyn, NY 11231, USA',
            bedrooms: '2',
            bathrooms: '2',
            area: '2750 Sq Ft',
            position: {
                lat: 40.686838,
                lng: -73.990078
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'Modern Residence in New York',
            image: '1-1-thmb.png',
            type: 'For Sale',
            price: '$1,550,000',
            address: '39 Remsen St, Brooklyn, NY 11201, USA',
            bedrooms: '3',
            bathrooms: '2',
            area: '3430 Sq Ft',
            position: {
                lat: 40.703686,
                lng: -73.982910
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'Hauntingly Beautiful Estate',
            image: '2-1-thmb.png',
            type: 'For Rent',
            price: '$1,750,000',
            address: '169 Warren St, Brooklyn, NY 11201, USA',
            bedrooms: '2',
            bathrooms: '2',
            area: '4430 Sq Ft',
            position: {
                lat: 40.702189,
                lng: -73.995098
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'Sophisticated Residence',
            image: '3-1-thmb.png',
            type: 'For Sale',
            price: '$1,340,000',
            address: '38-62 Water St, Brooklyn, NY 11201, USA',
            bedrooms: '2',
            bathrooms: '3',
            area: '2640 Sq Ft',
            position: {
                lat: 40.687417,
                lng: -73.982653
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'House With a Lovely Glass-Roofed Pergola',
            image: '4-1-thmb.png',
            type: 'For Sale',
            price: '$1,930,000',
            address: 'Wunsch Bldg, Brooklyn, NY 11201, USA',
            bedrooms: '3',
            bathrooms: '2',
            area: '2800 Sq Ft',
            position: {
                lat: 40.694120,
                lng: -73.974413
            },
            markerIcon: "marker-green.png"
        }, {
            title: 'Luxury Mansion',
            image: '5-1-thmb.png',
            type: 'For Rent',
            price: '$2,350,000',
            address: '95 Butler St, Brooklyn, NY 11231, USA',
            bedrooms: '2',
            bathrooms: '2',
            area: '2750 Sq Ft',
            position: {
                lat: 40.682665,
                lng: -74.000934
            },
            markerIcon: "marker-green.png"
        }];

    // custom infowindow object
    var infobox = new InfoBox({
        disableAutoPan: false,
        maxWidth: 202,
        pixelOffset: new google.maps.Size(-101, -285),
        zIndex: null,
        boxStyle: {
            background: "url('images/infobox-bg.png') no-repeat",
            opacity: 1,
            width: "202px",
            height: "245px"
        },
        closeBoxMargin: "28px 26px 0px 0px",
        closeBoxURL: "",
        infoBoxClearance: new google.maps.Size(1, 1),
        pane: "floatPane",
        enableEventPropagation: false
    });

    // function that adds the markers on map
    var addMarkers = function (props, map) {
        $.each(props, function (i, prop) {
            var latlng = new google.maps.LatLng(prop.position.lat, prop.position.lng);
            var marker = new google.maps.Marker({
                position: latlng,
                map: map,
                icon: new google.maps.MarkerImage(
                        'images/' + prop.markerIcon,
                        null,
                        null,
                        null,
                        new google.maps.Size(36, 36)
                        ),
                draggable: false,
                animation: google.maps.Animation.DROP,
            });
            var infoboxContent = '<div class="infoW">' +
                    '<div class="propImg">' +
                    '<img src="images/prop/' + prop.image + '">' +
                    '<div class="propBg">' +
                    '<div class="propPrice">' + prop.price + '</div>' +
                    '<div class="propType">' + prop.type + '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="paWrapper">' +
                    '<div class="propTitle">' + prop.title + '</div>' +
                    '<div class="propAddress">' + prop.address + '</div>' +
                    '</div>' +
                    '<div class="propRating">' +
                    '<span class="fa fa-star"></span>' +
                    '<span class="fa fa-star"></span>' +
                    '<span class="fa fa-star"></span>' +
                    '<span class="fa fa-star"></span>' +
                    '<span class="fa fa-star-o"></span>' +
                    '</div>' +
                    '<ul class="propFeat">' +
                    '<li><span class="fa fa-moon-o"></span> ' + prop.bedrooms + '</li>' +
                    '<li><span class="icon-drop"></span> ' + prop.bathrooms + '</li>' +
                    '<li><span class="icon-frame"></span> ' + prop.area + '</li>' +
                    '</ul>' +
                    '<div class="clearfix"></div>' +
                    '<div class="infoButtons">' +
                    '<a class="btn btn-sm btn-round btn-gray btn-o closeInfo">Close</a>' +
                    '<a href="single.html" class="btn btn-sm btn-round btn-green viewInfo">View</a>' +
                    '</div>' +
                    '</div>';

            google.maps.event.addListener(marker, 'click', (function (marker, i) {
                return function () {
                    infobox.setContent(infoboxContent);
                    infobox.open(map, marker);
                }
            })(marker, i));

            $(document).on('click', '.closeInfo', function () {
                infobox.open(null, null);
            });

            markers.push(marker);
        });
    }

    var windowHeight;
    var windowWidth;
    var contentHeight;
    var contentWidth;
    var isDevice = true;

    // calculations for elements that changes size on window resize
    var windowResizeHandler = function () {
        windowHeight = window.innerHeight;
        windowWidth = $(window).width();
        contentHeight = windowHeight - $('#header').height();
        contentWidth = $('#content').width();

        $('#leftSide').height(contentHeight);
        $('.closeLeftSide').height(contentHeight);
        $('#wrapper').height(contentHeight);
        $('#mapView').height(contentHeight);
        $('#content').height(contentHeight);
        setTimeout(function () {
            $('.commentsFormWrapper').width(contentWidth);
        }, 300);

        if (map) {
            google.maps.event.trigger(map, 'resize');
        }

        // Add custom scrollbar for left side navigation
        if (windowWidth > 767) {
            $('.bigNav').slimScroll({
                height: contentHeight - $('.leftUserWraper').height()
            });
        } else {
            $('.bigNav').slimScroll({
                height: contentHeight
            });
        }
        if ($('.bigNav').parent('.slimScrollDiv').size() > 0) {
            $('.bigNav').parent().replaceWith($('.bigNav'));
            if (windowWidth > 767) {
                $('.bigNav').slimScroll({
                    height: contentHeight - $('.leftUserWraper').height()
                });
            } else {
                $('.bigNav').slimScroll({
                    height: contentHeight
                });
            }
        }

        // reposition of prices and area reange sliders tooltip
        var priceSliderRangeLeft = parseInt($('.priceSlider .ui-slider-range').css('left'));
        var priceSliderRangeWidth = $('.priceSlider .ui-slider-range').width();
        var priceSliderLeft = priceSliderRangeLeft + (priceSliderRangeWidth / 2) - ($('.priceSlider .sliderTooltip').width() / 2);
        $('.priceSlider .sliderTooltip').css('left', priceSliderLeft);

        var areaSliderRangeLeft = parseInt($('.areaSlider .ui-slider-range').css('left'));
        var areaSliderRangeWidth = $('.areaSlider .ui-slider-range').width();
        var areaSliderLeft = areaSliderRangeLeft + (areaSliderRangeWidth / 2) - ($('.areaSlider .sliderTooltip').width() / 2);
        $('.areaSlider .sliderTooltip').css('left', areaSliderLeft);
    }

    var repositionTooltip = function (e, ui) {
        var div = $(ui.handle).data("bs.tooltip").$tip[0];
        var pos = $.extend({}, $(ui.handle).offset(), {
            width: $(ui.handle).get(0).offsetWidth,
            height: $(ui.handle).get(0).offsetHeight
        });
        var actualWidth = div.offsetWidth;

        var tp = {left: pos.left + pos.width / 2 - actualWidth / 2}
        $(div).offset(tp);

        $(div).find(".tooltip-inner").text(ui.value);
    }

    windowResizeHandler();
    $(window).resize(function () {
        windowResizeHandler();
    });

    setTimeout(function () {

        $('body').removeClass('notransition');
        map = new maplibregl.Map({
            container: 'mapView', // container id            
            style: 'http://' + hostname + ':' + portweb + '/khukt/style/' + activeId + '.json',
            center: [105.479, 19.015], // starting position
            zoom: 9 // starting zoom
        });
        
        // Disable default box zooming.
        map.boxZoom.disable();
 
        // Create a popup, but don't add it to the map yet.
        /*const popup = new maplibregl.Popup({
            closeButton: false
        });*/

        map.on('click', (e) => {
            console.log(e);
            $('#overview2').hide();
            $('#bangchugiai').hide();
        });
        
        map.on("load", function () {
            const canvas = map.getCanvasContainer();

            // Biến điều khiển tọa độ xy khi sự kiện 'mousedown' xảy ra.
            let start;

            // Biến điều khiển tọa độ xy khi sự kiện `mousemove` or `mouseup` xảy ra.
            let current;

            // Biến cho phần tử vẽ box
            let box;

            // Thêm các lớp bản đồ
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
            
            //console.log(map.getStyle().layers);

            // Set `true` to dispatch the event before other functions
            // call it. This is necessary for disabling the default map
            // dragging behaviour.
            canvas.addEventListener('mousedown', mouseDown, true);

            // Return the xy coordinates of the mouse position
            function mousePos(e) {
                const rect = canvas.getBoundingClientRect();
                return new maplibregl.Point(
                        e.clientX - rect.left - canvas.clientLeft,
                        e.clientY - rect.top - canvas.clientTop
                        );
            }
            
            function isExistObjectSelect(arr, value) {
                var rc=false;
                for(let i=0;i<arr.length;i++){
                    if(arr[i].id === value.id && arr[i].typeobject === value.typeobject){
                        rc=true;
                        break;
                    }
                }
                return rc;
            }

            function queryHangmucXDHT(features,isCtrl, hot){
                var lsArrayQHPolygon = [];
                var lsArrayQHLine = [];
                var lsArrayQHPoint = []; 
                var arrSelected = [];                
                const fips = features.map(
                    (feature) => {   
                        if(feature.layer.id === 'khucn_hatangkythuat_circle'){
                            if (lsArrayQHPoint.indexOf(feature.properties.highlight) < 0){
                                if(isCtrl && arrSelected.length > 0){console.log('not working');}
                                else{
                                    try {
                                        dynamicSQL('http://' + hostname + ':' + port + '/hatangkythuatpoint/hatangkythuatpoint/'+feature.id);
                                        var f = JSON.parse(JSON.stringify(dataResponse));
                                        if(f.idDuanXdht === null){
                                            lsArrayQHPoint.push(feature.properties.highlight); 
                                            var elementSelected = {};
                                            elementSelected.typeobject = 3;
                                            elementSelected.id = f.gid;
                                            elementSelected.ten = f.ten + ' ('+f.gid+')';
                                            elementSelected.mark = 0;
                                            if(!isExistObjectSelect(arrSelected, elementSelected)) arrSelected.push(elementSelected);                                            
                                        }else{
                                            //alert('id chon='+f.idDuanXdht.id+', id_duan='+idDuanHTKTSelected);
                                            if(f.idDuanXdht.id === idDuanHTKTSelected){
                                                //alert('cho phep');
                                                lsArrayQHPoint.push(feature.properties.highlight);    
                                                var elementSelected = {};
                                                elementSelected.typeobject = 3;
                                                elementSelected.id = f.gid;
                                                elementSelected.ten = f.ten + ' ('+f.gid+')';
                                                elementSelected.mark = 0;
                                                if(!isExistObjectSelect(arrSelected, elementSelected)) arrSelected.push(elementSelected);
                                            }else{
                                                //alert('khong cho phep');
                                            }
                                        }
                                    } catch (e) {
                                        console.log(e);
                                    }
                                }
                            }                                        
                        }                                     
                        if(feature.layer.id === 'khucn_hatangkythuat_line'){
                            if (lsArrayQHLine.indexOf(feature.properties.highlight) < 0){
                                if(isCtrl && arrSelected.length > 0){console.log('not working');}
                                else{
                                    try {
                                        dynamicSQL('http://' + hostname + ':' + port + '/hatangkythuatline/hatangkythuatline/'+feature.id);
                                        var f = JSON.parse(JSON.stringify(dataResponse));
                                        if(f.idDuanXdht === null){
                                            lsArrayQHLine.push(feature.properties.highlight); 
                                            var elementSelected = {};
                                            elementSelected.typeobject = 2;
                                            elementSelected.id = f.gid;
                                            elementSelected.ten = f.ten + ' ('+f.gid+')';
                                            elementSelected.mark = 0;
                                            if(!isExistObjectSelect(arrSelected, elementSelected)) arrSelected.push(elementSelected);
                                        }else{
                                            //alert('id chon='+f.idDuanXdht.id+', id_duan='+idDuanHTKTSelected);
                                            if(f.idDuanXdht.id === idDuanHTKTSelected){
                                                //alert('cho phep');
                                                lsArrayQHLine.push(feature.properties.highlight);   
                                                var elementSelected = {};
                                                elementSelected.typeobject = 2;
                                                elementSelected.id = f.gid;
                                                elementSelected.ten = f.ten + ' ('+f.gid+')';
                                                elementSelected.mark = 0;
                                                if(!isExistObjectSelect(arrSelected, elementSelected)) arrSelected.push(elementSelected);
                                            }else{
                                                //alert('khong cho phep');
                                            }
                                        }
                                    } catch (e) {
                                        console.log(e);
                                    }    
                                }
                            }                                                                    
                        }
                        if(feature.layer.id === 'khucn_hatangkythuat_polygon'){
                            if (lsArrayQHPolygon.indexOf(feature.properties.highlight) < 0){
                                if(isCtrl && arrSelected.length > 0){console.log('not working');}
                                else{
                                    try {
                                        dynamicSQL('http://' + hostname + ':' + port + '/hatangkythuatpolygon/hatangkythuatpolygon/'+feature.id);
                                        var f = JSON.parse(JSON.stringify(dataResponse));
                                        if(f.idDuanXdht === null){
                                            lsArrayQHPolygon.push(feature.properties.highlight);
                                            var elementSelected = {};
                                            elementSelected.typeobject = 1;
                                            elementSelected.id = f.gid;
                                            elementSelected.ten = f.ten + ' ('+f.gid+')';
                                            elementSelected.mark = 0;
                                            if(!isExistObjectSelect(arrSelected, elementSelected)) arrSelected.push(elementSelected);
                                        }else{
                                            //alert('id chon='+f.idDuanXdht.id+', id_duan='+idDuanHTKTSelected);
                                            if(f.idDuanXdht.id === idDuanHTKTSelected){
                                                //alert('cho phep');
                                                lsArrayQHPolygon.push(feature.properties.highlight);
                                                var elementSelected = {};
                                                elementSelected.typeobject = 1;
                                                elementSelected.id = f.gid;
                                                elementSelected.ten = f.ten + ' ('+f.gid+')';
                                                elementSelected.mark = 0;
                                                if(!isExistObjectSelect(arrSelected, elementSelected)) arrSelected.push(elementSelected);
                                            }else{
                                                //alert('khong cho phep');
                                            }
                                        }
                                    } catch (e) {
                                        console.log(e);
                                    }       
                                }
                            }
                        }                                
                    }
                );
                for(var i=0;i<lsArrayQHPolygon.length;i++){
                    const index = lsHighlightQHPolygon.indexOf(lsArrayQHPolygon[i]);
                    if(index === -1) lsHighlightQHPolygon.push(lsArrayQHPolygon[i]);
                    else lsHighlightQHPolygon.splice(index, 1);
                }
                map.setFilter('khucn_hatangkythuat_polygon_highlighted', ['in', 'highlight', ...lsHighlightQHPolygon]);
                //---------------------
                for(var i=0;i<lsArrayQHLine.length;i++){
                    const index = lsHighlightQHLine.indexOf(lsArrayQHLine[i]);
                    if(index === -1) lsHighlightQHLine.push(lsArrayQHLine[i]);
                    else lsHighlightQHLine.splice(index, 1);
                }
                map.setFilter('khucn_hatangkythuat_line_highlighted', ['in', 'highlight', ...lsHighlightQHLine]);
                //---------------------
                for(var i=0;i<lsArrayQHPoint.length;i++){
                    const index = lsHighlightQHPoint.indexOf(lsArrayQHPoint[i]);
                    if(index === -1) lsHighlightQHPoint.push(lsArrayQHPoint[i]);
                    else lsHighlightQHPoint.splice(index, 1);
                }
                map.setFilter('khucn_hatangkythuat_circle_highlighted', ['in', 'highlight', ...lsHighlightQHPoint]);

                if(activedHtml === "window_thanhphanxaydunghatang_edit"
                        || activedHtml === "window_thanhphanxaydunghatang_add"){
                    //duyet grid
                    //console.log('id:' + elementSelected.id + ',type:' + elementSelected.typeobject);
                    for(var j=0;j<arrSelected.length;j++){
                        for (var i = 0; i < hot.countRows(); i++) {
                            var row = hot.getDataAtRow(i);
                            //console.log('row '+i+': '+row.toString());
                            if(row[0] === arrSelected[j].id && row[1] === arrSelected[j].typeobject){
                                hot.alter('remove_row', i);
                                arrSelected[j].mark = 1;
                                break;
                            }
                        }
                    }
                    var dem=0;
                    for(var j=0;j<arrSelected.length;j++){ 
                        if(arrSelected[j].mark === 0){
                            dem=dem+1;
                        }
                    }
                    console.log('soluong:'+dem);
                    if(dem > 0) hot.alter('insert_row', 0, dem);
                    dem=0;
                    for(var j=0;j<arrSelected.length;j++){ 
                        if(arrSelected[j].mark === 0){
                            var changes = [
                                [dem, 'id', arrSelected[j].id],
                                [dem, 'ten', arrSelected[j].ten],
                                [dem, 'typeobject', arrSelected[j].typeobject],
                              ];
                            hot.setDataAtRowProp(changes);
                            dem=dem+1;
                        }
                    }
                    /*
                    hot.updateSettings({
                        height: hot.countRows() * 41
                    });*/
                }
            }
            
            function mouseDown(e) {
                // Continue the rest of the function if the shiftkey is pressed.                
                if(taoduan_hatangkythuat > 0){ //edit
                    //if (!(e.shiftKey && e.button === 0) || !(e.ctrlKey && e.button === 0))
                    //    return;

                    if(e.shiftKey){
                        // Disable default drag zooming when the shift key is held down.
                        map.dragPan.disable();

                        // Call functions for the following events
                        document.addEventListener('mousemove', onMouseMove);
                        document.addEventListener('mouseup', onMouseUp);
                        document.addEventListener('keydown', onKeyDown);

                        // Capture the first xy coordinates
                        start = mousePos(e);
                    }
                    if(e.ctrlKey){
                        const rect = canvas.getBoundingClientRect();
                        var pointClick = new maplibregl.Point(
                            e.clientX - rect.left - canvas.clientLeft,
                            e.clientY - rect.top - canvas.clientTop
                        );
                        var bboxQuery = [
                                [pointClick.x - 0.001, pointClick.y - 0.001],
                                [pointClick.x + 0.001, pointClick.y + 0.001]
                        ];
                        
                        var features = map.queryRenderedFeatures(bboxQuery, {
                            layers: ['khucn_hatangkythuat_polygon','khucn_hatangkythuat_line','khucn_hatangkythuat_circle']
                        });
                        if(taoduan_hatangkythuat === 1){
                            queryHangmucXDHT(features, true, hotedithtkt);                       
                        }else if(taoduan_hatangkythuat === 2){
                            queryHangmucXDHT(features, true, hotaddhtkt);  
                        }
                    }
                }
                if(active_zoomin === 1){
                    map.dragPan.disable();

                    // Call functions for the following events
                    document.addEventListener('mousemove', onMouseMove);
                    document.addEventListener('mouseup', onMouseUp);
                    document.addEventListener('keydown', onKeyDown);

                    // Capture the first xy coordinates
                    start = mousePos(e);
                }
            }

            function onMouseMove(e) {
                // Capture the ongoing xy coordinates
                current = mousePos(e);

                // Append the box element if it doesnt exist
                if (!box) {
                    box = document.createElement('div');
                    box.classList.add('boxdraw');
                    canvas.appendChild(box);
                }

                const minX = Math.min(start.x, current.x),
                        maxX = Math.max(start.x, current.x),
                        minY = Math.min(start.y, current.y),
                        maxY = Math.max(start.y, current.y);

                // Adjust width and xy position of the box element ongoing
                const pos = `translate(${minX}px, ${minY}px)`;
                box.style.transform = pos;
                box.style.width = maxX - minX + 'px';
                box.style.height = maxY - minY + 'px';
            }

            function onMouseUp(e) {
                // Capture xy coordinates
                finish([start, mousePos(e)]);
            }

            function onKeyDown(e) {
                // If the ESC key is pressed
                if (e.keyCode === 27)
                    finish();
            }

            function finish(bbox) {
                // Remove these events now that finish has been called.
                document.removeEventListener('mousemove', onMouseMove);
                document.removeEventListener('keydown', onKeyDown);
                document.removeEventListener('mouseup', onMouseUp);

                if (box) {
                    box.parentNode.removeChild(box);
                    box = null;
                }

                // If bbox exists. use this value as the argument for `queryRenderedFeatures`
                if (bbox) {
                    if(active_zoomin === 1){
                        active_zoomin = 0;     
                        //hàm unproject chuyển đổi từ tọa độ vuông góc về hệ địa lý
                        //hàm project chuyển đổi từ tọa độ địa lý về hệ tọa độ vuông góc
                        var n1 = map.unproject(bbox[0]); 
                        var n2 = map.unproject(bbox[1]);
                        console.log(n1);
                        var bboxQuery = [
                                [n1.lng, n1.lat],
                                [n2.lng, n2.lat]
                        ];                        
                        map.fitBounds(bboxQuery);
                        $("#zoomin").css("border", "");
                    }
                    if(taoduan_dautusanxuat === 1){
                        const features = map.queryRenderedFeatures(bbox, {
                            layers: ['khucn_hatangkythuat_polygon','khucn_hatangkythuat_line','khucn_hatangkythuat_circle']
                        });                                       
                        if (features.length >= 1000) {
                            return window.alert('Chọn số lượng phần tử ít hơn 1000');
                        }
                        // Run through the selected features and set a filter
                        // to match features with unique FIPS codes to activate
                        // the `counties-highlighted` layer.
                        lsHighlightQHPolygon = [];
                        lsHighlightQHLine = [];
                        lsHighlightQHPoint = [];                    
                        const fips = features.map(
                            (feature) => {
                                if(feature.layer.id === 'khucn_hatangkythuat_polygon'){
                                    if (lsHighlightQHPolygon.indexOf(feature.properties.highlight) < 0){lsHighlightQHPolygon.push(feature.properties.highlight);}
                                }
                                if(feature.layer.id === 'khucn_hatangkythuat_line'){
                                    if (lsHighlightQHLine.indexOf(feature.properties.highlight) < 0){lsHighlightQHLine.push(feature.properties.highlight);}
                                }
                                if(feature.layer.id === 'khucn_hatangkythuat_circle'){
                                    if (lsHighlightQHPoint.indexOf(feature.properties.highlight) < 0){lsHighlightQHPoint.push(feature.properties.highlight);}
                                }                                
                            }
                        );
                        map.setFilter('khucn_hatangkythuat_polygon_highlighted', ['in', 'highlight', ...lsHighlightQHPolygon]);                    
                        map.setFilter('khucn_hatangkythuat_line_highlighted', ['in', 'highlight', ...lsHighlightQHLine]);                    
                        map.setFilter('khucn_hatangkythuat_circle_highlighted', ['in', 'highlight', ...lsHighlightQHPoint]);

                        //selected
                        if(activedHtml === "window_duandautusanxuat-create"){
                            $('#DADTSX-element').empty();
                            $('#DADTSX-element').load("duandautusanxuat-select.html");
                        }else{                                        
                            $('#mapView').css("width", "70%");
                            $('#content').css("width", "30%");                                        
                            loadHtml("window_duandautusanxuat-create", 'duandautusanxuat-create.html');
                        }
                    }
                    if(taoduan_hatangkythuat === 1){
                        const features = map.queryRenderedFeatures(bbox, {
                            layers: ['khucn_hatangkythuat_polygon','khucn_hatangkythuat_line','khucn_hatangkythuat_circle']
                        });                                       
                        if (features.length >= 1000) {
                            return window.alert('Chọn số lượng phần tử ít hơn 1000');
                        }
                        queryHangmucXDHT(features, false, hotedithtkt);  
                    }
                    if(taoduan_hatangkythuat === 2){                        
                        const features = map.queryRenderedFeatures(bbox, {
                            layers: ['khucn_hatangkythuat_polygon','khucn_hatangkythuat_line','khucn_hatangkythuat_circle']
                        });                                       
                        if (features.length >= 1000) {
                            return window.alert('Chọn số lượng phần tử ít hơn 1000');
                        }
                        queryHangmucXDHT(features, false, hotaddhtkt);  
                    }
                }

                map.dragPan.enable();
            }

            map.on('mousemove', (e) => {
                const features = map.queryRenderedFeatures(e.point, {
                    layers: ['khucn_hatangkythuat_polygon_highlighted','khucn_hatangkythuat_line_highlighted','khucn_hatangkythuat_circle_highlighted']
                });

// Change the cursor style as a UI indicator.
                map.getCanvas().style.cursor = features.length ? 'pointer' : '';

                if (!features.length) {
                    popup.remove();
                    return;
                }

                msgFeature(e.lngLat, features[0].properties.ten + ' ('+features[0].id+')');
            });

        });

    }, 300);

    if (!(('ontouchstart' in window) || window.DocumentTouch && document instanceof DocumentTouch)) {
        $('body').addClass('no-touch');
        isDevice = false;
    }

    // Header search icon transition
    $('.search input').focus(function () {
        $('.searchIcon').addClass('active');
    });
    $('.search input').blur(function () {
        $('.searchIcon').removeClass('active');
    });

    // Notifications list items pulsate animation
    $('.notifyList a').hover(
            function () {
                $(this).children('.pulse').addClass('pulsate');
            },
            function () {
                $(this).children('.pulse').removeClass('pulsate');
            }
    );

    // Expand left side navigation
    var navExpanded = false;
    $('.navHandler, .closeLeftSide').click(function () {
        if (!navExpanded) {
            $('.logo').addClass('expanded');
            $('#leftSide').addClass('expanded');
            if (windowWidth < 768) {
                $('.closeLeftSide').show();
            }
            $('.hasSub').addClass('hasSubActive');
            $('.leftNav').addClass('bigNav');
            if (windowWidth > 767) {
                $('.full').addClass('m-full');
            }
            windowResizeHandler();
            navExpanded = true;
        } else {
            $('.logo').removeClass('expanded');
            $('#leftSide').removeClass('expanded');
            $('.closeLeftSide').hide();
            $('.hasSub').removeClass('hasSubActive');
            $('.bigNav').slimScroll({destroy: true});
            $('.leftNav').removeClass('bigNav');
            $('.leftNav').css('overflow', 'visible');
            $('.full').removeClass('m-full');
            navExpanded = false;
        }
        if (windowWidth < 768) {
            $('#zoomin').css({"left": 10});
            $('#overview1').css({"left": 10});
            $('#overview2').css({"left": 80});
            $('#chugiai').css({"left": 80});
            $('#lopdulieu').css({"left": 110});
            $('#bangchugiai').css({"left": 10});
        } else {
            let left1 = $("#leftSide").width() + 10;
            let left2 = $("#leftSide").width() + 80;
            let leftChugiai = $("#leftSide").width() + 80;
            let leftLopdulieu = $("#leftSide").width() + 110;
            let leftBangChugiai = $("#leftSide").width() + 10;
            $('#zoomin').css({"left": left1});
            $('#overview1').css({"left": left1});
            $('#overview2').css({"left": left2});
            $('#chugiai').css({"left": leftChugiai});
            $('#lopdulieu').css({"left": leftLopdulieu});
            $('#bangchugiai').css({"left": leftBangChugiai});
        }
    });

    // functionality for map manipulation icon on mobile devices
    $('.mapHandler').click(function () {
        if ($('#mapView').hasClass('mob-min') ||
                $('#mapView').hasClass('mob-max') ||
                $('#content').hasClass('mob-min') ||
                $('#content').hasClass('mob-max')) {
            $('#mapView').toggleClass('mob-max');
            $('#content').toggleClass('mob-min');
        } else {
            $('#mapView').toggleClass('min');
            $('#content').toggleClass('max');
        }

        setTimeout(function () {
            var priceSliderRangeLeft = parseInt($('.priceSlider .ui-slider-range').css('left'));
            var priceSliderRangeWidth = $('.priceSlider .ui-slider-range').width();
            var priceSliderLeft = priceSliderRangeLeft + (priceSliderRangeWidth / 2) - ($('.priceSlider .sliderTooltip').width() / 2);
            $('.priceSlider .sliderTooltip').css('left', priceSliderLeft);

            var areaSliderRangeLeft = parseInt($('.areaSlider .ui-slider-range').css('left'));
            var areaSliderRangeWidth = $('.areaSlider .ui-slider-range').width();
            var areaSliderLeft = areaSliderRangeLeft + (areaSliderRangeWidth / 2) - ($('.areaSlider .sliderTooltip').width() / 2);
            $('.areaSlider .sliderTooltip').css('left', areaSliderLeft);

            if (map) {
                //google.maps.event.trigger(map, 'resize');
            }

            $('.commentsFormWrapper').width($('#content').width());
        }, 300);

    });

    // Expand left side sub navigation menus
    $(document).on("click", '.hasSubActive', function () {
        $(this).toggleClass('active');
        $(this).children('ul').toggleClass('bigList');
        $(this).children('a').children('.arrowRight').toggleClass('fa-angle-down');
    });

    if (isDevice) {
        $('.hasSub').click(function () {
            $('.leftNav ul li').not(this).removeClass('onTap');
            $(this).toggleClass('onTap');
        });
    }

    // functionality for custom dropdown select list
    $('.dropdown-select li a').click(function () {
        if (!($(this).parent().hasClass('disabled'))) {
            $(this).prev().prop("checked", true);
            $(this).parent().siblings().removeClass('active');
            $(this).parent().addClass('active');
            $(this).parent().parent().siblings('.dropdown-toggle').children('.dropdown-label').html($(this).text());
        }
    });

    $('.priceSlider').slider({
        range: true,
        min: 0,
        max: 2000000,
        values: [500000, 1500000],
        step: 10000,
        slide: function (event, ui) {
            $('.priceSlider .sliderTooltip .stLabel').html(
                    '$' + ui.values[0].toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") +
                    ' <span class="fa fa-arrows-h"></span> ' +
                    '$' + ui.values[1].toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
                    );
            var priceSliderRangeLeft = parseInt($('.priceSlider .ui-slider-range').css('left'));
            var priceSliderRangeWidth = $('.priceSlider .ui-slider-range').width();
            var priceSliderLeft = priceSliderRangeLeft + (priceSliderRangeWidth / 2) - ($('.priceSlider .sliderTooltip').width() / 2);
            $('.priceSlider .sliderTooltip').css('left', priceSliderLeft);
        }
    });
    $('.priceSlider .sliderTooltip .stLabel').html(
            '$' + $('.priceSlider').slider('values', 0).toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") +
            ' <span class="fa fa-arrows-h"></span> ' +
            '$' + $('.priceSlider').slider('values', 1).toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
            );
    var priceSliderRangeLeft = parseInt($('.priceSlider .ui-slider-range').css('left'));
    var priceSliderRangeWidth = $('.priceSlider .ui-slider-range').width();
    var priceSliderLeft = priceSliderRangeLeft + (priceSliderRangeWidth / 2) - ($('.priceSlider .sliderTooltip').width() / 2);
    $('.priceSlider .sliderTooltip').css('left', priceSliderLeft);

    $('.areaSlider').slider({
        range: true,
        min: 0,
        max: 20000,
        values: [5000, 10000],
        step: 10,
        slide: function (event, ui) {
            $('.areaSlider .sliderTooltip .stLabel').html(
                    ui.values[0].toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ' Sq Ft' +
                    ' <span class="fa fa-arrows-h"></span> ' +
                    ui.values[1].toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ' Sq Ft'
                    );
            var areaSliderRangeLeft = parseInt($('.areaSlider .ui-slider-range').css('left'));
            var areaSliderRangeWidth = $('.areaSlider .ui-slider-range').width();
            var areaSliderLeft = areaSliderRangeLeft + (areaSliderRangeWidth / 2) - ($('.areaSlider .sliderTooltip').width() / 2);
            $('.areaSlider .sliderTooltip').css('left', areaSliderLeft);
        }
    });
    $('.areaSlider .sliderTooltip .stLabel').html(
            $('.areaSlider').slider('values', 0).toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ' Sq Ft' +
            ' <span class="fa fa-arrows-h"></span> ' +
            $('.areaSlider').slider('values', 1).toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ' Sq Ft'
            );
    var areaSliderRangeLeft = parseInt($('.areaSlider .ui-slider-range').css('left'));
    var areaSliderRangeWidth = $('.areaSlider .ui-slider-range').width();
    var areaSliderLeft = areaSliderRangeLeft + (areaSliderRangeWidth / 2) - ($('.areaSlider .sliderTooltip').width() / 2);
    $('.areaSlider .sliderTooltip').css('left', areaSliderLeft);

    $('.volume .btn-round-right').click(function () {
        var currentVal = parseInt($(this).siblings('input').val());
        if (currentVal < 10) {
            $(this).siblings('input').val(currentVal + 1);
        }
    });
    $('.volume .btn-round-left').click(function () {
        var currentVal = parseInt($(this).siblings('input').val());
        if (currentVal > 1) {
            $(this).siblings('input').val(currentVal - 1);
        }
    });

    $('.handleFilter').click(function () {
        $('.filterForm').slideToggle(200);
    });

    //Enable swiping
    $(".carousel-inner").swipe({
        swipeLeft: function (event, direction, distance, duration, fingerCount) {
            $(this).parent().carousel('next');
        },
        swipeRight: function () {
            $(this).parent().carousel('prev');
        }
    });

    $(".carousel-inner .card").click(function () {
        window.open($(this).attr('data-linkto'), '_self');
    });

    $('#content').scroll(function () {
        if ($('.comments').length > 0) {
            var visible = $('.comments').visible(true);
            if (visible) {
                $('.commentsFormWrapper').addClass('active');
            } else {
                $('.commentsFormWrapper').removeClass('active');
            }
        }
    });

    $('.btn').click(function () {
        if ($(this).is('[data-toggle-class]')) {
            $(this).toggleClass('active ' + $(this).attr('data-toggle-class'));
        }
    });

    $('.tabsWidget .tab-scroll').slimScroll({
        height: '235px',
        size: '5px',
        position: 'right',
        color: '#939393',
        alwaysVisible: false,
        distance: '5px',
        railVisible: false,
        railColor: '#222',
        railOpacity: 0.3,
        wheelStep: 10,
        allowPageScroll: true,
        disableFadeOut: false
    });

    $('.progress-bar[data-toggle="tooltip"]').tooltip();
    $('.tooltipsContainer .btn').tooltip();

    $("#slider1").slider({
        range: "min",
        value: 50,
        min: 1,
        max: 100,
        slide: repositionTooltip,
        stop: repositionTooltip
    });
    $("#slider1 .ui-slider-handle:first").tooltip({title: $("#slider1").slider("value"), trigger: "manual"}).tooltip("show");

    $("#slider2").slider({
        range: "max",
        value: 70,
        min: 1,
        max: 100,
        slide: repositionTooltip,
        stop: repositionTooltip
    });
    $("#slider2 .ui-slider-handle:first").tooltip({title: $("#slider2").slider("value"), trigger: "manual"}).tooltip("show");

    $("#slider3").slider({
        range: true,
        min: 0,
        max: 500,
        values: [190, 350],
        slide: repositionTooltip,
        stop: repositionTooltip
    });
    $("#slider3 .ui-slider-handle:first").tooltip({title: $("#slider3").slider("values", 0), trigger: "manual"}).tooltip("show");
    $("#slider3 .ui-slider-handle:last").tooltip({title: $("#slider3").slider("values", 1), trigger: "manual"}).tooltip("show");

    $('#autocomplete').autocomplete({
        source: ["ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl", "PHP", "Python", "Ruby", "Scala", "Scheme"],
        focus: function (event, ui) {
            var label = ui.item.label;
            var value = ui.item.value;
            var me = $(this);
            setTimeout(function () {
                me.val(value);
            }, 1);
        }
    });

    $('#tags').tagsInput({
        'height': 'auto',
        'width': '100%',
        'defaultText': 'Add a tag',
    });

    $('#datepicker').datepicker();

    $('input, textarea').placeholder();

    $("#img-layer-basic").click(function (e) {
        switchMap('basic');
    });
    $("#img-layer-street").click(function (e) {
        switchMap('street');
    });
    $("#img-layer-hybrid").click(function (e) {
        switchMap('hybrid');
    });
    $("#img-layer-outdoor").click(function (e) {
        switchMap('Outdoor');
    });
    $("#img-layer-topo").click(function (e) {
        switchMap('topo');
    });

    $("#zoomin").click(function (e) { 
        active_zoomin = 1;
        $("#zoomin").css("border", "2px solid red");
    });

    $("#img-chugiai").click(function (e) {        
        const bounds = map.getBounds();
        var features = map.queryRenderedFeatures(bounds);
        var str = "";
        var lsIDQuyhoach = [];
        var lsLayer = [];
        for (let i = 0; i < features.length; i++) {
            if (features[i].source == "khucn_quyhoach_polygon"
                    || features[i].source == "khucn_quyhoach_line"
                    || features[i].source == "khucn_quyhoach_circle"
                    || features[i].source == "khucn_hatangkythuat_polygon"
                    || features[i].source == "khucn_hatangkythuat_line"
                    || features[i].source == "khucn_hatangkythuat_circle") {
                if (lsIDQuyhoach.indexOf(features[i].properties.id_loaiquyhoach) < 0) {
                    lsIDQuyhoach.push(features[i].properties.id_loaiquyhoach);
                    lsLayer.push(features[i].source);
                }
            }
        }
        for(let i=0;i<arrStyle.length;i++){
            var chiso = lsIDQuyhoach.indexOf(arrStyle[i].quyhoach_id);
            if ( chiso >= 0) {
                arrStyle[i].chon=1;
                arrStyle[i].layer = lsLayer[chiso];
            }else{
                arrStyle[i].chon=0;
                arrStyle[i].layer = '';
            }
        }                
        for (let i = 0; i < arrStyle.length; i++) {
            if(arrStyle[i].chon == 1){
                console.log('e:'+JSON.stringify(arrStyle[i]));
            str = str + "<div class='row'><div class='col-xs-10'><label class='box_legend'>" + arrStyle[i].quyhoach_ten + "</label></div><div class='col-xs-2'><input type='color' id='colorLayer' name='colorLayer' \n\
onchange='changeColorLayer(this,"+arrStyle[i].quyhoach_id+",\""+arrStyle[i].layer+"\")' value='"+ new w3color(String(arrStyle[i].color)).toHexString() + "' style='width:28px;height:28px;border: none;padding: 0'></div></div>\n\
<div class='row'><div class='col-xs-12'><input type='range' min='0' max='1' step='0.1' onchange='changeOpacityLayer(this,"+arrStyle[i].quyhoach_id+",\""+arrStyle[i].layer+"\")' value='1'></div></div>";
            }
        }
        $("#bangchugiai").html(str);
    });

    $("#img-layer").click(function (e) {
        const layoutPolygon = map.getLayoutProperty('khucn_quyhoach_polygon', 'visibility');
        const layoutLine = map.getLayoutProperty('khucn_quyhoach_line', 'visibility');
        const layoutCircle = map.getLayoutProperty('khucn_quyhoach_circle', 'visibility');
        
        const hatangPolygon = map.getLayoutProperty('khucn_hatangkythuat_polygon', 'visibility');
        const hatangLine = map.getLayoutProperty('khucn_hatangkythuat_line', 'visibility');
        const hatangCircle = map.getLayoutProperty('khucn_hatangkythuat_circle', 'visibility');
        
        //const hientrangPolygon = map.getLayoutProperty('khucn_hientrang_polygon', 'visibility');        
        
        var str = "<div class='row'><div class='col-xs-10'><label class='box_legend'>Quy hoạch (Polygon)</label></div><div class='col-xs-2'><input type='checkbox' id='cb_layer_quyhoach_polygon' ";
        if (layoutPolygon == "visible")
            str = str + " checked></div></div>";
        else
            str = str + "></div></div>";
        str = str + "<div class='row'><div class='col-xs-10'><label class='box_legend'>Quy hoạch (Line)</label></div><div class='col-xs-2'><input type='checkbox' id='cb_layer_quyhoach_line' ";
        if (layoutLine == "visible")
            str = str + " checked></div></div>";
        else
            str = str + "></div></div>";
        str = str + "<div class='row'><div class='col-xs-10'><label class='box_legend'>Quy hoạch (Point)</label></div><div class='col-xs-2'><input type='checkbox' id='cb_layer_quyhoach_circle' ";
        if (layoutCircle == "visible")
            str = str + " checked></div></div>";
        else
            str = str + "></div></div>";
        
        str = str + "<div class='row'><div class='col-xs-10'><label class='box_legend'>Hạ tầng kỹ thuật (Polygon)</label></div><div class='col-xs-2'><input type='checkbox' id='cb_layer_hatang_polygon' ";
        if (hatangPolygon == "visible")
            str = str + " checked></div></div>";
        else
            str = str + "></div></div>";
        str = str + "<div class='row'><div class='col-xs-10'><label class='box_legend'>Hạ tầng kỹ thuật (Line)</label></div><div class='col-xs-2'><input type='checkbox' id='cb_layer_hatang_line' ";
        if (hatangLine == "visible")
            str = str + " checked></div></div>";
        else
            str = str + "></div></div>";
        str = str + "<div class='row'><div class='col-xs-10'><label class='box_legend'>Hạ tầng kỹ thuật (Point)</label></div><div class='col-xs-2'><input type='checkbox' id='cb_layer_hatang_circle' ";
        if (hatangCircle == "visible")
            str = str + " checked></div></div>";
        else
            str = str + "></div></div>";
        /*
        str = str + "<div class='row'><div class='col-xs-10'><label class='box_legend'>Hiện trạng</label></div><div class='col-xs-2'><input type='checkbox' id='cb_layer_hientrang_polygon' ";
        if (hientrangPolygon == "visible")
            str = str + " checked></div></div>";
        else
            str = str + "></div></div>";*/
        
        $("#bangchugiai").html(str);

        $('#cb_layer_quyhoach_polygon').change(function () {
            if (this.checked) {
                map.setLayoutProperty('khucn_quyhoach_polygon', 'visibility', 'visible');
            } else {
                map.setLayoutProperty('khucn_quyhoach_polygon', 'visibility', 'none');
            }
        });

        $('#cb_layer_quyhoach_line').change(function () {
            if (this.checked) {
                map.setLayoutProperty('khucn_quyhoach_line', 'visibility', 'visible');
            } else {
                map.setLayoutProperty('khucn_quyhoach_line', 'visibility', 'none');
            }
        });
        $('#cb_layer_quyhoach_circle').change(function () {
            if (this.checked) {
                map.setLayoutProperty('khucn_quyhoach_circle', 'visibility', 'visible');
            } else {
                map.setLayoutProperty('khucn_quyhoach_circle', 'visibility', 'none');
            }
        });
        
        $('#cb_layer_hatang_polygon').change(function () {
            if (this.checked) {
                map.setLayoutProperty('khucn_hatangkythuat_polygon', 'visibility', 'visible');
            } else {
                map.setLayoutProperty('khucn_hatangkythuat_polygon', 'visibility', 'none');
            }
        });

        $('#cb_layer_hatang_line').change(function () {
            if (this.checked) {
                map.setLayoutProperty('khucn_hatangkythuat_line', 'visibility', 'visible');
            } else {
                map.setLayoutProperty('khucn_hatangkythuat_line', 'visibility', 'none');
            }
        });
        $('#cb_layer_hatang_circle').change(function () {
            if (this.checked) {
                map.setLayoutProperty('khucn_hatangkythuat_circle', 'visibility', 'visible');
            } else {
                map.setLayoutProperty('khucn_hatangkythuat_circle', 'visibility', 'none');
            }
        });
        /*
        $('#cb_layer_hientrang_polygon').change(function () {
            if (this.checked) {
                map.setLayoutProperty('khucn_hientrang_polygon', 'visibility', 'visible');
            } else {
                map.setLayoutProperty('khucn_hientrang_polygon', 'visibility', 'none');
            }
        });*/
    });

})(jQuery);