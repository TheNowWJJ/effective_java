package effective.compareable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 对于一个值类，要考虑实现Compareable接口，利用其compareTo方法实现简单排序
 * Created by barton on 16-3-28.
 */
public class CompareAble {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();

        Student student1 = new Student("name1", 15);
        Student student2 = new Student("name2", 12);
        Student student3 = new Student("name3", 17);
        Student student4 = new Student("name4", 14);

        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);

        Collections.sort(list);

        for (Student s : list) {
            System.out.println(s.getName() + ":" + s.getAge());
        }
    }

}

class Student implements Comparable {

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 利用age比较大小
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Student student = (Student) o;
        if (this.age < student.age) {
            return -1;
        }
        if (this.age > student.age) {
            return 1;
        }
        if (this.age == student.age) {
            return 0;
        }
        return 0;
    }
}