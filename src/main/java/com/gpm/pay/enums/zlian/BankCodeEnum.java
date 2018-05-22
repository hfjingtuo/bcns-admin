package com.gpm.pay.enums.zlian;

import lombok.Getter;

/**
 * @Author:张立华
 * @Description: 银行代码
 * @Date：Created in 19:13 2018/5/11
 * @Modified By:
 */
@Getter
public enum BankCodeEnum {
    CHINA_EVERBRIGHT_BANK("0303","中国光大银行"),
    ICBC("0102","中国工商银行"),
    CAB("0103","中国农业银行"),
    BC("0104","中国银行"),
    CCB("0105","中国建设银行"),
    BANK_OF_COMMUNICATIONS("0301","交通银行"),
    CHINA_MERCHANTS_BANK("0308","招商银行"),
    SHANGHAI_PUDONG_DEVELOPMENT_BANK("0310","上海浦东发展银行"),
    CHINA_MINSHENG_BANK("0305","中国民生银行"),
    XINGYE_BANK("0310","兴业银行"),
    HUAXIA_BANK("0305","华夏银行"),
    GUANGDONG_DEVELOPMENT_BANK("0310","广东发展银行"),
    SHENZHEN_DEVELOPMENT_BANK("0305"," 深圳发展银行"),
    CITIC_BANK("0302","中信银行");

    private String code ;
    private String name ;

    BankCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
