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
import java.util.ArrayList;
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
    private final JPanel fieldsPanel;
    private final ProductDAO productDAO;
    
     public EditProduct() {
        categoryDAO = new CategoryDAO();
        checkboxes = new JPanel(); // Initialize checkboxes here
        btnPanel = new JPanel();
        fieldsPanel = new JPanel();
        initComponents(); // Move initComponents after initializing checkboxes
        productService = new ProductService();
        productDAO = new ProductDAO();
    }


    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Product");
        getContentPane().setLayout(new BorderLayout());

        fieldsPanel.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns for labels and text fields

        JLabel titleLabel = new JLabel("                     Edit Product");
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fieldsPanel.add(titleLabel);
        fieldsPanel.add(new JLabel()); // Placeholder for alignment

        fieldsPanel.add(new JLabel("   SN#"));
        fieldsPanel.add(sntf);
        fieldsPanel.add(new JLabel("   Name"));
        fieldsPanel.add(nametf);
        fieldsPanel.add(new JLabel("   Available Quantity"));
        fieldsPanel.add(quantitytf);
        fieldsPanel.add(new JLabel("   Price"));
        fieldsPanel.add(pricetf);
        fieldsPanel.add(new JLabel("   Validity (In Days)"));
        fieldsPanel.add(exptf);
        fieldsPanel.add(new JLabel("   Product Description"));
        fieldsPanel.add(desctf);

        checkboxes.setLayout(new BorderLayout());
        JLabel categoryLabel = new JLabel("   Select Category : ");
        checkboxes.add(categoryLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = createCategoryCheckboxesScrollPane();
        checkboxes.add(scrollPane, BorderLayout.CENTER);
     
        JButton editbtn = new JButton("EDIT");
        btnPanel.setBackground(new java.awt.Color(0, 153, 204));
        editbtn.addActionListener(e -> editBtnActionPerformed(e));
        btnPanel.add(editbtn);

        add(fieldsPanel, BorderLayout.NORTH);
        add(checkboxes, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

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

        ProductModel updatedProduct = new ProductModel(sn, name, description, price, quantity, validity);

        // Get the list of selected categories from the checkboxes
        List<String> selectedCategories = new ArrayList<>();
        for (Component component : checkboxes.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    selectedCategories.add(checkBox.getText());
                }
            }
        }

        // Retrieve the product by SN
        ProductDAO productDAO = new ProductDAO();
        ProductModel existingProduct = productDAO.getProductBySN(sn);

// Delete the existing product
productDAO.deleteProduct(existingProduct);

        // Add the updated product
        productDAO.addProduct(updatedProduct);

        // Associate the updated product with selected categories
        for (String categoryName : selectedCategories) {
            CategoryModel category = categoryDAO.getCategoryByName(categoryName);
            if (category != null) {
                productDAO.addProductToCategory(updatedProduct, category);
            }
        }

        javax.swing.JOptionPane.showMessageDialog(this, "Product updated successfully!");
        this.dispose();
    }

    
    private JScrollPane createCategoryCheckboxesScrollPane() {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryModel> categories = categoryDAO.getCategories();

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(0, 3, 20, 10)); // Adjust grid layout as per your requirement

        for (CategoryModel category : categories) {
            JCheckBox checkBox = new JCheckBox(category.getName());
            checkboxPanel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(checkboxPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }


    public void populateProductDetails(int sn) {
        ProductDAO productDAO = new ProductDAO();
        ProductModel product = productDAO.getProductBySN(sn);

        // Populate the fields with retrieved product details
        sntf.setText(String.valueOf(product.getSn()));
        nametf.setText(product.getName());
        quantitytf.setText(String.valueOf(product.getQuantity()));
        pricetf.setText(String.valueOf(product.getPrice()));
        exptf.setText(String.valueOf(product.getValidity()));
        desctf.setText(product.getDescription());

        // Select the categories associated with the product
        List<String> categoriesForProduct = productDAO.getCategoriesForProduct(product);
        for (Component component : checkboxes.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (categoriesForProduct.contains(checkBox.getText())) {
                    checkBox.setSelected(true);
                }
            }
        }
    }


    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new AddProduct().setVisible(true);
        });
    }
}