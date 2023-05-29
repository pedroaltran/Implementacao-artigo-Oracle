package javaapplication5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    public Connection getMySqlConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/artigooracle","root","");
        }
        catch (SQLException e){
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return null;
    }
    
     public Connection getSqliteConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:C:/Users/Program Files/Docs/SQLite/artigooracle.sqlite");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados SQLite: " + e.getMessage());
        }
        return null;
    }
}

