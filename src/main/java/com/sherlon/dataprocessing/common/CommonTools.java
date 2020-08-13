package com.sherlon.dataprocessing.common;

import java.io.Serializable;

/**
 * @author :  sherlonWang
 * @description :  通用工具类
 * @date: 2020-08-13
 */
public class CommonTools implements Serializable {
    /**
     *
     * @param str 传入的字符串
     * @return null or "" 返回true 否则返回false
     */
    public static boolean isEmptyStr(String str){
        return str == null || str.trim().isEmpty();
    }
}
