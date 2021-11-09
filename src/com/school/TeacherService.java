package com.school;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
public class TeacherService {
    List<Teacher> teachers = new ArrayList<>();

    public TeacherService() {
        teachers.add(new FullTimeTeacher("mahla", "hedayat", "123", 6000000));
        teachers.add(new FullTimeTeacher("ali", "akbari", "124", 4000000));
        teachers.add(new PartTimeTeacher("sahar", "kamali", "234", 15000, 576));
        teachers.add(new PartTimeTeacher("mohammad", "moradi", "235", 20000, 400));
    }

    public Optional<Teacher> findByPersonalCode(String personalCode){
        return teachers.stream().filter(i-> i.getPersonalNumber().equals(personalCode)).findAny();
    }

    public Teacher addSchoolByPersonalCode(String personalCode, School school){
        Optional<Teacher> foundTeacher = findByPersonalCode(personalCode);
        if(!foundTeacher.isPresent()){
            throw new RuntimeException("the teacher not found");
        }
        if(foundTeacher.get().getSchool() == null){
            foundTeacher.get().setSchool(new HashSet<>());
        }
        if(foundTeacher.get().getSchool().contains(school)){
            throw new RuntimeException("duplicate school!");
        }
        foundTeacher.get().getSchool().add(school);

        return foundTeacher.get();
    }

    public Teacher addCourseByPersonalCode(String personalCode, Course course){
        Optional<Teacher> foundTeacher = findByPersonalCode(personalCode);
        if(!foundTeacher.isPresent()){
            throw new RuntimeException("the teacher not found");
        }
        if(foundTeacher.get().getCourse().contains(course)){
            throw new RuntimeException("duplicate course!");
        }
        foundTeacher.get().getCourse().add(course);

        return foundTeacher.get();
    }

}
