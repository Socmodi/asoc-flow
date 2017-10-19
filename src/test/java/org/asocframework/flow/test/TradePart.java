package org.asocframework.flow.test;

import org.asocframework.flow.event.EventContext;
import org.springframework.stereotype.Component;

/**
 * @author jiqing
 * @version $Id: TradePart，v 1.0 2017/10/17 09:24 jiqing Exp $
 * @desc
 */
@Component
public class TradePart {

    public EventContext pay(EventContext context){
        context.setSuccess(true);
        System.out.println(context.getParam());
        return context;
    }

}
