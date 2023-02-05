package edu.sabanciuniv.homework1.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;

import java.util.List;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String schoolName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Establish_Year")
    private String establishYear;

    @OneToMany
    private List<Student> studentList = new ArrayList<>();

    //Constructor


    public School(String schoolName, String establishYear) {
        this.schoolName = schoolName;
        this.establishYear = establishYear;
    }

    public School() {
    }

    //Getter and Setter
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEstablishYear() {
        return establishYear;
    }

    public void setEstablishYear(String establishYear) {
        this.establishYear = establishYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", establishYear=" + establishYear +
                '}';
    }
}
