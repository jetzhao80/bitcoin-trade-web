package com.dianping.ssp.recommend.dashboard.controller;

import com.dianping.ssp.logger.LoggerManager;
import com.dianping.ssp.logger.SSPLogger;
import com.dianping.ssp.recommend.dashboard.constant.RecDashWebLogEnum;
import com.dianping.ssp.recommend.dashboard.controller.base.BaseController;
import com.dianping.ssp.recommend.dashboard.dto.*;
import com.dianping.ssp.recommend.dashboard.service.KwSummaryService;
import com.dianping.ssp.recommend.dashboard.util.DateUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/dashboard")
public class KeywordController extends BaseController {

    private static final SSPLogger ERROR = LoggerManager.getLogger(RecDashWebLogEnum.RCM_DASHBOARD_ERROR.getValue());
    private static final SSPLogger INFO = LoggerManager.getLogger(RecDashWebLogEnum.RCM_DASHBOARD_INFO.getValue());

    @Autowired
    private KwSummaryService kwSummaryService ;
    private static final String FORMAT = "yyyy-MM-dd" ;
    @RequestMapping("/keyword")
    public String keyword(HttpServletRequest request) {
        SimpleDateFormat sd = new SimpleDateFormat(FORMAT);
        Calendar calendar = Calendar.getInstance();
        String endTime = sd.format(calendar.getTime());
        calendar.add(Calendar.DATE, -6);
        String startTime = sd.format(calendar.getTime());
        request.setAttribute("startTime", startTime + " 00:00");
        request.setAttribute("endTime", endTime + " 00:00");
        return "keyword/keyword";
    }

    @RequestMapping(value = "/keyword/getData", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public StatementChartVo acquireKeywordData(@RequestBody KwRequest request) throws Exception{
        if(request.getStartTime() > request.getEndTime()){
             return StatementChartVo.errorChartVo(500,"参数错误,结束日期不能早于开始日期");
        }

        Date fromDate = new Date(request.getStartTime());
        Date toDate =  new Date(request.getEndTime());
        List<Integer> articleTypeList = acquireArticleTypeList(request);
        request.setArticleTypeList(articleTypeList);
        KwSummaryDto dto =  kwSummaryService.query(fromDate, toDate, articleTypeList, Arrays.asList(request.getAnalysisType()));
        ChartVo chartVo = getChartVo(dto, request);
        return StatementChartVo.successChartVo(chartVo);
    }

    private List<Integer> acquireArticleTypeList(KwRequest request) {
        List<Integer> all  = Lists.newArrayList();
        if(ArticleType.validate(request.getArticleType())){
            all.add(request.getArticleType());
            return all;
        }else {
            return ArticleType.all();
        }
    }

    private ChartVo getChartVo(KwSummaryDto result,KwRequest request) {

        try {
            if (result.getArticleMap().size() == 0 && result.getUserMap().size() == 0) {
                ERROR.error("can't init two charts");
                return null;
            }
            Map<String, KwSummaryDto.VC> map = null;
            if (result.getUserMap().size() != 0) {
                map = result.getUserMap();
            } else {
                map = result.getArticleMap();
            }

            ChartVo charVo = new ChartVo();
            List<String> timeLine = DateUtil.dateGaps(new Date(request.getStartTime()), new Date(request.getEndTime())) ;
            charVo.setTimeline(timeLine);
            List<LineVo> lines = getLines(map,timeLine);
            for(int index = lines.size() -1 ;index >=0 ;index --){
                LineVo line  = lines.get(index);
                int lineType = Integer.parseInt(line.getName());
                boolean isNeed = false ;
                for(int targetType : request.getArticleTypeList()){
                    if(lineType == targetType)
                        isNeed = true ;
                }
                if(!isNeed){
                    lines.remove(index);
                }
            }
            charVo.setLines(lines);
            return charVo;
        }catch (Exception e){
            ERROR.error("构造图标error ", e);
        }
        return null ;
    }


    public List<LineVo> getLines(Map<String, KwSummaryDto.VC> map,List<String> timeLines) {

        List<LineVo> lines = Lists.newArrayList();

        List<Integer> typeList = ArticleTypeEnum.getTypeList();
        for(int type : typeList){
            LineVo lineVo = new LineVo();
            for(String time : timeLines){
                KwSummaryDto.VC vc =  map.get(time);
                int clickCount = 0;
                int viewCount = 0 ;
                if(vc != null){
                     clickCount = vc.getClickCount()[type];
                     viewCount = vc.getViewCount()[type];
                }
                double rate = 0.0 ;
                if(viewCount != 0){
                    rate = clickCount/(viewCount+0.0) ;
                }
                lineVo.addValue(rate);
                LinkedHashMap<String,Double> descriptions = new LinkedHashMap<String, Double>();
                descriptions.put("点击量",clickCount+0.0);
                descriptions.put("曝光量", viewCount + 0.0);
                lineVo.addDescription(descriptions);
                lineVo.setName(type + "");
            }
            lines.add(lineVo);

        }
        return lines;
    }
}
 enum ArticleType{
    NEWS(1,"新闻"), MEETING(2,"会议") , STRATEGY(3,"攻略");
     private int code ;  private String msg ;

     ArticleType(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

     public int getCode() {
         return code;
     }

     public void setCode(int code) {
         this.code = code;
     }

     public String getMsg() {
         return msg;
     }

     public void setMsg(String msg) {
         this.msg = msg;
     }


     public static  List<Integer> all(){
         List<Integer> list =   new ArrayList<Integer>() ;
         list.add(MEETING.code);
         list.add(NEWS.code);
         list.add(STRATEGY.code);
         return list ;
     }

     public static  boolean validate(List<Integer> types){
         if(CollectionUtils.isEmpty(types)){
             return false ;
         }
         for(Integer type : types){
             return validate(type);
         }

         return true ;
     }

     public static  boolean validate(int typeValue){
         return typeValue == NEWS.code
                 || typeValue == MEETING.code
                 || typeValue == STRATEGY.code;

    }

}