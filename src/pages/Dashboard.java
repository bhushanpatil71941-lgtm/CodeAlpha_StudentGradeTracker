package pages;

import components.Card;
import components.TableRekap;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Student;

public class Dashboard extends JFrame {
    private ArrayList<Student> studentList = new ArrayList<>();
    private JTextField txtName, txtGrade, txtSearch;
    private Card cardTotal, cardAverage, cardHighest, cardLowest;
    private TableRekap tableRekap;

    public Dashboard() {
        setTitle("Student Grade Tracker - Java Professional Dashboard");
        setSize(1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 246, 248));
        setLayout(new BorderLayout(15, 15));

        //Header Section
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 2, 2));
        headerPanel.setBackground(getContentPane().getBackground());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));
        
        JLabel title = new JLabel("Student Grade Tracker");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        JLabel subTitle = new JLabel("Java Programming Task 1 - Advanced Modular GUI");
        subTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subTitle.setForeground(Color.GRAY);
        
        headerPanel.add(title);
        headerPanel.add(subTitle);
        
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(getContentPane().getBackground());
        topPanel.add(headerPanel, BorderLayout.NORTH);

        //Dashboard
        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        cardsPanel.setBackground(getContentPane().getBackground());
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        cardTotal = new Card("Total Students", "0", new Color(230, 242, 255), new Color(0, 102, 204));
        cardAverage = new Card("Average Score", "0.00", new Color(230, 247, 230), new Color(0, 153, 51));
        cardHighest = new Card("Highest Score", "0.0", new Color(245, 230, 255), new Color(153, 0, 255));
        cardLowest = new Card("Lowest Score", "0.0", new Color(255, 230, 230), new Color(204, 0, 0));

        cardsPanel.add(cardTotal);
        cardsPanel.add(cardAverage);
        cardsPanel.add(cardHighest);
        cardsPanel.add(cardLowest);
        
        topPanel.add(cardsPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Main Content Section
        JPanel mainContent = new JPanel(new BorderLayout(20, 0));
        mainContent.setBackground(getContentPane().getBackground());
        mainContent.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        //Input Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblName = new JLabel("Student Name:");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblName.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        txtName = new JTextField();
        txtName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtName.setPreferredSize(new Dimension(240, 35));
        txtName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblGrade = new JLabel("Marks Obtained (0-100):");
        lblGrade.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblGrade.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        txtGrade = new JTextField();
        txtGrade.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtGrade.setPreferredSize(new Dimension(240, 35));
        txtGrade.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add Button
        JButton btnAdd = new JButton("Add Data");
        btnAdd.setBackground(new Color(0, 102, 204));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAdd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnAdd.setPreferredSize(new Dimension(240, 40));
        btnAdd.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e -> addStudentData());

        // Reset Button
        JButton btnReset = new JButton("Reset All");
        btnReset.setBackground(new Color(108, 117, 125));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnReset.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnReset.setPreferredSize(new Dimension(240, 35));
        btnReset.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(e -> resetDashboard());

        formPanel.add(lblName);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtName);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(lblGrade);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtGrade);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(btnAdd);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(btnReset);

        JPanel formWrapper = new JPanel(new BorderLayout());
        formWrapper.setBackground(getContentPane().getBackground());
        formWrapper.add(formPanel, BorderLayout.NORTH);
        formWrapper.setPreferredSize(new Dimension(260, 450));

        mainContent.add(formWrapper, BorderLayout.WEST);

        //Search Bar
        JPanel tableContainer = new JPanel(new BorderLayout(0, 10));
        tableContainer.setBackground(getContentPane().getBackground());
        
        // Live Search Panel
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(getContentPane().getBackground());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        JLabel lblSearch = new JLabel("Search Student:");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(300, 32));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { searchTable(); }
            @Override
            public void removeUpdate(DocumentEvent e) { searchTable(); }
            @Override
            public void changedUpdate(DocumentEvent e) { searchTable(); }
        });
        
        searchPanel.add(lblSearch, BorderLayout.WEST);
        searchPanel.add(txtSearch, BorderLayout.CENTER);
        
        tableContainer.add(searchPanel, BorderLayout.NORTH);
        
        tableRekap = new TableRekap();
        tableContainer.add(tableRekap, BorderLayout.CENTER);
        
        // Action Buttons Panel
        JPanel tableActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        tableActionPanel.setBackground(getContentPane().getBackground());
        
        JButton btnRemove = new JButton("Remove Selected Student");
        btnRemove.setBackground(new Color(220, 53, 69));
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRemove.setPreferredSize(new Dimension(200, 35));
        btnRemove.setFocusPainted(false);
        btnRemove.addActionListener(e -> removeSelectedStudent());
        
        JButton btnPrint = new JButton("Print Report");
        btnPrint.setBackground(new Color(40, 167, 69));
        btnPrint.setForeground(Color.WHITE);
        btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnPrint.setPreferredSize(new Dimension(150, 35));
        btnPrint.setFocusPainted(false);
        btnPrint.addActionListener(e -> printTableReport());
        
        tableActionPanel.add(btnRemove);
        tableActionPanel.add(btnPrint);
        
        tableContainer.add(tableActionPanel, BorderLayout.SOUTH);
        mainContent.add(tableContainer, BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);
    }

    private void searchTable() {
        String text = txtSearch.getText().trim();
        if (text.isEmpty()) {
            tableRekap.getRowSorter().setRowFilter(null);
        } else {
            tableRekap.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    private void addStudentData() {
        String name = txtName.getText().trim();
        String gradeStr = txtGrade.getText().trim();

        if (name.isEmpty() || gradeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double grade = Double.parseDouble(gradeStr);
            if (grade < 0 || grade > 100) {
                JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Grading
            String letterGrade;
            String status = "PASS";

            if (grade >= 90) {
                letterGrade = "O";
            } else if (grade >= 80) {
                letterGrade = "A+";
            } else if (grade >= 70) {
                letterGrade = "A";
            } else if (grade >= 60) {
                letterGrade = "B+";
            } else if (grade >= 55) {
                letterGrade = "B";
            } else if (grade >= 50) {
                letterGrade = "C";
            } else if (grade >= 40) {
                letterGrade = "P";
            } else {
                letterGrade = "F";
                status = "FAIL";
            }

            studentList.add(new Student(name, grade));
            tableRekap.addRow(name, grade, letterGrade, status);

            txtName.setText("");
            txtGrade.setText("");
            txtName.requestFocus();

            updateAnalytics();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric value for marks!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSelectedStudent() {
        JTable table = tableRekap.getTable();
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student row from the table to remove!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int modelRow = table.convertRowIndexToModel(selectedRow);
        
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this student record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            studentList.remove(modelRow);
            tableRekap.getTableModel().removeRow(modelRow);
            updateAnalytics();
        }
    }

    private void printTableReport() {
        try {
            boolean complete = tableRekap.getTable().print(JTable.PrintMode.FIT_WIDTH, 
                new java.text.MessageFormat("Student Performance Summary Report"), 
                new java.text.MessageFormat("Page {0}"));
            if (complete) {
                JOptionPane.showMessageDialog(this, "Printing Job Completed Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Printing Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetDashboard() {
        if (studentList.isEmpty()) return;
        
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all data?", "Confirm Reset", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            studentList.clear();
            tableRekap.clearTable();
            cardTotal.setValue("0");
            cardAverage.setValue("0.00");
            cardHighest.setValue("0.0");
            cardLowest.setValue("0.0");
            txtName.setText("");
            txtGrade.setText("");
            txtSearch.setText("");
        }
    }

    private void updateAnalytics() {
        int total = studentList.size();
        if (total == 0) {
            cardTotal.setValue("0");
            cardAverage.setValue("0.00");
            cardHighest.setValue("0.0");
            cardLowest.setValue("0.0");
            return;
        }

        double sum = 0;
        double highest = studentList.get(0).getGrade();
        double lowest = studentList.get(0).getGrade();

        for (Student s : studentList) {
            sum += s.getGrade();
            if (s.getGrade() > highest) highest = s.getGrade();
            if (s.getGrade() < lowest) lowest = s.getGrade();
        }

        double average = sum / total;

        cardTotal.setValue(String.valueOf(total));
        cardAverage.setValue(String.format("%.2f", average));
        cardHighest.setValue(String.valueOf(highest));
        cardLowest.setValue(String.valueOf(lowest));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
    }
}
