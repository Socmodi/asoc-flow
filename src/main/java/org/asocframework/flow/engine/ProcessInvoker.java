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
public class ProcessInvoker extends AbstractProcessInvoker implements EngineInvoker{


    public ProcessInvoker(List<EventInvoker> invokers, AccidentPlugin accidentPlugin) {
        super(invokers,accidentPlugin);
    }


    public void doExp(EventContext eventContext) {
        getAccidentPlugin().processAccident(eventContext);
    }


}
