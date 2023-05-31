package com.example.student_project.DAO;

import com.example.student_project.DAO.connection.MyConnection;
import com.example.student_project.model.Classes;
import com.example.student_project.model.Student;
import com.example.student_project.service.ClassesService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final Connection connection;
    private final ClassesService classesService = ClassesService.getInstance();
    private final String SELECT_ALL = "select * from student;";
    private final String SELECT_BY_ID = "select * from student where id = ?;";
    private final String INSERT_INTO = "insert into student(name,age,gender,address,class_id) value (?,?,?,?,?);";
    private final String UPDATE_BY_ID = "update student set name = ?,age=?,gender=?,address=?,class_id=? where id = ?;";
    private final String DELETE_BY_ID = "delete from student where id = ?";
    private final String SEARCH_BY_NAME = "select * from student where name like ?;";

    public StudentDAO() {
        connection = MyConnection.getConnection();
    }

    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            convertResultSetToList(studentList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public Student findById(Long id) {
        Student student = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String names = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                Long classId = resultSet.getLong("class_id");
                Classes classes = classesService.getById(classId);
                student = new Student(id, names, age, gender, address, classes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void addStudent(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setLong(5, student.getClasses().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setLong(5, student.getClasses().getId());
            preparedStatement.setLong(6, student.getId());
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

    public List<Student> searchByName(String name) {
        List<Student> studentList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            convertResultSetToList(studentList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    private void convertResultSetToList(List<Student> studentList, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String names = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            String address = resultSet.getString("address");
            Long classId = resultSet.getLong("class_id");
            Classes classes = classesService.getById(classId);
            Student student = new Student(id, names, age, gender, address, classes);
            studentList.add(student);
        }
    }
}
