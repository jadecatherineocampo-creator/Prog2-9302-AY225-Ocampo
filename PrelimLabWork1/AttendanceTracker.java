import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * AttendanceTracker - A Java Swing application for tracking student attendance
 * This application captures attendance name, course/year, timestamp, and generates an e-signature
 * Now includes a history tab to view all saved attendance records
 */
public class AttendanceTracker {
    
    // Main frame for the application
    private JFrame frame;
    
    // Text fields for user input and generated data
    private JTextField nameField;
    private JTextField courseField;
    private JTextField timeInField;
    private JTextField eSignatureField;
    
    // Button to submit attendance
    private JButton submitButton;
    
    // Tabbed pane for organizing attendance form and history
    private JTabbedPane tabbedPane;
    
    // Table for displaying attendance history
    private JTable historyTable;
    private DefaultTableModel tableModel;
    
    // List to store attendance records
    private ArrayList<AttendanceRecord> attendanceRecords;
    
    /**
     * Inner class to represent an attendance record
     */
    private class AttendanceRecord {
        String name;
        String course;
        String timeIn;
        String eSignature;
        
        public AttendanceRecord(String name, String course, String timeIn, String eSignature) {
            this.name = name;
            this.course = course;
            this.timeIn = timeIn;
            this.eSignature = eSignature;
        }
    }
    
    /**
     * Constructor - initializes and displays the attendance tracker window
     */
    public AttendanceTracker() {
        attendanceRecords = new ArrayList<>();
        initializeUI();
    }
    
    /**
     * Initializes the user interface components
     */
    private void initializeUI() {
        // Create the main frame
        frame = new JFrame("Attendance Tracker");
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Create and add the attendance form panel
        JPanel formPanel = createFormPanel();
        tabbedPane.addTab("Attendance Form", formPanel);
        
        // Create and add the history panel
        JPanel historyPanel = createHistoryPanel();
        tabbedPane.addTab("History", historyPanel);
        
        // Add tabbed pane to frame
        frame.add(tabbedPane, BorderLayout.CENTER);
        
        // Center the frame on screen
        frame.setLocationRelativeTo(null);
        
        // Make frame visible
        frame.setVisible(true);
    }
    
    /**
     * Creates the attendance form panel
     */
    private JPanel createFormPanel() {
        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title label
        JLabel titleLabel = new JLabel("Student Attendance System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        
        // Reset gridwidth for subsequent components
        gbc.gridwidth = 1;
        
        // Attendance Name field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel nameLabel = new JLabel("Attendance Name:");
        mainPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        nameField = new JTextField(20);
        mainPanel.add(nameField, gbc);
        
        // Course/Year field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel courseLabel = new JLabel("Course/Year:");
        mainPanel.add(courseLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        courseField = new JTextField(20);
        mainPanel.add(courseField, gbc);
        
        // Time In field (auto-generated, non-editable)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        JLabel timeLabel = new JLabel("Time In:");
        mainPanel.add(timeLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        timeInField = new JTextField(20);
        timeInField.setEditable(false);
        timeInField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(timeInField, gbc);
        
        // E-Signature field (auto-generated, non-editable)
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        JLabel signatureLabel = new JLabel("E-Signature:");
        mainPanel.add(signatureLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        eSignatureField = new JTextField(20);
        eSignatureField.setEditable(false);
        eSignatureField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(eSignatureField, gbc);
        
        // Submit button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton = new JButton("Generate Attendance Record");
        submitButton.setFont(new Font("Arial", Font.BOLD, 12));
        submitButton.addActionListener(e -> generateAttendanceRecord());
        mainPanel.add(submitButton, gbc);
        
        return mainPanel;
    }
    
    /**
     * Creates the history panel with a table to display all attendance records
     */
    private JPanel createHistoryPanel() {
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create title label
        JLabel titleLabel = new JLabel("Attendance History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        historyPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create table model with column names
        String[] columnNames = {"Name", "Course/Year", "Time In", "E-Signature"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        // Create table
        historyTable = new JTable(tableModel);
        historyTable.setFont(new Font("Arial", Font.PLAIN, 12));
        historyTable.setRowHeight(25);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(historyTable);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton clearButton = new JButton("Clear History");
        clearButton.addActionListener(e -> clearHistory());
        buttonPanel.add(clearButton);
        historyPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return historyPanel;
    }
    
    /**
     * Generates attendance record with timestamp and e-signature
     * Validates input fields before generation and saves to history
     */
    private void generateAttendanceRecord() {
        // Validate input fields
        String name = nameField.getText().trim();
        String course = courseField.getText().trim();
        
        if (name.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Please fill in all required fields (Name and Course/Year)", 
                "Input Required", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Generate current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeIn = now.format(formatter);
        timeInField.setText(timeIn);
        
        // Generate unique e-signature using UUID
        String eSignature = UUID.randomUUID().toString();
        eSignatureField.setText(eSignature);
        
        // Save record to history
        AttendanceRecord record = new AttendanceRecord(name, course, timeIn, eSignature);
        attendanceRecords.add(record);
        
        // Add to table
        Object[] rowData = {name, course, timeIn, eSignature};
        tableModel.addRow(rowData);
        
        // Show success message
        JOptionPane.showMessageDialog(frame, 
            "Attendance record generated and saved successfully!\n\nName: " + name + 
            "\nCourse: " + course + "\nTime In: " + timeIn, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Clear form fields for next entry
        nameField.setText("");
        courseField.setText("");
        timeInField.setText("");
        eSignatureField.setText("");
        
        // Switch to history tab to show the new record
        tabbedPane.setSelectedIndex(1);
    }
    
    /**
     * Clears all attendance history after confirmation
     */
    private void clearHistory() {
        if (attendanceRecords.isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "No records to clear.", 
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to clear all attendance records?", 
            "Confirm Clear", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            attendanceRecords.clear();
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(frame, 
                "All attendance records have been cleared.", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Main method - entry point of the application
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> new AttendanceTracker());
    }
}