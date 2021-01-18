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

        Matcher matcher= Pattern.compile("_([a-z])").matcher(str);

        if (!matcher.find()) return str;
        StringBuffer stringBuffer = null;
       do {
            stringBuffer = new StringBuffer();
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
            matcher.appendTail(stringBuffer);
            matcher= Pattern.compile("_([a-z])").matcher(stringBuffer);
        } while (matcher.find());

        return stringBuffer.toString().trim();
    }

    public static String[] toCamelCase(String[] str){
        for (int i = 0;i<str.length;i++) {
            String s = toCamelCase(str[i]);
            str[i]=s;
        }
        return str;
    }
    public static String toUnderlineCase(String str){

        Matcher matcher= Pattern.compile("([A-Z])").matcher(str);
        if (!matcher.find()) return str;
        StringBuffer stringBuffer=null;
        while (matcher.find()){
            stringBuffer = new StringBuffer();
            matcher.appendReplacement(stringBuffer, "_" + matcher.group(0).toLowerCase());
            matcher.appendTail(stringBuffer);
            matcher= Pattern.compile("([A-Z])").matcher(stringBuffer);

        }
        return stringBuffer.toString().trim();

    }
}
