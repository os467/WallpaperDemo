package com.os467;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Yaml配置对象工厂
 */
public class YamlObjectFactory {

    //包路径
    private static final String PACKAGE = "com.os467";

    //字节码所在位置
    private static final String SCAN_PATH = System.getProperty("user.dir") + "\\target\\test-classes\\" + PACKAGE.replace(".","\\");

    private Map<String,YamlConfigEvent> rootMap;

    private YamlConfigEvent event;

    //工厂仓库
    private static Map<String,Object> wareHouse = new HashMap<>();

    public void inject(Map<String, YamlConfigEvent> rootMap) {
        this.rootMap = rootMap;
        inject();
        for (String s : wareHouse.keySet()) {
            Object o = wareHouse.get(s);
            System.out.println(o);
        }
    }


    private void inject() {
        scanClassType();
    }

    /**
     * 扫描类类型依赖，对应根配置
     */
    private void scanClassType() {
        File file = new File(SCAN_PATH);
        handleFile(file);
    }

    private void handleFile(File file) {
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                handleFile(files[i]);
            }
        }else {
            //不是一个文件夹
            String extendName = getExtendName(file.getName());
            if (extendName.equals(".class")){
                String className = PACKAGE + "." + file.getName().replace(".class", "");
                try {
                    Class<?> aClass = Class.forName(className);
                    YamlConfigValue configValue = aClass.getDeclaredAnnotation(YamlConfigValue.class);
                    if (configValue != null){
                        String rootEventName = configValue.value();
                        //获取到对应的根配置
                        event = rootMap.get(rootEventName);
                        //创建Yaml配置对象，注入依赖，存入仓库
                        Object object = createObject(aClass, event);
                        wareHouse.put(rootEventName,object);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private Object createObject(Class objClass, YamlConfigEvent fatherEvent) {
        Object obj = null;
        try {
            obj = objClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //属性字节码列表
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        Type genericType;
        for (int i = 0; i < declaredFields.length; i++) {
            YamlConfigValue fieldValue = declaredFields[i].getDeclaredAnnotation(YamlConfigValue.class);
            if (fieldValue != null){
                //注入依赖
                genericType = declaredFields[i].getGenericType();
                //获取属性全类名
                String fieldClassName = genericType.toString().replace("class ", "");

                //被注入的实例
                Object injectObj = null;
                //实例配置的值
                String childEventValue = fatherEvent.getChildEventValue(fieldValue.value());
                //基础类型创建
                injectObj = selectType(fieldClassName, childEventValue);

                if (injectObj == null){
                    //创建复杂类型，该类需要存在在
                    Class<?> eventClass = null;
                    try {
                        eventClass = Class.forName(fieldClassName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    YamlConfigEvent childEvent = fatherEvent.getChildEvent(fieldValue.value());
                    injectObj = createObject(eventClass, childEvent);
                }

                //打破封装
                declaredFields[i].setAccessible(true);
                //注入
                try {
                    declaredFields[i].set(obj,injectObj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    /**
     * 选择类
     * @param fieldClassName
     * @param childEventValue
     * @return
     */
    private Object selectType(String fieldClassName, String childEventValue) {
        Object injectObj = null;
        //常用数据类型
        switch (fieldClassName){
            case "java.lang.Boolean":
            case "boolean": {
                injectObj = new Boolean(childEventValue);
                break;
            }
            case "java.lang.Integer":
            case "int": {
                if (childEventValue == null || childEventValue.length() == 0){
                    injectObj = new Integer(0);
                }else {
                    injectObj = new Integer(childEventValue);
                }
                break;
            }
            case "java.lang.Double":
            case "double": {
                if (childEventValue == null || childEventValue.length() == 0){
                    injectObj = new Double(0);
                }else {
                    injectObj = new Double(childEventValue);
                }
                break;
            }
            case "java.lang.Long":
            case "long": {
                if (childEventValue == null || childEventValue.length() == 0){
                    injectObj = new Long(0);
                }else {
                    injectObj = new Long(childEventValue);
                }
                break;
            }
            case "java.lang.Byte":
            case "byte": {
                if (childEventValue == null || childEventValue.length() == 0){
                    injectObj = new Byte((byte) (0));
                }else {
                    injectObj = new Byte(childEventValue);
                }
                break;
            }
            case "java.lang.Short":
            case "short": {
                if (childEventValue == null || childEventValue.length() == 0){
                    injectObj = new Short((short) (0));
                }else {
                    injectObj = new Short(childEventValue);
                }
                break;
            }
            case "java.lang.Float":
            case "float": {
                if (childEventValue == null || childEventValue.length() == 0){
                    injectObj = new Float((float) (0));
                }else {
                    injectObj = new Float(childEventValue);
                }
                break;
            }
            case "java.lang.Character":
            case "char": {
                if (childEventValue == null || childEventValue.length() == 0){
                    injectObj = new Character((char) (0));
                }else {
                    injectObj = new Character(childEventValue.charAt(0));
                }
                break;
            }
            case "java.lang.String":
            {
                injectObj = childEventValue;
                break;
            }
        }
        return injectObj;
    }

    /**
     * 获取文件拓展名
     * @param fileName
     * @return
     */
    private String getExtendName(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }
}
