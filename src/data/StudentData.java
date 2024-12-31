package data;

import models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private final List<Student> students;

    public StudentData() {
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(int id, String newName) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(newName);
                break;
            }
        }
    }
}
