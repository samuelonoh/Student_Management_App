package gui.Panels;

import gui.StudentManagementGUI;
import data.StudentData;
import models.Course;
import models.Student;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GradePanel extends JPanel {

    private StudentManagementGUI gui;
    private StudentData studentData;
    private JComboBox<Student> studentComboBox;
    private JComboBox<Course> courseComboBox;
    private JButton assignGradeButton;
    private Map<Student, Map<Course, String>> grades = new HashMap<>();

    public GradePanel(StudentManagementGUI gui) {
        this.gui = gui;
        this.studentData = new StudentData();

        setLayout(new GridLayout(3, 2));

        // Add combo boxes for selecting student and course
        studentComboBox = new JComboBox<>();
        courseComboBox = new JComboBox<>();

        studentData.getStudents().forEach(student -> studentComboBox.addItem(student));

        // Load courses from your data model
        Course course1 = new Course(1, "Math", "Mathematics");
        Course course2 = new Course(2, "History", "World History");

        courseComboBox.addItem(course1);
        courseComboBox.addItem(course2);

        // Assign Grade Button
        assignGradeButton = new JButton("Assign Grade");

        // Add listeners
        assignGradeButton.addActionListener(e -> assignGrade());

        // Add components to layout
        add(new JLabel("Select Student:"));
        add(studentComboBox);
        add(new JLabel("Select Course:"));
        add(courseComboBox);
        add(assignGradeButton);
    }

    private void assignGrade() {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();

        if (selectedStudent != null && selectedCourse != null) {
            String grade = JOptionPane.showInputDialog(this, "Enter Grade:");
            grades.computeIfAbsent(selectedStudent, k -> new HashMap<>()).put(selectedCourse, grade);
            JOptionPane.showMessageDialog(this, "Grade assigned: " + grade);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student and a course", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
