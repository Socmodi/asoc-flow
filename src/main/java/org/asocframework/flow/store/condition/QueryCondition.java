package org.asocframework.flow.store.condition;

/**
 * @author jiqing
 * @version $Id: QueryCondition，v 1.0 2017/10/19 11:32 jiqing Exp $
 * @desc
 */
public class QueryCondition {

    public static final int defaultPageSize = 20;


    private Integer pageSize = defaultPageSize;


    private String event_name;

    private int status;

    private Integer startRow;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
