package com.dianping.ssp.recommend.dashboard.service;

import com.dianping.ssp.recommend.dashboard.dao.KwSummaryDao;
import com.dianping.ssp.recommend.dashboard.dto.KwSummaryDto;
import com.dianping.ssp.recommend.dashboard.entity.KwSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.actors.threadpool.Arrays;

import java.util.Date;
import java.util.List;

/**
 * Created by jnkmhbl on 16/12/23.
 */
@Component
public class KwSummaryService {
    @Autowired
    private KwSummaryDao dao;

    public KwSummaryDto query(Date startTime ,Date endTime ,List<Integer> articleTypeList,
                              List<Integer> anaTypeList){
        List<KwSummary> summaries =  dao.query(startTime, endTime, articleTypeList, anaTypeList);
        return  new KwSummaryDto(summaries);
    }
}
