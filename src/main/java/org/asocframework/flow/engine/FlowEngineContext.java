package org.asocframework.flow.engine;

import org.asocframework.flow.constants.PluginConstants;
import org.asocframework.flow.context.EngineContext;
import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventHolder;
import org.asocframework.flow.exception.EngineRuntimeException;
import org.asocframework.flow.plugin.AccidentPlugin;
import org.asocframework.flow.plugin.Plugin;
import org.asocframework.flow.plugin.PluginHandler;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author jiqing
 * @version $Id: FlowEngineContext，v 1.0 2017/10/16 17:09 jiqing Exp $
 * @desc
 */
public class FlowEngineContext {

    private Map<String,EventHolder> holders = new HashMap();

    private Map<String,Boolean> plugins = new HashMap();

    private PluginHandler pluginHandler;

    private DataSource dataSource;

    private boolean accidentMirror;

    private boolean lazy;

    public FlowEngineContext() {

    }


    public FlowEngineContext(Map<String, EventHolder> holders) {
        this.holders = holders;
        EngineHandler.eventZone = holders;
    }


    public EventContext execute(String eventName,EventContext context){
        EventHolder eventHolder = holders.get(eventName);
        eventHolder.isActive();

        return context;
    }


    @PostConstruct
    private void init(){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        FileInputStream inputStream = null;
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Thread.currentThread().setContextClassLoader(classLoader);
            String fileName = classLoader.getResource(PluginConstants.PLUGINS_FILE).getFile();
            inputStream = new FileInputStream(fileName);
            Properties prop = new Properties();
            prop.load(inputStream);
            Set pluginSet = prop.entrySet();
            initPlugins(pluginSet);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initPlugins(Set pluginSet){
        if(pluginSet==null||pluginSet.isEmpty()){
            throw new EngineRuntimeException();
        }
        try {
            Iterator<Map.Entry<String,String>> iterator = pluginSet.iterator();
            while (iterator.hasNext()){
                Map.Entry<String,String> entry = iterator.next();
                EngineContext.registerPlugin(entry.getKey(),createPlugin(entry.getKey(),Class.forName(entry.getValue())));
            }
        } catch (Exception e) {
            throw new EngineRuntimeException();
        }
    }

    private Plugin createPlugin(String pluginName,Class pluginClass) throws IllegalAccessException, InstantiationException {
        Plugin plugin = (Plugin) pluginClass.newInstance();
        if(PluginConstants.ACCIDENT_PLUGIN.equals(pluginName)&&accidentMirror){
            AccidentPlugin accidentPlugin = (AccidentPlugin) plugin;
            accidentPlugin.setAccidentMirror(accidentMirror);
            accidentPlugin.setDataSource(dataSource);
        }
        plugin.init();
        return plugin;
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

    public void setHolders(Map<String, EventHolder> holders) {
        this.holders = holders;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isAccidentMirror() {
        return accidentMirror;
    }

    public void setAccidentMirror(boolean accidentMirror) {
        this.accidentMirror = accidentMirror;
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

}
