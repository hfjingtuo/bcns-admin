package com.mainiway.enums;

import com.mainiway.common.base.IStatusMessage;
import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-06-11 18:56
 */
@Getter
public enum TransferEnum implements IStatusMessage{
    SUCCESS("0", "成功"),
    FROM_USER_IS_NULL("1", "用户不存在!"),
    FROM_USER_PAY_ACCOUNT_IS_NULL("2", "当前用户未开户!"),
    TO_USER_IS_NULL("3", "收款人不存在!"),
    TO_USER_PAY_ACCOUNT_IS_NULL("4", "收款人未开户"),
    TO_USER_REAL_NAME_ERROR("5", "收款人信息不一致"),
    ACCOUT_BALANCE_LESS_FAIL("6", "余额不足!"),
    PAY_PASSWORD_ERROR("7", "支付密码错误!");
    private String code;

    private String message;

    TransferEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
