package com.os467.dev.wallpaper.core;

import com.os467.dev.wallpaper.core.process.WallpaperProcessBuilder;
import com.os467.dev.wallpaper.core.process.ProcessExecutor;
import com.os467.dev.wallpaper.entity.Wallpaper;

import java.util.Scanner;


/**
 * 默认壁纸可视化器(媒体壁纸可视化器)
 */
public class DefaultWallpaperVisualizer implements WallpaperVisualizer{

    private ProcessExecutor processExecutor;

    private WallpaperProcessBuilder wallpaperProcessBuilder;

    //运行进程
    private Process runningProcess;

    public DefaultWallpaperVisualizer(ProcessExecutor processExecutor, WallpaperProcessBuilder wallpaperProcessBuilder) {
        this.processExecutor = processExecutor;
        this.wallpaperProcessBuilder = wallpaperProcessBuilder;
    }


    /**
     * 展示壁纸
     * 用当前类的壁纸进程执行器处理壁纸
     * @param wallpaper 需要被展示的壁纸对象
     */
    @Override
    public void display(Wallpaper wallpaper) {
        //注册进程信息
        wallpaperProcessBuilder.register(wallpaper);
        //执行壁纸进程
        runningProcess = processExecutor.execute(wallpaperProcessBuilder);
        //todo 控制进程,被阻塞
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        runningProcess.destroy();
    }


}
