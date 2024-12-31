package gui;

import gui.Panels.HomePanel;
import gui.Panels.StudentPanel;
import gui.Panels.CoursePanel;
import gui.Panels.GradePanel;

import javax.swing.*;
import java.awt.*;

public class StudentManagementGUI {

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public StudentManagementGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new HomePanel(this), "Home");
        mainPanel.add(new StudentPanel(this), "Student Management");
        mainPanel.add(new CoursePanel(this), "Course Enrollment");
        mainPanel.add(new GradePanel(this), "Grade Management");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void switchToPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}
