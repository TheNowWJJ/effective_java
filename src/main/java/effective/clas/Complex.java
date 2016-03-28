package effective.clas;

/**
 * 一个final类的实例是不能被修改的。每个实例中所包含的所有信息都必须在该实例被创建的时候提供出来，并且在对象的整个生存期内固定不变。
 * 为了让一个类成为一个final类，要遵循的规则：
 * 1.不要提供任何会修改对象的方法。(不是说不能创建新对象，而是说对于一个已经创建了的对象，不能进行修改）
 * 2.保证没有可被子类改写的方法。（把类声明为final,或者私有化构造方法并且提供相应的静态工厂方法）
 * 3.使所有成员变量都是final的。
 * 4.使所有成员变量都是private的。
 * 5.保证对于任何可变组件的互斥访问。如果final类中具有可变对象的成员，则必须确保该类的客户无法获得指向这些对象的引用，并且也不要用
 * 客户提供的对象引用来初始化这样的成员，也不要在任何一个方法中返回该对象的引用，在构造方法，访问方法和readObject方法中使用保护性拷贝
 * <p>
 * 非可变对象只有对象刚被创建时的一个状态
 * 非可变对象本质上是线程安全的它们不要求同步（因为其只有一个状态，创建了就不会被更改。），所以非可变对象可以被自由的共享
 * 不仅可以共享非可变对象，甚至可以共享它们的内部信息。
 * 非可变对象唯一的缺陷是，对于每一个不同的值都要求一个单独的对象
 * Created by wangjj on 16-3-27.
 */
public final class Complex {
    /**
     * 规则3,4
     */
    private final float re;
    private final float im;

    public Complex(float re, float im) {
        this.re = re;
        this.im = im;
    }

    public float readPart() {
        return this.re;
    }

    public float imaginaryPart() {
        return this.im;
    }

    /**
     * 规则1
     *
     * @param c
     * @return
     */
    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Complex)) {
            return false;
        }
        Complex c = (Complex) o;

        return (Float.floatToIntBits(re) == Float.floatToIntBits(c.re)) && (Float.floatToIntBits(im) == Float.floatToIntBits(c.im));
    }

    @Override
    public int hashCode() {
        int result = 17 + Float.floatToIntBits(re);
        result = 37 * result + Float.floatToIntBits(im);
        return result;
    }

    @Override
    public String toString() {
        return "(" + re + ":" + im + ")";
    }

    public static void main(String[] args) {
        /**
         * String 类同，可以解释String的不可变性。（已经创建的对象是不可变的，但不是说不能创建新对象）
         */
        Complex c1 = new Complex(1, 2);
        Complex add = new Complex(2, 3);
        Complex c2 = c1.add(add);
        System.out.println(c1.readPart());
        System.out.println(c1.imaginaryPart());
        System.out.println(c2.readPart());
        System.out.println(c2.imaginaryPart());
    }
}
