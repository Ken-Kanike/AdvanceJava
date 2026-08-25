package awt_and_swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * <h1>Student Management System Desktop App</h1>
 * <p>
 * A complete, production-grade Swing GUI mini-project demonstrating:
 * <ul>
 *   <li>Modern UI Design with cohesive color palette and card styling</li>
 *   <li><code>DefaultTableModel</code> with dynamic Row Addition, Editing, and Deletion</li>
 *   <li>Input validation with feedback alerts via <code>JOptionPane</code></li>
 *   <li>Real-time table search & regex filtering via <code>TableRowSorter</code></li>
 *   <li>Form reset, selection synchronization, and total metrics calculation</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class StudentManagementSystemGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementFrame());
    }
}

class StudentManagementFrame extends JFrame {
    private JTextField txtId, txtName, txtEmail, txtCourse, txtGrade, txtSearch;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JLabel lblTotalCount;

    public StudentManagementFrame() {
        setTitle("Student Management System - Java Swing Enterprise Desktop Edition");
        setSize(1000, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 247, 250));

        // 1. Top Header Banner
        add(createHeaderPanel(), BorderLayout.NORTH);

        // 2. Center Content Split: Left Form Card, Right Table Card
        JPanel mainContent = new JPanel(new BorderLayout(15, 15));
        mainContent.setBorder(new EmptyBorder(10, 15, 10, 15));
        mainContent.setBackground(new Color(245, 247, 250));

        mainContent.add(createFormPanel(), BorderLayout.WEST);
        mainContent.add(createTablePanel(), BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);

        // 3. Bottom Status Bar
        add(createStatusBar(), BorderLayout.SOUTH);

        updateTotalCount();
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 82, 118));
        header.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel title = new JLabel("🎓 Student Record & Enrollment System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Advance Java Swing Application with Interactive Data Management");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(new Color(214, 234, 248));

        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.SOUTH);
        return header;
    }

    private JPanel createFormPanel() {
        JPanel formCard = new JPanel(new GridBagLayout());
        formCard.setPreferredSize(new Dimension(340, 500));
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel formTitle = new JLabel("Manage Student Form");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formTitle.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formCard.add(formTitle, gbc);

        // Form Fields
        gbc.gridwidth = 1;
        addFormRow(formCard, gbc, 1, "Student ID:", txtId = new JTextField(12));
        addFormRow(formCard, gbc, 2, "Full Name:", txtName = new JTextField(12));
        addFormRow(formCard, gbc, 3, "Email:", txtEmail = new JTextField(12));
        addFormRow(formCard, gbc, 4, "Course:", txtCourse = new JTextField(12));
        addFormRow(formCard, gbc, 5, "Grade (%):", txtGrade = new JTextField(12));

        // Action Buttons
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        btnPanel.setBackground(Color.WHITE);

        JButton btnAdd = createStyledButton("Add Record", new Color(39, 174, 96));
        JButton btnUpdate = createStyledButton("Update", new Color(41, 128, 185));
        JButton btnDelete = createStyledButton("Delete", new Color(231, 76, 60));
        JButton btnClear = createStyledButton("Reset", new Color(127, 140, 141));

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.insets = new Insets(15, 6, 6, 6);
        formCard.add(btnPanel, gbc);

        // Button Event Actions
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearForm());

        return formCard;
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field) {
        gbc.gridy = row; gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(field, gbc);
    }

    private JPanel createTablePanel() {
        JPanel tableCard = new JPanel(new BorderLayout(10, 10));
        tableCard.setBackground(Color.WHITE);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230), 1),
            new EmptyBorder(12, 12, 12, 12)
        ));

        // Search Bar
        JPanel searchPanel = new JPanel(new BorderLayout(8, 8));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(new JLabel("🔍 Filter / Search Records:"), BorderLayout.WEST);
        txtSearch = new JTextField();
        searchPanel.add(txtSearch, BorderLayout.CENTER);
        tableCard.add(searchPanel, BorderLayout.NORTH);

        // Data Table
        String[] columnNames = {"ID", "Full Name", "Email", "Course", "Grade (%)"};
        Object[][] sampleData = {
            {"STU-1001", "Alice Johnson", "alice.johnson@example.com", "Computer Science", "94.5"},
            {"STU-1002", "Bob Smith", "bob.smith@example.com", "Information Tech", "88.0"},
            {"STU-1003", "Charlie Davis", "charlie.d@example.com", "Software Engg", "91.2"},
            {"STU-1004", "Diana Prince", "diana.prince@example.com", "Cyber Security", "96.8"},
            {"STU-1005", "Ethan Hunt", "ethan.hunt@example.com", "Data Analytics", "85.4"}
        };

        tableModel = new DefaultTableModel(sampleData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Row editing via form synchronization
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(26);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        studentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        studentTable.getTableHeader().setBackground(new Color(236, 240, 241));
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        rowSorter = new TableRowSorter<>(tableModel);
        studentTable.setRowSorter(rowSorter);

        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            private void filter() {
                String query = txtSearch.getText().trim();
                if (query.isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
                }
            }
        });

        // Table Row Click Synchronization
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = studentTable.convertRowIndexToModel(selectedRow);
                    txtId.setText(tableModel.getValueAt(modelRow, 0).toString());
                    txtName.setText(tableModel.getValueAt(modelRow, 1).toString());
                    txtEmail.setText(tableModel.getValueAt(modelRow, 2).toString());
                    txtCourse.setText(tableModel.getValueAt(modelRow, 3).toString());
                    txtGrade.setText(tableModel.getValueAt(modelRow, 4).toString());
                }
            }
        });

        tableCard.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        return tableCard;
    }

    private JPanel createStatusBar() {
        JPanel status = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 8));
        status.setBackground(new Color(236, 240, 241));
        lblTotalCount = new JLabel("Total Registered Students: 0");
        lblTotalCount.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTotalCount.setForeground(new Color(44, 62, 80));
        status.add(lblTotalCount);
        return status;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        return btn;
    }

    private void addStudent() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String course = txtCourse.getText().trim();
        String grade = txtGrade.getText().trim();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty() || course.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields before adding.", "Validation Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{id, name, email, course, grade});
        clearForm();
        updateTotalCount();
        JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Select a record from the table to update.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = studentTable.convertRowIndexToModel(selectedRow);
        tableModel.setValueAt(txtId.getText().trim(), modelRow, 0);
        tableModel.setValueAt(txtName.getText().trim(), modelRow, 1);
        tableModel.setValueAt(txtEmail.getText().trim(), modelRow, 2);
        tableModel.setValueAt(txtCourse.getText().trim(), modelRow, 3);
        tableModel.setValueAt(txtGrade.getText().trim(), modelRow, 4);

        JOptionPane.showMessageDialog(this, "Student record updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Select a record from the table to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int modelRow = studentTable.convertRowIndexToModel(selectedRow);
            tableModel.removeRow(modelRow);
            clearForm();
            updateTotalCount();
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtCourse.setText("");
        txtGrade.setText("");
        studentTable.clearSelection();
    }

    private void updateTotalCount() {
        lblTotalCount.setText("Total Registered Students: " + tableModel.getRowCount());
    }
}
