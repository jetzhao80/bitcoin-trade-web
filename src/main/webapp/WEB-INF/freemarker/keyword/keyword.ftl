<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>关键字转化报表</title>
	<link rel="stylesheet" href="/recommend/static/css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="/recommend/static/css/common.css" type="text/css"/>
	<link rel="stylesheet" href="/recommend/static/css/recmd.css" type="text/css"/>
	<link rel="stylesheet" href="/recommend/static/css/datetimepicker.css" type="text/css"/>
	<link rel="stylesheet" href="/recommend/static/css/nav.css" type="text/css"/>
</head>

<body>
	<#assign pageType="2"/>
	<#include "/partials/navbar.ftl"/>
    <div class="container">
        <div class="form-inline" style="margin-top: 30px">
            <div class="form-group">
                <label for="startTime" >开始时间</label>
                <input size="16" type="text" id="startTime" readonly class="form_datetime form-control"
                       placeholder="开始时间"
					   value="${startTime}"
				/>
            </div>
            <div class="form-group">
                <label for="endTime">结束时间</label>
                <input size="16" type="text" id="endTime" readonly class="form_datetime form-control"
                       placeholder="结束时间" value="${endTime}"/>
            </div>
            <#--<div class="form-group">
                <label for="timeType">时间维度</label>
                <select id="timeType" class="form-control">
                    <option value="1">天</option>
                    <option value="2">小时</option>
                </select>
            </div>-->
            <div class="form-group">
                <label for="articleType">类型</label>
                <select id="articleType" class="form-control">
                    <option value="0">不限</option>
                    <option value="1">资讯</option>
                    <option value="2">会议</option>
                    <option value="3">攻略</option>
                </select>
            </div>

        </div>
<#--

        <div class="form-inline" style="margin-top: 30px;display:none">
        	<input type='hidden' id="startTime" value="${startTime}" readonly/>
        	<input type='hidden' id="endTime" value="${endTime}" readonly/>
        	<input type='hidden' id="timeType" value="1" readonly/>
        	<input type='hidden' id="entrance" value="100" readonly/>
        	<div id="strategy">
            	<input type="checkbox" value='0'></input>
	        </div>
			<div id="algo">
	            <input type="checkbox" value='0'></input>
	        </div>
        </div>
-->

            <a href="javascript:;" id="queryChartButton" class="btn btn-primary btn-search" style="margin-top: 30px;display:black">
                查询
            </a>


        <div class="echart-wrap" style="margin-top: 30px">
            <div class="echart-body" id="keywordUserClickChartDiv" style="width: 500px;height:400px;"></div>
            <div class="echart-body" id="keywordArticleClickChartDiv" style="width: 500px;height:400px;"></div>
        </div>

    </div>
<script src="/recommend/static/js/jquery-1.7.2.min.js"></script>
<script src="/recommend/static/js/echarts.min.js"></script>
<script src="/recommend/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="/recommend/static/js/keyword.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  keyword.dashboardInit();
  $("#queryChartButton").click();
});
</script>

</body>

</html>
