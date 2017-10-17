package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventContext;

/**
 * @author jiqing
 * @version $Id: EngineInvoker，v 1.0 2017/10/17 15:03 jiqing Exp $
 * @desc
 */
public interface EngineInvoker {

    EventContext invoke(EventContext eventContext);

}
