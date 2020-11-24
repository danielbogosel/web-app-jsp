package ro.rexttech.internship.dao;

import ro.rexttech.internship.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/accountancy_db?useSSL=false";
    private String username = "root";
    private String password = "password";

    private static final String INSERT_USER = "INSERT INTO user" + "(username,password,email,user_role,company_id)VALUES" + "(?,?,?,?,?)";
    private static final String SELECT_ALL_USERS = "select * from user;";
    private static final String SELECT_USER_BY_ID = "select * from user where id=?";
    private static final String DELETE_USER = "delete from user where id=?;";
    private static final String UPDATE_USER = "update user set username=?,password=?,email=?,user_role=?,company_id=?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserRole());
            preparedStatement.setString(5, String.valueOf(user.getCompanyId()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("user_id");
                String username = result.getString("username");
                String password = result.getString("password");
                String email = result.getString("email");
                String userRole = result.getString("user_role");
                int companyId = result.getInt("company_id");
                users.add(new User(id, username, password, email, userRole, companyId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateMethod(User user) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUserRole());
            preparedStatement.setString(5, String.valueOf(user.getCompanyId()));
            rowUpdated = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public User getUserById(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                String email = result.getString("email");
                String userRole = result.getString("user_role");
                int companyId = result.getInt("company_id");
                user = new User(id, username, password, email, userRole, companyId);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);) {
            preparedStatement.setInt(1, id);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
