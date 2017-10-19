package org.asocframework.flow.engine;

import org.asocframework.flow.common.constants.FlowEngineConstants;
import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventInvoker;
import org.asocframework.flow.plugin.AccidentPlugin;

import java.util.List;

/**
 * @author jiqing
 * @version $Id: EngineProcesser，v 1.0 2017/9/27 16:09 jiqing Exp $
 * @desc
 */
public class EngineProcesser {

    private EventContext context;

    private EngineInvoker engineInvoker;

    public EngineProcesser() {
    }

    public EngineProcesser(EventContext context, EngineInvoker engineInvoker) {
        this.context = context;
        this.engineInvoker = engineInvoker;
    }

    public EventContext getContext() {
        return context;
    }

    public void setContext(EventContext context) {
        this.context = context;
    }

    public EngineInvoker getEngineInvoker() {
        return engineInvoker;
    }

    public void setEngineInvoker(EngineInvoker engineInvoker) {
        this.engineInvoker = engineInvoker;
    }

    public EventContext process(){
        return engineInvoker.invoke(context);
    }




}
