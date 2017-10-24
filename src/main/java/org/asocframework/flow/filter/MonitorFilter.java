package org.asocframework.flow.filter;

import org.asocframework.flow.common.extension.Activate;
import org.asocframework.flow.engine.EngineInvoker;
import org.asocframework.flow.event.EventContext;


/**
 * @author jiqing
 * @version $Id: MonitorFilter，v 1.0 2017/10/17 14:47 jiqing Exp $
 * @desc
 */
@Activate(order = 100)
public class MonitorFilter implements Filter{

    public EventContext invoke(EngineInvoker invoker, EventContext context) {
        return invoker.invoke(context);
    }

}
