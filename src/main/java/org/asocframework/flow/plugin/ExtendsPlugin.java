package org.asocframework.flow.plugin;

/**
 * @author jiqing
 * @version $Id: ExtendsPlugin，v 1.0 2017/9/29 14:17 jiqing Exp $
 * @desc
 */
public abstract class ExtendsPlugin extends AbstrctPlugin{

    @Override
    public PluginType getType() {
        return PluginType.BEFORE_PLUGIN;
    }

}
