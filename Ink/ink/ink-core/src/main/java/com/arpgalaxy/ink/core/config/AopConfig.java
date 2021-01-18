package com.arpgalaxy.ink.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author ArpGalaxy
 * @version 1.0
 * @description
 * @date 2021/1/18 9:18
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.arpgalaxy.ink.core.aop")
public class AopConfig {
}
