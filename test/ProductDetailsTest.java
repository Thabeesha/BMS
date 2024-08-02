/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JButton;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.Statement;
import Project.ConnectionProvider;
import billing.management.system.productDetails;
/**
 *
 * @author PC
 */
public class ProductDetailsTest {
    
    private productDetails productDetailsFrame;
    private ConnectionProvider connectionProvider;

    private boolean printInvoked = false;
    private boolean closed = false;

    @Before
    public void setUp() throws Exception {
        productDetailsFrame = new productDetails();
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
        st.execute("INSERT INTO product (pId, pName, rate, description, activation) VALUES ('12345', 'SampleProduct', '100', 'SampleDescription', 'YES')");
        st.close();
        con.close();
    }

    @Test
    public void testTableDataLoading() {
        
        productDetailsFrame.triggerFormComponentShown();

        JTable table = productDetailsFrame.getJTable1();
        assertEquals("SampleProduct", table.getValueAt(0, 1));
        assertEquals("12345", table.getValueAt(0, 0));
        assertEquals("100", table.getValueAt(0, 2));
        assertEquals("SampleDescription", table.getValueAt(0, 3));
        assertEquals("YES", table.getValueAt(0, 4));
    }

    @Test
    public void testPrintButtonAction() {
        JTable table = productDetailsFrame.getJTable1();
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"12345", "SampleProduct", "100", "SampleDescription", "YES"}
            },
            new String [] {
                "pId", "pName", "rate", "description", "activation"
            }
        ));

        JButton printButton = productDetailsFrame.getJButton1();
        printButton.addActionListener(e -> printInvoked = true);
        printButton.doClick();

        assertTrue(printInvoked);
    }

    @Test
    public void testCloseButtonAction() {
        JButton closeButton = productDetailsFrame.getJButton2();
        closeButton.addActionListener(e -> closed = true);
        closeButton.doClick();

        assertTrue(closed);
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
