package org.asocframework.flow.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jiqing
 * @version $Id: FlowClassLoader，v 1.0 2017/9/29 16:03 jiqing Exp $
 * @desc
 */
public class FlowClassLoader extends ClassLoader{


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream inputStream =null;
        try{
            String FileName = name.substring(name.lastIndexOf(".")+1)+".class";
            //inputStream = new FileInputStream(new File(name));
            inputStream = this.getClass().getResourceAsStream(FileName);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return defineClass(name,bytes,0,bytes.length);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
