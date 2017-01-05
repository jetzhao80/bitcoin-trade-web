package com.dianping.ssp.recommend.dashboard.constant;

import com.dianping.ssp.annotation.Display;
import com.dianping.ssp.enums.Code;

/**
 * Created with IntelliJ IDEA
 * SearchType
 *
 * @author Yuxiang Ye
 * @date 2016/12/2
 * @time 下午2:43
 */
public class SearchType extends Code {
	@Display("整体")
	public static final int ALL = 0;
    @Display("策略")
    public static final int STRA_TYPE = 1;
    @Display("算法")
    public static final int ALGO_TYPE = 2;
}
