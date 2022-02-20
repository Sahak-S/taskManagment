package taskManagment.manager;


import taskManagment.db.DBConnectionProvider;
import taskManagment.model.Task;
import taskManagment.model.TaskStatus;
import taskManagment.model.User;
import taskManagment.model.UserType;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

public class TaskManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private UserManager userManager = new UserManager();

    public void addTask(Task task) {
        String sql = "insert into task (name,description,dataline,status,user_id ) VALUES (?,?,?,?,?)";


        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setString(3, sdf.format(task.getDataline()));
            ps.setString(4, task.getTaskStatus().name());
            ps.setInt(5, task.getUserId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                task.setId(id);
            }
            System.out.println("task was added successfully");
            System.out.println(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Task> getAllTasks() {
        String sql = "select * from task";
        List<Task> tasks = new ArrayList<>();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            tasks = getTasksFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void deleteTaskById(int id) {
        String sql = "delete from task where id = " + id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasksByUserId(int userid) {
        String sql = "select * from task where user_id = ?";
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userid);
            ResultSet resultSet = statement.executeQuery(); //
            tasks = getTasksFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task set status =? where id = ?");
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Task> getTasksFromResultSet(ResultSet resultSet) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getInt(1));
            task.setName(resultSet.getString(2));
            task.setDescription(resultSet.getString("description"));
            try {
                task.setDataline(sdf.parse(resultSet.getString("dataline")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            task.setTaskStatus(TaskStatus.valueOf(resultSet.getString("status")));
            task.setUserId(resultSet.getInt("user_id"));
            task.setUser(userManager.getUserById(task.getUserId()));
            tasks.add(task);
        }
        return tasks;
    }


    public List<Task>  searchTask(String keyword) {
        String sql = "select * from task where name like '%" + keyword +"'";

        try {
            Statement    statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Task> tasks = new ArrayList<>();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setDescription(resultSet.getString("description"));
                try {
                    task.setDataline(sdf.parse(resultSet.getString("dataline")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                task.setTaskStatus(TaskStatus.valueOf(resultSet.getString("status")));
                task.setUserId(resultSet.getInt("user_id"));
                task.setUser(userManager.getUserById(task.getUserId()));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

}
