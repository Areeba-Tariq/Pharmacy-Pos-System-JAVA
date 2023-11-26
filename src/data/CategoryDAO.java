package data;

import business.CategoryModel;
import data.DatabaseConnection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import business.ProductModel;

public class CategoryDAO {

    private List<CategoryModel> categories;
    private Connection connection = null;
    public Statement st;

    public CategoryDAO() {
        // Initialize the list of categories (this can be retrieved from a database as well)
        categories = new ArrayList<>();
        try {
            this.connection = DatabaseConnection.getConnection();
            st = connection.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
        // Add more categories as needed
    }

    public List<CategoryModel> getCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM category")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int sn = resultSet.getInt("sn");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                categories.add(new CategoryModel(sn, name, description));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error fetching category from the database", ex);
        }
        return categories;
    }

    public void addCategory(CategoryModel category) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO category(sn, name, description) VALUES (?, ?, ?)")) {
            statement.setInt(1, category.getSn());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error adding category to the database", ex);
        }
    }

    public void deleteCategory(CategoryModel category) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM category WHERE sn = ?")) {
            statement.setInt(1, category.getSn());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error deleting category from the database", ex);
        }
    }
    
    public void addProductToCategory(ProductModel product, CategoryModel category) {
        category.addProduct(product); // Add product to the category's list of products
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO product_category(category_sn, product_sn) VALUES (?, ?)")) {
            statement.setInt(1, category.getSn());
            statement.setInt(2, product.getSn());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error adding product to the category in the database", ex);
        }
    }

    public void removeProductFromCategory(ProductModel product, CategoryModel category) {
        category.removeProduct(product); // Remove product from the category's list of products
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM product_category WHERE category_sn = ? AND product_sn = ?")) {
            statement.setInt(1, category.getSn());
            statement.setInt(2, product.getSn());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error removing product from the category in the database", ex);
        }
    }
    
    public CategoryModel getCategoryByName(String name) {
        for (CategoryModel category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }


}


