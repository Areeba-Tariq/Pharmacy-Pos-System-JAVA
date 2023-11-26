package business;

public class User {
    private String username;
    private String password;
    private String Role;
    public User(String username, String password, String userRole) {
        this.username = username;
        this.password = password;
        this.Role = userRole;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String userRole) {
        this.Role = userRole;
    }
}