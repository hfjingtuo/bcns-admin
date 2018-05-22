package com.mainiway.utils;

import java.util.UUID;

public class RandomUtil {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
}
