package org.asocframework.flow.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author jiqing
 * @version $Id: FlowNameSpaceHandler，v 1.0 2017/9/27 14:37 jiqing Exp $
 * @desc
 */
public class FlowNameSpaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("flow", new FlowEngineBeanDefinitionParser());
    }

}
