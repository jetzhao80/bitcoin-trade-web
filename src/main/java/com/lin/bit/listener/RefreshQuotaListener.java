package com.lin.bit.listener;

import com.lin.bit.entity.TradeEvent;

/**
 * Created by lin.zhao on 2017/1/6.
 */
public interface RefreshQuotaListener {

    void onEvent(TradeEvent event);

}
