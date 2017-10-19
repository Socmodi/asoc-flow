package org.asocframework.flow.engine;

import org.asocframework.flow.common.constants.FlowEngineConstants;
import org.asocframework.flow.common.extension.ExtensionWrapper;
import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventHolder;
import org.asocframework.flow.common.exception.EngineRuntimeException;
import org.asocframework.flow.filter.Filter;
import org.asocframework.flow.plugin.AccidentPlugin;
import org.asocframework.flow.plugin.Plugin;
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
public class FlowEngineApplication {

    private Map<String,EventHolder> holders = new HashMap();

    private Map<String,Boolean> plugins = new HashMap();

    private DataSource dataSource;

    private boolean accidentMirror;

    private boolean lazy;

    private ExtensionWrapper wrapper;

    public FlowEngineApplication() {

    }

    public FlowEngineApplication(Map<String, EventHolder> holders) {
        this.holders = holders;
    }

    public EventContext execute(EventContext context){
        EventHolder eventHolder = holders.get(context.getEvent());
        if(eventHolder==null||eventHolder.isActive()){
            throw new EngineRuntimeException("未被定义的事件或者事件已经撤销");
        }
        ProcessInvoker processInvoker = new ProcessInvoker(eventHolder.getInvokers(),(AccidentPlugin) EngineContext.getPlugin(FlowEngineConstants.ACCIDENT_PLUGIN));
        EngineInvoker engineInvoker = this.wrapper.buildInvokerChain(processInvoker);
        return new EngineProcesser(context,engineInvoker).process();
    }

    @PostConstruct
    private void init(){
        this.wrapper = new ExtensionWrapper();
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Thread.currentThread().setContextClassLoader(classLoader);
            String pluginFile = classLoader.getResource(FlowEngineConstants.PLUGINS_FILE).getFile();
            String filterFile = classLoader.getResource(FlowEngineConstants.FILTER_FILE).getFile();
            initPlugins(getPropertySet(pluginFile));
            initFilters(getPropertySet(filterFile));
        }catch (Exception e){
            throw new EngineRuntimeException(e);
        }finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
        }
    }

    private Set getPropertySet(String fileName) throws IOException {
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(fileName);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties.entrySet();
        }catch (Exception e){
            throw new EngineRuntimeException(e);
        }finally {
            if(fileInputStream!=null){
                fileInputStream.close();
            }
        }
    }

    private void  initFilters(Set filterSet){
        if(filterSet==null||filterSet.isEmpty()){
            return;
        }
        try {
            Iterator<Map.Entry<String,String>> iterator = filterSet.iterator();
            while (iterator.hasNext()){
                Map.Entry<String,String> entry = iterator.next();
                EngineContext.registerFilter(entry.getKey(),createFilter(Class.forName(entry.getValue())));
            }
        } catch (Exception e) {
            throw new EngineRuntimeException();
        }

    }

    private Filter createFilter(Class filterClass) throws IllegalAccessException, InstantiationException {
        Filter filter = (Filter) filterClass.newInstance();
        return filter;
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
