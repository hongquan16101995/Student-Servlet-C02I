package com.example.student_project.DAO;

import com.example.student_project.DAO.connection.MyConnection;
import com.example.student_project.model.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO {

    private final Connection connection;

    private final String SELECT_ALL = "select * from classes;";
    private final String SELECT_BY_ID = "select * from classes where id = ?;";
    private final String INSERT_INTO = "insert into classes(name) value (?);";
    private final String UPDATE_BY_ID = "update classes set name = ? where id = ?;";
    private final String DELETE_BY_ID = "delete from classes where id = ?";

    public ClassesDAO() {
        connection = MyConnection.getConnection();
    }

    public List<Classes> findAll() {
        List<Classes> classesList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Classes classes = new Classes(id, name);
                classesList.add(classes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classesList;
    }

    public Classes findById(Long id) {
        Classes classes = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                classes = new Classes(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public void addClasses(Classes classes) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, classes.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClasses(Classes classes) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, classes.getName());
            preparedStatement.setLong(2, classes.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
