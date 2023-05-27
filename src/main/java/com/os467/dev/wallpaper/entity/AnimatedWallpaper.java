package com.os467.dev.wallpaper.entity;

/**
 * 动态壁纸类
 */
public class AnimatedWallpaper implements Wallpaper {

    private String filePath;

    private Boolean audio;

    public AnimatedWallpaper(String filePath, Boolean audio) {
        this.filePath = filePath;
        this.audio = audio;
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
