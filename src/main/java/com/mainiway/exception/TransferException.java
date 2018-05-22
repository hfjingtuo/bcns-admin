package com.mainiway.exception;

import com.mainiway.enums.TransferExceptionEnum;
import lombok.Data;

/**
 * @Author:张立华
 * @Description: 交易异常
 * @Date：Created in 11:53 2018/5/21
 * @Modified By:
 */
@Data
public class TransferException extends RuntimeException{

    private Integer code;

    public TransferException(TransferExceptionEnum transferEnum) {
        super(transferEnum.getMessage());

        this.code = transferEnum.getCode();
    }

    public TransferException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}