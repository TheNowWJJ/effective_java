package effective.util;

/**
 * Created by barton on 16-3-18.
 */
public class ClassUtil {
    // 私有化构造方法
    private ClassUtil() {
    }

    public static Object getInstance(String path) {
        return convert(path);
    }

    /**
     * 根据类名和具体需要转换的类型获得相应的实例
     * @param path
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getInstance(String path, Class<T> requiredType) {

        if (null == requiredType) {
            throw new RuntimeException("需要转换的类型不对!");
        }
        return (T) getInstance(path);
    }

    /**
     * 根据类名获得该类的实例
     * @param path
     * @return
     */
    private static Object convert(String path) {

        try {
            return Class.forName(path).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
