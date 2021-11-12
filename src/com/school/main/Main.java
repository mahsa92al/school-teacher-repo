package com.school.main;

import com.school.Course;
import com.school.School;
import com.school.TeacherService;

public class Main {
    private static TeacherService teacherService;

    public static void main(String[] args) {

        teacherService = new TeacherService();

        Course course = new Course("math", 1);
        Course course1 = new Course("computer", 2);
        Course course2 = new Course("physic", 3);
        Course course3 = new Course("history", 4);
        Course course4 = new Course("art", 5);


        School school =  new School("maktab", 1);
        School school1 = new School("madani", 2);
        School school2 = new School("alavi", 3);
        School school3 = new School("razavi", 3);
        School school4 = new School("jalal", 1);
        School school5 = new School("diba", 1);

        System.out.println(teacherService.addSchoolByPersonalCode("123", school));
        System.out.println(teacherService.addSchoolByPersonalCode("123", school2));
        System.out.println(teacherService.addSchoolByPersonalCode("123", school3));
        System.out.println(teacherService.addSchoolByPersonalCode("124", school4));
        System.out.println(teacherService.addSchoolByPersonalCode("124", school5));
        System.out.println(teacherService.addSchoolByPersonalCode("234", school));
        System.out.println(teacherService.addSchoolByPersonalCode("234", school1));
        System.out.println(teacherService.addSchoolByPersonalCode("235", school3));
        System.out.println(teacherService.addSchoolByPersonalCode("235", school4));

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(teacherService.addCourseByPersonalCode("123", course));
        System.out.println(teacherService.addCourseByPersonalCode("234", course));
        System.out.println(teacherService.addCourseByPersonalCode("234", course1));
        System.out.println(teacherService.addCourseByPersonalCode("234", course2));

        System.out.println("-----------------------------------------------------------------------------");
        teacherService.listFullTimeTeacherGreaterThanAverageSalary().forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------------");
        teacherService.listTeacherTenYearsExperience().forEach((i,j)-> System.out.println(i + ": " + j));
        System.out.println("-----------------------------------------------------------------------------");
        teacherService.listTeacherBAPartTimeSchoolDegree2CourseMoreThan2().forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------------");
        teacherService.teachersSchoolSet().forEach(i-> System.out.println(i.getName()));
        System.out.println("-----------------------------------------------------------------------------");
        teacherService.teacherListBySchool().forEach((i,j)-> System.out.println(i.getName() + ": " + j));


    }

}
