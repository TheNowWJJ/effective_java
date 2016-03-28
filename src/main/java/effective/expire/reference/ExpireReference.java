package effective.expire.reference;

import java.util.EmptyStackException;

/**
 * 消除过期引用,可以解决比如说复制时引发的内存溢出
 * 1.对于java等有自动垃圾回收机制的编程语言来说,不是没有OOM只是OOM变得很隐蔽
 * 如果一个栈先是增长,然后再收缩.那么,从栈中弹出来的对象将不会被当做垃圾回收掉,即使使用栈的客户不再对其进行引用,也不会被回收.
 * 这是因为,栈内部维护着对这些对象的过期引用(obsolete reference),就是永远不会被回收的引用.如果产生这种情况,垃圾回收也不会处理
 * 过期对象所引用的其他对象.
 * <p>
 * Created by barton on 16-3-21.
 */
public class ExpireReference {
    private Object[] elements;
    private int size = 0;

    public ExpireReference(int size) {
        this.elements = new Object[size];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;

    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size]; // 这样写,栈会维护过期引用elements[size]. (栈的出入规则)

    }

    public Object pop2() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null; // 将过期引用赋值null,再引用这个值的时候就会出现空指针异常.
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            Object[] oldElements = elements;
            elements = new Object[2 * elements.length + 1];
            System.arraycopy(oldElements, 0, elements, 0, size);
        }
    }

    public static void main(String[] args) {
        ExpireReference er = new ExpireReference(5);
        er.push(new String("123"));
        er.push(new String("456"));
        System.out.println("打印栈中正确的数据");
        System.out.println(er.elements[0]);
        System.out.println(er.elements[1]);

        // 移除栈中的一个数据
        er.pop();

        System.out.println("栈中依旧保留过期的引用");
        System.out.println(er.elements[0]);
        System.out.println(er.elements[1]);

        // 用删除过期引用的方式移除栈中的一个数据
        er.pop2();
        System.out.println("抛出空指针异常");
        System.out.println(er.elements[0] == null);
        System.out.println(er.elements[1]);
    }
}
