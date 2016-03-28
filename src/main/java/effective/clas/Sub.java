package effective.clas;

import java.util.Date;

/**
 * Created by wangjj on 16-3-27.
 */
public class Sub extends Super {

    private Date date;

    /**
     * Sub构造方法调用时，会先调用Super的构造方法，Super构造方法执行时，执行的print方法还是子类的方法，这个时候date还没有被初始化
     * 因此会先打印null，再打印date
     */
    Sub() {
        date = new Date();
    }

    @Override
    public void print() {
        System.out.println(date);
    }

    public static void main(String[] args) {
        Sub s = new Sub();
        s.print();
    }
}

class Super {
    public Super() {
        print();
    }

    public void print() {
        System.out.println("super.print");
    }
}
