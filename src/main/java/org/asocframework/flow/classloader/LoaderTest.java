package org.asocframework.flow.classloader;

import org.asocframework.flow.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiqing
 * @version $Id: LoaderTest，v 1.0 2017/9/29 16:15 jiqing Exp $
 * @desc
 */
public class LoaderTest {

    public static void main(String args[]){
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
