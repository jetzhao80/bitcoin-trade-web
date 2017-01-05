package com.dianping.ssp.recommend.dashboard.service;

import com.dianping.ssp.recommend.dashboard.dao.ArticlePVDao;
import com.dianping.ssp.recommend.dashboard.dto.ArticlePvHourSummary;
import com.dianping.ssp.recommend.dashboard.entity.ArticlePVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by jnkmhbl on 16/12/29.
 */
@Component
public class ArticlePvService {
    @Autowired
    private ArticlePVDao dao ;

    public ArticlePvHourSummary querySummary(Date startTime ,Date endTime){
        List<ArticlePVO> voList = dao.query(startTime,endTime);
        if(voList == null){
            return null ;
        }
        ArticlePvHourSummary summary = new ArticlePvHourSummary(voList);
        return summary ;
    }
}
