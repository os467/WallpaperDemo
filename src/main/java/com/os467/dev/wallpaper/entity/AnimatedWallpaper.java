package com.os467.dev.wallpaper.entity;

import com.os467.dev.wallpaper.config.AnimateEngineConfig;
import com.os467.dev.wallpaper.config.EngineConfig;

/**
 * 动态壁纸类
 */
public class AnimatedWallpaper implements Wallpaper {

    private String filePath;

    private Boolean audio;

    public AnimatedWallpaper(EngineConfig engineConfig) {
        //todo 暂时强转,之后换成配置控制
        AnimateEngineConfig animateEngineConfig = (AnimateEngineConfig) engineConfig;
        this.filePath = animateEngineConfig.getFile();
        this.audio = animateEngineConfig.getAudio();
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public String path() {
        return filePath;
    }

    public Boolean getAudio() {
        return audio;
    }
}
