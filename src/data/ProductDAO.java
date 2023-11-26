package data;

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


    public void addProduct(ProductModel product) {
        String INSERT_PRODUCT_SQL = "INSERT INTO product (sn, price, quantity, name, category, description) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setInt(1, product.getSn());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getName());
            preparedStatement.setString(4, product.getCategory());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(ProductModel product) {

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE sn = ?")) {
            statement.setInt(1, product.getSn());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void updateProduct(ProductModel product) {
        String UPDATE_PRODUCT_SQL = "UPDATE product SET sn=?, price=?, quantity=?, name=?, category=?, description=? WHERE sn=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, product.getPrice());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setString(3, product.getName());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, product.getSn());
            preparedStatement.setString(6, product.getCategory());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public List<ProductModel> getProducts() {
        List<ProductModel> products = new ArrayList<>();
        String SELECT_ALL_PRODUCTS = "SELECT * FROM product";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int sn = resultSet.getInt("sn");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");

                ProductModel product = new ProductModel(sn, price, quantity, name, category, description);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public ProductModel SearchProductbyName (String name1)
    {
        try {
            this.connection = DatabaseConnection.getConnection();
            String query="Select * From product where name=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name1);
            ResultSet resultSet=preparedStatement.executeQuery();
            int sn = 0,quantity = 0;
            int price=0;
            String category = null,description = null,name2=null;
            
            while(resultSet.next())
            {
                    sn = resultSet.getInt("sn");
                    price = resultSet.getInt("price");
                    quantity = resultSet.getInt("quantity");
                    name2 = resultSet.getString("name");
                    category = resultSet.getString("category");
                    description = resultSet.getString("description");
            }
//            if(!resultSet.next())
//            {
//                return null;
//            }
            ProductModel p=new ProductModel(sn, price, quantity, name2, category, description);
            return p;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
    
}
