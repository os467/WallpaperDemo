package com.os467;

import com.os467.dev.wallpaper.annotation.YamlConfigValue;
import com.os467.dev.wallpaper.config.EngineConfig;

@YamlConfigValue("wallpaper")
public class DefaultYamlConfig implements EngineConfig {

    @YamlConfigValue("run")
    private Boolean run;

    @YamlConfigValue("animate")
    private AnimateEngineConfig animateEngineConfig;

    //下面是测试
    @YamlConfigValue("boolean")
    private boolean x;

    @YamlConfigValue("int")
    private Integer anInt;

    @YamlConfigValue("byte")
    private byte aByte;

    @YamlConfigValue("char")
    private char aChar;

    @YamlConfigValue("short")
    private short aShort;

    @YamlConfigValue("long")
    private long aLong;

    @YamlConfigValue("double")
    private double aDouble;

    @YamlConfigValue("float")
    private float aFloat;

    @YamlConfigValue("test")
    private String test;

    @Override
    public void init() {

    }

    @Override
    public String toString() {
        return "DefaultYamlConfig{" +
                "run=" + run +
                ", animateEngineConfig=" + animateEngineConfig +
                ", x=" + x +
                ", anInt=" + anInt +
                ", aByte=" + aByte +
                ", aChar=" + aChar +
                ", aShort=" + aShort +
                ", aLong=" + aLong +
                ", aDouble=" + aDouble +
                ", aFloat=" + aFloat +
                ", test='" + test + '\'' +
                '}';
    }
}
