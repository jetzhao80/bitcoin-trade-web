package com.dianping.ssp.recommend.dashboard.service;

import com.dianping.ssp.article.constant.ArticleStatusCode;
import com.dianping.ssp.logger.LoggerManager;
import com.dianping.ssp.logger.SSPLogger;
import com.dianping.ssp.recommend.dashboard.constant.RecDashWebLogEnum;
import com.dianping.ssp.recommend.dashboard.dao.UserOpStatisticDao;
import com.dianping.ssp.recommend.dashboard.entity.QueryEntity;
import com.dianping.ssp.recommend.dashboard.entity.StraAndAlgoInfo;
import com.dianping.ssp.recommend.dashboard.entity.UserOpData;
import com.dianping.ssp.search.article.entity.Article;
import com.dianping.ssp.search.article.entity.ArticleRequest;
import com.dianping.ssp.search.article.service.ArticleSearchService;
import com.dianping.ssp.search.common.SspResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * StatementService
 *
 * @author Yuxiang Ye
 * @date 2016/11/30
 * @time 下午5:48
 */
@Service
public class StatementService {
    private static final SSPLogger ERROR = LoggerManager.getLogger(RecDashWebLogEnum.STATEMENT_SERVICE_ERROR.getValue());
    private static final SSPLogger INFO = LoggerManager.getLogger(RecDashWebLogEnum.STATEMENT_SERVICE_INFO.getValue());

    @Autowired
    private ArticleSearchService articleSearchService;

    @Autowired
    private UserOpStatisticDao userOpStatisticDao;

    /**
     * 查询转化率
     * @param queryEntity
     * @return
     */
    public List<UserOpData> getConversionRate(QueryEntity queryEntity){
        try {
            return userOpStatisticDao.loadConversionRate(queryEntity);
        }catch (Exception e){
            ERROR.error("fail to load conversion rate, queryEntity:" + queryEntity, e);
            return null;
        }
    }

    /**
     * 查询覆盖率
     * @param queryEntity
     * @return
     */
    public List<UserOpData> getCoverageRate(QueryEntity queryEntity){
        try {
            return userOpStatisticDao.loadCoverageRate(queryEntity);
        }catch (Exception e){
            ERROR.error("fail to load coverage rate, queryEntity:" + queryEntity, e);
            return null;
        }
    }
    public List<UserOpData> getCoverageRateNew(QueryEntity queryEntity){
        try {
            return userOpStatisticDao.loadCoverageRateNew(queryEntity);
        }catch (Exception e){
            ERROR.error("fail to load coverage rate new, queryEntity:" + queryEntity, e);
            return null;
        }
    }

    /**
     * 查询多样性
     * @param queryEntity
     * @return
     */
    public List<UserOpData> getDiversity(QueryEntity queryEntity){
        try {
            return userOpStatisticDao.loadDiversity(queryEntity);
        }catch (Exception e){
            ERROR.error("fail to load diversity, queryEntity:" + queryEntity, e);
            return null;
        }
    }

    /**
     * 查询露出率
     * @param queryEntity
     * @return
     */
    public List<UserOpData> getShowRate(QueryEntity queryEntity){
        try {
            return userOpStatisticDao.loadShowRate(queryEntity);
        }catch (Exception e){
            ERROR.error("fail to load show rate, queryEntity:" + queryEntity, e);
            return null;
        }
    }

    /**
     * 查询文章数量
     * @return
     */
    public int getArticleCount(){
        ArticleRequest articleRequest = new ArticleRequest();
        List<Integer> status = Collections.singletonList(ArticleStatusCode.ONLINE);
        articleRequest.setAuditStatuses(status);
        try {
            SspResponse<Article> articleSspResponse = articleSearchService.search4appByEs(articleRequest);
            if (articleSspResponse.getStatus().equals(SspResponse.ERROR)) {
                ERROR.error("fail to get article count, resp:" + articleSspResponse.getErrorMessage());
                return -2;
            }
            return articleSspResponse.getTotalHits();
        }catch (Exception e){
            ERROR.error("fail to invoke article count service", e);
            return -1;
        }
    }

    /**
     * 获取测了和算法信息
     * @param queryEntity
     * @return
     */
    public List<StraAndAlgoInfo> getStraAndAlgoInfo(QueryEntity queryEntity){
        try {
            return userOpStatisticDao.loadStraAndAlgoInfo(queryEntity);
        }catch (Exception e){
            ERROR.error("fail to load StraAndAlgoInfo, queryEntity:" + queryEntity, e);
            return null;
        }
    }
}
