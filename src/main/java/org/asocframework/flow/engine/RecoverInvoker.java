package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventInvoker;
import org.asocframework.flow.plugin.AccidentPlugin;

import java.util.List;

/**
 * @author jiqing
 * @version $Id: RecoverInvoker，v 1.0 2017/10/24 16:49 jiqing Exp $
 * @desc
 */
public class RecoverInvoker extends AbstractProcessInvoker implements EngineInvoker{

    public RecoverInvoker(List<EventInvoker> invokers, AccidentPlugin accidentPlugin) {
        super(invokers,accidentPlugin);
    }
    public void doExp(EventContext eventContext) {
        getAccidentPlugin().processAccident(eventContext,false);
    }

}
