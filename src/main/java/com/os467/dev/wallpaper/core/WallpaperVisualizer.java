package com.os467.dev.wallpaper.core;

import com.os467.dev.wallpaper.entity.Wallpaper;

/**
 * 壁纸可视化接口，选择具体的可视化方案
 */
public interface WallpaperVisualizer {

    void display(Wallpaper wallpaper);
}
