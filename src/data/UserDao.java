package data;

import business.User;

import java.nio.channels.SelectableChannel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection = null;
    void Save(User u)
    {
        //save user
    }

    public List<User> getUsers(){
        List<User>userList=new ArrayList<>(5);
        try {
            
            this.connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from user1");
            while (resultSet.next()) {
                User user = new User((resultSet.getString("name")),(resultSet.getString("password")),(resultSet.getString("role")));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("password"));
                System.out.println(resultSet.getString("role"));
                userList.add(user);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println("\nin userdao");
        return userList;
    }
//    List<User>getALlUsers()
//    {
//        //getAllusersinDB
//
//    }
}
