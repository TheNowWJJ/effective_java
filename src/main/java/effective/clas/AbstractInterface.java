package effective.clas;

import java.util.*;

/**
 * 接口抽象的是行为，是"有没有"的关系，而抽象类抽象的是事物的共性，是"是不是"的关系。接口是从上到下的定义，根本不需要知道
 * 子类的存在，而抽象类是从下到上的抽象，需要先知道具体的事物及其共性，然后才会有抽象类的产出。抽象类体现的是一种继承关系，
 * 即父类和子类在本质上是一样的；而接口并不要求接口的实现者和接口定义在概念的本质上一致，仅仅是实现了接口定义的契约而已。
 * <p>
 * 接口优于抽象类
 * java用来定义一个允许多个实现的类型：接口和抽象类
 * 区别在于：抽象类允许包含某些方法的实现，接口不可以（java8已经有了接口的默认方法）。
 * 优点：
 * 1.已有的类可以很容易的被更新，以实现新的接口。增加需求的方法，在类的声明上增加一个implements字句。如果扩展以前实现的接口
 * 不合适，那么新建一个接口就可以。由于java只能单重继承，所以不能新建一个抽象类，让子类去实现，这样只能扩展已经存在的抽象类，
 * 创建一个新的抽象类，继承已经存在的抽象类，然后再扩展相应的方法，这样就破坏了原先的类型结构。
 * 2.接口时定义mixin(混合类型)的理想选择。（有待研究，还不是很懂。）
 * 3.接口使得我们可以构造出非层次结构的类型框架。并不要求存在"is-a"的关系，即不要求某一个事物一定是另一个事物。
 * 4.接口使得安全的增加一个类的功能成为可能。抽象类会影响所有其他子类，而接口不会。例如：定义一个抽象类动物，定义两个子类鸟和鱼
 * ，现在要求给鸟增加一个fly方法，如果用抽象类来做，可以定义一个方法在动物抽象类中，但是鱼是不会飞的。这样做，就不叫抽象了，因为
 * 抽象指的是共性。
 * <p>
 * 结合接口和抽象类的优点，对于你期望导出的每一个重要接口，都提供一个抽象的骨架实现类，接口的作用仍然时定义类型，骨架实现类负责
 * 实现类负责所有与接口实现相关的工作。惯例：骨架实现类被称为AbstractInterface，其中Interface是所实现的接口的名字。
 * <p>
 * 通过intArrayAsList和intArrayAsMap这两个方法可以提供类似与多重继承的功能
 * <p>
 * Created by barton on 16-3-30.
 */
public class AbstractInterface {

    public static List intArrayAsList(final int[] a) {
        if (a == null) {
            throw new NullPointerException();
        }
        return new AbstractList() {
            @Override
            public Object get(int index) {
                return new Integer(a[index]);
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    /**
     * just a demo
     *
     * @param a
     * @return
     */
    public static Map intArrayAsMap(final int[] a) {
        return new AbstractMap() {
            @Override
            public Set<Entry> entrySet() {
                return null;
            }
        };
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        List list = intArrayAsList(a);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("================分割线=========");
        System.out.println(list.size());
        System.out.println(list.get(3));

        System.out.println("========================华丽的分割线==========================");

        Animal animal = Person.personAsAnimal();
        animal.eat();
        animal.move();
    }
}

interface Animal {
    void eat();

    void move();
}

/**
 * 骨架实现类为抽象类提供了实现上的帮助，但也没有强加"抽象类被用作类型定义时候"所特有的严格限制。
 * 实现了这个接口的类可以把对于接口方法的地啊用，转发到一个内部私有类的实例上，而这个内部私有类扩展了骨架实现类，这项技术
 * 叫做"模拟多重继承"。（通过这样的方式，实现了一个类有多种不同类的行为）
 */
abstract class AbstractAnimal implements Animal {

    @Override
    public void eat() {
        System.out.println("默认的吃");
    }

    @Override
    public void move() {
        System.out.println("默认的行走");
    }
}

class Person {
    public static Animal personAsAnimal() {
        return new AbstractAnimal() {
//            @Override
//            public void eat() {
//                System.out.println("我用嘴巴吃饭");
//            }
//
//            @Override
//            public void move() {
//                System.out.println("我用双腿走路");
//            }
        };
    }
}