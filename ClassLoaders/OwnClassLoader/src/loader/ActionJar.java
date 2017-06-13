package loader;


import java.lang.reflect.InvocationTargetException;

public class ActionJar {
    public static void main(String[] args) {
        JarClassLoader jarClassLoader = new JarClassLoader("C:\\Users\\Siarhei_Slabadniak\\Documents\\compiled\\Example.jar", "C:\\Users\\Siarhei_Slabadniak\\Documents\\compiled");

// Загружаем класс

        Class<?> clazz = null;
        try {
            clazz = jarClassLoader.loadClass("compiled.JustExample");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Object object = null;
// Создаем экземпляр класса
        try {
            object = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println("object was created");

        try {
            System.out.println("invoke: test" + clazz.getMethod("test").invoke(object));
        } catch (IllegalAccessException  | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
