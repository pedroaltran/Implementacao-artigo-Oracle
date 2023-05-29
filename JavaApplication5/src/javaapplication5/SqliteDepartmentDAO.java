package javaapplication5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class SqliteDepartmentDAO implements DepartmentDAO {
    private Connection connection;

    public SqliteDepartmentDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insertDepartment(Department department) {
        String sql = "INSERT INTO Departments (department_id, department_name, manager_id, location_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, department.getDepartment_id());
            statement.setString(2, department.getDepartment_name());
            statement.setInt(3, department.getManager_id());
            statement.setString(4, department.getLocation_id());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        String sql = "DELETE FROM Departments WHERE department_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Department findDepartment(int departmentId) {
        String sql = "SELECT * FROM Departments WHERE department_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Department department = new Department();
                department.setDepartment_id(resultSet.getInt("department_id"));
                department.setDepartment_name(resultSet.getString("department_name"));
                department.setManager_id(resultSet.getInt("manager_id"));
                department.setLocation_id(resultSet.getString("location_id"));
                return department;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return null;
    }

    @Override
    public JdbcRowSet selectDepartmentsRS() {
        String sql = "SELECT * FROM Departments";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            JdbcRowSet rowSet = rowSetFactory.createJdbcRowSet();
            rowSet.setCommand(sql);
            rowSet.execute();
            return rowSet;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateDepartment(Department department) {
        String sql = "UPDATE Departments SET department_name = ?, manager_id = ?, location_id = ? WHERE department_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getDepartment_name());
            statement.setInt(2, department.getManager_id());
            statement.setString(3, department.getLocation_id());
            statement.setInt(4, department.getDepartment_id());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return false;
    }
}
