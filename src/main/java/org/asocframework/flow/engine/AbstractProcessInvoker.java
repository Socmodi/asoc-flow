package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventInvoker;
import org.asocframework.flow.plugin.AccidentPlugin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author jiqing
 * @version $Id: AbstractProcessInvoker，v 1.0 2017/10/24 15:43 jiqing Exp $
 * @desc
 */
public abstract class AbstractProcessInvoker implements EngineInvoker{

    private static final ProcessInvoker.EventInvokerComparator EVENT_INVOKER_COMPARATOR = new ProcessInvoker.EventInvokerComparator();

    private List<EventInvoker> invokers;

    private AccidentPlugin accidentPlugin;

    public AbstractProcessInvoker(List<EventInvoker> invokers) {
        this.invokers = invokers;
        Collections.sort(this.invokers, EVENT_INVOKER_COMPARATOR);
    }

    public AbstractProcessInvoker(List<EventInvoker> invokers, AccidentPlugin accidentPlugin) {
        this.invokers = invokers;
        this.accidentPlugin = accidentPlugin;
        Collections.sort(this.invokers, EVENT_INVOKER_COMPARATOR);
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
            doExp(context);
        }
        return context;
    }

    public abstract void doExp(EventContext eventContext);

    static class EventInvokerComparator implements Comparator<EventInvoker> {
        public int compare(EventInvoker before, EventInvoker after) {
            return before.getOrder()>=after.getOrder()? 1 : -1;
        }
    }

    public List<EventInvoker> getInvokers() {
        return invokers;
    }

    public void setInvokers(List<EventInvoker> invokers) {
        this.invokers = invokers;
    }

    public AccidentPlugin getAccidentPlugin() {
        return accidentPlugin;
    }

    public void setAccidentPlugin(AccidentPlugin accidentPlugin) {
        this.accidentPlugin = accidentPlugin;
    }
}
