package org.asocframework.flow.test;

import org.asocframework.flow.engine.FlowEngineContext;
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
    private FlowEngineContext flowEngineContext;

    @Test
    public void test(){
        System.out.println("ssssssssss");

    }
}
