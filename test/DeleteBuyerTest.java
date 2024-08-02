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
import java.sql.Statement;
import java.sql.ResultSet;
import Project.ConnectionProvider;
import billing.management.system.deleteBuyer;

/**
 *
 * @author PC
 */
public class DeleteBuyerTest {
    
    private deleteBuyer deleteBuyerFrame;
    private ConnectionProvider connectionProvider;

    @Before
    public void setUp() throws Exception {
        deleteBuyerFrame = new deleteBuyer();
        connectionProvider = ConnectionProvider.getInstance();

       
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS buyer (" +
                   "name VARCHAR(255), " +
                   "contactNo VARCHAR(255) PRIMARY KEY, " +
                   "email VARCHAR(255), " +
                   "address VARCHAR(255), " +
                   "gender VARCHAR(255))");
        st.execute("INSERT INTO buyer (name, contactNo, email, address, gender) VALUES ('John Doe', '1234567890', 'john@gmail.com', '12 hill Street', 'Male')");
        st.close();
        con.close();
    }

    @Test
    public void testSearchButtonAction() {
        JTextField contactNoField = deleteBuyerFrame.getJTextField1();
        contactNoField.setText("1234567890");

        JButton searchButton = deleteBuyerFrame.getJButton1();
        searchButton.doClick();

        assertEquals("John Doe", deleteBuyerFrame.getJTextField2().getText());
        assertEquals("1234567890", deleteBuyerFrame.getJTextField3().getText());
        assertEquals("john@gmail.com", deleteBuyerFrame.getJTextField4().getText());
        assertEquals("12 hill Street", deleteBuyerFrame.getJTextField5().getText());
        assertEquals("Male", deleteBuyerFrame.getJTextField6().getText());
    }

    @Test
    public void testDeleteButtonAction() {
        JTextField contactNoField = deleteBuyerFrame.getJTextField1();
        contactNoField.setText("1234567890");

        JButton deleteButton = deleteBuyerFrame.getJButton2();
        deleteButton.doClick();

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.getCon();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM buyer WHERE contactNo = '1234567890'");
            assertFalse(rs.next()); 
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
        JButton resetButton = deleteBuyerFrame.getJButton3();
        resetButton.doClick();

        assertEquals("", deleteBuyerFrame.getJTextField1().getText());
        assertEquals("", deleteBuyerFrame.getJTextField2().getText());
        assertEquals("", deleteBuyerFrame.getJTextField3().getText());
        assertEquals("", deleteBuyerFrame.getJTextField4().getText());
        assertEquals("", deleteBuyerFrame.getJTextField5().getText());
        assertEquals("", deleteBuyerFrame.getJTextField6().getText());
    }

    @Test
    public void testCloseButtonAction() {
        JButton closeButton = deleteBuyerFrame.getJButton4();
        closeButton.doClick();

        assertFalse(deleteBuyerFrame.isVisible());
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
