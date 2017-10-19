package org.asocframework.flow.engine;


import org.asocframework.flow.filter.Filter;
import org.asocframework.flow.plugin.Plugin;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiqing
 * @version $Id: EngineContext，v 1.0 2017/9/27 14:46 jiqing Exp $
 * @desc
 */
public class EngineContext {

    private static final Map<String, Plugin> PLUGINS = new HashMap();

    private static final Map<String,Filter> FILTERS = new HashMap();

    protected static <T extends Plugin> boolean registerPlugin(String name,T plugin){
        PLUGINS.put(name,plugin);
        return true;
    }


    protected static <T extends Filter> boolean registerFilter(String name,T filter){
        FILTERS.put(name,filter);
        return true;
    }

    public static Plugin getPlugin(String pluginName){
        return PLUGINS.get(pluginName);
    }

    public static Filter getFilter(String filterName){
        return FILTERS.get(filterName);
    }

    public static Map<String, Plugin> getPLUGINS() {
        return PLUGINS;
    }

    public static Map<String, Filter> getFILTERS() {
        return FILTERS;
    }
}
