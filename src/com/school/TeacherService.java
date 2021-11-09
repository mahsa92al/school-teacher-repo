package com.school;

import com.school.enums.Degree;
import com.school.enums.TeacherType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mahsa Alikhani m-58
 */
public class TeacherService {
    List<Teacher> teachers = new ArrayList<>();

    public TeacherService() {
        teachers.add(new FullTimeTeacher("mahla", "hedayat", "123", Degree.PHD, TeacherType.FULL_TIME,10, 6000000));
        teachers.add(new FullTimeTeacher("ali", "akbari", "124", Degree.MA, TeacherType.FULL_TIME, 7, 4000000));
        teachers.add(new PartTimeTeacher("sahar", "kamali", "234", Degree.BS, TeacherType.PART_TIME, 5, 15000, 576));
        teachers.add(new PartTimeTeacher("mohammad", "moradi", "235", Degree.MA, TeacherType.PART_TIME, 10, 20000, 400));
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

    public List<Teacher> listFullTimeTeacherGreaterThanAverageSalary(){
        Double sum = teachers.stream().filter(i-> i.getType().equals(TeacherType.FULL_TIME))
                .map(j-> j.calculateSalary()).reduce(0.0, (k,l)-> k+l);
        int count = (int) teachers.stream().filter(m-> m.getType().equals(TeacherType.FULL_TIME)).count();
        Double averageSalary = sum / count;
        return teachers.stream().filter(n-> n.getType().equals(TeacherType.FULL_TIME))
                .filter(p-> p.calculateSalary() > averageSalary).collect(Collectors.toList());
    }

    public Map<TeacherType , List<Teacher>> listTeacherTenYearsExperience(){
        return teachers.stream().filter(i-> i.getExperienceYear() == 10).collect(Collectors.groupingBy(j-> j.getType()));
    }

}
