package org.asocframework.flow.plugin;

import javax.sql.DataSource;

/**
 * @author jiqing
 * @version $Id: AccidentPlugin，v 1.0 2017/9/27 19:17 jiqing Exp $
 * @desc
 */
public class AccidentPlugin extends AbstrctPlugin{

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public PluginType getType() {
        return PluginType.AROUND_PLUGIN;
    }


    public void init() {

    }

}
