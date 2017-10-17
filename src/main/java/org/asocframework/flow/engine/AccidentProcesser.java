package org.asocframework.flow.engine;

import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventInvoker;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author jiqing
 * @version $Id: AccidentProcesser，v 1.0 2017/9/27 19:00 jiqing Exp $
 * @desc
 */
public class AccidentProcesser {

    private Boolean accidentMirror;

    private DataSource dataSource;

    public AccidentProcesser(Boolean accidentMirror, DataSource dataSource) {
        this.accidentMirror = accidentMirror;
        this.dataSource = dataSource;
    }

    public boolean mirror(EventContext context, List<EventInvoker> survivors){
        //事故日志记录
        //事故入库
        return true;
    }

}
