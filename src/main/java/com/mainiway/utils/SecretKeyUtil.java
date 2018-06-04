package com.mainiway.utils;

/**
 * @Author:张立华
 * @Description:
 * @Date：Created in 22:27 2018/5/22
 * @Modified By:
 */
public class SecretKeyUtil {
    public static String token(String userName , String password ){
        return MD5Util.toMD5(userName + "|" + password );
    };

    public static String payPassword(String password ){
        return MD5Util.toMD5( password );
    };
}
