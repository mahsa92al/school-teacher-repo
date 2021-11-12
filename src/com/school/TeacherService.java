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
        if(foundTeacher.isEmpty()){
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
                .map(Teacher::calculateSalary).reduce(0.0, (k, l)-> k+l); //reduce(0.0, Double::sum)
        int count = (int) teachers.stream().filter(m-> m.getType().equals(TeacherType.FULL_TIME)).count();
        Double averageSalary = sum / count;
        return teachers.stream().filter(n-> n.getType().equals(TeacherType.FULL_TIME))
                .filter(p-> p.calculateSalary() > averageSalary).collect(Collectors.toList());
    }

    public Map<TeacherType , List<Teacher>> listTeacherTenYearsExperience(){
        return teachers.stream().filter(i-> i.getExperienceYear() == 10).collect(Collectors.groupingBy(Teacher::getType));
    }

    public List<Teacher> listTeacherBAPartTimeSchoolDegree2CourseMoreThan2(){
        return teachers.stream().filter(i-> i.getType().equals(TeacherType.PART_TIME))
                .filter(j-> j.getDegree().equals(Degree.BS))
                .filter(k-> k.getSchool().stream().anyMatch(l-> l.getDegree() == 2))
                .filter(m-> (long) m.getCourse().size() >2).collect(Collectors.toList());
    }

    public Set<School> teachersSchoolSet(){
        return teachers.stream().map(Teacher::getSchool).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public Map<School, List<Teacher>> teacherListBySchool(){
        Set schools = teachersSchoolSet();
        Map<School, List<Teacher>> teacherSchoolMap = new HashMap<>();
        schools.forEach(school-> teacherSchoolMap.put((School) school, new ArrayList<>()));
         teachers.forEach(teacher -> {
            schools.stream()
                    .filter(school-> teacher.getSchool().contains(school))
                    .forEach(school -> teacherSchoolMap.get(school).add(teacher));
        });
         return teacherSchoolMap;
    }

}
