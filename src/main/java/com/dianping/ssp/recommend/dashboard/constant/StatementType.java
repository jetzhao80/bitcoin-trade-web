package com.dianping.ssp.recommend.dashboard.constant;

import com.dianping.ssp.annotation.Display;
import com.dianping.ssp.enums.Code;

/**
 * Created with IntelliJ IDEA
 * StatementType
 *
 * @author Yuxiang Ye
 * @date 2016/12/2
 * @time 下午2:41
 */
public class StatementType extends Code {
    @Display("转化率")
    public static final int CONVERSION_CHART = 1;
    @Display("覆盖率")
    public static final int COVERAGE_CHART = 2;
    @Display("多样性")
    public static final int DIVERSITY_CHART = 3;
    @Display("露出率")
    public static final int EXPOSURE_CHART = 4;
    @Display("新覆盖率")
    public static final int COVERAGE_NEW_CHART = 5;
}
