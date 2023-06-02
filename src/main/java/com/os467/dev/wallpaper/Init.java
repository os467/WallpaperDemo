package com.os467.dev.wallpaper;

import com.os467.dev.wallpaper.config.YamlReader;


/*public class Init {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        new WallpaperEngine(
                //壁纸文件视频路径
                new DefaultEngineConfig().file(path+"\\resources\\media\\sea.mp4")
                .audio(false)//是否开启声音
        ).init();
    }
}*/

/**
 * 启动壁纸引擎
 */
public class Init {
    public static void main(String[] args) {
        //todo 进行yaml配置读取测试
        YamlReader yamlReader = new YamlReader();
        /*String path = System.getProperty("user.dir");
        new WallpaperEngine(
                //壁纸文件视频路径
                new DefaultEngineConfig().file(path+"\\resources\\media\\sea.mp4")
                        .audio(false)//是否开启声音
        ).init();*/
    }
}