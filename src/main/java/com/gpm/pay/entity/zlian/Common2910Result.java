package com.gpm.pay.entity.zlian;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @Author:张立华
 * @Description:
 * @Date：Created in 8:57 2018/5/18
 * @Modified By:
 */
@Data
public class Common2910Result {
    //证联支付分配给商户的机构代码
    @Max(9)
    private String instuId ;
    //商户的系统日期，YYYYMMDD（証联发起该报文时可空）
    @Max(8)
    private String merchantDate ;
    //商户的时间戳，HHMMSS（証联发起该报文时可空）
    @Max(6)
    private String merchantTime ;
    //商户系统发起交易时的请求流水号（証联发起该报文时可空）
    @Max(32)
    private String merchantSeqId ;
    //证联支付发起交易的系统日期，YYYYMMDD（商户发起该报文时可空）
    @Max(8)
    private String pnrDate ;
    //证联支付发给基金的时间戳，HHMMSS（商户发起该报文时可空）
    @Max(6)
    private String pnrTime ;
    //证联支付的流水号。需要保证流水号的唯一性（商户发起该报文时可空）
    @Max(32)
    private String pnrSeqId ;
    //用户在证联支付平台里的客户号
    @NotNull
    @Max(12)
    private String userId ;
    //客户的姓名
    @NotNull
    @Max(120)
    private String userNameText ;
    //银行代码
    @NotNull
    @Max(4)
    private String bankCode ;
    //银行卡号
    @NotNull
    @Max(32)
    private String cardNo ;
    //资金转入金额
    @NotNull
    @Max(16)
    private String transAmt ;
    //应答码
    private String respCode ;
    //业务应答描述
    private String respDesc ;
    //保留域
    private String resv ;
}
