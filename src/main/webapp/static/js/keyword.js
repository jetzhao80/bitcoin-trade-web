var keyword = {
    init: function (ftlData) {

        $(".form_datetime").datetimepicker({
            format: 'yyyy-mm-dd hh:00',
            autoclose: true,
            todayHighlight: true,
            minView: "day"
        });
        initEcharts();
    	
        console.log(ftlData);
        this.bindEvent();
        $.ajax({
        	url:'',
        	success:function(data){
        		console.log(data);
        	}
        })
        getConfigButtonClick();
        queryChartButtonClick();
    },
    
    dashboardInit: function(){
        initDateTimePicker()
    	initEcharts();
    	queryChartButtonClick();
    },

    bindEvent: function () {
        var self = this;

        $('.J-push-btn').on('click', function (e) {
            console.log(1)
        });
    }
};

function initDateTimePicker() {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:00',
        autoclose: true,
        todayHighlight: true,
        minView: "day"
    });
}
function initEcharts(){
	// 基于准备好的dom，初始化echarts实例
    // keywordChart = echarts.init(document.getElementById('keywordChartDiv'));
    kwUserChart = echarts.init(document.getElementById('keywordUserClickChartDiv'));
    kwArticleChart = echarts.init(document.getElementById('keywordArticleClickChartDiv'));


    keywordText = {
        title: '用户点击转化率 %',
        description: '关键词点击数/关键词露出数'
    }

    kwUserText = {
        title: '用户点击转化率 %',
        description: '文章关键词点击用户数/文章关键词露出的用户数'
    }

    kwArticleText = {
        title: '文章点击转化率 %',
        description: '关键词点击数/关键词列表露出数'
    }

    conversionText = {
        title: '转化率 %',
        description: '推荐文章点击数/推荐文章露出数'
    }
    exposureText = {
        title: '露出率 %',
        description: '推荐文章露出数/总推荐文章数。'
    }
    coverageText = {
        title: '覆盖率 %',
        description: '推荐文章数(去重)/总文章量。'
    }
    diversityText = {
        title: '多样性 %',
        description: '推荐文章种类占/总文章类目数量'
    }

    initChart(kwUserChart, kwUserText.title, kwUserText.description);
    initChart(kwArticleChart, kwArticleText.title, kwArticleText.description);
}
function getConfigButtonClick(){
	$("#getConfigButton").click(
            function() {

                var startStr = $("#startTime").val();
                var endStr = $("#endTime").val();

                var articleTyep = $("articleType").val();

                // var timeType = $("#timeType").val();
                // var entrance = $("#entrance").val();

                if(!validateParam(startStr, endStr)) {
                    return;
                }

                var startDate = new Date(startStr);
                var endDate = new Date(endStr);
                var straAndAlgoRequest = {
                    startTime: startDate.getTime(),
                    endTime: endDate.getTime(),
                    articleType: articleTyep
                    //timeType: timeType,
                    //entrance: entrance
                }

                $.ajax({
                    url : '/recommend/getStraAndAlgo',
                    type : 'POST',
                    data : JSON.stringify(straAndAlgoRequest), 
                    // Request body
                    contentType : 'application/json;charset=utf-8',
                    dataType : 'json',
                    success : function(response) {
                        var result = response;
                        
                        var code = result.code;
                        if (code != 200) {
                            alert(result.msg);
                            return;
                        };

                        //请求成功
                        var strategyies = result.strategies;
                        var algos = result.algos;

                        var strategyIdsHtml = '';
                        for (var id in strategyies) {
                            strategyIdsHtml = strategyIdsHtml + '<div class="checkbox"><label><input type="checkbox" value='+id+'>'+id+' '+ strategyies[id]+'</input></label></div>';
                        };
                        $('#strategy').html(strategyIdsHtml);

                        var algoIdsHtml = '';
                        for(var i=0; i<algos.length; i++){
                            var algoInfo = algos[i];
                            var identity = algoInfo.algoId+'#'+algoInfo.algoVersion+' '+algoInfo.desc;
                            algoIdsHtml = algoIdsHtml +'<div class="checkbox"><label><input type="checkbox" value='+identity+'>'+identity+'</input></label></div>';
                        }
                        $('#algo').html(algoIdsHtml);

                    },
                    error : function(msg) {
                        alert(msg);
                    }
                });
            }
        );
}
function queryChartButtonClick(){
	$("#queryChartButton").click(
        function() {

            var startStr = $("#startTime").val();
            var endStr = $("#endTime").val();
            var articleType = $("#articleType").val();
            // var timeType = $("#timeType").val();
            // var entrance = $("#entrance").val();

            // var strategyIds = getCheckedValues($('#strategy input:checkbox'));
            // var algoStrs = getCheckedValues($('#algo input:checkbox'));

            if(!validateChartParam(startStr, endStr)) {
                return;
            }

            var startDate = new Date(startStr);
            var endDate = new Date(endStr);
            var statementRequest = {
                startTime: startDate.getTime(),
                endTime: endDate.getTime(),
                articleType:articleType
                // timeType: timeType,
                // entrance: entrance,
                // strategyIds: strategyIds,
                // algos: parseAlgoInfo(algoStrs)
            };
            /**
             	@Display("转化率")
			    public static final int CONVERSION_CHART = 1;
			    @Display("覆盖率")
			    public static final int COVERAGE_CHART = 2;
			    @Display("多样性")
			    public static final int DIVERSITY_CHART = 3;
			    @Display("露出率")
			    public static final int EXPOSURE_CHART = 4;
             */
            loadChartByType(statementRequest,1);
            loadChartByType(statementRequest,2);
            /*if(timeType == 2){
            	coverageChart.clear();
                diversityChart.clear();
            }else{
            	loadChartByType(statementRequest,2);
            	loadChartByType(statementRequest,3);
            }*/
        }
    );
}
function loadChartAll(statementRequest,timeType){
	conversionChart.showLoading();
    $.ajax({
        url : '/recommend/getStatement',
        type : 'POST',
        data : JSON.stringify(statementRequest),
        // Request body
        contentType : 'application/json;charset=utf-8',
        dataType : 'json',
        success : function(response) {
        	conversionChart.hideLoading();

            var result = response;
            
            var code = result.code;
            if (code != 200) {
                alert(result.msg);
                return;
            };

            //请求成功
            var conversionChartData = result.conversionChart;

            renderChart(conversionChart, conversionText.title, conversionText.description, conversionChartData);
            renderChart(exposureChart, exposureText.title, exposureText.description, exposureChartData);
            if (timeType!=2) {
                renderChart(coverageChart, coverageText.title, coverageText.description, coverageChartData);
                renderChart(diversityChart, diversityText.title, diversityText.description, diversityChartData);
            }else {
                coverageChart.clear();
                diversityChart.clear();
            }
        },
        error : function(msg) {
        	conversionChart.hideLoading();
        }
    });
}
function loadChartByType(statementRequest,type){
	// statementRequest.statementType=type;
	statementRequest.analysisType=type;
	var chartObj=returnChartObjByType(type);
	var chartText=returnChartTextByType(type);
	chartObj.showLoading();
	$.ajax({
        url : '/dashboard/keyword/getData',
        type : 'POST',
        data : JSON.stringify(statementRequest),
        // Request body
        contentType : 'application/json;charset=utf-8',
        dataType : 'json',
        success : function(response) {
        	chartObj.hideLoading();
            var result = response;
            
            var code = result.code;
            if (code != 200) {
                alert(result.msg);
                return;
            };
            //请求成功
            renderChart(chartObj, chartText.title, chartText.description, result.chartVo);
        },
        error : function(msg) {
        	chartObj.hideLoading();
        }
    });
}
function returnChartObjByType(type){
	if(type==1){
		return kwUserChart;
	}
    if(type==2){
        return kwArticleChart;
    }
}
function returnChartTextByType(type){
	if(type==1){
		return kwUserText;
	}
	if(type==2){
		return kwArticleText;
	}
}
function validateParam(startStr, endStr) {
    if(startStr=="" || endStr=="" ) {
        alert("时间选择错误");
        return false;
    };

    var startDate = new Date(startStr);
    var endDate = new Date(endStr);
    if (startDate > endDate) {
        alert("开始时间不得晚于结束时间");
        return false;
    };

    return true;
}

function validateChartParam(startStr, endStr) {
    if (!validateParam(startStr, endStr)) {
        return false;
    };

    return true;
}

function renderChart(chart, name, description, chartData) {
    if (chartData==null) {
        initChart(chart, name, description);
        return
    };

    chart.clear();

    var legend=[];
    var series=[];

    var lines = chartData.lines;

    for(var i=0; i<lines.length; i++) {
        lineVo = lines[i];
        legend.push(lineVo.name);

        var serie={
            name:lineVo.name,
            type:'line',
            smooth: true,
            data:lineVo.values,
            extraData:lineVo.extraValues
        };
        series.push(serie);
    };


    // 指定图表的配置项和数据
    var option = {
        title: {
            text: name,
            subtext: description,
            subtextStyle :{
                fontSize: 14
            },
         	x:"center"
        },
        grid:{
            top:100,
            right: 100
        },
        tooltip: {
        	trigger: 'item',
            formatter: function(params) {  
                    var res = '';  
                    var ths=[];
                    var rows=[];
                    ths.push(params.name);
                    ths.push(name);
                    var myseries = option.series;  
                    var myxdatas=option.xAxis.data;
                    var targetIndex=0;
                    for (var i = 0; i < myxdatas.length; i++) {  
                        if(myxdatas[i]==params.name){  
                            targetIndex=i;
                            break;
                        }  
                    }
                    for (var i = 0; i < myseries.length; i++) {
                    	var row=[];
                    	row.push(myseries[i].name);
                    	row.push(myseries[i].data[targetIndex]);
                    	var extraObj=myseries[i].extraData[targetIndex];
                    	for(var key in extraObj){
                    		if(i==0){
                    			ths.push(key);
                    		}
                    		row.push(extraObj[key]);
                    	}
                    	rows[i] = row;
                    }
                    var table$=createTable(ths, rows,params.seriesName);
                    res+='<table border="1" style="background-color:white">'+table$.html()+'</table>';
                    return res;
                }  
        },
        legend: {
        	x:"right",
         	data: legend,
         	orient:"vertical"
        },
        xAxis: {
            type: 'category',
            axisLabel:{
                interval: 'auto',
                //rotate:35,
                formatter: function (value, index) {//解决x刻度太多引起的重叠
                    // 格式化，只在第一个刻度显示年月
                	if (index == 0) {
                        return value;
                    }
                	var ymd=value.split('-');
                	if(ymd.length==3){
                		return ymd[2];
                	}else{
                		return value;
                	}
                }
            },
            boundaryGap: false,
            data: chartData.timeline
        },
        yAxis: {
            type: 'value'
        },
        series: series
    };

    chart.setOption(option);
}
function createTable(ths,rows,mouseRow0){
	var table = $("<table border=\"1\"></table>");  //</table>
      
    var trHeader = $("<tr></tr>");
    trHeader.css({'background-color':'gray'});
    trHeader.appendTo(table);  
    for (var j = 0; j < ths.length; j++) {  
        var td = $("<th>" + ths[j] + "</th>");  
        td.appendTo(trHeader); 
    }  

    for (var i = 0; i < rows.length; i++) {  
        var tr = $("<tr></tr>");
        if(typeof(mouseRow0) != undefined){
            if(rows[i][0]!=null && rows[i][0]==mouseRow0){
            	tr.css({'background-color':'yellow'});
    		}
    	}
        tr.appendTo(table);
        for (var j = 0; j < rows[i].length; j++) {  
            var val="";  
            if (rows[i][j] != null){  
                val = rows[i][j];  
            }
            var td = $("<td>" + val + "</td>");  
            td.appendTo(tr);  
        }  
    }
    return table;
}
function initChart(chart, name, description) {
    chart.clear();

    var option = {
        title: {
            text: name,
            subtext: description,
            subtextStyle :{
                fontSize: 14
            },
         	x:"center"
        },
        grid:{
            top:100,
            right: 100
        },
        tooltip: {},
        legend: {
            data: []
        },
        xAxis: {
            data: []
        },
        yAxis: {
            name: '',
            type: 'value',
            data:[]
        },
        series:[]
    };

    chart.setOption(option);
}

function getCheckedValues(checkBoxes) {
    var values = [];
    checkBoxes.each(function(){
        if ($(this).is(':checked')) {
            values.push($(this).val());
        }    
    });

    return values;   
}

function parseAlgoInfo(algoStrs) {
    var result = [];
    for (var i = 0; i < algoStrs.length; i++) {
        var algoInfo = algoStrs[i].split('#');
        if (algoInfo.length==2) {
            var id = algoInfo[0];
            var ver = algoInfo[1];
            var algoInfoVo = {
                algoId: id,
                algoVersion: ver
            };
            result.push(algoInfoVo);
        };
    };

    return result;
}