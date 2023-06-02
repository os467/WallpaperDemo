package com.os467;

import java.util.HashMap;
import java.util.Map;

public class YamlConfigEvent {

    //父配置项
    private YamlConfigEvent father;

    //yaml配置项层级
    private Integer level;

    //yaml配置名
    private String name;

    //yaml配置值,若不存在值则为null
    private String value;

    //若存在子项配置则启用
    private Map<String,YamlConfigEvent> children;

    public YamlConfigEvent(YamlConfigEvent father, Integer level, String name, String value, Map<String, YamlConfigEvent> children) {
        this.father = father;
        this.level = level;
        this.name = name;
        this.value = value;
        this.children = children;
    }

    public YamlConfigEvent getFather() {
        return father;
    }

    public void setFather(YamlConfigEvent father) {
        this.father = father;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, YamlConfigEvent> getChildren() {
        return children;
    }

    public void setChildren(Map<String, YamlConfigEvent> children) {
        this.children = children;
    }

    public void addChildEvent(YamlConfigEvent child) {
        if (children == null){
            children = new HashMap<>();
        }
        children.put(child.name, child);
    }

    public void addValue(String value) {
        if (this.value == null){
            this.value = value;
        }
    }

    /**
     * 获取值
     * @param eventName
     * @return
     */
    public String getChildEventValue(String eventName) {
        YamlConfigEvent yamlConfigEvent = children.get(eventName);
        if (yamlConfigEvent == null){
            throw new YamlConfigEventNotFoundException("没有此配置: "+ getPath() + eventName);
        }
        String value = yamlConfigEvent.getValue();
        return value;
    }

    private String getPath() {
        if (father == null){
            return name + ":";
        }
        return father.getPath() + name + ":";
    }
}
