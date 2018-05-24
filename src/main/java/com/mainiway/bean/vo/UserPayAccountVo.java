package com.mainiway.bean.vo;

import com.mainiway.common.base.BaseEntry;
import lombok.Data;

/**
 * @Author:张立华
 * @Description: 用户支付平台账号
 * @Date：Created in 10:53 2018/5/22
 * @Modified By:
 */
@Data
public class UserPayAccountVo extends BaseEntry {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 支付类型  1 为证联 暂时默认为 1
     */
    private String type;
    /**
     *  用户在支付平台的客户号
     */
    private String payUserId;
    /**
     *  客户的姓名 最大长度120位 不可为空
     */
    private String userNameText ;
    /**
     * 支付平台中的客户类型  0：个人 1：企业 最大长度1位 不可为空
     */
    private String custType;
    /**
     * 证件类型，参考附录 最大长度2位 不可为空
     */
    private String certType ;
    /**
     * 证件号码 最大长度32位 不可为空
     */
    private String certId ;
    /**
     * 组织机构代码 注：（企业客户开户不允许为空，个人客户可空）  最大长度32位 可空
     */
    private String orgId ;
    /**
     * 银行代码 最大长度4位 不可为空
     */
    private String bankCode ;
    /**
     * 省份代码 最大长度4位 可为空
     */
    private String bankProvinceCode ;
    /**
     * 地区代码 最大长度4位 可为空
     */
    private String bankRegionCode ;
    /**
     * 银行卡号 最大长度32位 不可为空
     */
    private String cardNo ;
    // 支付密码（银行代码为0001时不允许为空，其他可空） 最大长度32位 可为空
    private String payPassWord ;
    //手机号码
    private String mobile ;
}