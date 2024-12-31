package utils;

import javax.swing.*;

public class DialogUtils {

    public static void showErrorDialog(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}