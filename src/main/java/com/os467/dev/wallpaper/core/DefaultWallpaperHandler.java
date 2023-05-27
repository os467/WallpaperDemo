package com.os467.dev.wallpaper.core;

import com.os467.dev.wallpaper.entity.Wallpaper;

public class DefaultWallpaperHandler implements WallpaperHandler{

    private WallpaperVisualizer wallpaperVisualizer;

    public DefaultWallpaperHandler(WallpaperVisualizer wallpaperVisualizer) {
        this.wallpaperVisualizer = wallpaperVisualizer;
    }

    /**
     * 处理当前传入的壁纸
     * @param wallpaper 需要被处理器处理的壁纸对象
     */
    @Override
    public void handle(Wallpaper wallpaper) {
        wallpaperVisualizer.display(wallpaper);
    }
}
