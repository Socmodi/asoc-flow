package org.asocframework.flow.common.classloader;

import org.asocframework.flow.common.component.LogComponent;
import org.slf4j.Logger;
import java.io.File;
import java.util.Properties;

/**
 * @author jiqing
 * @version $Id: LoaderTest，v 1.0 2017/9/29 16:15 jiqing Exp $
 * @desc
 */
public class LoaderTest {

    private static final Logger logger = LogComponent.getLogger(LoaderTest.class);

    public static void main(String args[]){
        Properties properties = System.getProperties();
        logger.error("logger error");
        logger.info("logger test");
        FlowClassLoader classLoader = new FlowClassLoader();
        ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
        try {
            //Class monitor = classLoader.loadClass("/Users/zcy/IdeaProjects/asoc-flow/target/classes/org/asocframework/flow/monitor/FlowMontitor.class");
            Class monitor = classLoader.loadClass("org.asocframework.flow.monitor.FlowMonitor");
            Class clazz = contextLoader.loadClass("org.asocframework.flow.monitor.FlowMonitor");
            Object flowMontitor = monitor.newInstance();
            /*没有破坏委托类型，都是父加载器加载*/
            System.out.println(monitor.equals(clazz));
            //System.out.println(flowMontitor instanceof org.asocframework.flow.monitor.FlowMonitor);
            String fileName = classLoader.getResource("META-INF/dubbo/internal").getFile();
            System.out.println(fileName);
            File file = new File("/Users/zcy/IdeaProjects/asoc-flow/target/classes/org/asocframework/flow/monitor/FlowMontitor.class");
            System.out.println(file.exists());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
