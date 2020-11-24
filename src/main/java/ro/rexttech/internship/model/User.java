package ro.rexttech.internship.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String userRole;
    private int companyId;

    public User(int id, String username, String password, String email, String userRole, int companyId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.companyId = companyId;
    }

    public User(String username, String password, String email, String userRole, int companyId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
