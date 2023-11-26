package business;
import data.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User>userList;
    private UserDao userdao;

    public UserService() {
        userList=new ArrayList<>();
        //load data from db
        load();
    }

    public boolean CheckLogin(String username,String password,String role)
    {
        for(User user:userList)
        {
            if((user.getUsername().equals(username)) &&(user.getPassword().equals(password))&&(user.getRole().equals(role)))
            {
                return true;
            }
        }
        return false;
    }
    public void load()
    {
        userdao=new UserDao();//why
//        userList.add(new User("Areeba","123","Manager"));
//        userList.add(new User("Zara","1234","Manager"));
//        userList.add(new User("Ali","12345","Sales Assistant"));
//        userList.add(new User("Kamal","1234567","Manager"));
//        userList.add(new User("Omar","123456","Sales Assistant"));
        userList=userdao.getUsers();
    }
}
