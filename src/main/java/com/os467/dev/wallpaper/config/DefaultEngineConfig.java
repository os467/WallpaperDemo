package com.os467.dev.wallpaper.config;

public class DefaultEngineConfig implements EngineConfig{

    private String filePath;

    private Boolean audio;

    @Override
    public EngineConfig file(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @Override
    public EngineConfig audio(Boolean audio){
        this.audio = audio;
        return this;
    }

    @Override
    public String getFile() {
        return this.filePath;
    }

    @Override
    public Boolean getAudio() {
        return this.audio;
    }
}
