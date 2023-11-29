package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import business.CategoryModel;
import business.ProductService;
import business.ProductModel;
import data.CategoryDAO;
import data.ProductDAO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EditProduct extends JFrame {

    private ProductService productService;
    private final CategoryDAO categoryDAO;
    JTextField sntf = new JTextField();
    JTextField nametf = new JTextField();
    JTextField quantitytf = new JTextField();
    JTextField pricetf = new JTextField();
    JTextField exptf = new JTextField();
    JTextField desctf = new JTextField();
    private final JPanel checkboxes;    
    private final JPanel btnPanel;
    
     public EditProduct() {
        categoryDAO = new CategoryDAO();
        checkboxes = new JPanel(); // Initialize checkboxes here
        btnPanel = new JPanel();
        initComponents(); // Move initComponents after initializing checkboxes
        productService = new ProductService();
    }


    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Product");
        setLayout(new BorderLayout()); // Use BorderLayout

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Add Product");
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titleLabel);
        titleLabel.setBounds(50, 20, 300, 30);

        JLabel snLabel = new JLabel("SN#");
        getContentPane().add(snLabel);
        snLabel.setBounds(50, 70, 100, 20);

        getContentPane().add(sntf);
        sntf.setBounds(200, 70, 150, 25);

        JLabel nameLabel = new JLabel("Name");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(50, 110, 100, 20);

        getContentPane().add(nametf);
        nametf.setBounds(200, 110, 150, 25);

        JLabel quantityLabel = new JLabel("Available Quantity");
        getContentPane().add(quantityLabel);
        quantityLabel.setBounds(50, 150, 150, 20);

        getContentPane().add(quantitytf);
        quantitytf.setBounds(200, 150, 150, 25);

        JLabel priceLabel = new JLabel("Price");
        getContentPane().add(priceLabel);
        priceLabel.setBounds(50, 190, 100, 20);

        getContentPane().add(pricetf);
        pricetf.setBounds(200, 190, 150, 25);

        JLabel dateLabel = new JLabel("Validity (In Days)");
        getContentPane().add(dateLabel);
        dateLabel.setBounds(50, 230, 100, 20);

        getContentPane().add(exptf);
        exptf.setBounds(200, 230, 150, 25);

        JLabel descriptionLabel = new JLabel("Product Description");
        getContentPane().add(descriptionLabel);
        descriptionLabel.setBounds(50, 270, 150, 20);

        getContentPane().add(desctf);
        desctf.setBounds(200, 270, 150, 60);

        getContentPane().add(Box.createRigidArea(new Dimension(60, 0))); 
        
        JLabel categoryLabel = new JLabel("Select Category : ");
        getContentPane().add(categoryLabel);
        categoryLabel.setBounds(50, 340, 150, 20);

        initializeCategoryCheckboxes();

        JButton addbtn = new JButton("DONE");
        addbtn.setBackground(new java.awt.Color(0, 153, 204));
        addbtn.setBounds(150, 10, 70, 40);

        // Add ActionListener to the button
        addbtn.addActionListener(e -> editBtnActionPerformed(e));
       
        JScrollPane scrollPane = new JScrollPane(checkboxes);
        scrollPane.setBounds(50, 330, 300, 80);

        add(scrollPane, BorderLayout.CENTER);
        add(addbtn, BorderLayout.SOUTH);

        setSize(450, 550);
        setLocationRelativeTo(null);
    }

    
    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {
    // Extracting input values from the text fields
    int sn, price, quantity, validity;
    String name, description;
  
    try {
        sn = Integer.parseInt(sntf.getText());
        price = Integer.parseInt(pricetf.getText());
        quantity = Integer.parseInt(quantitytf.getText());
        name = nametf.getText();
        description = desctf.getText();
        validity = Integer.parseInt(exptf.getText());
    } catch (NumberFormatException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        return;
    }

    ProductModel newProduct = new ProductModel(sn, name, description, price, quantity, validity);
    productService.updateProduct(newProduct);
    CategoryDAO categoryDAO = new CategoryDAO();
    List<CategoryModel> categories = categoryDAO.getCategories();

    for (Component component : checkboxes.getComponents()) {
        if (component instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                String categoryName = checkBox.getText();
                CategoryModel selectedCategory = categoryDAO.getCategoryByName(categoryName);
                if (selectedCategory != null) {
                    productService.updateProduct(newProduct);
                }
            }
        }
    }

    // Clearing input fields after adding the product
    nametf.setText("");
    pricetf.setText("");
    quantitytf.setText("");
    desctf.setText("");
    exptf.setText("");

    javax.swing.JOptionPane.showMessageDialog(this, "Product updated successfully!");
    this.dispose();
}

    
    private void initializeCategoryCheckboxes() {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryModel> categories = categoryDAO.getCategories();

        checkboxes.setLayout(new GridLayout(0, 3, 20, 10)); // Use the layout for checkboxes

        for (CategoryModel category : categories) {
            JCheckBox checkBox = new JCheckBox(category.getName());
            checkboxes.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(checkboxes);
        scrollPane.setBounds(50, 365, 300, 80); // Set bounds for the scroll pane

        getContentPane().add(scrollPane);
        pack(); // Pack the frame to layout its components properly
        setLocationRelativeTo(null); // Center the frame on the screen
    }



    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new AddProduct().setVisible(true);
        });
    }
}