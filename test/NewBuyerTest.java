/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import Project.ConnectionProvider;
import billing.management.system.newBuyer;

/**
 *
 * @author PC
 */

public class NewBuyerTest {
    
   private newBuyer newBuyerFrame;
    private ConnectionProvider connectionProvider;

    @Before
    public void setUp() throws Exception {
        connectionProvider = ConnectionProvider.getInstance();
        newBuyerFrame = new newBuyer();

       
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS buyer (" +
                   "name VARCHAR(255), " +
                   "contactNo VARCHAR(255) PRIMARY KEY, " +
                   "email VARCHAR(255), " +
                   "address VARCHAR(255), " +
                   "gender VARCHAR(255))");
        st.execute("INSERT INTO buyer (name, contactNo, email, address, gender) VALUES ('jace mist', '1234567890', 'j@gmail.com', '12, station road', 'Male')");
        st.close();
        con.close();
    }

    @Test
    public void testSaveButtonAction() {
        newBuyerFrame.getTextField1().setText("lia mist");
        newBuyerFrame.getTextField2().setText("0987654321");
        newBuyerFrame.getTextField3().setText("lia@gmail.com");
        newBuyerFrame.getTextField4().setText("4, station road");
        newBuyerFrame.getComboBox1().setSelectedItem("Female");

        JButton saveButton = newBuyerFrame.getButton1();
        saveButton.doClick();

      
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.getCon();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM buyer WHERE contactNo = '0987654321'");
            assertTrue(rs.next()); // The record should be found
            assertEquals("lia mist", rs.getString("name"));
            assertEquals("lia@gmail.com", rs.getString("email"));
            assertEquals("4, station road", rs.getString("address"));
            assertEquals("Female", rs.getString("gender"));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {}
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {}
            }
        }
    }

    @Test
    public void testResetButtonAction() {
        
        newBuyerFrame.getTextField1().setText("lia mist");
        newBuyerFrame.getTextField2().setText("0987654321");
        newBuyerFrame.getTextField3().setText("lia@gmail.com");
        newBuyerFrame.getTextField4().setText("4, station road");
        newBuyerFrame.getComboBox1().setSelectedItem("Female");

        JButton resetButton = newBuyerFrame.getResetButton();
        resetButton.doClick();

        
        assertEquals("Enter Name", newBuyerFrame.getTextField1().getText());
        assertEquals("Enter Contact No", newBuyerFrame.getTextField2().getText());
        assertEquals("Enter Email", newBuyerFrame.getTextField3().getText());
        assertEquals("Enter Address", newBuyerFrame.getTextField4().getText());
        assertEquals("Male", newBuyerFrame.getComboBox1().getSelectedItem());
    }

    @Test
    public void testCloseButtonAction() {
        JButton closeButton = newBuyerFrame.getCloseButton();
        closeButton.doClick();

        
        assertFalse(newBuyerFrame.isVisible());
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
