package org.asocframework.flow.plugin;

import org.asocframework.flow.event.EventContext;

import javax.sql.DataSource;

/**
 * @author jiqing
 * @version $Id: AccidentPlugin，v 1.0 2017/9/27 19:17 jiqing Exp $
 * @desc
 */
public class AccidentPlugin extends AbstrctPlugin{

    private boolean accidentMirror;

    private DataSource dataSource;

    @Override
    public PluginType getType() {
        return PluginType.AROUND_PLUGIN;
    }


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
