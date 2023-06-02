package com.os467;

import com.os467.dev.wallpaper.annotation.YamlConfigValue;
import com.os467.dev.wallpaper.config.EngineConfig;

public class AnimateEngineConfig implements EngineConfig {

    @YamlConfigValue("enable")
    private Boolean enable;

    @YamlConfigValue("mediaFilePath")
    private String mediaFilePath;

    @YamlConfigValue("audio")
    private Boolean audio;

    public AnimateEngineConfig() {
    }

    public AnimateEngineConfig file(String filePath) {
        this.mediaFilePath = filePath;
        return this;
    }

    public AnimateEngineConfig audio(Boolean audio){
        this.audio = audio;
        return this;
    }

    public String getFile() {
        return this.mediaFilePath;
    }

    public Boolean getAudio() {
        return this.audio;
    }

    @Override
    public void init() {

    }
}
