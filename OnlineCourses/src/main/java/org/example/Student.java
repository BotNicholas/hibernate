package org.example;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    @Column(name = "registration_date")
    private Date registrationDate;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//Student Entity is the owning side! (there are no any proof, because here we do not have any kind of Course's foreign key, but logically Student has Courses thus Student is the owning side)
    @JoinTable(name = "Subscriptions",
               joinColumns = {@JoinColumn(name = "student_id")},
               inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses;



    public Student(){}

    public Student(int id, String name, int age, Date registrationDate, List<Course> courses){
        this.id = id;
        this.name = name;
        this.age = age;
        this.registrationDate = registrationDate;
        this.courses = courses;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        //return id == student.id && age == student.age && Objects.equals(name, student.name) && Objects.equals(registrationDate, student.registrationDate) && Objects.equals(courses, student.courses);
//        return id == student.getId();
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, registrationDate, courses);
    }
}
