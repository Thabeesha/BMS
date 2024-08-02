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
import billing.management.system.buyersDetails;
/**
 *
 * @author PC
 */
public class BuyersDetailsTest {
    
    private buyersDetails buyersDetailsFrame;
    private ConnectionProvider connectionProvider;

    private boolean printInvoked = false;
    private boolean closed = false;

    @Before
    public void setUp() throws Exception {
        buyersDetailsFrame = new buyersDetails();
        connectionProvider = ConnectionProvider.getInstance();

        
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS buyer (" +
                   "name VARCHAR(255), " +
                   "contactNo VARCHAR(255) PRIMARY KEY, " +
                   "email VARCHAR(255), " +
                   "address VARCHAR(255), " +
                   "gender VARCHAR(255))");
        st.execute("INSERT INTO buyer (name, contactNo, email, address, gender) VALUES ('John Doe', '1234567890', 'john@gmail.com', '12 hill street', 'Male')");
        st.close();
        con.close();
    }

    @Test
    public void testTableDataLoading() {
      
        buyersDetailsFrame.triggerFormComponentShown();

        JTable table = buyersDetailsFrame.getJTable1();
        assertEquals("John Doe", table.getValueAt(0, 0));
        assertEquals("1234567890", table.getValueAt(0, 1));
        assertEquals("john@gmail.com", table.getValueAt(0, 2));
        assertEquals("12 hill street", table.getValueAt(0, 3));
        assertEquals("Male", table.getValueAt(0, 4));
    }

    @Test
    public void testPrintButtonAction() {
        
        JTable table = buyersDetailsFrame.getJTable1();
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"John Doe", "1234567890", "john@gmail.com", "12 hill street", "Male"}
            },
            new String [] {
                "Name", "ContactNo", "Email", "Address", "Gender"
            }
        ));

        JButton printButton = buyersDetailsFrame.getJButton1();
        printButton.addActionListener(e -> printInvoked = true);
        printButton.doClick();

        assertTrue(printInvoked);
    }

    @Test
    public void testCloseButtonAction() {
        JButton closeButton = buyersDetailsFrame.getJButton2();
        closeButton.addActionListener(e -> closed = true);
        closeButton.doClick();

        assertTrue(closed);
    }

    @After
    public void tearDown() throws Exception {
        // Clean up the test database
        Connection con = connectionProvider.getCon();
        Statement st = con.createStatement();
        st.execute("DROP TABLE buyer");
        st.close();
        con.close();
    }
}
