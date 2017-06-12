package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class InstanceClassLoader extends ClassLoader {
    HashMap mappings;


    InstanceClassLoader(ClassLoader parent,HashMap mappings) {
       // super(parent);
        this.mappings = mappings;
    }


    public synchronized Class loadClass(String name) throws ClassNotFoundException {
        try {
            System.out.println("loadClass (" + name + ")");
            //load by parent loader
             if (!mappings.containsKey(name)) {
                return super.findSystemClass(name);

            }
            String fileName = (String) mappings.get(name);
            FileInputStream fin = new FileInputStream(fileName);
            byte[] bbuf = new byte[(int) (new File(fileName).length())];
            fin.read(bbuf);
            fin.close();


            return defineClass(name, bbuf, 0, bbuf.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

}
