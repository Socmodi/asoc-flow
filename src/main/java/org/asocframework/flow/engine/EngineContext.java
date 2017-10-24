package org.asocframework.flow.engine;


import org.asocframework.flow.common.extension.Activate;
import org.asocframework.flow.filter.Filter;
import org.asocframework.flow.plugin.Plugin;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jiqing
 * @version $Id: EngineContext，v 1.0 2017/9/27 14:46 jiqing Exp $
 * @desc
 */
public class EngineContext {

    private static final Map<String, Plugin> PLUGINS = new HashMap();

    private static final Map<String,Filter> FILTERS = new HashMap();

    private static final List<Filter> FILTER_LIST = new LinkedList<Filter>();

    private static final FilterComparator FILTER_COMPARATOR = new FilterComparator();

    protected static <T extends Plugin> boolean registerPlugin(String name,T plugin){
        PLUGINS.put(name,plugin);
        return true;
    }


    protected static <T extends Filter> boolean registerFilter(String name,T filter){
        if(FILTERS.containsKey(name)){
            return false;
        }
        FILTERS.put(name,filter);
        addFilter(filter);
        return true;
    }

    private static <T extends Filter> void addFilter(T filter){
        FILTER_LIST.add(filter);
        Collections.sort(FILTER_LIST,FILTER_COMPARATOR);
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

    public static List<Filter> getFilterList() {
        return FILTER_LIST;
    }

    static class FilterComparator implements Comparator<Filter> {

        public int compare(Filter before, Filter after) {
            Activate beforeActivate = before.getClass().getAnnotation(Activate.class);
            Activate afterActivate = after.getClass().getAnnotation(Activate.class);
            return beforeActivate.order()>=afterActivate.order()? 1 : -1;
        }
    }
}
