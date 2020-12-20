package com.epam.faculty.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
@DynamicInsert
@DynamicUpdate
@NamedQueries({@NamedQuery(name = "courseByName",
        query = "from Course s where s.courseName=:nameCourse")})
public class Course {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "course_id", length = 16, nullable = false, updatable = false)
    private UUID courseId = UUID.randomUUID();

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "duration_weeks", nullable = false)
    private int durationWeeks;

    @Column(name = "student_count", nullable = false)
    private int studentCount;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "price", nullable = false)
    private int price;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> usersRegistration = new ArrayList<>();

    public Course() {
    }

    public Course(UUID courseId, String courseName, LocalDateTime dateTime, int durationWeeks, long userId, String status, int price)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.dateTime = dateTime;
        this.durationWeeks = durationWeeks;
        this.userId = userId;
        this.status = status;
        this.price = price;
    }
    public Course(String courseName, LocalDateTime dateTime, int durationWeeks, int studentCount, long userId, String status, int price)
    {
        this.courseName = courseName;
        this.dateTime = dateTime;
        this.durationWeeks = durationWeeks;
        this.studentCount = studentCount;
        this.userId = userId;
        this.status = status;
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Registration> getRegistrations() {
        return usersRegistration;
    }

    public void setPurchaseList(List<Registration> registrationList) {
        this.usersRegistration = registrationList;
    }

}
