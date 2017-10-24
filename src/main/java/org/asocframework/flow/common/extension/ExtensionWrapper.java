package org.asocframework.flow.common.extension;

import org.asocframework.flow.engine.EngineContext;
import org.asocframework.flow.engine.EngineInvoker;
import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.filter.Filter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author jiqing
 * @version $Id: ExtensionWapper，v 1.0 2017/10/18 15:33 jiqing Exp $
 * @desc
 */
public class ExtensionWrapper {

    public EngineInvoker buildInvokerChain(EngineInvoker last){

        List<Filter> filters = EngineContext.getFilterList();
        for(final Filter filter:filters){
            final EngineInvoker next = last;
            last = new EngineInvoker() {
                public EventContext invoke(EventContext eventContext) {
                    return filter.invoke(next,eventContext);
                }
            };
        }
        return last;
        /*Iterator<Map.Entry<String,Filter>> iterator = EngineContext.getFILTERS().entrySet().iterator();
        while (iterator.hasNext()){
            final Filter filter = iterator.next().getValue();
            final EngineInvoker next = last;
            last = new EngineInvoker() {
                public EventContext invoke(EventContext eventContext) {
                    return filter.invoke(next,eventContext);
                }
            };
        }
        return last;*/
    }

}
