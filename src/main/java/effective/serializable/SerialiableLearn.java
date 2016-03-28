package effective.serializable;

import java.io.*;

/**
 * java 序列化和用readResolve控制反序列化时获得实例的行为
 * Created by barton on 16-3-28.
 */
public class SerialiableLearn {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = User.getUserInstance("barton");

        File file = new File("/home/wangjingj/serialiable.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        // 对象序列化的过程
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // 此方法可以将user序列化存储到硬盘中
        oos.writeObject(user);

        oos.flush();
        oos.close();
        fos.close();

        // 对象反序列化的过程
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        User user2 = (User) ois.readObject();
        System.out.println(user2.getName());

        System.out.println(user == user2);// 看readResolve到底是不是返回单例。单例:true

    }
}

class User implements Serializable {
    private User(String name) {
        this.name = name;
    }

    private static User user = null;

    public static User getUserInstance(String name) {
        if (null == user) {
            user = new User(name);
        }
        return user;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Object readResolve() throws ObjectStreamException {
        //return this; // 返回的是一个对象的clone
        return user; // 在单例模式中 还是返回单一对象
    }
}