package effective.clas;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 组合优于继承
 * <p>
 * 继承的优点
 * 1.实现代码重用。
 * 继承的缺点
 * 1.打破了封装型（但不绝对）（本来需要隐藏实现的东西，由于继承之后重写了某一些方法而使类的行为发生了改变，
 * 导致，父类重构后，子类没有发生相应的变化）。除非是父类专门为了用继承实现的。
 * <p>
 * 为了允许继承：
 * 1.构造函数一定不能调用可被改写的方法。Sub.java 类证明这一点 @link{Sub}
 * <p>
 * InstrumentedSet是set的一个包装类，修饰Set接口增加了计数的功能。
 * 包装类不适合用在回调方法中。（Set是包装类，被包装）在回调时，被回调的对象并不知道自己外层的包装
 * 从封装的角度来讲，该用复合而用了继承时，暴露了不必要暴露的实现细节，这样得到的API会把你限制在原始的实现上，永远限定了类的性能
 * ，而且客户可以直接访问这些实现细节
 * Created by wangjj on 16-3-27.
 */
public class InstrumentedSet implements Set {

    private Set s;

    private int addCount = 0;

    public InstrumentedSet(Set s) {
        this.s = s;
    }

    @Override
    public int size() {
        return s.size();
    }

    @Override
    public boolean isEmpty() {
        return s.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return s.contains(o);
    }

    @Override
    public Iterator iterator() {
        return s.iterator();
    }

    @Override
    public Object[] toArray() {
        return s.toArray();
    }

    @Override
    public boolean add(Object o) {
        addCount++;
        return s.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return s.remove(o);
    }

    @Override
    public boolean addAll(Collection collection) {
        addCount += collection.size();
        return s.addAll(collection);
    }

    @Override
    public void clear() {
        s.clear();
    }

    @Override
    public boolean removeAll(Collection collection) {
        return s.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection collection) {
        return s.retainAll(collection);
    }

    @Override
    public boolean containsAll(Collection collection) {
        return s.containsAll(collection);
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return s.toArray(objects);
    }
}
