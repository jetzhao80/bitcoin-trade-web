package com.lin.bit.account;

/**
 * 账户信息
 * Created by lin.zhao on 2017/1/6.
 */
public interface AccountService {

    /**
     * 获取用户信息
     * 返回对象包含：
     * asset:账户资产，包含净资产及总资产
     * borrow:账户借款信息(只有在账户有借款信息时才会返回)
     * free:账户余额
     * freezed:账户冻结余额
     * union_fund:账户理财信息(只有在账户有理财信息时才返回)
     */
    void queryAccountInfo();


}
