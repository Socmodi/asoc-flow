package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventHolder;
import org.asocframework.flow.common.exception.EngineRuntimeException;

import java.util.Map;

/**
 * @author jiqing
 * @version $Id: EngineHandler，v 1.0 2017/9/27 15:51 jiqing Exp $
 * @desc
 */
public class EngineHandler {

    private static FlowEngine engine;

    protected static Map<String,EventHolder> eventZone;

    private EngineHandler() {

    }

    public static EventContext execute(String eventName, EventContext context){
        EventHolder holder = engine.getHolders().get(eventName);
        if(holder==null){
            throw new EngineRuntimeException("非法事件");
        }
        return context;
    }



}
