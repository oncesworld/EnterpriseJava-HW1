package edu.sabanciuniv.homework1.main;

import edu.sabanciuniv.homework1.model.School;
import edu.sabanciuniv.homework1.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class mainClass {

    public static void main(String[] args) {
        Student student1 = new Student("Onur Cem", "Işık", "Science",new Date());
        Student student2 = new Student("Onur", "Işık2", "Law",new Date());
        Student student3 = new Student("Cem", "Işık3", "Lecture",new Date());

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
        EntityManager entityManager = emf.createEntityManager();

        School school1 = new School("Sabancı University", "1994");
        School school2 = new School("Boğaziçi University" , "1863");

        student1.setSchool(school1);
        student2.setSchool(school1);
        student3.setSchool(school2);

        List<School> schoolList = new ArrayList<>();
        schoolList.add(school1);
        schoolList.add(school2);

        //saveSchool(schoolList,entityManager);
        //saveStudents(studentList, entityManager);
        //updateStudentFaculty(entityManager,student2, "Pokemon");
        //findByStudentName(entityManager,"Onur");
        //findAllStudents(entityManager);
        //deleteStudent(entityManager,student1);
        //findSchools(entityManager);
        findStudentsSchoolWithName("Onur",entityManager);

    }

    private static void saveSchool(List<School> schoolList, EntityManager entityManager) {
        for (School school : schoolList) {
            entityManager.getTransaction().begin();
            entityManager.persist(school);
            entityManager.getTransaction().commit();
        }
    }

    private static void findSchools(EntityManager entityManager){
        TypedQuery schoolJpql = entityManager.createQuery("FROM School s", School.class);
        List<School> schoolList =schoolJpql.getResultList();

        for (School school : schoolList) {
            System.out.println(school);
        }
    }

    private static void findStudentsSchoolWithName(String studentName, EntityManager entityManager){
        Student firstQuery = entityManager.createQuery("FROM Student s WHERE s.firstName = ?1", Student.class )
                .setParameter(1,studentName).getSingleResult();
        System.out.println(studentName + "'s school is " +
                firstQuery.getSchool().getSchoolName());

    }

    private static void findAllStudents(EntityManager entityManager) {
        TypedQuery studentJpql = entityManager.createQuery("FROM Student s", Student.class);
        List<Student> studentList = studentJpql.getResultList();

        for (Student student : studentList) {
            System.out.println(student);
        }
    }


    private static void findByStudentName(EntityManager entityManager, String studentName) {
        Student foundStudent = entityManager.createQuery("FROM Student s WHERE s.firstName = ?1", Student.class)
                .setParameter(1, studentName).getSingleResult();
        System.out.println(foundStudent);
    }

    private static void updateStudentFaculty(EntityManager entityManager, Student student, String newStudentFaculty) {

        entityManager.getTransaction().begin();

        Student foundStudent = entityManager.createQuery("FROM Student s WHERE s.studentId = :stId", Student.class)
                .setParameter("stId", student.getStudentId())
                .getSingleResult();
        foundStudent.setFaculty(newStudentFaculty);
        entityManager.merge(foundStudent);

        entityManager.getTransaction().commit();
        System.out.println("Student faculty updated to : " + newStudentFaculty);
    }

    private static void saveStudents(List<Student> studentList, EntityManager entityManager) {
        for (Student student : studentList) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }
    }

    private static void deleteStudent(EntityManager entityManager, Student student) {
        entityManager.getTransaction().begin();

        Student foundStudent = entityManager.createQuery("FROM Student s WHERE s.studentId = :stId", Student.class)
                .setParameter("stId", student.getStudentId())
                .getSingleResult();
        entityManager.remove(foundStudent);
        entityManager.getTransaction().commit();
        System.out.println("Student deleted");
    }

}
