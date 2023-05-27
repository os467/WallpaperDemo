package com.os467.dev.wallpaper.core.process;

import com.os467.dev.jna.User32;
import com.os467.dev.wallpaper.EngineConstant;
import com.os467.dev.wallpaper.entity.Wallpaper;
import com.os467.dev.wallpaper.exception.WallpaperProcessException;
import com.os467.test.util.FfmpegUtils;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import java.awt.*;
import java.util.Objects;
import java.util.UUID;

/**
 * 默认壁纸进程
 */
public class DefaultWallpaperProcess implements WallpaperProcess {

    private Wallpaper wallpaper;

    private static User32 user32 = User32.user32;

    private int timeout = 0;

    private int windowWith;

    private int windowHeight;

    @Override
    public WallpaperProcess register(Wallpaper wallpaper) {
        if (Objects.isNull(wallpaper)){
            throw new NullPointerException();
        }
        this.wallpaper = wallpaper;
        return this;
    }

    /**
     * 利用pm窗口的特殊消息机制，隐藏第二个WorkW窗口，将壁纸程序窗口设置为pm子窗口
     * @return 返回运行的壁纸进程对象
     */
    @Override
    public Process run() {
        WinDef.HWND pm = updatePM();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowWith = screenSize.width;
        windowHeight = screenSize.height;
        //进程随机uuid号
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //创建媒体进程
        Process mediaProcess = createMediaProcess(uuid);

        WinDef.HWND paperProcess;
        do {
            paperProcess = user32.FindWindowA(null,uuid);
            timeout++;
        }while (paperProcess == null && timeout < EngineConstant.PROCESS_BUILD_TIMEOUT);
        if (timeout >= EngineConstant.PROCESS_BUILD_TIMEOUT){
            throw new WallpaperProcessException("壁纸进程创建超时");
        }

        //设置壁纸进程窗口为pm窗口的子窗口
        user32.SetParent(paperProcess, pm);

        //重绘窗口大小
        boolean repaint = user32.MoveWindow(paperProcess, 0, 0, 1920, 1080, true);
        if (!repaint){
            throw new WallpaperProcessException("窗口重绘失败");
        }

        return mediaProcess;
    }

    /**
     * 用ffmpeg创建进程
     * @param uuid 壁纸进程窗口名称
     */
    private Process createMediaProcess(String uuid) {
        return FfmpegUtils.playVideoAudio(
                wallpaper.path(),
                windowWith,
                windowHeight,
                EngineConstant.LOOP_CYCLE,
                uuid,
                false,
                true);
    }

    /**
     * 改变pm窗口状态
     */
    private WinDef.HWND updatePM() {
        WinDef.HWND pm = user32.FindWindowA(EngineConstant.WIN10_PM_WINDOW_CLASSNAME, EngineConstant.WIN10_PM_WINDOW_NAME);
        //使PM窗口裂变的消息
        user32.SendMessageTimeoutA(pm,
                EngineConstant.WIN10_PM_WALLPAPER_MSG,
                null,
                null,
                WinUser.SMTO_NORMAL,
                EngineConstant.WIN10_PM_WALLPAPER_DEFAULT_MESSAGE_TIME_OUT,
                null);

        //隐藏第二个WorkW窗口
        user32.EnumWindows((hwnd, param) -> {
            WinDef.HWND hDefView = user32.FindWindowExA(hwnd, null, "SHELLDLL_DefView", null);
            if (hDefView != null) {
                WinDef.HWND hide = user32.FindWindowExA(null, hwnd, "WorkerW", null);
                user32.ShowWindow(hide, WinUser.SW_HIDE);
                return false;
            }
            return true;
        }, null);
        return pm;
    }
}
