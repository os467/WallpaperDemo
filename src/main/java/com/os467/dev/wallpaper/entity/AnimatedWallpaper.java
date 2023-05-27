package com.os467.dev.wallpaper.entity;

/**
 * 动态壁纸类
 */
public class AnimatedWallpaper implements Wallpaper {

    private String filePath;

    public AnimatedWallpaper(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public String path() {
        return filePath;
    }

}
