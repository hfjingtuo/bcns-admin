package com.mainiway.bean.po;

import com.mainiway.common.base.BaseEntry;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:张立华
 * @Description: 用户交易记录
 * @Date：Created in 11:03 2018/5/22
 * @Modified By:
 */
@Data
public class UserTransactionRecord extends BaseEntry {
    private static final long serialVersionUID = 1L;
    /**
     * 交易流水号
     */
    private String seqId ;
    /**
     * 交易状态 0 交易启动  100 交易完成
     */
    private String status ;
    /**
     * 交易类型  1 支付  2 收入  3 转入 4 转出
     */
    private String type ;
    /**
     * 金额
     */
    private BigDecimal amt ;
    /**
     * 当前余额
     */
    private String balance ;
    /**
     * 来源账户
     */
    private String fromUserId ;
    /**
     * 目标账户
     */
    private String ToUserId ;
    /**
     * 来源银行账户
     */
    private String FromBankCode ;
    /**
     * 来源银行账户
     */
    private String FromCardNo ;
    /**
     * 目标银行账户
     */
    private String ToBankCode ;
    /**
     * 目标银行卡号
     */
    private String ToCardNo ;
    /**
     * 支付账号
     */
    private String payAccountId ;
}