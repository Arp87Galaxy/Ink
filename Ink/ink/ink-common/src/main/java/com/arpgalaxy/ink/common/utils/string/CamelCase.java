package com.arpgalaxy.ink.common.utils.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author arpgalaxy
 * @date 2021/1/15
 * @email 13605560342@163.com
 * @description
 */
public class CamelCase {
    public static String toCamelCase(String str){
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher= Pattern.compile("_([a-z])").matcher(str);
        if (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString().trim();
        }
        return str;
    }

    public static String[] toCamelCase(String[] str){
        for (int i = 0;i<str.length;i++) {
            String s = toCamelCase(str[i]);
            str[i]=s;
        }
        return str;
    }
}
