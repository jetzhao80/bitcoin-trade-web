package com.dianping.ssp.recommend.dashboard.util;

import com.dianping.ssp.recommend.dashboard.constant.EntranceType;
import com.dianping.ssp.recommend.dashboard.constant.SearchType;
import com.dianping.ssp.recommend.dashboard.constant.StatementType;
import com.dianping.ssp.recommend.dashboard.constant.TimeType;
import com.dianping.ssp.recommend.dashboard.dto.*;
import com.dianping.ssp.recommend.dashboard.entity.QueryEntity;
import com.dianping.ssp.recommend.dashboard.entity.StraAndAlgoInfo;
import com.dianping.ssp.recommend.dashboard.entity.UserOpData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 * BuildUtil
 *
 * @author Yuxiang Ye
 * @date 2016/12/1
 * @time 上午10:41
 */
public class BuildUtil {

    public static QueryEntity generateQueryEntity(StraAndAlgoRequest request){
        QueryEntity queryEntity = new QueryEntity();
        queryEntity.setStartDate(new Date(request.getStartTime()));
        queryEntity.setEndDate(new Date(request.getEndTime()));
        queryEntity.setSceneIds(getSceneIds(request.getEntrance()));

        return queryEntity;
    }

    public static QueryEntity generateQueryEntity(StatementRequest request){
        QueryEntity queryEntity = new QueryEntity();
        queryEntity.setStartDate(new Date(request.getStartTime()));
        queryEntity.setEndDate(new Date(request.getEndTime()));
        if (request.getTimeType() == TimeType.DAY){
            queryEntity.setTimeType(Calendar.DAY_OF_YEAR);
        }else if(request.getTimeType() == TimeType.HOUR){
            queryEntity.setTimeType(Calendar.HOUR_OF_DAY);
        }
        if (CollectionUtils.isNotEmpty(request.getStrategyIds())) {
            queryEntity.setStrategyIds(request.getStrategyIds());
        }
        if (CollectionUtils.isNotEmpty(request.getAlgos())) {
            List<Integer> algoIds = Lists.newArrayList();
            List<Integer> algoVersions = Lists.newArrayList();
            for (AlgoInfoVo algoInfoVo : request.getAlgos()){
                algoIds.add(algoInfoVo.getAlgoId());
                algoVersions.add(algoInfoVo.getAlgoVersion());
            }
            queryEntity.setAlgorithmIds(algoIds);
            queryEntity.setAlgorithmVersions(algoVersions);
        }
        queryEntity.setSceneIds(getSceneIds(request.getEntrance()));
        queryEntity.setArticleTypes(request.getBiz()<=0?null:Lists.newArrayList(request.getBiz()));
        return queryEntity;
    }

    public static List<AlgoInfoVo> generateAlgoInfo(List<StraAndAlgoInfo> algoInfoList, Map<String, String> algoInfoMap){
        List<AlgoInfoVo> algos = Lists.newArrayList();
        if (CollectionUtils.isEmpty(algoInfoList)){
            return algos;
        }
        Set<String> algoSet = Sets.newHashSet();
        for (StraAndAlgoInfo straAndAlgoInfo : algoInfoList){
            AlgoInfoVo algoInfoVo = new AlgoInfoVo();
            algoInfoVo.setAlgoId(straAndAlgoInfo.getAlgorithmId());
            algoInfoVo.setAlgoVersion(straAndAlgoInfo.getAlgorithmVersion());
            String algoIdAndVersion = algoInfoVo.getAlgoId() + "#" + algoInfoVo.getAlgoVersion();
            if (MapUtils.isNotEmpty(algoInfoMap)){
                algoInfoVo.setDesc(algoInfoMap.get(algoIdAndVersion));
            }
            if (algoSet.add(algoIdAndVersion)) {
                algos.add(algoInfoVo);
            }
        }
        return algos;
    }

    public static Map<Integer, String> generateStraInfo(List<StraAndAlgoInfo> straInfoList, Map<Integer, String> straInfoMap){
        Map<Integer, String> straMap = Maps.newHashMap();
        Set<Integer> straSet = Sets.newHashSet();
        List<Integer> straList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(straInfoList)){
            return straMap;
        }

        for (StraAndAlgoInfo straAndAlgoInfo : straInfoList) {
            if (straSet.add(straAndAlgoInfo.getStrategyId())){
                straList.add(straAndAlgoInfo.getStrategyId());
            }
        }

        if (CollectionUtils.isNotEmpty(straList) && MapUtils.isNotEmpty(straInfoMap)) {
            for (Integer straId : straList) {
                straMap.put(straId, straInfoMap.get(straId));
            }
        }

        return straMap;
    }

    public static ChartVo generateCharVo(List<UserOpData> dataList, int searchType, int chartType){
        if (CollectionUtils.isEmpty(dataList)){
            return null;
        }

        // 构建<time, UserOpData>映射
        Map<String, List<UserOpData>> partitionData = Maps.newHashMap();
        // 构建line名称与lineVo映射
        Map<String, LineVo> lineVoMap = Maps.newHashMap();
        // 填充lineVo
        List<String> timeline = Lists.newArrayList();

        for (UserOpData userOpData : dataList){
            if (!partitionData.containsKey(userOpData.getTime())){
                List<UserOpData> emptyList = Lists.newArrayList();
                partitionData.put(userOpData.getTime(), emptyList);
            }
            partitionData.get(userOpData.getTime()).add(userOpData);

            String name = getLineName(userOpData, searchType);
            if (!lineVoMap.containsKey(name)){
                LineVo lineVo = new LineVo();
                lineVo.setName(name);
                List<Double> values = Lists.newArrayList();
                List<LinkedHashMap<String, Double>> extraValues = Lists.newArrayList();
                lineVo.setExtraValues(extraValues);
                lineVo.setValues(values);
                lineVoMap.put(name, lineVo);
            }

            if (!timeline.contains(userOpData.getTime())){
                timeline.add(userOpData.getTime());
            }
        }

        Collections.sort(timeline);

        LinkedHashMap<String, Double> defExtraValue=Maps.newLinkedHashMap();
        for (String time : timeline){
            Set<String> tmpNameSet = Sets.newHashSet(lineVoMap.keySet());
            for (UserOpData userOpData : partitionData.get(time)){
                String name = getLineName(userOpData, searchType);
                LineVo lineVo = lineVoMap.get(name);
                lineVo.getValues().add(BigDecimal.valueOf(getLineValue(userOpData, chartType)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                lineVo.getExtraValues().add(getExtraValue(userOpData, chartType));
                if(defExtraValue.isEmpty()){
                	for (Map.Entry<String, Double> entry : lineVo.getExtraValues().get(0).entrySet()) {
                		defExtraValue.put(entry.getKey(), 0.0);
					}
                }
                tmpNameSet.remove(name);
            }
            if(CollectionUtils.isEmpty(tmpNameSet)){
                continue;
            }
            for (String name : tmpNameSet){
            	LineVo lineVo = lineVoMap.get(name);
            	lineVo.getValues().add(0.0);
                lineVo.getExtraValues().add(defExtraValue);
            }
        }

        ChartVo chartVo = new ChartVo();
        chartVo.setTimeline(timeline);
        chartVo.setLines(Lists.newArrayList(lineVoMap.values()));
        return chartVo;
    }

    private static String getLineName(UserOpData userOpData, int searchType){
        if (userOpData == null){
            return "";
        }

        if (searchType == SearchType.STRA_TYPE){
            return Long.toString(userOpData.getStrategyId());
        }else if (searchType == SearchType.ALGO_TYPE){
            return userOpData.getAlgoIdAndVersion();
        }else if (searchType == SearchType.ALL){
            return "整体";
        }

        return "";
    }

    private static LinkedHashMap<String, Double> getExtraValue(UserOpData userOpData, int chartType){
    	LinkedHashMap<String, Double> linkedHashMap=Maps.newLinkedHashMap();
    	if (userOpData == null || chartType <= 0){
    		return linkedHashMap;
    	}
    	
    	switch (chartType) {
    	case StatementType.CONVERSION_CHART:
    		linkedHashMap.put("点击数", (double)userOpData.getClickCount());
    		linkedHashMap.put("露出数", (double)userOpData.getShowCount());
    		return linkedHashMap;
    	case StatementType.COVERAGE_CHART:
    	case StatementType.COVERAGE_NEW_CHART:
    		linkedHashMap.put("推荐数", (double)userOpData.getRecmdCount());
    		linkedHashMap.put("总文章数", (double)userOpData.getArticleCount());
    		return linkedHashMap;
    	case StatementType.DIVERSITY_CHART:
    		linkedHashMap.put("推荐类目数", (double)userOpData.getRecmdCategoryCount());
    		linkedHashMap.put("推荐用户数", (double)userOpData.getUserCount());
    		linkedHashMap.put("总类目数", (double)userOpData.getTotalCategoryCount());
    		return linkedHashMap;
    	case StatementType.EXPOSURE_CHART:
    		linkedHashMap.put("露出数", (double)userOpData.getShowCount());
    		linkedHashMap.put("推荐数", (double)userOpData.getRecmdCount());
    		return linkedHashMap;
    	default:
    		return linkedHashMap;
    	}
    }
    private static double getLineValue(UserOpData userOpData, int chartType){
        if (userOpData == null || chartType <= 0){
            return 0;
        }

        switch (chartType) {
            case StatementType.CONVERSION_CHART:
                return userOpData.getConversionRate();
            case StatementType.COVERAGE_CHART:
            case StatementType.COVERAGE_NEW_CHART:
                return userOpData.getCoverageRate();
            case StatementType.DIVERSITY_CHART:
                return userOpData.getDiversity();
            case StatementType.EXPOSURE_CHART:
                return userOpData.getShowRate();
            default:
                return 0;
        }
    }

    private static List<Integer> getSceneIds(int entrance){
        if (entrance < 0){
            return null;
        }

        List<Integer> sceneIds = Lists.newArrayList();
        switch (entrance) {
            case EntranceType.NO_LIMIT:
                return null;
            case EntranceType.SHOUYE:
                sceneIds.add(2);
                return sceneIds;
            case EntranceType.ZIXUN_LIST:
                sceneIds.add(1);
                sceneIds.add(4);
                sceneIds.add(5);
                sceneIds.add(8);
                return sceneIds;
            case EntranceType.ZIXUN_DETAIL:
                sceneIds.add(3);
                return sceneIds;
            case EntranceType.XIAOSHOU:
                sceneIds.add(10);
                return sceneIds;
            case EntranceType.KEYWORD:
                sceneIds.add(9);
                return sceneIds;
            default:
                return null;
        }
    }
}
