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


}


