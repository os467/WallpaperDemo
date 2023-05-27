package com.os467.dev.wallpaper;

import com.os467.dev.wallpaper.config.DefaultEngineConfig;

public class Init {
    public static void main(String[] args) {

        new WallpaperEngine(
                //壁纸文件视频路径
                new DefaultEngineConfig().file("C:\\Users\\tly20\\Desktop\\Shelter.mp4")
                .audio(true)//是否开启声音
        ).init();
    }
}
