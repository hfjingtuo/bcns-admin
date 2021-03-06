package com.gpm.pay.entity.zlian;

import lombok.Data;

/**
 * @Author:张立华
 * @Description:交易状态 2304
 * @Date：Created in 20:47 2018/5/11
 * @Modified By:
 */
@Data
public class TranStatusDTO extends ZlBaseDTO<TranStatusDTO> {
    private static final long serialVersionUID = 1L;
    // 证联支付分配给商户的机构代码 最大长度9位 不可为空
    private String instuId ;
    // 商户的系统日期，YYYYMMDD 最大长度8位 不可为空
    private String merchantDate ;
    // 商户的时间戳 最大长度6位 不可为空
    private String merchantTime ;
    // 交易流水必须保证唯一性 最大长度32位 不可为空
    private String merchantSeqId ;
    // 原业务请求的交易日期YYYYMMDD 最大长度8位 不可为空
    private String merchantLiqDate ;
    // 业务类型 01：资金转入 02：资金转出 最大长度2位 不可为空
    private String tradeType ;
    // 交易方式 最大长度1位 不可为空
    private String tiedCardType ;
    // 保留域 最大长度128位 可为空
    private String resv ;

    public String toDtoString(){
        return super.toDtoString(this);
    }
}
