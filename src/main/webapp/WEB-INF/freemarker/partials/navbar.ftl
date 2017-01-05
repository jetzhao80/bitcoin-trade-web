<nav class="navbar navbar-default">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="<#if pageType?? && pageType==1>active</#if>">
                <a href="/recommend/dashboard">推荐转化率</a>
            </li>

        	<li class="<#if pageType?? && pageType==2>active</#if>"><a href="/dashboard/keyword">关键字</a></li>
            <li class="<#if pageType?? && pageType==3>active</#if>"><a href="/dashboard/pv">红餐网</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout" class="btn btn-default">登出</a></li>
        </ul>
    </div>
</nav>
