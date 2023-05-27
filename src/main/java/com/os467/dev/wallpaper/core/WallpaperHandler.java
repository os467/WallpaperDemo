package com.os467.dev.wallpaper.core;

import com.os467.dev.wallpaper.entity.Wallpaper;

/**
 * 壁纸处理器
 */
public interface WallpaperHandler {
    /**
     * 处理提供的壁纸，装载到桌面
     * @param wallpaper 壁纸对象
     */
    void handle(Wallpaper wallpaper);
}
