package com.os467.dev.wallpaper;

import com.os467.dev.wallpaper.config.EngineConfig;
import com.os467.dev.wallpaper.core.DefaultWallpaperHandler;
import com.os467.dev.wallpaper.core.DefaultWallpaperVisualizer;
import com.os467.dev.wallpaper.core.WallpaperHandler;
import com.os467.dev.wallpaper.core.process.DefaultProcessExecutor;
import com.os467.dev.wallpaper.core.process.MediaWallpaperProcessBuilder;
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
        //创建默认处理器
        this.wallpaperHandler = new DefaultWallpaperHandler(
                //默认可视化器
                new DefaultWallpaperVisualizer(
                        //默认执行器
                        new DefaultProcessExecutor(),
                        //默认壁纸进程
                        new MediaWallpaperProcessBuilder()
                )
        );
        this.engineConfig = engineConfig;
    }

    public void init(){
        //执行器处理
        wallpaperHandler.handle(
                //动态壁纸
                new AnimatedWallpaper(engineConfig)
        );
    }
}
