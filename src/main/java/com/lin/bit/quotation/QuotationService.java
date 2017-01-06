package com.lin.bit.quotation;

/**
 * Created by lin.zhao on 2017/1/6.
 */
public interface QuotationService {

    /**
     * 查询最新行情
     * <p>
     * 返回：
     * date: 返回数据时服务器时间
     * buy: 买一价
     * high: 最高价
     * last: 最新成交价
     * low: 最低价
     * sell: 卖一价
     * vol: 成交量(最近的24小时)
     */
    void queryLatestPrice();

    /**
     * 查询：获取比特币或莱特币的K线数据
     * <p>
     * 返回：
     * 时间戳
     * 开
     * 高
     * 低
     * 收
     * 交易量
     */
    void queryKLine();

}

