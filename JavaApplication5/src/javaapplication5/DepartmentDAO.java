package javaapplication5;
import javax.sql.rowset.JdbcRowSet;  

public interface DepartmentDAO {
    public int insertDepartment(Department department);
    public boolean deleteDepartment(int departmentId);
    public Department findDepartment(int departmentId);
    public JdbcRowSet selectDepartmentsRS();
    public boolean updateDepartment(Department department);
}