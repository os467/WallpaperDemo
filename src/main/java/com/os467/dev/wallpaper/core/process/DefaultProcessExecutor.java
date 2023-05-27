package com.os467.dev.wallpaper.core.process;


public class DefaultProcessExecutor implements ProcessExecutor {

    /**
     * 执行壁纸程序
     * @param wallpaperProcess 壁纸进程对象
     * @return 执行的壁纸进程
     */
    @Override
    public Process execute(WallpaperProcess wallpaperProcess) {
        return wallpaperProcess.run();
    }
}
