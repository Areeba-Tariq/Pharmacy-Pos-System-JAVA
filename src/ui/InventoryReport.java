package ui;

import business.ProductService;
import business.ProductModel;
import ui.EditProduct;
import data.ProductDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class InventoryReport extends javax.swing.JFrame {

    private List<ProductModel> products;

    public InventoryReport() {
        initComponents();
        populateInventoryTable();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inventoryTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("INVENTORY REPORT");

        inventoryTable.setForeground(new java.awt.Color(0, 153, 153));
        inventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Product Id", "Name", "Category", "Quantity", "Price", "Validity", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inventoryTable.setRowHeight(50);
        jScrollPane1.setViewportView(inventoryTable);
        if (inventoryTable.getColumnModel().getColumnCount() > 0) {
            inventoryTable.getColumnModel().getColumn(0).setResizable(false);
            inventoryTable.getColumnModel().getColumn(1).setResizable(false);
            inventoryTable.getColumnModel().getColumn(2).setResizable(false);
            inventoryTable.getColumnModel().getColumn(3).setResizable(false);
            inventoryTable.getColumnModel().getColumn(4).setResizable(false);
            inventoryTable.getColumnModel().getColumn(5).setResizable(false);
            inventoryTable.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1043, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(393, 393, 393))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
        // Inside Product.java class
    public void populateInventoryTable() {
        ProductDAO productDAO = new ProductDAO();
        products = productDAO.getProducts();

        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        model.setRowCount(0); // Clear the existing rows from the table

        for (ProductModel product : products) {
            List<String> categoriesForProduct = productDAO.getCategoriesForProduct(product);
            String categoryList = String.join(", ", categoriesForProduct);

            Object[] rowData = {
                product.getSn(),
                product.getName(),
                categoryList, // Categories as a comma-separated string
                product.getQuantity(),
                product.getPrice(),
                product.getValidity(),
                product.getStatus(),
            };
            model.addRow(rowData);
        }
    }

    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable inventoryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
