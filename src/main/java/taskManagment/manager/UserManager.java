package taskManagment.manager;


import taskManagment.model.User;
import taskManagment.model.UserType;
import taskManagment.db.DBConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addUser(User user) {
        String sql = "insert into user (name,surname,email,password,type ) VALUES (?,?,?,?,?)";


        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getType().name());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
            System.out.println("user was added successfully");
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void edit (User user) {

        String sql = "update user SET name = ?,surname = ?,email =?,password =?,type = ?  where id = ?";


        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getType().name());
            ps.setInt(6,user.getId());
            ps.executeUpdate();

            System.out.println("user was edited successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        String sql = "select * from user where email = '" + email + "'";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setType(UserType.valueOf(resultSet.getString("type")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<User> getAllUsers() {
        String sql = "select * from user";
        List<User> result = new ArrayList<>();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setType(UserType.valueOf(resultSet.getString("type")));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteUserById(int id) {
        String sql = "delete from user where id = " + id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String sql = "select * from user where id = id";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setType(UserType.valueOf(resultSet.getString("type")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


}
