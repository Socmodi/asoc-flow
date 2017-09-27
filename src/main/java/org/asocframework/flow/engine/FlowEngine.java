package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiqing
 * @version $Id: FlowEngine，v 1.0 2017/9/27 14:42 jiqing Exp $
 * @desc
 */
public class FlowEngine {

    private Map<String,EventHolder> holders = new HashMap();

    public FlowEngine(Map<String, EventHolder> holders) {
        this.holders = holders;
        EngineHandler.eventZone = holders;
    }

    protected Map<String, EventHolder> getHolders() {
        return holders;
    }



}
