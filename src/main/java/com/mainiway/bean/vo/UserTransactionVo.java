package com.mainiway.bean.vo;

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
public class UserTransactionVo extends BaseVo {
    private static final long serialVersionUID = 1L;
    /**
     * 来源账户
     */
    private String fromUserId ;
    /**
     * 目标账户用户名或者手机号
     */
    private String toUserName ;

    /**
     * 目标账户真实姓名
     */
    private String toUserRealName ;
    /**
     * 目标账户
     */
    private String toUserId ;
    /**
     * 金额
     */
    private BigDecimal amt ;
    /**
     * 支付密码
     */
    private String payPassWord ;
    /**
     * 说明
     */
    private String remarks ;
}