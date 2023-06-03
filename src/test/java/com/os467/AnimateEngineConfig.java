package com.os467;

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

    @Override
    public String toString() {
        return "AnimateEngineConfig{" +
                "enable=" + enable +
                ", mediaFilePath='" + mediaFilePath + '\'' +
                ", audio=" + audio +
                '}';
    }
}
