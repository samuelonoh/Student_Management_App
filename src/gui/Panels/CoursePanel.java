package gui.Panels;

import gui.StudentManagementGUI;
import data.StudentData;
import models.Course;
import models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoursePanel extends JPanel {

    private StudentManagementGUI gui;
    private StudentData studentData; // Reference to student data
    private List<Course> courses = new ArrayList<>(); // Centralized course list
    private Map<Student, List<Course>> enrollments = new HashMap<>(); // Enrollment map

    private JButton addCourseButton;
    private JButton enrollButton;

    private JTable courseTable;
    private DefaultTableModel tableModel;
    private JComboBox<Student> studentComboBox;
    private JComboBox<Course> courseComboBox;

    public CoursePanel(StudentManagementGUI gui) {
        this.gui = gui;
        this.studentData = new StudentData(); // Replace with shared data if applicable

        setLayout(new BorderLayout());

        // Buttons
        addCourseButton = new JButton("Add Course");
        enrollButton = new JButton("Enroll Student");

        addCourseButton.addActionListener(e -> showAddCourseDialog());
        enrollButton.addActionListener(e -> enrollStudentInCourse());

        // Table to display courses
        String[] columns = { "ID", "Course Name" };
        tableModel = new DefaultTableModel(columns, 0);
        courseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(courseTable);

        // Combo boxes
        studentComboBox = new JComboBox<>();
        courseComboBox = new JComboBox<>();

        populateComboBoxes();

        // Panels
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(addCourseButton);
        buttonPanel.add(enrollButton);

        JPanel comboBoxPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        comboBoxPanel.add(new JLabel("Select Student:"));
        comboBoxPanel.add(studentComboBox);
        comboBoxPanel.add(new JLabel("Select Course:"));
        comboBoxPanel.add(courseComboBox);

        // Add components to panel
        add(buttonPanel, BorderLayout.NORTH);
        add(comboBoxPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);

        refreshCourseTable();
    }

    private void populateComboBoxes() {
        // Populate students
        for (Student student : studentData.getStudents()) {
            studentComboBox.addItem(student);
        }

        // Populate courses
        for (Course course : courses) {
            courseComboBox.addItem(course);
        }
    }

    private void showAddCourseDialog() {
        JTextField courseNameField = new JTextField();
        JTextField descriptionField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Course Name:"));
        panel.add(courseNameField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Course", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String courseName = courseNameField.getText();
            String description = descriptionField.getText();

            if (!courseName.isEmpty() && !description.isEmpty()) {
                int id = courses.size() + 1; // Course ID based on size
                Course newCourse = new Course(id, courseName, description);
                courses.add(newCourse);
                courseComboBox.addItem(newCourse);
                refreshCourseTable();
            } else {
                JOptionPane.showMessageDialog(this, "Course name and description cannot be empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void enrollStudentInCourse() {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();

        if (selectedStudent != null && selectedCourse != null) {
            enrollments.computeIfAbsent(selectedStudent, k -> new ArrayList<>()).add(selectedCourse);
            JOptionPane.showMessageDialog(this, "Student enrolled in " + selectedCourse.getCourseName());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student and a course", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshCourseTable() {
        tableModel.setRowCount(0);
        for (Course course : courses) {
            tableModel.addRow(new Object[] { course.getId(), course.getCourseName() });
        }
    }
}
