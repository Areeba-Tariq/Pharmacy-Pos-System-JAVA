package data;

import business.CategoryModel;
import business.ProductModel;
import data.DatabaseConnection;
import data.CategoryDAO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductDAO {
 
    private List<ProductModel> products;
    CategoryDAO categoryDAO = new CategoryDAO();
    private Connection connection = null;
    public Statement st;

    public ProductDAO() {
        products = new ArrayList<>();
        try {
            this.connection = DatabaseConnection.getConnection();
            st = connection.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }


    // Add product to the database
    public void addProduct(ProductModel product) {
        String insertProductQuery = "INSERT INTO product (sn, name, description, price, quantity, validity) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductQuery)) {
            preparedStatement.setInt(1, product.getSn());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setInt(6, product.getValidity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
    }

    

    // Delete product from the database including its references in Product_Category table
    public void deleteProduct(ProductModel product) {
        String deleteFromProductCategoryQuery = "DELETE FROM Product_Category WHERE product_sn=?";
        String deleteProductQuery = "DELETE FROM product WHERE sn=?";

        try (PreparedStatement deleteFromProductCategoryStatement = connection.prepareStatement(deleteFromProductCategoryQuery);
             PreparedStatement deleteProductStatement = connection.prepareStatement(deleteProductQuery)) {
            connection.setAutoCommit(false);

            // Delete references to the product in the Product_Category table
            deleteFromProductCategoryStatement.setInt(1, product.getSn());
            deleteFromProductCategoryStatement.executeUpdate();

            // Delete the product from the Product table
            deleteProductStatement.setInt(1, product.getSn());
            deleteProductStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
            // Handle exceptions accordingly
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public void updateProduct(ProductModel old, ProductModel product) {
            
        deleteProduct(old);
        addProduct(product);
                
              /*  String fetchProductQuery = "SELECT * FROM product WHERE sn = ?";
                PreparedStatement stmtFetch = connection.prepareStatement(fetchProductQuery);
                stmtFetch.setInt(1, product.getSn());
                ResultSet rs = stmtFetch.executeQuery(); 
               
                
 
                   String updateproductQuery = "UPDATE product SET name = ? WHERE id = ?";
                   PreparedStatement stmtproduct = connection.prepareStatement(updateproductQuery);
                   stmtproduct.setString(1, product.getName());
                   stmtproduct.setString(2, product.getDescription());
                   stmtproduct.setInt(3, product.getPrice());
                   stmtproduct.setInt(4, product.getQuantity());
                   stmtproduct.setInt(5, product.getValidity());
                   stmtproduct.setInt(6, product.getSn());
                   stmtproduct.executeUpdate();

                    // Delete existing category for the product
                    String deleteCategoryQuery = "DELETE FROM Product_Category WHERE product_sn = ?";
                    PreparedStatement stmtCategory = connection.prepareStatement(deleteCategoryQuery);
                    stmtCategory.setInt(1, product.getSn());
                    stmtCategory.executeUpdate();
                    
                    
                    String deleteProductQuery = "DELETE FROM Product WHERE sn = ?";
                    PreparedStatement stmtProduct = connection.prepareStatement(deleteProductQuery);
                    stmtCategory.setInt(1, product.getSn());
                    stmtCategory.executeUpdate();
                    
                    // Insert or update skills
                    String insertOrUpdateProductQuery = "INSERT INTO Product_Category (sn, name, description, price, quantity, validity) VALUES (?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrUpdateProductQuery)) {
                        preparedStatement.setInt(1, product.getSn());
                        preparedStatement.setString(2, product.getName());
                        preparedStatement.setString(3, product.getDescription());
                        preparedStatement.setInt(4, product.getPrice());
                        preparedStatement.setInt(5, product.getQuantity());
                        preparedStatement.setInt(6, product.getValidity());
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Handle exceptions accordingly
                    }*/
      
    }
    
    /*
    public void updateProduct(ProductModel product, List<String> selectedCategories) {
        String updateProductQuery = "UPDATE product SET name=?, description=?, price=?, quantity=?, validity=? WHERE sn=?";
        String deleteFromProductCategoryQuery = "DELETE FROM Product_Category WHERE product_sn=?";
        String addProductToCategoryQuery = "INSERT INTO Product_Category (product_sn, category_sn) VALUES (?, ?)";

        
        System.out.println("---1");
        try (PreparedStatement updateProductStatement = connection.prepareStatement(updateProductQuery);
             PreparedStatement deleteFromProductCategoryStatement = connection.prepareStatement(deleteFromProductCategoryQuery);
             PreparedStatement addProductToCategoryStatement = connection.prepareStatement(addProductToCategoryQuery)) {

            connection.setAutoCommit(false);
            // Update product details in the Product table
            updateProductStatement.setString(1, product.getName());
            updateProductStatement.setString(2, product.getDescription());
            updateProductStatement.setInt(3, product.getPrice());
            updateProductStatement.setInt(4, product.getQuantity());
            updateProductStatement.setInt(5, product.getValidity());
            updateProductStatement.setInt(6, product.getSn());
            updateProductStatement.executeUpdate();

            // Delete existing references to the product in the Product_Category table
            deleteFromProductCategoryStatement.setInt(1, product.getSn());
            deleteFromProductCategoryStatement.executeUpdate();

            // Insert the product into the Product_Category table with selected categories
            for (String categoryName : selectedCategories) {
                CategoryModel category = categoryDAO.getCategoryByName(categoryName); // Assuming you have a method to retrieve a category by name
                if (category != null) {
                    addProductToCategoryStatement.setInt(1, product.getSn());
                    addProductToCategoryStatement.setInt(2, category.getSn());
                    addProductToCategoryStatement.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
            // Handle exceptions accordingly
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
*/
    
    // Get all products from the database
    public List<ProductModel> getProducts() {
        List<ProductModel> products = new ArrayList<>();
        String selectProductsQuery = "SELECT * FROM product";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectProductsQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int sn = resultSet.getInt("sn");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                int validity = resultSet.getInt("validity");

                ProductModel product = new ProductModel(sn, name, description, price, quantity, validity);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
        return products;
    }  
   
    
    // Inside ProductDAO class
    public void addProductToCategory(ProductModel product, CategoryModel category) {
        String addProductToCategoryQuery = "INSERT INTO Product_Category (product_sn,category_sn) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(addProductToCategoryQuery)) {
            preparedStatement.setInt(1, product.getSn());
            preparedStatement.setInt(2, category.getSn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public ProductModel getProductBySN(int sn) {
    String selectProductQuery = "SELECT * FROM product WHERE sn = ?";
    ProductModel product = null;

    try (PreparedStatement preparedStatement = connection.prepareStatement(selectProductQuery)) {
        preparedStatement.setInt(1, sn);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int serialNumber = resultSet.getInt("sn");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int quantity = resultSet.getInt("quantity");
            int validity = resultSet.getInt("validity");

            // Create a new ProductModel object with retrieved data
            product = new ProductModel(serialNumber, name, description, price, quantity, validity);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exceptions accordingly
    }
    return product; // Return null if no product found with the given SN
}

    // Inside ProductDAO class
    public List<String> getCategoriesForProduct(ProductModel product) {
        List<String> categoriesForProduct = new ArrayList<>();
        String selectCategoriesForProductQuery = "SELECT category.name FROM Category category INNER JOIN Product_Category pc ON category.sn = pc.category_sn WHERE pc.product_sn = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectCategoriesForProductQuery)) {
            preparedStatement.setInt(1, product.getSn());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String categoryName = resultSet.getString("name");
                categoriesForProduct.add(categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoriesForProduct;
    }
    

    
}
