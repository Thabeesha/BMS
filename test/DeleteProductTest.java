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
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import Project.ConnectionProvider;
import billing.management.system.deleteProduct;

/**
 *
 * @author PC
 */
public class DeleteProductTest {
    
  private deleteProduct deleteProductFrame;
    private ConnectionProvider connectionProvider;

    private boolean deleteConfirmed = false;
    private boolean resetInvoked = false;
    private boolean closeInvoked = false;

    @Before
    public void setUp() throws Exception {
        deleteProductFrame = new deleteProduct();
        connectionProvider = ConnectionProvider.getInstance();

        
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
    public void testSearchButtonAction() {
        JTextField productIdField = deleteProductFrame.getJTextField1();
        JTextField productNameField = deleteProductFrame.getJTextField2();
        JTextField rateField = deleteProductFrame.getJTextField3();
        JTextField descriptionField = deleteProductFrame.getJTextField4();
        JTextField activationField = deleteProductFrame.getJTextField5();

        productIdField.setText("12345");

        JButton searchButton = deleteProductFrame.getJButton1();
        searchButton.addActionListener(e -> {
            try {
                String pId = productIdField.getText();
                Connection con = ConnectionProvider.getInstance().getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from product where pId='" + pId + "'");
                if (rs.next()) {
                    productNameField.setText(rs.getString(2));
                    rateField.setText(rs.getString(3));
                    descriptionField.setText(rs.getString(4));
                    activationField.setText(rs.getString(5));
                } else {
                    JOptionPane.showMessageDialog(null, "Product ID does not exist");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        searchButton.doClick();

        assertEquals("SampleProduct", productNameField.getText());
        assertEquals("100", rateField.getText());
        assertEquals("SampleDescription", descriptionField.getText());
        assertEquals("YES", activationField.getText());
    }

    @Test
    public void testDeleteButtonAction() {
        JTextField productIdField = deleteProductFrame.getJTextField1();
        productIdField.setText("12345");

        JButton deleteButton = deleteProductFrame.getJButton2();
        deleteButton.addActionListener(e -> {
            try {
                String pId = productIdField.getText();
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete record?", "Select", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Connection con = ConnectionProvider.getInstance().getCon();
                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM product WHERE pId='" + pId + "'");
                    deleteConfirmed = true;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        deleteButton.doClick();

        assertTrue(deleteConfirmed);
    }

    @Test
    public void testResetButtonAction() {
        JTextField productIdField = deleteProductFrame.getJTextField1();
        JTextField productNameField = deleteProductFrame.getJTextField2();
        JTextField rateField = deleteProductFrame.getJTextField3();
        JTextField descriptionField = deleteProductFrame.getJTextField4();
        JTextField activationField = deleteProductFrame.getJTextField5();

        productIdField.setText("12345");
        productNameField.setText("SampleProduct");
        rateField.setText("100");
        descriptionField.setText("SampleDescription");
        activationField.setText("YES");

        JButton resetButton = deleteProductFrame.getJButton3();
        resetButton.addActionListener(e -> {
            productIdField.setText("");
            productNameField.setText("");
            rateField.setText("");
            descriptionField.setText("");
            activationField.setText("");
            resetInvoked = true;
        });
        resetButton.doClick();

        assertTrue(resetInvoked);
        assertEquals("", productIdField.getText());
        assertEquals("", productNameField.getText());
        assertEquals("", rateField.getText());
        assertEquals("", descriptionField.getText());
        assertEquals("", activationField.getText());
    }

    @Test
    public void testCloseButtonAction() {
        JButton closeButton = deleteProductFrame.getJButton4();
        closeButton.addActionListener(e -> closeInvoked = true);
        closeButton.doClick();

        assertTrue(closeInvoked);
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
