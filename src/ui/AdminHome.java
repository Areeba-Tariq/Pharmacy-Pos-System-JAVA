package ui;
import ui.Category;
import ui.Product;
import ui.InventoryReport;

public class AdminHome extends javax.swing.JFrame {

    private Category category;
    private Product product;
    private InventoryReport inventory;
    
    public AdminHome() {
        initComponents();
        product = new Product();
        category = new Category();
        inventory = new InventoryReport();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ProductBtn = new javax.swing.JButton();
        SReportBtn = new javax.swing.JButton();
        IReportBtn = new javax.swing.JButton();
        signUpBtn = new javax.swing.JButton();
        categoryBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("MANAGER");

        ProductBtn.setBackground(new java.awt.Color(0, 153, 153));
        ProductBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ProductBtn.setText("PRODUCT CATALOG");
        ProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductBtnActionPerformed(evt);
            }
        });

        SReportBtn.setBackground(new java.awt.Color(0, 153, 153));
        SReportBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SReportBtn.setText("SALES REPORT");
        SReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SReportBtnActionPerformed(evt);
            }
        });

        IReportBtn.setBackground(new java.awt.Color(0, 153, 153));
        IReportBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IReportBtn.setText("INVENTORY REPORT");
        IReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IReportBtnActionPerformed(evt);
            }
        });

        signUpBtn.setBackground(new java.awt.Color(0, 153, 153));
        signUpBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        signUpBtn.setText("LOG IN");
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });

        categoryBtn.setBackground(new java.awt.Color(0, 153, 153));
        categoryBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        categoryBtn.setText("CATEGORY CATALOG");
        categoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(categoryBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(ProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(SReportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(IReportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IReportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SReportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductBtnActionPerformed
        product.setVisible(true);
        product.populateProductTable();
    }//GEN-LAST:event_ProductBtnActionPerformed

    private void SReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SReportBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SReportBtnActionPerformed

    private void IReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IReportBtnActionPerformed
        inventory.setVisible(true);
    }//GEN-LAST:event_IReportBtnActionPerformed

    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpBtnActionPerformed

    private void categoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBtnActionPerformed
        category.setVisible(true); // Show the Category screen
        category.populateCategoryTable();
    }//GEN-LAST:event_categoryBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IReportBtn;
    private javax.swing.JButton ProductBtn;
    private javax.swing.JButton SReportBtn;
    private javax.swing.JButton categoryBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton signUpBtn;
    // End of variables declaration//GEN-END:variables
}
