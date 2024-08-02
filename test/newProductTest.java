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
import billing.management.system.newProduct;

/**
 *
 * @author PC
 */
public class newProductTest {
     private newProduct newProductFrame;
    private ConnectionProvider connectionProvider;

    @Before
    public void setUp() throws Exception {
        connectionProvider = ConnectionProvider.getInstance();
        newProductFrame = new newProduct(connectionProvider);

        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS product (" +
                   "pId INT PRIMARY KEY, " +
                   "pName VARCHAR(255), " +
                   "rate VARCHAR(255), " +
                   "description VARCHAR(255), " +
                   "activate VARCHAR(255))");
        st.execute("INSERT INTO product (pId, pName, rate, description, activate) VALUES (10, 'Sample Product', '100.0', 'Sample Description', 'YES')");
        st.close();
        con.close();
    }

    @Test
    public void testSaveButtonAction() {
        newProductFrame.getProductNameField().setText("Test Product");
        newProductFrame.getRateField().setText("200.0");
        newProductFrame.getDescriptionField().setText("Test Description");
        newProductFrame.getActivateComboBox().setSelectedItem("YES");

        JButton saveButton = newProductFrame.getSaveButton();
        saveButton.doClick();

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.getCon();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM product WHERE pName = 'Test Product'");
            assertTrue(rs.next()); 
            assertEquals("200.0", rs.getString("rate"));
            assertEquals("Test Description", rs.getString("description"));
            assertEquals("YES", rs.getString("activate"));
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
        newProductFrame.getProductNameField().setText("Test Product");
        newProductFrame.getRateField().setText("200.0");
        newProductFrame.getDescriptionField().setText("Test Description");
        newProductFrame.getActivateComboBox().setSelectedItem("NO");

        JButton resetButton = newProductFrame.getjButton2();
        resetButton.doClick();

        assertEquals("Enter Product Name", newProductFrame.getProductNameField().getText());
        assertEquals("Enter Rate", newProductFrame.getRateField().getText());
        assertEquals("Enter Description", newProductFrame.getDescriptionField().getText());
        assertEquals("YES", newProductFrame.getActivateComboBox().getSelectedItem());
    }

    @Test
    public void testCloseButtonAction() {
        JButton closeButton = newProductFrame.getjButton3();
        closeButton.doClick();

        assertFalse(newProductFrame.isVisible());
    }

    @After
    public void tearDown() throws Exception {
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        st.execute("DROP TABLE IF EXISTS product");
        st.close();
        con.close();
    }
}
