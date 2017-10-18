package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventHolder;
import org.asocframework.flow.common.exception.EngineRuntimeException;
import org.asocframework.flow.plugin.Plugin;
import org.asocframework.flow.plugin.Plugins;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jiqing
 * @version $Id: FlowEngine，v 1.0 2017/9/27 14:42 jiqing Exp $
 * @desc
 */
public class FlowEngine{

    private Map<String,EventHolder> holders = new HashMap();

    private Map<String,Boolean> plugins = new HashMap();

    private PluginHandler pluginHandler;

    private DataSource dataSource;

    @PostConstruct
    private void plugins(){
        Iterator<Map.Entry<String,Boolean>> iterator = plugins.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Boolean> entry = iterator.next();
            if(entry.getValue()){
                Class<? extends Plugin> pluginClass = Plugins.getPluginClass(entry.getKey());
                EngineContext.registerPlugin(entry.getKey(),createPlugin(pluginClass));
            }
        }
    }

    private Plugin createPlugin(Class<? extends Plugin> clazz){
        if(clazz==null){
            throw new EngineRuntimeException();
        }
        try {
            Plugin plugin = clazz.newInstance();
            plugin.init();
            return plugin;
        } catch (Exception e) {
            throw new EngineRuntimeException();
        }
    }

    public FlowEngine(Map<String, EventHolder> holders) {
        this.holders = holders;
        EngineHandler.eventZone = holders;
    }

    protected Map<String, EventHolder> getHolders() {
        return holders;
    }

    public Map<String, Boolean> getPlugins() {
        return plugins;
    }

    public void setPlugins(Map<String, Boolean> plugins) {
        this.plugins = plugins;
    }

    public PluginHandler getPluginHandler() {
        return pluginHandler;
    }

    public void setPluginHandler(PluginHandler pluginHandler) {
        this.pluginHandler = pluginHandler;
    }

    public static void main(String args[]){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader classLoader = contextClassLoader;
            Thread.currentThread().setContextClassLoader(classLoader);
            String fileName = classLoader.getResource("META-INF/flow").getFile();
            System.out.println(fileName);
            File file = new File(fileName);
            System.out.println(file.isFile());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
