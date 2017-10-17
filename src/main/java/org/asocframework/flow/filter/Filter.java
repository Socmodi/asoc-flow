package org.asocframework.flow.filter;

import org.asocframework.flow.engine.EngineInvoker;
import org.asocframework.flow.event.EventContext;


/**
 * @author jiqing
 * @version $Id: Filter，v 1.0 2017/9/29 15:05 jiqing Exp $
 * @desc
 */
public interface Filter {

    EventContext invoke(EngineInvoker invoker, EventContext context);

}
