<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>自定义查询</title>
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
        <div class="form-inline" style="margin-top: 30px">
            <div class="form-group">
                <label for="startTime" >开始时间</label>
                <input size="16" type="text" id="startTime" readonly class="form_datetime form-control"
                       placeholder="开始时间"/>
            </div>
            <div class="form-group">
                <label for="endTime">结束时间</label>
                <input size="16" type="text" id="endTime" readonly class="form_datetime form-control"
                       placeholder="结束时间"/>
            </div>
            <div class="form-group">
                <label for="timeType">时间维度</label>
                <select id="timeType" class="form-control">
                    <option value="1">天</option>
                    <option value="2">小时</option>
                </select>
            </div>
            <div class="form-group">
                <label for="entrance">入口</label>
                <select id="entrance" class="form-control">
                    <option value="0">不限</option>
                    <option value="100">首页</option>
                    <option value="50">销售</option>
                    <option value="201">关键字</option>
                    <option value="1">资讯列表页</option>
                    <option value="101">资讯详情页</option>
                </select>
            </div>
            <div class="form-group">
                <label for="biz">业务</label>
                <select id="biz" class="form-control">
                    <option value="0">不限</option>
                    <option value="1">资讯</option>
                    <option value="2">会议</option>
                    <option value="3">攻略</option>
                    <option value="4">餐饮系统</option>
                </select>
            </div>
            <a href="javascript:;" id="getConfigButton" class="btn btn-info">获取配置</a>
            <a href="/recommend/dashboard" id="getConfigButton" class="btn btn-info">首页推荐七日报表</a>
        </div>


        <div class="form-group" style="margin-top: 10px">
            <label for="strategy">策略</label>
            <div id="strategy"></div>
        </div>

        <div class="form-group" style="margin-top: 10px">
            <label for="algo">算法</label>
            <div id="algo"></div>
        </div>

        <a href="javascript:;" id="queryChartButton" class="btn btn-primary btn-search" style="margin-top: 5px">
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
  recmd.init({});
});
</script>

</body>

</html>
