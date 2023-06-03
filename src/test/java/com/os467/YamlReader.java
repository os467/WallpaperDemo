package com.os467;

import com.os467.dev.wallpaper.exception.ConfigInitException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class YamlReader implements EngineConfig {

    public static void main(String[] args) {
        YamlReader yamlReader = new YamlReader();
        yamlReader.init();
    }

    //yaml文件路径
    public static final String YAML_LOCATION = System.getProperty("user.dir") + "\\resources\\config\\wallpaper.yml";

    //缓冲读取器
    private BufferedReader bufferedReader;

    //字符拼接器
    private StringBuilder stringBuilder = new StringBuilder();

    //当前读入的行字符串
    private String line;

    //配置表
    private Map<String,YamlConfigEvent> rootMap = new HashMap<>();

    //当前处理的根配置项
    private YamlConfigEvent rootEvent;

    //配置注入器
    private YamlObjectFactory yamlObjectFactory;

    @Override
    public void init() {
        readYaml();
        yamlObjectFactory = new YamlObjectFactory();
        yamlObjectFactory.inject(rootMap);
    }

    private void readYaml() {
        File file = new File(YAML_LOCATION);
        try {
            String charset = null;
            try {
                charset = EncodeUtils.getEncode(YAML_LOCATION, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));
        } catch (FileNotFoundException e) {
            throw new ConfigInitException("配置文件不存在");
        } catch (IOException e) {
            throw new ConfigInitException("配置文件读取异常");
        }
        getConfigRoot();
    }

    private void getConfigRoot(){
        try {
            line = bufferedReader.readLine();
            while (line != null){
                //检查是否为注释
                if (ignore(line)){
                    continue;
                }
                //检查是否为根
                int level = checkLevel(line);
                if (level == 0){
                    String eventName = getEventName(line, level);
                    rootEvent = new YamlConfigEvent(null,0,eventName,null,null);
                    //读取配置内容
                    handleConfigContext(rootEvent);
                    rootMap.put(eventName,rootEvent);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("配置文件读取异常");
        }finally {
            rootEvent = null;
        }
    }


    /**
     * 处理配置上下文
     * @param fatherEvent
     */
    private void handleConfigContext(YamlConfigEvent fatherEvent) {
        try {
            YamlConfigEvent father = fatherEvent;
            Stack<YamlConfigEvent> stack = new Stack();
            stack.push(father);
            check: while ((line = bufferedReader.readLine()) != null){
                if (ignore(line)){
                    continue;
                }
                //获取该行的配置等级
                int level = checkLevel(line);
                father = stack.peek();
                while (level <= father.getLevel()){
                    //非子配置
                    //此项配置信息读取完毕,弹出
                    stack.pop();
                    if (stack.isEmpty()){
                        break check;
                    }
                    father = stack.peek();
                }
                //确定为子配置
                //获取子配置的值
                String value = getValue(line, level);
                //生成子配置
                String eventName = getEventName(line, level);
                YamlConfigEvent child = new YamlConfigEvent(father, level, eventName,value,null);
                father.addChildEvent(child);
                //检查子配置
                stack.push(child);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String catStr(String... strings){
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
        }
        String ret = stringBuilder.toString();
        stringBuilder.setLength(0);
        return ret;
    }

    private boolean ignore(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' '){
                continue;
            }else if (line.charAt(i) == '#'){
                return true;
            }else {
                return false;
            }
        }
        //无配置信息，忽略此行
        return true;
    }

    private String getValue(String line, int level) {
        if (line.charAt(level * 2) == '-'){
            //数组值
            return line.substring(level * 2);
        }
        int i = line.indexOf(":");
        if (line.length() - 1 == i){
            //该行无配置
            return null;
        }
        //返回配置值
        return line.substring(i+2);
    }

    private String getEventName(String line, int level) {
        String name = "";
        for (int i = level * 2; i < line.length(); i++) {
            if (line.charAt(i) == ':'){
                return name;
            }
            name = catStr(name, line.charAt(i) + "");
        }
        return null;
    }

    private int checkLevel(String line) {
        int sum = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' '){
                sum++;
            }else {
                break;
            }
        }
        return sum/2;
    }


}
