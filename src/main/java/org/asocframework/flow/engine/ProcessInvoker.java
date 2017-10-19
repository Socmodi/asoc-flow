package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventInvoker;
import org.asocframework.flow.plugin.AccidentPlugin;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jiqing
 * @version $Id: ProcessInvoker，v 1.0 2017/10/17 15:31 jiqing Exp $
 * @desc
 */
public class ProcessInvoker implements EngineInvoker{

    private List<EventInvoker> invokers;

    private AccidentPlugin accidentPlugin;

    public ProcessInvoker(List<EventInvoker> invokers) {
        this.invokers = invokers;
    }

    public ProcessInvoker(List<EventInvoker> invokers, AccidentPlugin accidentPlugin) {
        this.invokers = invokers;
        this.accidentPlugin = accidentPlugin;
    }

    public EventContext invoke(EventContext eventContext) {
        EventContext context = eventContext;
        try{
            for(EventInvoker invoker:invokers){
                context = invoker.execute(context);
                if(context.isBreaked()){
                    break;
                }
            }
        }catch (RuntimeException e){
            context.setSuccess(false);
            //日志输出执行的错误信息
            //事故插件介入
            accidentPlugin.processAccident(context);
        }
        return context;
    }

}
