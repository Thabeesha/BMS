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
import billing.management.system.updateProduct;
/**
 *
 * @author PC
 */
public class updateProductTest {
    
    private updateProduct updateProductFrame;
    private ConnectionProvider connectionProvider;

    @Before
    public void setUp() throws Exception {
        updateProductFrame = new updateProduct();
        connectionProvider = ConnectionProvider.getInstance();

        // Setting up a test database
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS product (" +
                   "pId VARCHAR(255) PRIMARY KEY, " +
                   "pName VARCHAR(255), " +
                   "rate VARCHAR(255), " +
                   "description VARCHAR(255), " +
                   "activation VARCHAR(255))");
        st.execute("INSERT INTO product (pId, pName, rate, description, activation) VALUES ('12345', 'OldProductName', '100', 'OldDescription', 'YES')");
        st.close();
        con.close();
    }

    @Test
    public void testSearchButtonAction() throws Exception {
        // Arrange
        JButton searchButton = updateProductFrame.getJButton1();
        JTextField productIdField = updateProductFrame.getJTextField1();
        JTextField productNameField = updateProductFrame.getJTextField2();
        JTextField rateField = updateProductFrame.getJTextField3();
        JTextField descriptionField = updateProductFrame.getJTextField4();
        JTextField activationField = updateProductFrame.getJTextField5();

        productIdField.setText("12345");

        
        searchButton.doClick();
        Thread.sleep(2000); 

        // Verify that the fields are populated correctly
        assertEquals("OldProductName", productNameField.getText());
        assertEquals("100", rateField.getText());
        assertEquals("OldDescription", descriptionField.getText());
        assertEquals("YES", activationField.getText());
    }

    @Test
    public void testUpdateButtonAction() throws Exception {
        // Arrange
        JButton searchButton = updateProductFrame.getJButton1();
        JButton updateButton = updateProductFrame.getJButton2();
        JTextField productIdField = updateProductFrame.getJTextField1();
        JTextField productNameField = updateProductFrame.getJTextField2();
        JTextField rateField = updateProductFrame.getJTextField3();
        JTextField descriptionField = updateProductFrame.getJTextField4();
        JTextField activationField = updateProductFrame.getJTextField5();

        productIdField.setText("12345");

       
        searchButton.doClick();
        Thread.sleep(2000); 

        
        productNameField.setText("NewProductName");
        rateField.setText("200");
        descriptionField.setText("NewDescription");
        activationField.setText("NO");

       
        updateButton.doClick();
        Thread.sleep(2000); 

        
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM product WHERE pId = '12345'");
        assertTrue(rs.next());
        assertEquals("12345", rs.getString("pId"));
        assertEquals("NewProductName", rs.getString("pName"));
        assertEquals("200", rs.getString("rate"));
        assertEquals("NewDescription", rs.getString("description"));
        assertEquals("NO", rs.getString("activation"));

       
        rs.close();
        st.close();
        con.close();
    }

    @Test
    public void testResetButtonAction() throws Exception {
        
        JButton searchButton = updateProductFrame.getJButton1();
        JButton resetButton = updateProductFrame.getJButton3();
        JTextField productIdField = updateProductFrame.getJTextField1();
        JTextField productNameField = updateProductFrame.getJTextField2();
        JTextField rateField = updateProductFrame.getJTextField3();
        JTextField descriptionField = updateProductFrame.getJTextField4();
        JTextField activationField = updateProductFrame.getJTextField5();

        productIdField.setText("12345");

       
        searchButton.doClick();
        Thread.sleep(2000);

       
        productNameField.setText("NewProductName");
        rateField.setText("200");
        descriptionField.setText("NewDescription");
        activationField.setText("NO");

       
        resetButton.doClick();
        Thread.sleep(2000);

      
        assertEquals("", productIdField.getText());
        assertEquals("", productNameField.getText());
        assertEquals("", rateField.getText());
        assertEquals("", descriptionField.getText());
        assertEquals("", activationField.getText());
    }

    @Test
    public void testCloseButtonAction() {
       
        JButton closeButton = updateProductFrame.getJButton4();

        closeButton.doClick();
        
        assertFalse(updateProductFrame.isVisible());
    }

    @Test
    public void testSearchButtonInvalidProductId() throws Exception {
       
        JButton searchButton = updateProductFrame.getJButton1();
        JTextField productIdField = updateProductFrame.getJTextField1();
        JTextField productNameField = updateProductFrame.getJTextField2();
        JTextField rateField = updateProductFrame.getJTextField3();
        JTextField descriptionField = updateProductFrame.getJTextField4();
        JTextField activationField = updateProductFrame.getJTextField5();

        productIdField.setText("InvalidID");

        searchButton.doClick();
        Thread.sleep(2000); 

        assertEquals("", productNameField.getText());
        assertEquals("", rateField.getText());
        assertEquals("", descriptionField.getText());
        assertEquals("", activationField.getText());
    }

    @After
    public void tearDown() throws Exception {
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        st.execute("DROP TABLE product");
        st.close();
        con.close();
    }
    
}
