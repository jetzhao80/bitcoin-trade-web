package com.dianping.ssp.recommend.dashboard.dto;

import com.dianping.ssp.recommend.dashboard.entity.ArticlePVO;
import com.dianping.ssp.recommend.dashboard.util.DateUtil;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jnkmhbl on 16/12/29.
 */
public class ArticlePvHourSummary {

    private static String format = "yyyy-MM-dd HH";
    private Map<String,Entity> entityMap ;
    private transient  List<ArticlePVO> vos ;

    public Map<String, Entity> getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(Map<String, Entity> entityMap) {
        this.entityMap = entityMap;
    }

    public int getPV(String datekey){
        Entity entity = entityMap.get(datekey);
        if(entity == null){
            return 0;
        }
        return entity.getPvNum();
    }


    public  class Entity {
    private String startHour;
    private int pvNum;

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public int getPvNum() {
        return pvNum;
    }

    public void setPvNum(int pvNum) {
        this.pvNum = pvNum;
    }

}
    public ArticlePvHourSummary(List<ArticlePVO> vos){
          this.vos = vos ;
          SummaryInit init = new SummaryInit();
          init.init();
    }

    class SummaryInit{

        public void init(){
            entityMap = new LinkedHashMap<String, Entity>();
            for(ArticlePVO vo : vos){
                String time = DateUtil.getHourAfterString(vo.getCreateTime(),format);
                Entity entity = entityMap.get(time);
                if(entity == null){
                    entity = new Entity();
                    entityMap.put(time,entity);
                    entity.setStartHour(time);
                }
                entity.setPvNum(entity.getPvNum()+vo.getPvDiff());
            }
        }

    }

}
