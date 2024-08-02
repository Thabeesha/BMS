/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author PC
 */
public class ConnectionProvider {
    
  private static ConnectionProvider instance;
    private Connection mockConnection; 

   
    public ConnectionProvider() {}

    /**
     * Get the singleton instance of ConnectionProvider.
     * 
     * @return the single instance of ConnectionProvider
     */
    public static synchronized ConnectionProvider getInstance() {
        if (instance == null) {
            instance = new ConnectionProvider();
        }
        return instance;
    }

    /**
     * Set a custom instance for testing purposes.
     * 
     * @param customInstance the custom ConnectionProvider instance
     */
    public static void setInstance(ConnectionProvider customInstance) {
        instance = customInstance;
    }

    /**
     * Set a mock connection for testing.
     * 
     * @param connection the mock Connection object
     */
    public void setMockConnection(Connection connection) {
        this.mockConnection = connection;
    }

   
    public Connection getCon() throws ClassNotFoundException, SQLException {
        if (mockConnection != null) {
            return mockConnection; 
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bms", "root", "BIRTHday_2");
    }
    
}
