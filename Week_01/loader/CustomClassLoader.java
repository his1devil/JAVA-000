package loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @Author antaifeng
 * @Version @version $Id: CustomClassLoader, v0.1 2020-10-20 8:25 下午 antaifeng Exp $
 * @Description
 */
public class CustomClassLoader extends ClassLoader {

    public String path;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] clazz = loadClassData(name);
        if (clazz == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, clazz, 0, clazz.length);
    }

    private byte[] loadClassData(String className) {
        String fileName = path + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try (InputStream inputStream = new FileInputStream(fileName);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int size = 0;
            while ((size = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, size);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void call(String path, String className, String method) throws Exception {
        CustomClassLoader loader = new CustomClassLoader();
        loader.path = path;

        // invoke method
        Class<?> clz = Class.forName(className, true, loader);
        Object instance = clz.newInstance();
        Method test = clz.getDeclaredMethod(method);
        test.setAccessible(true);
        test.invoke(instance);
    }

}
