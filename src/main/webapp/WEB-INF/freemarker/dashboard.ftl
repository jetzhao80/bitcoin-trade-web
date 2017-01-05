<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>首页推荐七日报表</title>
	<link rel="stylesheet" href="/recommend/static/css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="/recommend/static/css/common.css" type="text/css"/>
	<link rel="stylesheet" href="/recommend/static/css/recmd.css" type="text/css"/>
	<link rel="stylesheet" href="/recommend/static/css/datetimepicker.css" type="text/css"/>
    <link rel="stylesheet" href="/recommend/static/css/nav.css" type="text/css"/>
</head>

<body>
	<#assign pageType="1"/>
	<#include "/partials/navbar.ftl"/>

	<div class="container">
    	<a href="/recommend/statement" target='blank' class="btn btn-primary btn-search" style="margin-top: 30px;">
           	 自定义查询
        </a>
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

        <a href="javascript:;" id="queryChartButton" class="btn btn-primary btn-search" style="margin-top: 30px;display:none">
           	 查询
        </a>
        
        <#include "./echarts-data.ftl"/>

    </div>
<script src="/recommend/static/js/jquery-1.7.2.min.js"></script>
<script src="/recommend/static/js/echarts.min.js"></script>
<script src="/recommend/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="/recommend/static/js/recmd.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  recmd.dashboardInit();
  $("#queryChartButton").click();
});
</script>

</body>

</html>
