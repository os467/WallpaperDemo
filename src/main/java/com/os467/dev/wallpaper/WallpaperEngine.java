package com.os467.dev.wallpaper;

import com.os467.dev.wallpaper.config.DefaultEngineConfig;
import com.os467.dev.wallpaper.config.EngineConfig;
import com.os467.dev.wallpaper.core.DefaultWallpaperHandler;
import com.os467.dev.wallpaper.core.DefaultWallpaperVisualizer;
import com.os467.dev.wallpaper.core.WallpaperHandler;
import com.os467.dev.wallpaper.core.process.DefaultProcessExecutor;
import com.os467.dev.wallpaper.core.process.DefaultWallpaperProcess;
import com.os467.dev.wallpaper.entity.AnimatedWallpaper;

/**
 * wallpaperEngine by os467
 */
public class WallpaperEngine {
    private WallpaperHandler wallpaperHandler;

    private EngineConfig engineConfig;

    public WallpaperEngine(WallpaperHandler wallpaperHandler, EngineConfig engineConfig) {
        this.wallpaperHandler = wallpaperHandler;
        this.engineConfig = engineConfig;
    }

    public WallpaperEngine(EngineConfig engineConfig) {
        this.wallpaperHandler = new DefaultWallpaperHandler(
                new DefaultWallpaperVisualizer(
                        new DefaultProcessExecutor(),
                        new DefaultWallpaperProcess()
                )
        );
        this.engineConfig = engineConfig;
    }

    public void init(){
        //启动前通知
        //执行器
        wallpaperHandler.handle(new AnimatedWallpaper(engineConfig.getFile(),engineConfig.getAudio()));
        //启动后通知
    }
}
