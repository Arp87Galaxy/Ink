package com.arpgalaxy.ink.common.utils.response;

/**
 * 3位状态码类
 * 第一位 模块码
 * 第二三位  状态码
 * 目前 模块码:
 *          1: common
 *          2: core
 *     状态码：
 *          00：正常
 *          04：错误
 */
public class InkStatus {
    public static final int COMMON_OK = 100;

    public static final int COMMON_ERROR = 104;

    public static final int CORE_OK = 100;

    public static final int CORE_ERROR = 104;

}
