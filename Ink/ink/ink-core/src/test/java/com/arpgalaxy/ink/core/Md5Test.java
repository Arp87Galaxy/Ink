package com.arpgalaxy.ink.core;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

/**
 * @author arpgalaxy
 * @date 2020/9/20
 * @email 13605560342@163.com
 * @description
 */

public class Md5Test {

    @Test
    public void md5(){
        String s = new Sha256Hash("admin", "YzcmCZNvbXocrsz9dm8e").toHex();
        System.out.println(s);
    }
}
