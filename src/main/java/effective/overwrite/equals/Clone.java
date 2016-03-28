package effective.overwrite.equals;

import java.util.Date;

/**
 * 利用Object的clone方法获得对象的一份copy，对象引用不同，但是属性值相等。
 * Created by barton on 16-3-28.
 */
public class Clone {
    public static void main(String[] args) throws CloneNotSupportedException {
        User u1 = new User("barton", "barton", new Date());

        User u2 = u1;
        User u3 = (User) u1.clone();

        System.out.println(u3);
        System.out.println(u1 == u2); // true
        System.out.println(u1.equals(u2)); // true
        System.out.println(u1 == u3); // false
        System.out.println(u1.equals(u3)); // true (不同的jdk版本 结果可能会有所不同)


        System.out.println("============分割线==============");

        Administrator a1 = new Administrator(new User("Kent", "123456", new Date()), true);

        Administrator a2 = a1;

        Administrator a3 = (Administrator) a1.clone();


        System.out.println(a1 == a2);            // true

        System.out.println(a1.equals(a2));        // true


        System.out.println(a1 == a3);            // false

        System.out.println(a1.equals(a3));        // true (不同的jdk版本 结果可能会有所不同)

        // 浅clone下是true，深clone是false，参考Administrator类的clone方法的注释
        System.out.println(a1.getUser() == a3.getUser());

        System.out.println(a1.getUser().equals(a3.getUser()));    //true

    }
}

class User implements Cloneable {

    private String username;

    private String password;

    private Date birthday;

    public User(String username, String password, Date birthday) {

        this.username = username;

        this.password = password;

        this.birthday = birthday;

    }

    @Override

    protected Object clone() throws CloneNotSupportedException {

        return super.clone();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }

        User u = (User) obj;

        if (u.getUsername().equals(this.getUsername()) && u.getPassword().equals(this.getPassword
                ()) && u.getBirthday().equals(this.getBirthday())) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

class Administrator implements Cloneable {
    public Administrator(User u, Boolean editable) {
        this.user = u;
        this.editable = editable;
    }

    private User user;
    private Boolean editable;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    /**
     * 浅clone和深clone用在对象所包含的对象引用上(组合)
     * java.lang.Object规范中对clone的约定：x.clone() != x && x.clone().getClass() == x.getClass() && x
     * .clone.equals(x)
     * 其中x.clone().getClass()和x.clone.equals(x)不是绝对的要求
     * <p>
     * 实现clone方法之后，可以不用调用构造方法就能够创建一个对象！
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        //return super.clone(); // 浅clone，只克隆它自身以及它所包含的对象的引用，引用所包含的对象在clone之后和它本身相等(对象的引用相同，就是同一个对象)

        // 实现深clone
        Administrator admin = (Administrator) super.clone();
        admin.setUser((User) admin.user.clone());

        return admin;
    }

}
