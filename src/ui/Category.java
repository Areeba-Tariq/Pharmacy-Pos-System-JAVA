package ui;
import business.CategoryService;
import business.CategoryModel;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Category extends javax.swing.JFrame {

    private List<CategoryModel> categories;
    public DefaultTableModel model;

     public Category() {
        initComponents();
        populateCategoryTable();
        
        // Add a listener to the table to handle row selection
        categoryTable.getSelectionModel().addListSelectionListener(e -> {
            // Enable or disable the delete button based on row selection
            if (!categoryTable.getSelectionModel().isSelectionEmpty()) {
                delBtn.setEnabled(true);
            } else {
                delBtn.setEnabled(false);
            }
        });
     }
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        delBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        categoryTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();

        label1.setText("label1");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("CATEGORIES");

        delBtn.setBackground(new java.awt.Color(0, 153, 153));
        delBtn.setForeground(new java.awt.Color(255, 255, 255));
        delBtn.setText("DELETE");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "SN", "Name", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(categoryTable);
        if (categoryTable.getColumnModel().getColumnCount() > 0) {
            categoryTable.getColumnModel().getColumn(0).setResizable(false);
            categoryTable.getColumnModel().getColumn(1).setResizable(false);
            categoryTable.getColumnModel().getColumn(2).setResizable(false);
        }

        addBtn.setBackground(new java.awt.Color(0, 153, 153));
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        int selectedRow = categoryTable.getSelectedRow();
        if (selectedRow != -1) { // Ensure a row is selected
            // Retrieve the category data from the selected row
            int sn = (int) categoryTable.getValueAt(selectedRow, 0); // Assuming SN is in the first column

            // Delete the row from the table
            DefaultTableModel model = (DefaultTableModel) categoryTable.getModel();
            model.removeRow(selectedRow);

            // Delete the row from the database
            CategoryService categoryService = new CategoryService();
            CategoryModel categoryToDelete = getCategoryBySN(sn);
            if (categoryToDelete != null) {
                categoryService.delCategory(categoryToDelete);
            }
        }
    }//GEN-LAST:event_delBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
            java.awt.EventQueue.invokeLater(() -> {
            new AddCategory().setVisible(true);
            populateCategoryTable();
    });
    }//GEN-LAST:event_addBtnActionPerformed

    
    private CategoryModel getCategoryBySN(int sn) {
        // Find and return the CategoryDAO object by its SN
        for (CategoryModel category : categories) {
            if (category.getSn() == sn) {
                return category;
            }
        }
        return null; // Return null if not found
    }
    
    public void populateCategoryTable() {
        model = (DefaultTableModel) categoryTable.getModel();
        model.setRowCount(0); // Clear the existing rows

        // Create an instance of CategoryService
        CategoryService categoryService = new CategoryService();

        // Fetch categories using an instance of CategoryService
        categories = categoryService.getCategories();

        // Populate the table with category data
        for (CategoryModel category : categories) {
            Object[] row = { category.getSn(), category.getName(), category.getDescription() };
            model.addRow(row);
        }
    }
    
    
    public void _AddRow(CategoryModel c){
        Object[] row = { c.getSn(), c.getName(), c.getDescription() };
        model.addRow(row);  
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
           /* Set the Nimbus look and feel */
           //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
           // ...
           //</editor-fold>

           /* Create and display the form */
           java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                   new Category().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTable categoryTable;
    private javax.swing.JButton delBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
