package org.asocframework.flow.plugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jiqing
 * @version $Id: PluginHandler，v 1.0 2017/9/29 11:06 jiqing Exp $
 * @desc
 */
public class PluginHandler {

    private Map<String,Plugin> plugins = new HashMap();

    private Map<String,Plugin> afterPlugins = new HashMap<String, Plugin>();

    private Map<String,Plugin> aroundPlugins = new HashMap<String, Plugin>();

    private Map<String,Plugin> beforePlugins = new HashMap<String, Plugin>();

    public PluginHandler(Map<String, Plugin> plugins) {
        this.plugins = plugins;
        Iterator<Map.Entry<String,Plugin>> iterator = plugins.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Plugin> entry = iterator.next();
            Plugin plugin = entry.getValue();
            switch (plugin.getType()){
                case AFTER_PLUGIN:
                    afterPlugins.put(entry.getKey(),plugin);
                    break;
                case AROUND_PLUGIN:
                    aroundPlugins.put(entry.getKey(),plugin);
                    break;
                case BEFORE_PLUGIN:
                    beforePlugins.put(entry.getKey(),plugin);
                    break;
                default:
                    break;
            }
        }
    }



}
