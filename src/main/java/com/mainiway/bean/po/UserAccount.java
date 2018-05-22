package com.mainiway.bean.po;

import com.mainiway.common.base.BaseEntry;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:张立华
 * @Description: 个人账户
 * @Date：Created in 10:45 2018/5/22
 * @Modified By:
 */
@Data
public class UserAccount extends BaseEntry {

    private static final long serialVersionUID = 1L;

    private String userId;
    private BigDecimal amount;

}
