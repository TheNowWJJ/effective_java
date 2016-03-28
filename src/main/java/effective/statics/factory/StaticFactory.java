package effective.statics.factory;

import effective.util.ClassUtil;
import effective.util.InstanceUtil;

/**
 * Created by barton on 16-3-18.
 * 静态工厂方法的好处
 * 1.与构造方法不同,静态工厂方法有名字,更利于阅读和使用
 * 2.每次被调用时,不一定必须得创建新对象(PropertyUtil),结合单例模式
 * 3.可以返回一个原返回类型的子类型的对象(ClassUtil)
 * 静态工厂方法的坏处
 * 1.如果不包含共有的或者受保护的构造函数,就不能被实例化
 * 2.与其他静态方法没有任何区别
 */
public class StaticFactory {
    public static void main(String args[]) {
        // 解释注释第三点.
        Fruit fruit = ClassUtil.getInstance(InstanceUtil
                .getInstanceFromProperties("apple"), Apple.class);
        fruit.setShape("圆的");
        fruit.setName("apple");
        System.out.println(fruit.toString());
    }
}
