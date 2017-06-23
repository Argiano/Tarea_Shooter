/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_shooter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author e_ver
 */
public class Tarea_ShooterTest {
    
    public Tarea_ShooterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createFirstFrame method, of class Tarea_Shooter.
     */
    @Test
    public void testCreateFirstFrame() {
        System.out.println("createFirstFrame");
        Tarea_Shooter instance = new Tarea_Shooter();
        instance.createFirstFrame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFrame method, of class Tarea_Shooter.
     */
    @Test
    public void testCreateFrame() {
        System.out.println("createFrame");
        int width = 0;
        int height = 0;
        String frameText = "";
        Tarea_Shooter instance = new Tarea_Shooter();
        instance.createFrame(width, height, frameText);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endFrame method, of class Tarea_Shooter.
     */
    @Test
    public void testEndFrame() {
        System.out.println("endFrame");
        Tarea_Shooter instance = new Tarea_Shooter();
        instance.endFrame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Tarea_Shooter.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Tarea_Shooter.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEnemies method, of class Tarea_Shooter.
     */
    @Test
    public void testCreateEnemies() {
        System.out.println("createEnemies");
        int enemyNumber = 0;
        Tarea_Shooter instance = new Tarea_Shooter();
        instance.createEnemies(enemyNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
