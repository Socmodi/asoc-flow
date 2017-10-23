package org.asocframework.flow.test;

import org.asocframework.flow.event.EventContext;
import org.springframework.stereotype.Component;

/**
 * @author jiqing
 * @version $Id: DataPart，v 1.0 2017/10/23 09:31 jiqing Exp $
 * @desc
 */
@Component
public class DataPart {

    public EventContext pay(EventContext context){
        context.setSuccess(true);
        System.out.println("dataPart:"+context.getParam());
        throw  new RuntimeException();
        //return context;
    }

}
