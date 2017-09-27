package org.asocframework.flow.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiqing
 * @version $Id: EventHolder，v 1.0 2017/9/27 14:40 jiqing Exp $
 * @desc
 */
public class EventHolder {

    private String eventName;

    private List<EventInvoker> invokers = new ArrayList<EventInvoker>();

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<EventInvoker> getInvokers() {
        return invokers;
    }

    public void setInvokers(List<EventInvoker> invokers) {
        this.invokers = invokers;
    }

}
