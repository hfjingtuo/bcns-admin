package com.mainiway.enums;

import lombok.Getter;

/**
 * @Author:张立华
 * @Description: 交易类型
 * @Date：Created in 18:57 2018/5/11
 * @Modified By:
 */
@Getter
public enum TransactionTypeEnum {
    PAYMENT("1","付款"),
    receivables("2","收款"),
    TURN_IN("3","转入"),
    TURN_OUT("4","转出");

    private String code ;
    private String message ;

    TransactionTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
