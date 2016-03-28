package effective.overwrite.equals;

/**
 * Created by barton on 16-3-22.
 */
public class Equals {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Object.equals 本质上提供的是确定两个对象是否一致.这种情况下只有同一个对象的两个变量才会相等.
     * String和其他包装类提供的equals方法都是逻辑相等.(大致可以理解为值相等)
     * 改写equals方法的期望结果
     * 1.一个类的每一个实例本质上都是唯一的
     * 2.不关心一个类是否提供了逻辑相等的测试功能
     * 3.父类已经改写了equals,从父类继承来的行为对于子类也是合适的.(子类也是父类的一个实例.)父类改写了,子类就不需要再写了.
     * 4.一个类是私有的,或者是包级私有的(内部类,或者一个java文件里边的多个类),并且可以确定它的equals方法永远也不会被调用.
     * 那么 public boolean equals(Object obj) {throw new UnsupportedOperationException();} 确保它不会被调用.
     * <p>
     * 什么时候改写equals方法
     * 1.一个类有自己特有的逻辑相等
     * 2.超类没有改写equals以实现期望的行为.
     * <p>
     * 类型安全的枚举类型,因为其保证每一个值至多存在一个对象,所以这样的类型,Object的equals方法等同于逻辑意义上的equals方法
     * <p>
     * 改写equals方法的通用规则
     * 1.自反性:对于任意的引用值x,x.equals(x) 一定为true.如果违反,当你把该类的实例加入到一个集合中时,集合的contains方法将不会检测到该实例
     * 2.对称性:对于任意的引用值x,y.当且仅当x.equals(y)返回true时,y.equals(x)也一定返回true
     * 3.传递性:对于任意的引用值x,y,z.如果x.equals(y)返回true,并且y.equals(z)也返回true,那么x.equals(z)也一定返回true
     * 4.一致性:对于任意的引用值x和y,如果用于equals比较的对象信息没有被修改的化,那么,多次调用x.equals(y)要么一致返回true,要么一致返回false
     * 5.对于任意的非空引用值x,x.equals(null)一定返回false;
     * <p>
     * 实现一个高质量equals方法的方法
     * 1.使用==操作符检查"实参是否为指向对象的一个引用",是则true.
     * 2.使用instanceof操作符检查"实参是否为正确的类型",是则true.(有可能检查的是接口,也有可能是类)
     * 3.把实参转换到正确的类型.第2步操作成功的话这一步可以确保成功
     * 4.对于该类中每一个关键的字段,检查实参中的域与当前对象中对应的字段值是否相等,如果全部相等,则true (大概如下:field == null?o.field==null:field
     * .equals(o.field))
     * 5.写完equals方法后,应该再确认是否是对称的,传递的,一致的.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * 改写equals时总是要改写hashcode
     * <p>
     * hashcode约定内容，来自java.lang.Object的规范
     * 1.在一个应用程序执行期间，如果一个对象的equals方法做比较所用到的信息没有被修改的化，那么，对该对象调用hashcode方法多次，
     * 它必须始终如一地返回同一个整数。在同一个应用程序的多次执行过程中，这个整数可以不同，即这个应用程序这次执行返回的证书与下一次执行返回的整数可以不一致。
     * 2.如果两个对象根据equals方法是相等的，那么调用这两个对象中任一个对象产生的hashcode方法必须产生同样的整数结果
     * 3.如果两个对象根据equals方法是不想等的，那么地啊用这两个对象中任一个对象的hashcode方法，不要求必须产生不同的整数结果。
     * <p>
     * 只改写equals不改写hashCode违反的是第二条约定：相等的对象必须具有相等的hashcode。因为改写了equals，有可能出现两个对象是逻辑相等的。
     * <p>
     * 实现一个相对接近理想情形的hashCode方法的方法：
     * 1.把某个非零常数值，比如说17，保存在一个叫result的int类型的变量中。
     * 2.对于对象中每一个关键域f（指equals方法中考虑的每一个域），完成如下步骤：
     * a.为该域计算int类型的散列码c
     * 如果该域是boolean类型，则计算(f?0:1)
     * 如果该域是byte，char，short或者int类型，则计算(int)f;
     * 如果该域是long类型，则计算(int)(f ^ (f>>>32));
     * 如果该域是float类型，则计算Float.floatToIntBits(f);
     * 如果该域时double类型，则计算Double.doubleToLongBits(f)得到一个long类型的值，然后按照long类型进行计算散列值
     * 如果该域时一个对象引用，并且该类的equals方法通过地柜地啊用equals的方式来比较这个域，则同样对这个域递归调用hashcode
     * 。如果要求一个更为复杂的比较，则为这个域计算一个“规范表示”，然后针对这个范式表示调用hashcode。如果这个域的值为null，则返回0.
     * b.按照result = 37*result + c; 把步骤a中计算得到的散列码c组合到result中。
     * 3.返回result
     * 4.写完了hashcode方法之后，问“是否相等的实例具有相等的散列码”
     * <p>
     * 不要试图从散列码计算中排除掉一个对象的关键部分以提高性能
     *
     * @return
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }


    /**
     * 改写equals时总要改写toString
     * java.lang.Object 提供的toString方法，包含类的名称/@符号/散列码的无符号十六进制表示
     * toString方法的作用不仅仅是将对象转换为String字符串，也可以用来显示对象的状态信息
     *
     * @return
     */
    @Override
    public String toString() {
        return "Equals{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * @return
     * @throws CloneNotSupportedException
     * @Link {Clone.java}
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}