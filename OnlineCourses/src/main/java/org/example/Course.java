package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "students_count")
    private Integer studentsCount;

    private Integer price;

    @Column(name = "price_per_hour")
    private Float pricePerHour;


    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Student> students;


    public Course(){}

    public Course(int id, String name, int duration, CourseType type, String description, Teacher teacher, int studentCount, int price, float pricePerHour, List<Student> students){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.description = description;
        this.teacher = teacher;
        this.studentsCount = studentCount;
        this.price = price;
        this.pricePerHour = pricePerHour;
        this.students = students;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", duration=" + duration +
//                ", type=" + type +
//                ", description='" + description + '\'' +
//                ", teacherId=" + teacher +
//                ", studentsCount=" + studentsCount +
//                ", price=" + price +
//                ", pricePerHour=" + pricePerHour +
//                '}';
//    }
}
