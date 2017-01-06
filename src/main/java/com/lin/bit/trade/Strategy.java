package com.lin.bit.trade;

/**
 * 交易策略
 * Created by lin.zhao on 2017/1/5.
 */
public interface Strategy {

    /**
     * 下指令判断当前的操作
     * 返回：
     * 是否操作
     * 操作类型：买入，卖出
     * 仓位：半仓，全仓或者比例
     */
    void makeDirective();


}
