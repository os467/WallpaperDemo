package com.os467.dev.wallpaper.core.process;

import com.os467.dev.wallpaper.entity.Wallpaper;

/**
 * 媒体进程
 */
public interface WallpaperProcess {

    /**
     * 注册壁纸数据，需要提供一个壁纸源对象
     */
    WallpaperProcess register(Wallpaper wallpaper);

    /**
     * 进程运行方法
     * @return 执行壁纸程序，返回壁纸进程对象
     */
    Process run();

}
