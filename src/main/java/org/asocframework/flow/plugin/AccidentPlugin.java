package org.asocframework.flow.plugin;

import org.asocframework.flow.event.EventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

/**
 * @author jiqing
 * @version $Id: AccidentPlugin，v 1.0 2017/9/27 19:17 jiqing Exp $
 * @desc
 */
public class AccidentPlugin extends AbstrctPlugin{

    private static final Logger logger = LoggerFactory.getLogger(AccidentPlugin.class);

    private boolean accidentMirror;

    private DataSource dataSource;



    public void init() {

    }

    public boolean processAccident(EventContext context){

        return true;

    }

    public boolean isAccidentMirror() {
        return accidentMirror;
    }

    public void setAccidentMirror(boolean accidentMirror) {
        this.accidentMirror = accidentMirror;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
