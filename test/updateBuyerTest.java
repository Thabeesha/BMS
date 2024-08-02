/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Project.ConnectionProvider;
import billing.management.system.updateBuyer;
/**
 *
 * @author PC
 */
public class updateBuyerTest {
    
 private updateBuyer updateBuyerFrame;
    private ConnectionProvider connectionProvider;

@Before
public void setUp() throws Exception {
    updateBuyerFrame = new updateBuyer();
    connectionProvider = ConnectionProvider.getInstance();

    
    Connection con = connectionProvider.getCon();
    Statement st = con.createStatement();
    st.execute("CREATE TABLE IF NOT EXISTS buyer (" +
               "contactNo VARCHAR(255) PRIMARY KEY, " +
               "name VARCHAR(255), " +
               "email VARCHAR(255), " +
               "address VARCHAR(255), " +
               "gender VARCHAR(255))");
    st.execute("INSERT INTO buyer (name, contactNo, email, address, gender) VALUES ('OldName', '1234567890', 'oldemail@gmail.com', 'OldAddress', 'Male')");
    st.close();
    con.close();
}



    @Test
    public void testSearchButtonAction() throws Exception {
        // Arrange
        JButton searchButton = updateBuyerFrame.getJButton1();
        JTextField contactNoField = updateBuyerFrame.getJTextField1();
        JTextField nameField = updateBuyerFrame.getJTextField2();
        JTextField contactNoField2 = updateBuyerFrame.getJTextField3();
        JTextField emailField = updateBuyerFrame.getJTextField4();
        JTextField addressField = updateBuyerFrame.getJTextField5();
        JTextField genderField = updateBuyerFrame.getJTextField6();

        contactNoField.setText("1234567890");

       
        searchButton.doClick();
        Thread.sleep(2000); 

        
        assertEquals("OldName", nameField.getText());
        assertEquals("1234567890", contactNoField2.getText());
        assertEquals("oldemail@gmail.com", emailField.getText());
        assertEquals("OldAddress", addressField.getText());
        assertEquals("Male", genderField.getText());
    }

    @Test
    public void testUpdateButtonAction() throws Exception {
        // Arrange
        JButton searchButton = updateBuyerFrame.getJButton1();
        JButton updateButton = updateBuyerFrame.getJButton2();
        JTextField contactNoField = updateBuyerFrame.getJTextField1();
        JTextField nameField = updateBuyerFrame.getJTextField2();
        JTextField contactNoField2 = updateBuyerFrame.getJTextField3();
        JTextField emailField = updateBuyerFrame.getJTextField4();
        JTextField addressField = updateBuyerFrame.getJTextField5();
        JTextField genderField = updateBuyerFrame.getJTextField6();

        contactNoField.setText("1234567890");

     
        searchButton.doClick();
        Thread.sleep(2000); 

       
        nameField.setText("NewName");
        contactNoField2.setText("1234567890");
        emailField.setText("newemail@gmail.com");
        addressField.setText("NewAddress");
        genderField.setText("Female");

       
        updateButton.doClick();
        Thread.sleep(2000); 

       
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM buyer WHERE contactNo = '1234567890'");
        assertTrue(rs.next());
        assertEquals("1234567890", rs.getString("contactNo"));
        assertEquals("NewName", rs.getString("name"));
        assertEquals("0987654321", rs.getString("contactNo"));
        assertEquals("newemail@gmail.com", rs.getString("email"));
        assertEquals("NewAddress", rs.getString("address"));
        assertEquals("Female", rs.getString("gender"));

       
        rs.close();
        st.close();
        con.close();
    }

    @Test
    public void testResetButtonAction() throws Exception {
      
        JButton searchButton = updateBuyerFrame.getJButton1();
        JButton resetButton = updateBuyerFrame.getJButton3();
        JTextField contactNoField = updateBuyerFrame.getJTextField1();
        JTextField nameField = updateBuyerFrame.getJTextField2();
        JTextField contactNoField2 = updateBuyerFrame.getJTextField3();
        JTextField emailField = updateBuyerFrame.getJTextField4();
        JTextField addressField = updateBuyerFrame.getJTextField5();
        JTextField genderField = updateBuyerFrame.getJTextField6();

        contactNoField.setText("1234567890");

        
        searchButton.doClick();
        Thread.sleep(2000); 

        
        nameField.setText("NewName");
        contactNoField2.setText("1234567890");
        emailField.setText("newemail@gmail.com");
        addressField.setText("NewAddress");
        genderField.setText("Female");

     
        resetButton.doClick();
        Thread.sleep(2000); 

        
        assertEquals("", contactNoField.getText());
        assertEquals("", nameField.getText());
        assertEquals("", contactNoField2.getText());
        assertEquals("", emailField.getText());
        assertEquals("", addressField.getText());
        assertEquals("", genderField.getText());
    }

    @Test
    public void testCloseButtonAction() {
        
        JButton closeButton = updateBuyerFrame.getJButton4();

        
        closeButton.doClick();
        
        assertFalse(updateBuyerFrame.isVisible());
    }

    @Test
    public void testSearchButtonInvalidContactNo() throws Exception {
        
        JButton searchButton = updateBuyerFrame.getJButton1();
        JTextField contactNoField = updateBuyerFrame.getJTextField1();
        JTextField nameField = updateBuyerFrame.getJTextField2();
        JTextField contactNoField2 = updateBuyerFrame.getJTextField3();
        JTextField emailField = updateBuyerFrame.getJTextField4();
        JTextField addressField = updateBuyerFrame.getJTextField5();
        JTextField genderField = updateBuyerFrame.getJTextField6();

        contactNoField.setText("InvalidContactNo");

        
        searchButton.doClick();
        Thread.sleep(2000); 

       
        assertEquals("", nameField.getText());
        assertEquals("", contactNoField2.getText());
        assertEquals("", emailField.getText());
        assertEquals("", addressField.getText());
        assertEquals("", genderField.getText());
    }

@After
public void tearDown() throws Exception {
    
    Connection con = connectionProvider.getCon();
    Statement st = con.createStatement();
    st.execute("DROP TABLE IF EXISTS buyer");
    st.close();
    con.close();
}

    
}
