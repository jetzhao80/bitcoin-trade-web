package com.dianping.ssp.recommend.dashboard.dao;

import com.dianping.ssp.recommend.dashboard.entity.QueryEntity;
import com.dianping.ssp.recommend.dashboard.entity.StraAndAlgoInfo;
import com.dianping.ssp.recommend.dashboard.entity.UserOpData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOpStatisticDao {

	List<UserOpData> loadCoverageRate(@Param("queryEntity")QueryEntity queryEntity);
	List<UserOpData> loadCoverageRateNew(@Param("queryEntity")QueryEntity queryEntity);
	List<UserOpData> loadConversionRate(@Param("queryEntity")QueryEntity queryEntity);
	List<UserOpData> loadShowRate(@Param("queryEntity")QueryEntity queryEntity);
	List<UserOpData> loadDiversity(@Param("queryEntity")QueryEntity queryEntity);

	List<StraAndAlgoInfo> loadStraAndAlgoInfo(@Param("queryEntity") QueryEntity queryEntity);
}
