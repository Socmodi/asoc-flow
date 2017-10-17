package org.asocframework.flow.store;

import java.util.Date;

/**
 * @author jiqing
 * @version $Id: AccidentDO，v 1.0 2017/10/17 17:13 jiqing Exp $
 * @desc
 */
public class AccidentMirror {

    private String id;

    private String eventName;

    private String invokersInfo;

    private Integer status;

    private Date happenedTime;

    private Date processTime;

    private Date closeTime;

    private String processUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getInvokersInfo() {
        return invokersInfo;
    }

    public void setInvokersInfo(String invokersInfo) {
        this.invokersInfo = invokersInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getHappenedTime() {
        return happenedTime;
    }

    public void setHappenedTime(Date happenedTime) {
        this.happenedTime = happenedTime;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getProcessUser() {
        return processUser;
    }

    public void setProcessUser(String processUser) {
        this.processUser = processUser;
    }
}
