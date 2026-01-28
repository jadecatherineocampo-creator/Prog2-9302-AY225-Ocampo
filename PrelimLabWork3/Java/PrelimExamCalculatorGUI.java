import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrelimExamCalculatorGUI extends JFrame {
    // GUI Components
    private JTextField attendanceField, lab1Field, lab2Field, lab3Field;
    private JTextArea resultArea;
    private JButton calculateButton, clearButton;
    
    public PrelimExamCalculatorGUI() {
        // Set up the frame
        setTitle("Prelim Exam Score Calculator");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 245));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(102, 126, 234));
        JLabel titleLabel = new JLabel("PRELIM EXAM SCORE CALCULATOR");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        
        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(102, 126, 234), 2),
                "Enter Your Grades",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(102, 126, 234)
            ),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);
        
        // Attendance
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        JLabel attendanceLabel = new JLabel("Number of Attendances:");
        attendanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(attendanceLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        attendanceField = new JTextField(15);
        attendanceField.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        inputPanel.add(attendanceField, gbc);
        
        // Lab Work 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.4;
        JLabel lab1Label = new JLabel("Lab Work 1 Grade:");
        lab1Label.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(lab1Label, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        lab1Field = new JTextField(15);
        lab1Field.setFont(new Font("Arial", Font.PLAIN, 14));
        lab1Field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        inputPanel.add(lab1Field, gbc);
        
        // Lab Work 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.4;
        JLabel lab2Label = new JLabel("Lab Work 2 Grade:");
        lab2Label.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(lab2Label, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        lab2Field = new JTextField(15);
        lab2Field.setFont(new Font("Arial", Font.PLAIN, 14));
        lab2Field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        inputPanel.add(lab2Field, gbc);
        
        // Lab Work 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.4;
        JLabel lab3Label = new JLabel("Lab Work 3 Grade:");
        lab3Label.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(lab3Label, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        lab3Field = new JTextField(15);
        lab3Field.setFont(new Font("Arial", Font.PLAIN, 14));
        lab3Field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        inputPanel.add(lab3Field, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        calculateButton = new JButton("Calculate Required Score");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(102, 126, 234));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        calculateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calculateButton.addActionListener(new CalculateButtonListener());
        
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(new Color(150, 150, 150));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(new ClearButtonListener());
        
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        
        // Result Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(102, 126, 234), 2),
                "Results",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(102, 126, 234)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        resultArea = new JTextArea(12, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setBackground(new Color(250, 250, 250));
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(null);
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10, 10));
        centerPanel.setBackground(new Color(240, 240, 245));
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        add(mainPanel);
        
        // Add Enter key listener to all text fields
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateButton.doClick();
                }
            }
        };
        
        attendanceField.addKeyListener(enterKeyListener);
        lab1Field.addKeyListener(enterKeyListener);
        lab2Field.addKeyListener(enterKeyListener);
        lab3Field.addKeyListener(enterKeyListener);
    }
    
    // Calculate button listener
    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Get input values
                double attendance = Double.parseDouble(attendanceField.getText().trim());
                double lab1 = Double.parseDouble(lab1Field.getText().trim());
                double lab2 = Double.parseDouble(lab2Field.getText().trim());
                double lab3 = Double.parseDouble(lab3Field.getText().trim());
                
                // Validate input ranges
                if (attendance < 0 || attendance > 100 || lab1 < 0 || lab1 > 100 || 
                    lab2 < 0 || lab2 > 100 || lab3 < 0 || lab3 > 100) {
                    JOptionPane.showMessageDialog(
                        PrelimExamCalculatorGUI.this,
                        "Please enter values between 0 and 100.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                // Computation Section
                // Lab Work Average = (Lab1 + Lab2 + Lab3) / 3
                double labWorkAverage = (lab1 + lab2 + lab3) / 3;
                
                // Class Standing = (Attendance × 0.40) + (Lab Work Average × 0.60)
                double classStanding = (attendance * 0.40) + (labWorkAverage * 0.60);
                
                // Required Prelim Exam Score Calculation
                // Prelim Grade = (Prelim Exam × 0.70) + (Class Standing × 0.30)
                // Solving for Prelim Exam:
                // Prelim Exam = (Prelim Grade - Class Standing × 0.30) / 0.70
                
                double requiredPrelimForPassing = (75 - (classStanding * 0.30)) / 0.70;
                double requiredPrelimForExcellent = (100 - (classStanding * 0.30)) / 0.70;
                
                // Build result text
                StringBuilder result = new StringBuilder();
                result.append("==============================================\n");
                result.append("           COMPUTATION RESULTS\n");
                result.append("==============================================\n\n");
                
                result.append(String.format("Attendance Score:        %.0f\n", attendance));
                result.append(String.format("Lab Work 1:              %.2f\n", lab1));
                result.append(String.format("Lab Work 2:              %.2f\n", lab2));
                result.append(String.format("Lab Work 3:              %.2f\n", lab3));
                result.append(String.format("Lab Work Average:        %.2f\n", labWorkAverage));
                result.append(String.format("Class Standing:          %.2f\n\n", classStanding));
                
                result.append("----------------------------------------------\n");
                result.append("       REQUIRED PRELIM EXAM SCORES\n");
                result.append("----------------------------------------------\n\n");
                
                result.append(String.format("To Pass (75):            %.2f\n", requiredPrelimForPassing));
                result.append(String.format("For Excellent (100):     %.2f\n\n", requiredPrelimForExcellent));
                
                result.append("==============================================\n");
                result.append("       STUDENT STANDING ANALYSIS\n");
                result.append("==============================================\n\n");
                
                // Evaluate student standing and provide remarks
                if (requiredPrelimForPassing <= 0) {
                    result.append("PASSING STATUS:\n");
                    result.append("Congratulations! You have already passed\n");
                    result.append("the Prelim period based on your current\n");
                    result.append("Class Standing.\n\n");
                } else if (requiredPrelimForPassing > 100) {
                    result.append("PASSING STATUS:\n");
                    result.append("Unfortunately, it is mathematically\n");
                    result.append("impossible to pass the Prelim period even\n");
                    result.append("with a perfect exam score. You need to\n");
                    result.append("improve your attendance and lab work.\n\n");
                } else {
                    result.append("PASSING STATUS:\n");
                    result.append(String.format("You need to score at least %.2f on the\n", requiredPrelimForPassing));
                    result.append("Prelim Exam to pass the Prelim period.\n\n");
                }
                
                if (requiredPrelimForExcellent <= 0) {
                    result.append("EXCELLENT STANDING:\n");
                    result.append("You have already achieved an Excellent\n");
                    result.append("grade!\n");
                } else if (requiredPrelimForExcellent > 100) {
                    result.append("EXCELLENT STANDING:\n");
                    result.append("An Excellent grade (100) is not achievable\n");
                    result.append("with the current Class Standing.\n");
                    result.append("Keep improving!\n");
                } else {
                    result.append("EXCELLENT STANDING:\n");
                    result.append(String.format("You need to score %.2f on the Prelim\n", requiredPrelimForExcellent));
                    result.append("Exam to achieve an Excellent grade.\n");
                }
                
                result.append("\n==============================================");
                
                // Display result
                resultArea.setText(result.toString());
                resultArea.setCaretPosition(0);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    PrelimExamCalculatorGUI.this,
                    "Please enter valid numeric values in all fields.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    // Clear button listener
    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            attendanceField.setText("");
            lab1Field.setText("");
            lab2Field.setText("");
            lab3Field.setText("");
            resultArea.setText("");
            attendanceField.requestFocus();
        }
    }
    
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PrelimExamCalculatorGUI gui = new PrelimExamCalculatorGUI();
                gui.setVisible(true);
            }
        });
    }
}