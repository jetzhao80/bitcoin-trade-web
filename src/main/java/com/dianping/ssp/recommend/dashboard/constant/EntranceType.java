package com.dianping.ssp.recommend.dashboard.constant;

import com.dianping.ssp.annotation.Display;
import com.dianping.ssp.enums.Code;

/**
 * Created with IntelliJ IDEA
 * EntranceType
 *
 * @author Yuxiang Ye
 * @date 2016/12/2
 * @time 下午3:08
 */
public class EntranceType extends Code {
    @Display("不限")
    public static final int NO_LIMIT = 0;
    @Display("资讯列表页")
    public static final int ZIXUN_LIST = 1;
    @Display("首页")
    public static final int SHOUYE = 100;
    @Display("销售")
    public static final int XIAOSHOU = 50;//10
    @Display("关键字")
    public static final int KEYWORD = 201;//9
    @Display("资讯详情页")
    public static final int ZIXUN_DETAIL = 101;

}
