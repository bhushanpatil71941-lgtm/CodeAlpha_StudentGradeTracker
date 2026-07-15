package components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class TableRekap extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public TableRekap() {
        setLayout(new BorderLayout());
        
        String[] columns = {"Student Name", "Marks", "Grade", "Status"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        // Setting up the row sorter for the search filter functionality
        rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Performance Summary Report"));
        
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addRow(String name, double grade, String letterGrade, String status) {
        model.addRow(new Object[]{name, grade, letterGrade, status});
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public TableRowSorter<DefaultTableModel> getRowSorter() {
        return rowSorter;
    }

    public void clearTable() {
        model.setRowCount(0);
    }
}