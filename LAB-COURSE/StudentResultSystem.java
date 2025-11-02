import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentResultSystem {
    private JFrame mainFrame;
    private JPanel inputPanel;
    private JTextField nameField;
    private JTextField rollNoField;
    private JTextField[] subjectFields;
    private String[] subjects = {"Mathematics", "Physics", "Chemistry", "English", "Computer Science"};
    private JButton calculateButton;

    public StudentResultSystem() {
        createGUI();
    }

    private void createGUI() {
        // Main frame setup
        mainFrame = new JFrame("Student Result System");
        mainFrame.setSize(500, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(null);

        // Title label
        JLabel titleLabel = new JLabel("Student Marks Entry Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 0, 139)); // Dark blue
        mainFrame.add(titleLabel, BorderLayout.NORTH);

        // Input panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(subjects.length + 3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Student name field
        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // Roll number field
        inputPanel.add(new JLabel("Roll Number:"));
        rollNoField = new JTextField();
        inputPanel.add(rollNoField);

        // Empty row for spacing
        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));

        // Subject marks fields
        inputPanel.add(new JLabel("Subject"));
        inputPanel.add(new JLabel("Marks (0-100)"));

        subjectFields = new JTextField[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            inputPanel.add(new JLabel(subjects[i] + ":"));
            subjectFields[i] = new JTextField();
            inputPanel.add(subjectFields[i]);
        }

        mainFrame.add(inputPanel, BorderLayout.CENTER);

        // Calculate button
        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Calculate Result");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(0, 100, 0)); // Dark green
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayResult();
            }
        });

        buttonPanel.add(calculateButton);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }

    private void calculateAndDisplayResult() {
        // Validate inputs
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter student name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (rollNoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter roll number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double[] marks = new double[subjects.length];
        boolean valid = true;

        for (int i = 0; i < subjects.length; i++) {
            try {
                double mark = Double.parseDouble(subjectFields[i].getText().trim());
                if (mark < 0 || mark > 100) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Marks for " + subjects[i] + " must be between 0 and 100.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    valid = false;
                    break;
                }
                marks[i] = mark;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Please enter valid marks for " + subjects[i] + ".",
                        "Error", JOptionPane.ERROR_MESSAGE);
                valid = false;
                break;
            }
        }

        if (valid) {
            displayResult(marks);
        }
    }

    private void displayResult(double[] marks) {
        // Create result window
        JFrame resultFrame = new JFrame("Student Result");
        resultFrame.setSize(500, 400);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setLayout(new BorderLayout());
        resultFrame.setLocationRelativeTo(mainFrame);

        // Calculate results
        double totalMarks = 0;
        for (double mark : marks) {
            totalMarks += mark;
        }
        double percentage = (totalMarks / (subjects.length * 100)) * 100;
        String grade = calculateGrade(percentage);

        // Result panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(subjects.length + 6, 2, 10, 5));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Student information
        resultPanel.add(new JLabel("Student Name:"));
        resultPanel.add(new JLabel(nameField.getText()));

        resultPanel.add(new JLabel("Roll Number:"));
        resultPanel.add(new JLabel(rollNoField.getText()));

        resultPanel.add(new JLabel("")); // Empty space
        resultPanel.add(new JLabel(""));

        // Subject-wise marks
        resultPanel.add(new JLabel("Subject", SwingConstants.CENTER));
        resultPanel.add(new JLabel("Marks Obtained", SwingConstants.CENTER));

        for (int i = 0; i < subjects.length; i++) {
            resultPanel.add(new JLabel(subjects[i] + ":"));
            resultPanel.add(new JLabel(String.format("%.2f/100", marks[i])));
        }

        resultPanel.add(new JLabel("")); // Empty space
        resultPanel.add(new JLabel(""));

        // Summary
        resultPanel.add(new JLabel("Total Marks:"));
        resultPanel.add(new JLabel(String.format("%.2f/500", totalMarks)));

        resultPanel.add(new JLabel("Percentage:"));
        resultPanel.add(new JLabel(String.format("%.2f%%", percentage)));

        resultPanel.add(new JLabel("Grade:"));
        JLabel gradeLabel = new JLabel(grade);
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gradeLabel.setForeground(getGradeColor(grade));
        resultPanel.add(gradeLabel);

        resultFrame.add(resultPanel, BorderLayout.CENTER);

        // Close button
        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> resultFrame.dispose());
        buttonPanel.add(closeButton);
        resultFrame.add(buttonPanel, BorderLayout.SOUTH);

        resultFrame.setVisible(true);
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B+";
        if (percentage >= 60) return "B";
        if (percentage >= 50) return "C";
        if (percentage >= 40) return "D";
        return "F";
    }

    private Color getGradeColor(String grade) {
        switch (grade) {
            case "A+": return new Color(0, 100, 0); // Dark green
            case "A": return new Color(0, 128, 0); // Green
            case "B+": return new Color(0, 0, 139); // Dark blue
            case "B": return new Color(0, 0, 255); // Blue
            case "C": return new Color(139, 0, 0); // Dark red
            case "D": return new Color(178, 34, 34); // Firebrick
            case "F": return Color.RED;
            default: return Color.BLACK;
        }
    }

    public static void main(String[] args) {
        // Run on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentResultSystem();
            }
        });
    }
}