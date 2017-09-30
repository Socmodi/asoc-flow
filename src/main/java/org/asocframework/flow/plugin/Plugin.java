package org.asocframework.flow.plugin;

/**
 * @author jiqing
 * @version $Id: Plugin，v 1.0 2017/9/27 19:14 jiqing Exp $
 * @desc
 */
public interface Plugin {

    PluginType getType();

    void init();

}
