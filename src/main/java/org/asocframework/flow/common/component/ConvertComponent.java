package org.asocframework.flow.common.component;

import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.RecoverContext;

/**
 * @author jiqing
 * @version $Id: ConvertComponent，v 1.0 2017/10/24 13:44 jiqing Exp $
 * @desc
 */
public class ConvertComponent {

    private ConvertComponent() {
    }

    public static EventContext recoverContextToEventContext(RecoverContext recoverContext) {
        if (recoverContext == null) {
            return null;
        }
        EventContext eventContext = new EventContext();
        eventContext.setEvent(recoverContext.getEvent());
        eventContext.setParam(recoverContext.getParam());
        eventContext.setResult(recoverContext.getResult());
        eventContext.setSuccess(recoverContext.isSuccess());
        eventContext.setErrorMsg(recoverContext.getErrorMsg());
        eventContext.setBreaked(recoverContext.isBreaked());
        eventContext.setAttachments(recoverContext.getAttachments());
        eventContext.setRetryCount(recoverContext.getRetryCount());
        return eventContext;
    }


}
