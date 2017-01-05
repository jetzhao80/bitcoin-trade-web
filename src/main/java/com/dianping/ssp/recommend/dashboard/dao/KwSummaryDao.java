package com.dianping.ssp.recommend.dashboard.dao;

import com.dianping.ssp.recommend.dashboard.entity.KwSummary;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by jnkmhbl on 16/12/23.
 */
public interface KwSummaryDao {
    /*
      @Param startTime ，endTime 左开右闭
     */
    public List<KwSummary> query(@Param("startTime") Date startTime ,
                                 @Param("endTime") Date endTime ,
                                 @Param("articleTypeList") List<Integer> typeList   ,
                                 @Param("analysisTypeList") List<Integer> anaList);
}
