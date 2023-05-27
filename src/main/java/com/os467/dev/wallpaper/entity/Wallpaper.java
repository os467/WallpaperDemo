package com.os467.dev.wallpaper.entity;

/**
 * 壁纸接口
 */
public interface Wallpaper {

    /**
     * 获取到当前壁纸的唯一识别名
     * @return 壁纸识别名
     */
    String id();

    /**
     * 壁纸来源源路径
     * @return 文件路径
     */
    String path();

}
