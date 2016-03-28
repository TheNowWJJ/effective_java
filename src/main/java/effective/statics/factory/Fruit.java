package effective.statics.factory;

/**
 * Created by barton on 16-3-18.
 */
public class Fruit {
    private String name;
    private String shape;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", shape='" + shape + '\'' +
                '}';
    }

    /**
     * 此方法最好不要用.
     * 时间关键的任务不应该由终结函数来完成.
     * 在不同的JVM上此方法运行的结果并不一定一致.
     * 也不保证能够及时的执行,也不能保证其一定会执行.
     * 结束资源的操作最好用try-finally来完成.
     *
     * @throws Throwable
     */
//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//    }
}
