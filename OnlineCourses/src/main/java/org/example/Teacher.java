package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int salary;
    private int age;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Course> courses;



    public Teacher(){}


    public Teacher(int id, String name, int salary, int age){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

//    @Override
//    public String toString() {
//        return "Teacher{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", salary=" + salary +
//                ", age=" + age +
//                ", courses=" + courses +
//                '}';
//    }
}
