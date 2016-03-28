package effective.util;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by barton on 16-3-18.
 */
public class InstanceUtil implements Serializable {
    // 私有化构造方法
    private InstanceUtil() {
    }

    private static Map<String, String> instancesMap = null;

    /**
     *用private和public修饰符的区别(都使用私有化的构造方法,都使用static final修饰)
     * 两种方法都能保证实例的唯一性.
     * 区别在于方法1不管使用者做任何操作,实例都不会发生改变.
     * 方法2提供了灵活性,可以被修改.
     *
     */
    // 方法1:private static final InstanceUtil iu = new InstanceUtil();
    // 方法2:public static final InstanceUtil iu = new InstanceUtil();

    /**
     * @param propertiesPath
     */
    private static void initMapIfNecessory(String propertiesPath) {
        Properties properties = new Properties();
        InputStream is;
        if (null == instancesMap) {
            instancesMap = new HashMap();
            try {
                // 加载配置文件取得类的key和name
                is = new BufferedInputStream(new FileInputStream(propertiesPath));
                properties.load(is);
                is.close();

                // 初始化map
                Enumeration<?> propertyNames = properties.propertyNames();
                while (propertyNames.hasMoreElements()) {
                    String propertyName = (String) propertyNames.nextElement();
                    instancesMap.put(propertyName, properties.getProperty
                            (propertyName));
                }

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getInstanceFromProperties(String key) {
        initMapIfNecessory("/home/wangjingj/soft/workspaces/spring_boot_learn/src/main/java" +
                "/effective/statics/factory/classes.properties");
        return instancesMap.get(key);
    }

    /**
     * 防止用序列化技术存在硬盘上之后,再从硬盘中取得instanceMap对象(通过序列化获得一个怎么样的实例，由此方法说了算)
     * 现在通过可序列化的工具，我们可以将一个单例的实例对象写到磁盘，然后再读回来，
     * 从而有效地获得一个实例。即使构造函数是私有的，可序列化工具依然可以通过特殊的途径去创建类的一个新的实例。
     * 序列化操作提供了一个很特别的钩子（hook）-类中具有一个私有的被实例化的方法readresolve(),
     * 这个方法可以确保类的开发人员在序列化将会返回怎样的object上具有发言权。足够奇怪的，readresolve()并不是静态的，
     * 但是在序列化创建实例的时候被引用。
     *
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {

        return instancesMap;
    }

    public static void main(String args[]) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, ObjectStreamException {
        System.out.println(InstanceUtil.getInstanceFromProperties("apple"));
    }
}
