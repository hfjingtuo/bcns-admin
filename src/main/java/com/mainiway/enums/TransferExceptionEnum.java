package com.mainiway.enums;

import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-06-11 18:56
 */
@Getter
public enum TransferExceptionEnum {
    SUCCESS(0, "成功"),
    FROM_USER_IS_NULL(1, "用户不存在!"),
    TO_USER_IS_NULL(2, "用户不存在!"),
    ACCOUT_BALANCE_LESS_FAIL(3, "余额不足!");
    private Integer code;

    private String message;

    TransferExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
