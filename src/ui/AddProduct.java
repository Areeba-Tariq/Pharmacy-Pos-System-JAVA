package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import business.CategoryModel;
import business.ProductService;
import business.ProductModel;
import data.CategoryDAO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AddProduct extends JFrame {

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

    
     public AddProduct() {
        categoryDAO = new CategoryDAO();
        checkboxes = new JPanel(); // Initialize checkboxes here
        btnPanel = new JPanel();
        initComponents(); // Move initComponents after initializing checkboxes
        productService = new ProductService();
    }


    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Product");
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

        JButton addbtn = new JButton("ADD");
        addbtn.setBackground(new java.awt.Color(0, 153, 204));
        addbtn.setBounds(150, 10, 70, 40);

        JScrollPane scrollPane = new JScrollPane(checkboxes);
        scrollPane.setBounds(50, 330, 300, 80);

        add(scrollPane, BorderLayout.CENTER);
        add(addbtn, BorderLayout.SOUTH);

        setSize(450, 550);
        setLocationRelativeTo(null);
    }

    
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {                                       
        try {
            // Extracting input values from the text fields
            int sn = Integer.parseInt(sntf.getText());
            int price = Integer.parseInt(pricetf.getText());
            int quantity = Integer.parseInt(quantitytf.getText());
            String name = nametf.getText();
            String description = desctf.getText();
            int validity = Integer.parseInt(exptf.getText());

            ProductModel newProduct = new ProductModel(sn, price, quantity, validity, name, description);
            List<CategoryModel> categories = categoryDAO.getCategories();

            // Iterating through all the checkboxes and checking if they are selected
            for (Component component : checkboxes.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        // Finding the corresponding category and adding the product to it
                        for (CategoryModel category : categories) {
                            if (checkBox.getText().equals(category.getName())) {
                                categoryDAO.addProductToCategory(newProduct, category);
                            }
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

            javax.swing.JOptionPane.showMessageDialog(this, "Product added successfully!");
            this.dispose();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        }
    }                                      

    
    private void initializeCategoryCheckboxes() {

        List<CategoryModel> categories = categoryDAO.getCategories();

        // Use a GridLayout to manage checkboxes dynamically
        JPanel checkboxPanel = new JPanel(new GridLayout(0, 3, 20, 10)); // 3 columns, horizontal gap 20, vertical gap 10

        for (CategoryModel category : categories) {
            JCheckBox checkBox = new JCheckBox(category.getName());
            checkboxPanel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(checkboxPanel);
        scrollPane.setBounds(50, 365, 300, 80); // Set bounds for the scroll pane
        getContentPane().add(scrollPane);

        setSize(400, 550); // Adjust the frame size as needed
    }


    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new AddProduct().setVisible(true);
        });
    }
}
