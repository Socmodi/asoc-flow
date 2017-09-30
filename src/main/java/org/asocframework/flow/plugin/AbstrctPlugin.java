package org.asocframework.flow.plugin;

/**
 * @author jiqing
 * @version $Id: AbstrcPlugin，v 1.0 2017/9/29 14:16 jiqing Exp $
 * @desc
 */
public abstract class AbstrctPlugin implements Plugin {

    public PluginType getType() {
        return PluginType.BEFORE_PLUGIN;
    }

}
