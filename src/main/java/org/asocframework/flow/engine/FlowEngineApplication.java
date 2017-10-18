package org.asocframework.flow.engine;

import org.asocframework.flow.common.constants.FlowEngineConstants;
import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventHolder;
import org.asocframework.flow.event.EventInvoker;
import org.asocframework.flow.common.exception.EngineRuntimeException;
import org.asocframework.flow.plugin.AccidentPlugin;
import org.asocframework.flow.plugin.Plugin;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
public class FlowEngineApplication {

    private Map<String,EventHolder> holders = new HashMap();

    private Map<String,Boolean> plugins = new HashMap();

    private DataSource dataSource;

    private boolean accidentMirror;

    private boolean lazy;

    public FlowEngineApplication() {

    }

    public FlowEngineApplication(Map<String, EventHolder> holders) {
        this.holders = holders;
        EngineHandler.eventZone = holders;
    }

    public EventContext execute(EventContext context){
        EventHolder eventHolder = holders.get(context.getEvent());
        if(eventHolder==null||eventHolder.isActive()){
            throw new EngineRuntimeException("未被定义的事件或者事件已经撤销");
        }
        return new EngineProcesser(context,new ArrayList<EventInvoker>(eventHolder.getInvokers())).process();
    }

    @PostConstruct
    private void init(){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        FileInputStream inputStream = null;
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Thread.currentThread().setContextClassLoader(classLoader);
            String fileName = classLoader.getResource(FlowEngineConstants.PLUGINS_FILE).getFile();
            inputStream = new FileInputStream(fileName);
            Properties prop = new Properties();
            prop.load(inputStream);
            Set pluginSet = prop.entrySet();
            initPlugins(pluginSet);
        }catch (Exception e){
            throw new EngineRuntimeException(e);
        }finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new EngineRuntimeException(e);
            }
        }
    }

    private void  initFilter(){

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
        if(FlowEngineConstants.ACCIDENT_PLUGIN.equals(pluginName)&&accidentMirror){
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
