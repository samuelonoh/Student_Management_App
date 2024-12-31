package gui.Panels;

import gui.StudentManagementGUI;
import data.StudentData;
import models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentPanel extends JPanel {

    private StudentData studentData;
    private JButton addStudentButton;
    private JButton updateStudentButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentPanel(StudentManagementGUI gui) {
        this.studentData = new StudentData();
        setLayout(new BorderLayout());

        // Create buttons
        addStudentButton = new JButton("Add Student");
        updateStudentButton = new JButton("Update Student");

        addStudentButton.addActionListener(e -> showAddStudentDialog());
        updateStudentButton.addActionListener(e -> showUpdateStudentDialog());

        // Table to display students
        String[] columns = {"ID", "Name"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addStudentButton);
        buttonPanel.add(updateStudentButton);

        // Add components to main panel
        add(buttonPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        refreshStudentTable();
    }

    private void showAddStudentDialog() {
        JTextField nameField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Student Name:"));
        panel.add(nameField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String studentName = nameField.getText();
            if (!studentName.isEmpty()) {
                int id = studentData.getStudents().size() + 1;
                studentData.addStudent(new Student(id, studentName));
                refreshStudentTable();
            } else {
                JOptionPane.showMessageDialog(this, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showUpdateStudentDialog() {
        int rowIndex = studentTable.getSelectedRow();
        if (rowIndex != -1) {
            int id = (int) studentTable.getValueAt(rowIndex, 0);
            String currentName = (String) studentTable.getValueAt(rowIndex, 1);
            JTextField nameField = new JTextField(currentName);
            JPanel panel = new JPanel(new GridLayout(1, 2));
            panel.add(new JLabel("New Name:"));
            panel.add(nameField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Update Student", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newName = nameField.getText();
                if (!newName.isEmpty()) {
                    studentData.updateStudent(id, newName);
                    refreshStudentTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void refreshStudentTable() {
        List<Student> students = studentData.getStudents();
        tableModel.setRowCount(0); // Clear the table

        for (Student student : students) {
            tableModel.addRow(new Object[]{student.getId(), student.getName()});
        }
    }
}
