/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;
import ui.Category;
import ui.Product;

public class AdminHome extends javax.swing.JFrame {

    private Category category;
    private Product product;
    
    public AdminHome() {
        initComponents();
        product = new Product();
        category = new Category();
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
        signUpBtn.setText("SIGN UP");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(categoryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ProductBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SReportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IReportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SReportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IReportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductBtnActionPerformed
        product.populateProductTable();
    }//GEN-LAST:event_ProductBtnActionPerformed

    private void SReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SReportBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SReportBtnActionPerformed

    private void IReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IReportBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IReportBtnActionPerformed

    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpBtnActionPerformed

    private void categoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBtnActionPerformed
        category.populateCategoryTable();
    }//GEN-LAST:event_categoryBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

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
