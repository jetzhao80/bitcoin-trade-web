package com.dianping.ssp.recommend.dashboard.controller;

import com.dianping.lion.client.Lion;
import com.dianping.ssp.logger.LoggerManager;
import com.dianping.ssp.logger.SSPLogger;
import com.dianping.ssp.recommend.dashboard.constant.RecDashWebLogEnum;
import com.dianping.ssp.recommend.dashboard.constant.ResultCode;
import com.dianping.ssp.recommend.dashboard.constant.SearchType;
import com.dianping.ssp.recommend.dashboard.constant.StatementType;
import com.dianping.ssp.recommend.dashboard.controller.base.BaseController;
import com.dianping.ssp.recommend.dashboard.dto.*;
import com.dianping.ssp.recommend.dashboard.entity.QueryEntity;
import com.dianping.ssp.recommend.dashboard.entity.StraAndAlgoInfo;
import com.dianping.ssp.recommend.dashboard.entity.UserOpData;
import com.dianping.ssp.recommend.dashboard.service.StatementService;
import com.dianping.ssp.recommend.dashboard.util.BuildUtil;
import com.dianping.ssp.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recommend")
public class DashboardController extends BaseController {

    private static final SSPLogger ERROR = LoggerManager.getLogger(RecDashWebLogEnum.RCM_DASHBOARD_ERROR.getValue());
    private static final SSPLogger INFO = LoggerManager.getLogger(RecDashWebLogEnum.RCM_DASHBOARD_INFO.getValue());

    @Autowired
    private StatementService statementService;

    @RequestMapping(value="/getStatement", produces="application/json;charset=UTF-8")
    @ResponseBody
    public StatementResponseVo getStatement(@RequestBody StatementRequest request){
        StatementResponseVo vo = new StatementResponseVo();

        try {
            // 参数校验
            

            QueryEntity queryEntity = BuildUtil.generateQueryEntity(request);
            if (queryEntity == null) {
                vo.setCode(ResultCode.SERVER_ERROR);
                vo.setMsg("处理参数错误");
                return vo;
            }
            // 获取文章总数
            int articleCount = statementService.getArticleCount();
            if (articleCount < 0) {
                // 降级,覆盖率指标会受影响
                articleCount = 1;
            }
            // 获取分类总数
            int categoryCount = Lion.getIntValue("ssp-recommend-dashboard-web.category.count");
            queryEntity.setTotalArticleNum(articleCount);
            queryEntity.setTotalArticleCategoryNum(categoryCount);
            int searchType = 0;
            if (CollectionUtils.isNotEmpty(queryEntity.getStrategyIds())) {
                searchType = SearchType.STRA_TYPE;
            }
            if (CollectionUtils.isNotEmpty(queryEntity.getAlgorithmIds()) && CollectionUtils.isNotEmpty(queryEntity.getAlgorithmVersions())) {
                searchType = SearchType.ALGO_TYPE;
            }


            // 获取转化率
            List<UserOpData> conversionList = statementService.getConversionRate(queryEntity);
            // 获取覆盖率
            List<UserOpData> coverageList = statementService.getCoverageRate(queryEntity);
            // 获取多样性
            List<UserOpData> diversityList = statementService.getDiversity(queryEntity);
            // 获取露出率
            List<UserOpData> showRateList = statementService.getShowRate(queryEntity);

            // 构建返回值
            vo.setConversionChart(BuildUtil.generateCharVo(conversionList, searchType, StatementType.CONVERSION_CHART));
            vo.setCoverageChart(BuildUtil.generateCharVo(coverageList, searchType, StatementType.COVERAGE_CHART));
            vo.setDiversityChart(BuildUtil.generateCharVo(diversityList, searchType, StatementType.DIVERSITY_CHART));
            vo.setExposureChart(BuildUtil.generateCharVo(showRateList, searchType, StatementType.EXPOSURE_CHART));
            vo.setCode(ResultCode.SUCCESS);
            return vo;
        }catch (Exception e){
            ERROR.error("fail to process getting statement", e);
            vo.setCode(ResultCode.SERVER_ERROR);
            vo.setMsg("系统错误");
            return vo;
        }
    }
    
    @RequestMapping(value="/getStatementByType", produces="application/json;charset=UTF-8")
    @ResponseBody
    public StatementChartVo getStatementByType(@RequestBody StatementRequest request){
    	StatementChartVo vo = new StatementChartVo();
        try {
            int searchType = 0;
            QueryEntity queryEntity = BuildUtil.generateQueryEntity(request);
            if (queryEntity == null) {
                vo.setCode(ResultCode.SERVER_ERROR);
                vo.setMsg("处理参数错误");
                return vo;
            }
            if (CollectionUtils.isNotEmpty(queryEntity.getStrategyIds())) {
                searchType = SearchType.STRA_TYPE;
            }
            if (CollectionUtils.isNotEmpty(queryEntity.getAlgorithmIds()) && CollectionUtils.isNotEmpty(queryEntity.getAlgorithmVersions())) {
                searchType = SearchType.ALGO_TYPE;
            }
            if(request.getStatementType()==StatementType.CONVERSION_CHART){
            	// 获取转化率
            	List<UserOpData> conversionList = statementService.getConversionRate(queryEntity);
            	vo.setChartVo(BuildUtil.generateCharVo(conversionList, searchType, StatementType.CONVERSION_CHART));
            }else if(request.getStatementType()==StatementType.EXPOSURE_CHART){
            	// 获取露出率
            	List<UserOpData> showRateList = statementService.getShowRate(queryEntity);
            	vo.setChartVo(BuildUtil.generateCharVo(showRateList, searchType, StatementType.EXPOSURE_CHART));
            }else if(request.getStatementType()==StatementType.COVERAGE_NEW_CHART){
            	// 获取新覆盖率
            	List<UserOpData> coverageList = statementService.getCoverageRateNew(queryEntity);
            	vo.setChartVo(BuildUtil.generateCharVo(coverageList, searchType, StatementType.COVERAGE_NEW_CHART));

            }else if(request.getStatementType()==StatementType.COVERAGE_CHART){
                // 获取覆盖率
                // 获取文章总数
                int articleCount = statementService.getArticleCount();
                if (articleCount < 0) {
                    // 降级,覆盖率指标会受影响
                    articleCount = 1;
                }
                queryEntity.setTotalArticleNum(articleCount);
                List<UserOpData> coverageList = statementService.getCoverageRate(queryEntity);
                vo.setChartVo(BuildUtil.generateCharVo(coverageList, searchType, StatementType.COVERAGE_CHART));

            }else if(request.getStatementType()==StatementType.DIVERSITY_CHART){
            	// 获取多样性
            	// 获取分类总数
                int categoryCount = Lion.getIntValue("ssp-recommend-dashboard-web.category.count");
                queryEntity.setTotalArticleCategoryNum(categoryCount);
            	List<UserOpData> diversityList = statementService.getDiversity(queryEntity);
            	vo.setChartVo(BuildUtil.generateCharVo(diversityList, searchType, StatementType.DIVERSITY_CHART));
            	
            }
            vo.setCode(ResultCode.SUCCESS);
            return vo;
        }catch (Exception e){
            ERROR.error("fail to process getting statement", e);
            vo.setCode(ResultCode.SERVER_ERROR);
            vo.setMsg("系统错误");
            return vo;
        }
    }


    @RequestMapping(value="/getStraAndAlgo", produces="application/json;charset=UTF-8")
    @ResponseBody
    public StraAndAlgoResponseVo getStraAndAlgo(@RequestBody StraAndAlgoRequest request){
        StraAndAlgoResponseVo vo = new StraAndAlgoResponseVo();

        try {
            QueryEntity queryEntity = BuildUtil.generateQueryEntity(request);

            List<StraAndAlgoInfo> straAndAlgoInfo = statementService.getStraAndAlgoInfo(queryEntity);

            // 获取策略与算法描述文案
            String info = Lion.getStringValue("ssp-recommend-dashboard-web.desc.strategy", null);
            if (StringUtils.isBlank(info)){
                info = null;
            }
            Map<Integer, String> straInfoMap = JsonUtil.fromJson(info, new TypeToken<Map<Integer, String>>() {
            }.getType());
            info = Lion.getStringValue("ssp-recommend-dashboard-web.desc.algorithm", null);
            if (StringUtils.isBlank(info)){
                info = null;
            }
            Map<String, String> algoInfoMap = JsonUtil.fromJson(info, new TypeToken<Map<String, String>>(){}.getType());


            vo.setStrategies(BuildUtil.generateStraInfo(straAndAlgoInfo, straInfoMap));
            vo.setAlgos(BuildUtil.generateAlgoInfo(straAndAlgoInfo, algoInfoMap));
            vo.setCode(ResultCode.SUCCESS);
            return vo;
        }catch (Exception e){
            ERROR.error("fail to process getting straAndAlgo", e);
            vo.setCode(ResultCode.SERVER_ERROR);
            vo.setMsg("系统错误");
            return vo;
        }
    }

    @RequestMapping("/statement")
    public String statement(@RequestParam(value="id",defaultValue="0") long id){
        return "statement";
    }
    @RequestMapping("/dashboard")
    public String dashboard(HttpServletRequest request, HttpServletResponse response){
    	SimpleDateFormat sd = new SimpleDateFormat(
				"yyyy-MM-dd"); 
    	Calendar calendar=Calendar.getInstance();
    	String endTime=sd.format(calendar.getTime());
    	calendar.add(Calendar.DATE, -7);
    	String startTime=sd.format(calendar.getTime());
    	request.setAttribute("startTime", startTime+" 00:00");
    	request.setAttribute("endTime", endTime+" 00:00");
        return "dashboard";
    }
}
