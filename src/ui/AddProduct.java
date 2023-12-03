package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import business.CategoryModel;
import business.ProductService;
import business.ProductModel;
import ui.Product;
import data.CategoryDAO;
import data.ProductDAO;
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
    private final Product add;
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
    
     public AddProduct() {
        categoryDAO = new CategoryDAO();
        checkboxes = new JPanel(); // Initialize checkboxes here
        btnPanel = new JPanel();
        fieldsPanel = new JPanel();
        initComponents(); // Move initComponents after initializing checkboxes
        productService = new ProductService();
        productDAO = new ProductDAO();
        add = new Product();
    }


    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Product");
        getContentPane().setLayout(new BorderLayout());

        fieldsPanel.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns for labels and text fields

        JLabel titleLabel = new JLabel("                     Add Product");
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
     
        JButton addbtn = new JButton("ADD");
        btnPanel.setBackground(new java.awt.Color(0, 153, 204));
        addbtn.addActionListener(e -> addBtnActionPerformed(e));
        btnPanel.add(addbtn);

        add(fieldsPanel, BorderLayout.NORTH);
        add(checkboxes, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        setSize(450, 550);
        setLocationRelativeTo(null);
    }
    
    
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
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
        productService.addProduct(newProduct);
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryModel> categories = categoryDAO.getCategories();

        for (Component component : checkboxes.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    String categoryName = checkBox.getText();
                    CategoryModel selectedCategory = categoryDAO.getCategoryByName(categoryName);
                    if (selectedCategory != null) {
                        productDAO.addProductToCategory(newProduct, selectedCategory);
                    }
                }
            }
        }
        add._Product(newProduct);

        // Clearing input fields after adding the product
        nametf.setText("");
        pricetf.setText("");
        quantitytf.setText("");
        desctf.setText("");
        exptf.setText("");

        javax.swing.JOptionPane.showMessageDialog(this, "Product added successfully!");
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





    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new AddProduct().setVisible(true);
        });
    }
}