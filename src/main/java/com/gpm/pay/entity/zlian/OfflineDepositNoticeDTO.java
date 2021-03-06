package com.gpm.pay.entity.zlian;

import lombok.Data;

/**
 * @Author:张立华
 * @Description:线下入金 2105
 * @Date：Created in 20:44 2018/5/11
 * @Modified By:
 */
@Data
public class OfflineDepositNoticeDTO extends ZlBaseDTO<OfflineDepositNoticeDTO> {
    private static final long serialVersionUID = 1L;
    // 证联支付分配给商户的机构代码 最大长度9位 不可为空
    private String instuId ;
    // 商户的系统日期，YYYYMMDD 最大长度8位 不可为空
    private String merchantDate ;
    // 商户的时间戳 最大长度6位 不可为空
    private String merchantTime ;
    // 交易流水必须保证唯一性 最大长度32位 不可为空
    private String merchantSeqId ;
    // 用户在证联支付平台里的客户号 最大长度20位 不可为空
    private String userId ;
    // 客户的姓名 最大长度120位 不可为空
    private String userNameText ;
    // 证件类型 最大长度2位 不可为空
    private String certType ;
    // 证件号码 最大长度25位 不可为空
    private String certId ;
    // 银行代码 最大长度4位 不可为空
    private String bankCode ;
    // 收款方银行代码 最大长度4位 不可为空
    private String receiBankCode ;
    // 银行账户 最大长度120位 不可为空
    private String cardName ;
    // 银行卡号 最大长度32位 不可为空
    private String cardNo ;
    // 资金转入金额 最大长度16位 不可为空
    private String transAmt ;
    // 保留域 最大长度128位 可为空
    private String resv ;

    public String toDtoString(){
        return super.toDtoString(this);
    }
}
