package javaapplication5;

import java.sql.Connection;

/**
 *
 * @author Pedro Altran
 */
public class App {

    public static void main(String[] args) {

        //Conexão com MySQL e com SQLite
        ConnectionFactory connectionFactory = new ConnectionFactory();
       
        Connection mysqlConnection = connectionFactory.getMySqlConnection();
        if (mysqlConnection != null) {
            System.out.println("Conexão com MySQL estabelecida com sucesso!");
            testMysqlDepartmentDAO(mysqlConnection);
        } else {
            System.out.println("Falha ao estabelecer conexão com o banco de dados MySQL.");
        }
        
        Connection sqliteConnection = connectionFactory.getSqliteConnection();
        if (sqliteConnection != null) {
            System.out.println("Conexão com SQLite estabelecida com sucesso!");
            testSqliteDepartmentDAO(sqliteConnection);
        } else {
            System.out.println("Falha ao estabelecer conexão com o banco de dados SQLite.");
        }
    }

    //Testando os metodos no MySQL
    private static void testMysqlDepartmentDAO(Connection connection) {
        
        Department department = new Department();
        department.setDepartment_id(1);
        department.setDepartment_name("Teste MySQL");
        department.setManager_id(123);
        department.setLocation_id("12345");

        
        MysqlDepartmentDAO departmentDAO = new MysqlDepartmentDAO(connection);

        
        int rowsAffected = departmentDAO.insertDepartment(department);
        System.out.println("Linhas afetadas (insertDepartment): " + rowsAffected);

        Department retrievedDepartment = departmentDAO.findDepartment(1);
        System.out.println("Department encontrado (findDepartment): " + retrievedDepartment);

        department.setDepartment_name("Departamento Atualizado");
        boolean updateSuccess = departmentDAO.updateDepartment(department);
        System.out.println("Departamento atualizado com sucesso? (updateDepartment): " + updateSuccess);

        boolean deleteSuccess = departmentDAO.deleteDepartment(1);
        System.out.println("Departamento excluído com sucesso? (deleteDepartment): " + deleteSuccess);
    }

    //Testando os metodos no SQLite
    private static void testSqliteDepartmentDAO(Connection connection) {
        
        Department department = new Department();
        department.setDepartment_id(2);
        department.setDepartment_name("Teste SQLite");
        department.setManager_id(456);
        department.setLocation_id("54321");
      
        SqliteDepartmentDAO departmentDAO = new SqliteDepartmentDAO(connection);

        int rowsAffected = departmentDAO.insertDepartment(department);
        System.out.println("Linhas afetadas : " + rowsAffected);
      
        Department retrievedDepartment = departmentDAO.findDepartment(2);
        System.out.println("Department encontrado: " + retrievedDepartment);
     
        department.setDepartment_name("Departamento Atualizado");
        boolean updateSuccess = departmentDAO.updateDepartment(department);
        System.out.println("Departamento atualizado com sucesso? " + updateSuccess);

        boolean deleteSuccess = departmentDAO.deleteDepartment(2);
        System.out.println("Departamento excluído com sucesso? " + deleteSuccess);
    }
}


    
   
