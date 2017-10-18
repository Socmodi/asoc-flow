package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventInvoker;

import java.util.List;

/**
 * @author jiqing
 * @version $Id: EngineProcesser，v 1.0 2017/9/27 16:09 jiqing Exp $
 * @desc
 */
public class EngineProcesser {

    private EventContext context;

    private List<EventInvoker> invokers;

    public EngineProcesser() {

    }

    public EngineProcesser(EventContext context, List<EventInvoker> invokers) {
        this.context = context;
        this.invokers = invokers;
    }

    public EventContext getContext() {
        return context;
    }

    public void setContext(EventContext context) {
        this.context = context;
    }

    public List<EventInvoker> getInvokers() {
        return invokers;
    }

    public void setInvokers(List<EventInvoker> invokers) {
        this.invokers = invokers;
    }

    public EventContext process(){
        try {
            for(EventInvoker invoker:invokers){
                context = invoker.execute(context);
                //invoker.isAsync();
            }
        }catch (Exception e){
            //异常情况持久化当前上下文，以及处理节点当前情况，通过业务恢复工具，进行恢复
        }
        return context;
    }



}
