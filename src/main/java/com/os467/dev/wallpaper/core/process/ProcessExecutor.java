package com.os467.dev.wallpaper.core.process;

/**
 * 进程执行者
 * 负责壁纸进程的执行
 */
public interface ProcessExecutor {
    /**
     * 进程具体执行方法，生成进程并返回
     * @return 返回被执行的进程
     * @param wallpaperProcess 壁纸进程
     */
    Process execute(WallpaperProcess wallpaperProcess);
}
