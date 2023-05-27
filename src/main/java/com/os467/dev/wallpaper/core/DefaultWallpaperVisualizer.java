package com.os467.dev.wallpaper.core;

import com.os467.dev.wallpaper.core.process.WallpaperProcess;
import com.os467.dev.wallpaper.core.process.ProcessExecutor;
import com.os467.dev.wallpaper.entity.Wallpaper;
/**
 * 默认壁纸可视化器(媒体壁纸可视化器)
 */
public class DefaultWallpaperVisualizer implements WallpaperVisualizer{

    private ProcessExecutor processExecutor;

    private WallpaperProcess wallpaperProcess;

    public DefaultWallpaperVisualizer(ProcessExecutor processExecutor, WallpaperProcess wallpaperProcess) {
        this.processExecutor = processExecutor;
        this.wallpaperProcess = wallpaperProcess;
    }

    /**
     * 展示壁纸
     * 用当前类的壁纸进程执行器处理壁纸
     * @param wallpaper 需要被展示的壁纸对象
     */
    @Override
    public void display(Wallpaper wallpaper) {
        wallpaperProcess.register(wallpaper);
        processExecutor.execute(wallpaperProcess);
    }
}
