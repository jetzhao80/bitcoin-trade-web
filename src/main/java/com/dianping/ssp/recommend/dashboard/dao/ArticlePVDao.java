package com.dianping.ssp.recommend.dashboard.dao;

import com.dianping.ssp.recommend.dashboard.entity.ArticlePVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by jnkmhbl on 16/12/29.
 */
public interface ArticlePVDao {
    public List<ArticlePVO> query(@Param("startTime") Date startTime ,@Param("endTime")Date endTime);
}
