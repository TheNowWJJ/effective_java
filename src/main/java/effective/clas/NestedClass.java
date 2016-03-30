package effective.clas;

/**
 * 优先考虑静态成员类
 * 嵌套类指被定义在另一个类的内部的类，其目的应该只是为它的外围类提供服务。
 * 嵌套类有四种：静态成员类，非静态成员类，匿名类，局部类
 * Created by barton on 16-3-30.
 */
public class NestedClass {

    private static final double PI = Math.PI;

    private static String name = "barton";

    /**
     * 静态成员类
     * 可访问所有外部类的静态成员
     * 它是外部类的一个静态成员
     * 声明为private则只能是它的外部类才能使用
     */
    public static class StaticMemberClass {
        public void print() {
            System.out.println(PI);
            System.out.println(name);
        }
    }


    public static void main(String[] args) {
        StaticMemberClass smc = new StaticMemberClass();
        smc.print();
    }
}
