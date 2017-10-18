package org.asocframework.flow.plugin;

import org.asocframework.flow.common.constants.PluginConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiqing
 * @version $Id: Plugins，v 1.0 2017/9/27 19:47 jiqing Exp $
 * @desc
 */
public class Plugins {

    private static final Map<String,Class<? extends Plugin>> PLUGINS_CLASS = new HashMap();

    static {
        PLUGINS_CLASS.put(PluginConstants.ACCIDENT_PLUGIN,AccidentPlugin.class);
        PLUGINS_CLASS.put(PluginConstants.MONITOR_PLUGIN,MonitorPlugin.class);
    }

    public static Class<? extends Plugin> getPluginClass(String plugin){
        return PLUGINS_CLASS.get(plugin);
    }

}
