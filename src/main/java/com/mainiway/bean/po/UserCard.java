package com.mainiway.bean.po;

import com.mainiway.common.base.BaseEntry;
import lombok.Data;

/**
 * @Author:张立华
 * @Description: 用户银行卡信息，暂时不开放
 * @Date：Created in 10:47 2018/5/22
 * @Modified By:
 */
@Data
public class UserCard extends BaseEntry {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String bankCode;
    private String cardNo;
    private String userName;

}