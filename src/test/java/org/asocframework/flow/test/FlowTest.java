package org.asocframework.flow.test;

import org.asocframework.flow.engine.FlowEngineApplication;
import org.asocframework.flow.event.EventContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author jiqing
 * @version $Id: FlowTest，v 1.0 2017/10/16 18:27 jiqing Exp $
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:flow_test.xml"})
public class FlowTest {

    @Resource
    private FlowEngineApplication flowEngineApplication;

    @Test
    public void test(){
        EventContext context = new EventContext("demo","demo");
        context = flowEngineApplication.execute(context);
        System.out.println(context.isSuccess());
    }
}
