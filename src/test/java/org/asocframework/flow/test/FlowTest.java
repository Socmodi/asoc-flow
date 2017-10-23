package org.asocframework.flow.test;

import org.asocframework.flow.common.constants.FlowEngineConstants;
import org.asocframework.flow.engine.EngineContext;
import org.asocframework.flow.engine.FlowEngineApplication;
import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.plugin.AccidentPlugin;
import org.asocframework.flow.store.condition.QueryCondition;
import org.asocframework.flow.store.domain.AccidentMirror;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

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

    @Test
    public void testDb(){
        AccidentPlugin accidentPlugin = (AccidentPlugin) EngineContext.getPlugin(FlowEngineConstants.ACCIDENT_PLUGIN);
        EventContext context = new EventContext("demo","demo");
        context.setErrorMsg("test db");
        accidentPlugin.processAccident(context);
        System.out.println("ending");
    }

    @Test
    public void testDbFind(){
        AccidentPlugin accidentPlugin = (AccidentPlugin) EngineContext.getPlugin(FlowEngineConstants.ACCIDENT_PLUGIN);
        AccidentMirror accidentMirror = accidentPlugin.getAccidentStore().getAccidentDao().find("171020061638061142456763");
        System.out.println("ending");
    }
    @Test
    public void testDbQuery(){
        AccidentPlugin accidentPlugin = (AccidentPlugin) EngineContext.getPlugin(FlowEngineConstants.ACCIDENT_PLUGIN);
        QueryCondition condition = new QueryCondition();
        condition.setStatus(0);
        condition.setStartRow(0);
        List<AccidentMirror> list = accidentPlugin.getAccidentStore().getAccidentDao().query(condition);

        String str = "sdfsdf,sadfsd,sdfsdf,";
        str = str.substring(0,str.length()-1);
        System.out.println("ending");
    }

    @Test
    public void testClassLoader() {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
    }

}
