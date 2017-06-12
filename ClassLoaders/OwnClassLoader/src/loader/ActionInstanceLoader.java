package loader;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ActionInstanceLoader {
    private static final String fileName = "compiled.JustExample";
    private static final String filePath = "C:\\Users\\Siarhei_Slabadniak\\Documents\\compiled\\JustExample.class";

    public static void main(String[] args) {
        HashMap mappings = new HashMap();

        mappings.put(fileName, filePath);

        InstanceClassLoader insLoader = new InstanceClassLoader(ActionInstanceLoader.class.getClassLoader(), mappings);

        Class loadedClass = null;
        try {
            loadedClass = insLoader.loadClass(fileName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("class was loaded");

        System.out.println("begin object creation");


        Object object = null;
        try {
            object = loadedClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println("object was created");

        try {
            System.out.println("invoke: test" + loadedClass.getMethod("test").invoke(object));

            System.out.println("get: str = " + loadedClass.getField("STR").get(object));
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
