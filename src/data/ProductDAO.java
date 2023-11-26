package data;

import business.CategoryModel;
import business.ProductModel;
import data.DatabaseConnection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
 
    private List<ProductModel> products;
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

    // Update product in the database
    public void updateProduct(ProductModel product) {
        String updateProductQuery = "UPDATE product SET name=?, description=?, price=?, quantity=?, validity=? WHERE sn=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateProductQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getValidity());
            preparedStatement.setInt(6, product.getSn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
    }

    // Delete product from the database
    public void deleteProduct(ProductModel product) {
        String deleteProductQuery = "DELETE FROM product WHERE sn=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteProductQuery)) {
            preparedStatement.setInt(1, product.getSn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
    }

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

                ProductModel product = new ProductModel(sn, price, quantity, validity, name, description);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
        return products;
    }
    /*Coment */

    // Add product to a specific category in the database
    public void addProductToCategory(ProductModel product, CategoryModel category) {
        String insertProductCategoryQuery = "INSERT INTO product_category (product_sn, category_name) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductCategoryQuery)) {
            preparedStatement.setInt(1, product.getSn());
            preparedStatement.setString(2, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
    }    
}
