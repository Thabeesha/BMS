/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.fixture.JComboBoxFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import Project.ConnectionProvider;
import billing.management.system.newProduct;
/**
 *
 * @author PC
 */
public class newProductAT {
    
    private FrameFixture window;
    private newProduct frame;

    @Before
    public void setUp() {
        frame = new newProduct();
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testInitialState() {
        // Check if the product ID label is visible and has the expected text
        window.label("100").requireVisible(); // Adjust as necessary

        // Check initial text of fields
        JTextComponentFixture productNameField = window.textBox("Enter Product Name");
        productNameField.requireText("Enter Product Name");
        
        JTextComponentFixture rateField = window.textBox("Enter Rate");
        rateField.requireText("Enter Rate");
        
        JTextComponentFixture descriptionField = window.textBox("Enter Description");
        descriptionField.requireText("Enter Description");

        // Check default selection in combo box
//        JComboBoxFixture activateComboBox = window.comboBox();
//        activateComboBox.requireItem("YES");
    }

    @Test
    public void testSaveButton() {
        // Fill in the fields
        JTextComponentFixture productNameField = window.textBox("Enter Product Name");
        productNameField.enterText("Test Product");

        JTextComponentFixture rateField = window.textBox("Enter Rate");
        rateField.enterText("10.99");

        JTextComponentFixture descriptionField = window.textBox("Enter Description");
        descriptionField.enterText("Test Description");

        JComboBoxFixture activateComboBox = window.comboBox();
        activateComboBox.selectItem("NO");

        // Click the save button
        JButtonFixture saveButton = window.button("save");
        saveButton.click();

        // Verify the expected outcome
        // This might include checking if the new product was added to the database
        // or checking if a success message was displayed
        // You might need to mock the database or use a test database for this part
    }

    @Test
    public void testResetButton() {
        // Fill in the fields
        JTextComponentFixture productNameField = window.textBox("Enter Product Name");
        productNameField.enterText("Test Product");

        JTextComponentFixture rateField = window.textBox("Enter Rate");
        rateField.enterText("10.99");

        JTextComponentFixture descriptionField = window.textBox("Enter Description");
        descriptionField.enterText("Test Description");

        JComboBoxFixture activateComboBox = window.comboBox();
        activateComboBox.selectItem("NO");

        // Click the reset button
        JButtonFixture resetButton = window.button("Reset");
        resetButton.click();

        // Verify the fields are reset to their default values
        productNameField.requireText("Enter Product Name");
        rateField.requireText("Enter Rate");
        descriptionField.requireText("Enter Description");
//        activateComboBox.requireItem("YES");
    }

    @Test
    public void testCloseButton() {
        // Click the close button
        JButtonFixture closeButton = window.button("Close");
        closeButton.click();

        // Verify that the window is no longer visible
        window.requireNotVisible();
    }
    
}
