package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventInvoker;
import org.asocframework.flow.plugin.AccidentPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private static final  EventInvokerComparator EVENT_INVOKER_COMPARATOR = new EventInvokerComparator();

    public ProcessInvoker(List<EventInvoker> invokers) {
        this.invokers = invokers;
    }

    public ProcessInvoker(List<EventInvoker> invokers, AccidentPlugin accidentPlugin) {
        this.invokers = new ArrayList<EventInvoker>(invokers);
        Collections.sort(this.invokers, EVENT_INVOKER_COMPARATOR);
        this.accidentPlugin = accidentPlugin;
    }

    public EventContext invoke(EventContext eventContext) {
        EventContext context = eventContext;
        context.setInvokers(invokers);
        EventInvoker invoker ;
        try{
            for(int index=0;index<invokers.size();index++){
                invoker = invokers.get(index);
                context = invoker.execute(context);
                invokers.remove(index);
                if(context.isBreaked()){
                    break;
                }
            }
            context.setSuccess(true);
        }catch (RuntimeException e){
            context.setSuccess(false);
            //日志输出执行的错误信息
            //事故插件介入
            accidentPlugin.processAccident(context);
        }
        return context;
    }


    static class EventInvokerComparator implements Comparator<EventInvoker> {

        public int compare(EventInvoker before, EventInvoker after) {
            return before.getOrder()>=after.getOrder()? 1 : -1;
        }
    }

}
