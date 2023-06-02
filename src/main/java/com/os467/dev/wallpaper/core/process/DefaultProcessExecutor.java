package com.os467.dev.wallpaper.core.process;

/**
 * 默认壁纸进程执行者
 */
public class DefaultProcessExecutor implements ProcessExecutor {

    /**
     * 执行壁纸程序
     * @param wallpaperProcessBuilder 壁纸进程对象
     * @return 执行的壁纸进程
     */
    @Override
    public Process execute(WallpaperProcessBuilder wallpaperProcessBuilder) {
        return wallpaperProcessBuilder.run();
    }
}
