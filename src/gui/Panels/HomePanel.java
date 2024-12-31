package gui.Panels;

import gui.StudentManagementGUI;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    public HomePanel(StudentManagementGUI gui) {
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton manageStudentsBtn = new JButton("Manage Students");
        JButton courseEnrollmentBtn = new JButton("Course Enrollment");
        JButton gradeManagementBtn = new JButton("Grade Management");

        manageStudentsBtn.addActionListener(e -> gui.switchToPanel("Student Management"));
        courseEnrollmentBtn.addActionListener(e -> gui.switchToPanel("Course Enrollment"));
        gradeManagementBtn.addActionListener(e -> gui.switchToPanel("Grade Management"));

        add(manageStudentsBtn);
        add(courseEnrollmentBtn);
        add(gradeManagementBtn);
    }
}
