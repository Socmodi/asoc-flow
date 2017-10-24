package org.asocframework.flow.filter;

import org.asocframework.flow.common.extension.Activate;
import org.asocframework.flow.engine.EngineInvoker;
import org.asocframework.flow.event.EventContext;


/**
 * @author jiqing
 * @version $Id: AccessLogFilter，v 1.0 2017/10/17 14:48 jiqing Exp $
 * @desc
 */
@Activate(order = 101)
public class AccessLogFilter implements Filter{

    public EventContext invoke(EngineInvoker invoker, EventContext context) {
        return invoker.invoke(context);
    }

}
