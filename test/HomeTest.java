/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import javax.swing.JOptionPane;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import billing.management.system.home;
/**
 *
 * @author PC
 */
public class HomeTest {
    
private FrameFixture window;

    @Before
    public void setUp() {
        home frame = new home();
        window = new FrameFixture(frame);
        window.show(); // shows the frame
    }

    @Test
    public void testButton1Click() {
        window.button("jButton1").click();
        window.button("jButton2").requireVisible();
        window.button("jButton3").requireVisible();
        window.button("jButton4").requireVisible();
        window.button("jButton5").requireVisible();
        window.button("jButton6").requireVisible();
        window.button("jButton7").requireVisible();
        window.button("jButton8").requireVisible();
        window.button("jButton9").requireVisible();
        window.button("jButton10").requireVisible();
        window.label("jLabel1").requireVisible();
        window.label("jLabel2").requireVisible();
        window.label("jLabel3").requireVisible();
        window.label("jLabel4").requireVisible();
        window.label("jLabel5").requireVisible();
        window.label("jLabel6").requireVisible();
        window.label("jLabel7").requireVisible();
        window.label("jLabel8").requireVisible();
        window.label("jLabel9").requireVisible();
    }

   @Test
    public void testButton11ClickLogout() {
        window.button("jButton11").click();
        JOptionPaneFixture optionPane = window.optionPane();
        optionPane.requireTitle("Do you really want to logout?");
        optionPane.buttonWithText("Yes").click();
        // Verify home frame is invisible and login frame is visible
    }

    @Test
    public void testButton12ClickCloseApplication() {
        window.button("jButton12").click();
        JOptionPaneFixture optionPane = window.optionPane();
        optionPane.requireTitle("Do you really want to close the application?");
        optionPane.buttonWithText("Yes").click();
        // Verify application closes
    }

    @Test
    public void testButtonClicks() {
        window.button("jButton2").click();
        // Verify newBuyer frame is visible

        window.button("jButton3").click();
        // Verify updateBuyer frame is visible

        window.button("jButton4").click();
        // Verify buyersDetails frame is visible

        window.button("jButton5").click();
        // Verify deleteBuyer frame is visible

        window.button("jButton6").click();
        // Verify newProduct frame is visible

        window.button("jButton7").click();
        // Verify updateProduct frame is visible

        window.button("jButton8").click();
        // Verify productDetails frame is visible

        window.button("jButton9").click();
        // Verify deleteProduct frame is visible

        window.button("jButton10").click();
        // Verify billing frame is visible
    }
}
