package com.school;

import java.util.ArrayList;
import java.util.List;

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

}
