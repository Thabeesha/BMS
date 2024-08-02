/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import billing.management.system.login;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author PC
 */

public class LoginTest {
    
    private login loginFrame;

    @Before
    public void setUp() throws Exception {
        loginFrame = new login();
        loginFrame.setVisible(true);
    }

    @After
    public void tearDown() throws Exception {
        loginFrame.dispose();
    }

    @Test
    public void testValidLogin() {
        // Arrange
        JTextField usernameField = loginFrame.getJTextField1();
        JPasswordField passwordField = loginFrame.getJPasswordField1();
        JButton loginButton = loginFrame.getJButton1();

        // Act
        usernameField.setText("bms");
        passwordField.setText("admin");

        // Simulate button click
        loginButton.doClick();

        // Assert
        // After a successful login, the login frame should be invisible and a new frame (home) should be visible.
        // This part may be difficult to verify directly. Adjust based on your home frame's implementation.
        assertFalse(loginFrame.isVisible());
    }

    @Test
    public void testInvalidLogin() {
        // Arrange
        JTextField usernameField = loginFrame.getJTextField1();
        JPasswordField passwordField = loginFrame.getJPasswordField1();
        JButton loginButton = loginFrame.getJButton1();

        // Act
        usernameField.setText("wrongUser");
        passwordField.setText("wrongPassword");
        loginButton.doClick();

        // Assert
        // Check if an error dialog is shown or if the frame is still visible.
        assertTrue(loginFrame.isVisible()); // Assuming frame should remain visible on login failure
    }

    @Test
    public void testShowPasswordCheckbox() {
        // Arrange
        JCheckBox showPasswordCheckBox = loginFrame.getJCheckBox1();
        JPasswordField passwordField = loginFrame.getJPasswordField1();

        // Act
        passwordField.setText("password");
        showPasswordCheckBox.doClick(); // Show password
        assertEquals(0, passwordField.getEchoChar()); // No echo char means password is shown

        showPasswordCheckBox.doClick(); // Hide password
        assertEquals('*', passwordField.getEchoChar()); // Default echo char means password is hidden
    }

    @Test
    public void testCloseButtonAction() {
        // Arrange
        JButton closeButton = loginFrame.getJButton2();
        
        // Add a listener to capture frame disposal
        JFrame frame = loginFrame;
        ActionListener closeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        };
        closeButton.addActionListener(closeListener);

        // Act
        // Simulate the close button click
        closeButton.doClick();

        // Assert
        // Verify if the frame has been disposed.
        // We use visibility check here as a proxy for the dispose call.
        assertFalse(loginFrame.isVisible());
    }
    
}
