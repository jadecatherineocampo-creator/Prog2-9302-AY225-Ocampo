import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentRecordSystem extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtID, txtName, txtGrade;
    private JButton btnAdd, btnDelete;
    
    public StudentRecordSystem() {
        setTitle("Student Record System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize components
        initializeComponents();
        
        // Load CSV data
        loadCSVData();
        
        setVisible(true);
    }
    
    private void initializeComponents() {
        // Create table model with columns
        String[] columns = {"ID", "Name", "Grade"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Create scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new JLabel("ID:"));
        txtID = new JTextField();
        inputPanel.add(txtID);
        
        inputPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);
        
        inputPanel.add(new JLabel("Grade:"));
        txtGrade = new JTextField();
        inputPanel.add(txtGrade);
        
        // Create buttons
        btnAdd = new JButton("Add");
        btnDelete = new JButton("Delete");
        
        inputPanel.add(btnAdd);
        inputPanel.add(btnDelete);
        
        // Add action listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });
        
        // Layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
    
    private void loadCSVData() {
        try (BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.csv"))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                // Split the line by comma
                String[] data = line.split(",");
                
                if (data.length == 3) {
                    // Add row to table
                    tableModel.addRow(new Object[]{data[0], data[1], data[2]});
                }
            }
            
            JOptionPane.showMessageDialog(this, 
                "CSV data loaded successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error reading CSV file: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addRecord() {
        String id = txtID.getText().trim();
        String name = txtName.getText().trim();
        String grade = txtGrade.getText().trim();
        
        // Validation
        if (id.isEmpty() || name.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all fields!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Validate ID is numeric
            Integer.parseInt(id);
            // Validate Grade is numeric
            Integer.parseInt(grade);
            
            // Add row to table
            tableModel.addRow(new Object[]{id, name, grade});
            
            // Clear text fields
            txtID.setText("");
            txtName.setText("");
            txtGrade.setText("");
            
            JOptionPane.showMessageDialog(this, 
                "Record added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "ID and Grade must be numeric values!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void deleteRecord() {
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a row to delete!", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this record?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, 
                "Record deleted successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentRecordSystem();
            }
        });
    }
}