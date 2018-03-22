package org.asocframework.flow.common.classloader;

import org.asocframework.flow.common.component.LogComponent;
import org.slf4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                InputStream inputStream =null;
                try{
                    //String FileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    inputStream = new FileInputStream(new File(name));
                    //inputStream = getClass().getResourceAsStream(FileName);
                    if(inputStream==null){
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return defineClass(name,bytes,0,bytes.length);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        if(inputStream!=null){
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(loader);

            //Class monitor = loader.loadClass("org.asocframework.flow.filter.MonitorFilter");
            //Class monitor = loader.loadClass("org.asocframework.flow.common.classloader.LoaderTest");
            //Class clazz = contextLoader.loadClass("org.asocframework.flow.filter.MonitorFilter");
            //Object flowMontitor = monitor.newInstance();

            //System.out.println(flowMontitor instanceof  org.asocframework.flow.filter.MonitorFilter);
            //System.out.println(flowMontitor instanceof org.asocframework.flow.common.classloader.LoaderTest);
            //String fileName = classLoader.getResource("META-INF/dubbo/internal").getFile();
            //System.out.println(fileName);
            File file = new File("/Users/zcy/IdeaProjects/asoc-flow/target/classes/org/asocframework/flow/filter/Filter.class");
            System.out.println(file.exists());
            Class clazz1 = contextLoader.loadClass("org.asocframework.flow.filter.Filter");
            Class clazz2  = loader.loadClass("/Users/zcy/IdeaProjects/asoc-flow/target/classes/org/asocframework/flow/filter/Filter.class");
            System.out.println(clazz1.equals(clazz2));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Thread.currentThread().setContextClassLoader(contextLoader);
        }

    }

}
