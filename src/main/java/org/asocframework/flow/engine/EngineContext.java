package org.asocframework.flow.engine;


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

    public static <T extends Plugin> boolean registerPlugin(String name,T plugin){
        PLUGINS.put(name,plugin);
        return true;
    }



}
