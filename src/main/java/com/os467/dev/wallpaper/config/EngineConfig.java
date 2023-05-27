package com.os467.dev.wallpaper.config;

/**
 * 引擎配置类
 */
public interface EngineConfig {
    EngineConfig file(String filePath);
    EngineConfig audio(Boolean audio);

    String getFile();
    Boolean getAudio();
}
