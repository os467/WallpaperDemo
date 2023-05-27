package com.os467.dev.wallpaper;

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
    public void init(){
        //启动前通知
        //执行器

        wallpaperHandler = new DefaultWallpaperHandler(new DefaultWallpaperVisualizer(
                new DefaultProcessExecutor(),
                new DefaultWallpaperProcess())
        );
        wallpaperHandler.handle(new AnimatedWallpaper("C:\\Users\\tly20\\Desktop\\Shelter.mp4"));
        //启动后通知
    }
}
