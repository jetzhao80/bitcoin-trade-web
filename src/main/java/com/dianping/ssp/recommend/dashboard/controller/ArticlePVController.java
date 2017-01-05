package com.dianping.ssp.recommend.dashboard.controller;

import com.dianping.ssp.recommend.dashboard.dto.*;
import com.dianping.ssp.recommend.dashboard.service.ArticlePvService;
import com.dianping.ssp.recommend.dashboard.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.actors.threadpool.Arrays;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jnkmhbl on 16/12/29.
 */
@Controller
@RequestMapping("/dashboard")
public class ArticlePVController {

    @Autowired
    private ArticlePvService service;



    @RequestMapping("/pv")
    public String defaultPath(ArticlePVRequest request) {
        Calendar calendar = Calendar.getInstance();
        request.setEndTime(new Date());
        request.setStartTime(new Date());
        return "articlePv/articlePv";
    }

    @RequestMapping(value = "/pv/query", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public StatementChartVo acquireKeywordData(@RequestBody ArticlePVRequest request) throws Exception {
        ArticlePvHourSummary  summary = service.querySummary(request.getStartTime(), request.getEndTime());
        List<String> hourList = DateUtil.hourGaps(request.getStartTime(), request.getEndTime());
        ChartVo chartVo = new ChartVo();
        chartVo.setTimeline(hourList);
        LineVo line = new LineVo();
        for(String hour : hourList){
            line.addValue(summary.getPV(hour));
        }
        List<LineVo> lines = new ArrayList<LineVo>();
        lines.add(line);
        chartVo.setLines(lines);
        return StatementChartVo.successChartVo(chartVo);
    }
}
