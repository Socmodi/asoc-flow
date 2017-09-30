package org.asocframework.flow.plugin;

/**
 * @author jiqing
 * @version $Id: PluginType，v 1.0 2017/9/29 14:08 jiqing Exp $
 * @desc
 */
public enum  PluginType {

    BEFORE_PLUGIN(1000,"BEFORE_PLUGIN"),
    AROUND_PLUGIN(2000,"AROUND_PLUGIN"),
    AFTER_PLUGIN(3000,"AFTER_PLUGIN");


    private int value ;
    private String name;

    PluginType(int value, String name){
        this.value = value;
        this.name = name;
    }

    public static PluginType valueOf(int value){
        for(PluginType rcode : PluginType.values()){
            if(rcode.value == value){
                return rcode;
            }
        }
        return null;
    }

    public static PluginType nameOf(String name) {
        for(PluginType config : PluginType.values()){
            if(config.name.equals(name)){
                return config;
            }
        }
        return null;
    }

    public  int getValue(){
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
